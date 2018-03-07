package com.zennex.trl3lg.data.repository.connection.rental.book.web;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.data.entity.dto.AudioBookDto;
import com.zennex.trl3lg.data.entity.rest.request.FetchBookListRequest;
import com.zennex.trl3lg.data.entity.rest.request.FetchQueueRequest;
import com.zennex.trl3lg.data.entity.rest.request.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.entity.rest.response.FetchBookListResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchQueueBooksResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchRentalGroupsResponse;
import com.zennex.trl3lg.data.exception.WebApiException;
import com.zennex.trl3lg.data.mapper.AudioBookDtoMapper;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.repository.connection.rental.book.web.service.IRentalBookWebService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by zennex on 27.07.17.
 */

public class RentalBookWebRepository implements IRentalBookWebRepository {

    private static String NO_ERROR_CODE = "0";
    private static String ERROR_TEXT_BOOKS_NOT_FOUND = "";

    @Inject
    IRentalBookWebService mRentalBookWebService;

    @Inject
    IAuthRepository mAuthRepository;

    @Inject
    AudioBookDtoMapper mAudioBookDtoMapper;

    @Inject
    public RentalBookWebRepository() {
    }

    @Override
    public Observable<List<FetchRentalGroupsResponse>> getGroups(List<FetchRentalGroupsRequest> request) {
        return mRentalBookWebService.getGroups(request);
    }

    @Override
    public Observable<List<FetchBookListResponse>> fetchBooks(List<FetchBookListRequest> requests) {
        return mRentalBookWebService.fetchBooks(requests);
    }

    @Override
    public Observable<List<AudioBook>> fetchMyAudioBooks() {
        return Observable.fromCallable(this::createRequestsForFetchMyAudioBooks)
                .flatMap(mRentalBookWebService::fetchQueue)
                .doOnNext(checkResponse())
                .map(getDataAudioBooks())
                .map(combineAudioBookDtos())
                .map(transformAudioBookDtos());
    }

    private List<FetchQueueRequest> createRequestsForFetchMyAudioBooks() {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(renatalModuleid -> FetchQueueRequest.newInstance(renatalModuleid,
                        new FetchQueueRequest.Data(mAuthRepository.getSessionToken().blockingSingle())))
                .toList();
    }

    private Consumer<List<FetchQueueBooksResponse>> checkResponse() throws WebApiException {
        return fetchQueueBooksResponses -> {
            WebApiException exception = null;
            for (FetchQueueBooksResponse response : fetchQueueBooksResponses) {
                if (!response.getErrorCode().equals(NO_ERROR_CODE)
                        && !response.getErrorText().equals(ERROR_TEXT_BOOKS_NOT_FOUND)) {
                    exception = new WebApiException(response.getErrorText(), response.getErrorCode());
                }
            }
            if (exception != null) throw exception;
        };
    }

    private Function<List<FetchQueueBooksResponse>, List<List<AudioBookDto>>> getDataAudioBooks() {
        return responses -> Stream.of(responses)
                .map(response -> response.getData().getAudioBookDtoList())
                .toList();
    }

    private Function<List<List<AudioBookDto>>, List<AudioBookDto>> combineAudioBookDtos() {
        return lists -> Stream.of(lists)
                .flatMap(Stream::of)
                .toList();
    }

    private Function<List<AudioBookDto>, List<AudioBook>> transformAudioBookDtos() {
        return audioBookDtos -> mAudioBookDtoMapper.execute(audioBookDtos);
    }

}
