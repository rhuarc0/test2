package com.zennex.trl3lg.data.datasource.book;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.rest.response.book.FetchAudioBooksQueueResponse;
import com.zennex.trl3lg.data.util.repository.WebRepositoryUtils;
import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.data.entity.dto.AudioBookDto;
import com.zennex.trl3lg.data.rest.request.book.FetchBookListRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchQueueRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.rest.response.book.FetchBookListResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchRentalGroupsResponse;
import com.zennex.trl3lg.data.exception.WebApiException;
import com.zennex.trl3lg.data.mapper.dtomapper.AudioBookDtoMapper;
import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.data.rest.IRentalBookWebService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class RentalBookDataSourceRemote implements BookDataSourceRemote {


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
                .doOnNext(WebRepositoryUtils::checkResponse)
                .map(getDataAudioBooks())
                .map(combineAudioBookDtos())
                .map(transformAudioBookDtos());
    }

    private List<FetchQueueRequest> createRequestsForFetchMyAudioBooks() {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(rentalModuleId -> FetchQueueRequest.newInstance(rentalModuleId,
                        mAuthRepository.getSessionToken().blockingSingle()))
                .toList();
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
