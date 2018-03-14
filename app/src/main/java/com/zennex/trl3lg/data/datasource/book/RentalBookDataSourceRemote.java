package com.zennex.trl3lg.data.datasource.book;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.data.entity.dto.AudioBookDto;
import com.zennex.trl3lg.data.rest.request.book.FetchBookListRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchQueueRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.rest.response.book.FetchBookListResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchAudioBooksQueueResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchRentalGroupsResponse;
import com.zennex.trl3lg.data.exception.WebApiException;
import com.zennex.trl3lg.data.mapper.dtomapper.AudioBookDtoMapper;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.rest.IRentalBookWebService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RentalBookDataSourceRemote implements BookDataSourceRemote {

    private static String NO_ERROR_CODE = "0";
    private static String ERROR_TEXT_BOOKS_NOT_FOUND = "";

    @Inject
    IRentalBookWebService mRentalBookWebService;

    @Inject
    IAuthRepository mAuthRepository;

    @Inject
    AudioBookDtoMapper mAudioBookDtoMapper;

    @Inject
    public RentalBookDataSourceRemote() {
    }

    @Override
    public Observable<List<FetchRentalGroupsResponse>> getGroups(List<FetchRentalGroupsRequest> request) {
        return mRentalBookWebService.fetchRentalGroups(request);
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

    private Consumer<List<FetchAudioBooksQueueResponse>> checkResponse() throws WebApiException {
        return fetchQueueBooksResponses -> {
            WebApiException exception = null;
            for (FetchAudioBooksQueueResponse response : fetchQueueBooksResponses) {
                if (!response.getErrorCode().equals(NO_ERROR_CODE)
                        && !response.getErrorText().equals(ERROR_TEXT_BOOKS_NOT_FOUND)) {
                    exception = new WebApiException(response.getErrorText(), response.getErrorCode());
                }
            }
            if (exception != null) throw exception;
        };
    }

    private Function<List<FetchAudioBooksQueueResponse>, List<List<AudioBookDto>>> getDataAudioBooks() {
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
