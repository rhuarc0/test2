package com.zennex.trl3lg.data.datasource.book;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.entity.dto.BookDto;
import com.zennex.trl3lg.data.entity.dto.HistoryDto;
import com.zennex.trl3lg.data.rest.request.book.FetchHistoryRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchBooksQueueResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchHistoryResponse;
import com.zennex.trl3lg.data.util.repository.WebRepositoryUtils;
import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.data.entity.dto.AudioBookDto;
import com.zennex.trl3lg.data.rest.request.book.FetchBookListRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchQueueRequest;
import com.zennex.trl3lg.data.rest.request.book.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.rest.response.book.FetchBookListResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchRentalGroupsResponse;
import com.zennex.trl3lg.data.mapper.dtomapper.AudioBookDtoMapper;
import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.data.rest.IRentalBookWebService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
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
                .map(transformAudioBookDtos())
                .map(filterList())
                .map(sortList());
    }

    @Override
    public Observable<List<String>> fetchQueueIds() {
        return Observable.fromCallable(this::createRequestsForFetchMyAudioBooks)
                .flatMap(mRentalBookWebService::fetchQueue)
                .doOnNext(WebRepositoryUtils::checkResponse)
                .map(getQueueBookIds());
    }

    private List<FetchQueueRequest> createRequestsForFetchMyAudioBooks() {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(rentalModuleId -> FetchQueueRequest.newInstance(rentalModuleId,
                        mAuthRepository.getSessionToken().blockingSingle()))
                .toList();
    }

    private Function<List<FetchBooksQueueResponse>, List<AudioBookDto>> getDataAudioBooks() {
        return responses -> Stream.of(responses)
                .map(response -> response.getData().getAudioBookDtoList())
                .flatMap(Stream::of)
                .toList();
    }

    private Function<List<AudioBookDto>, List<AudioBook>> transformAudioBookDtos() {
        return audioBookDtos -> mAudioBookDtoMapper.execute(audioBookDtos);
    }

    private Function<List<AudioBook>, List<AudioBook>> filterList() {
        return list -> Stream.of(list)
                .filter(value -> value.getRentalEnd() == null || value.getDaysLeft() > 0)
                .toList();
    }

    private Function<List<AudioBook>, List<AudioBook>> sortList() {
        return list -> Stream.of(list)
                .sorted((lhs, rhs) -> {
                    if (lhs.getBillDate() != null || rhs.getBillDate() != null) {
                        if (lhs.getBillDate() != null && rhs.getBillDate() == null) {
                            return -1;
                        } else if (lhs.getBillDate() == null && rhs.getBillDate() != null) {
                            return 1;
                        } else
                            return 0;
                    }
                    return Integer.parseInt(lhs.getListOrder()) - Integer.parseInt(rhs.getListOrder());
                })
                .toList();
    }


    private Function<List<FetchBooksQueueResponse>, List<String>> getQueueBookIds() {
        return responses -> Stream.of(responses)
                .map(response -> {
                    int capacity = response.getData().getAudioBookDtoList().size() + response.getData().getCdBookDtoList().size();
                    List<String> result = new ArrayList<>(capacity);
                    result.addAll(Stream.of(response.getData().getAudioBookDtoList()).map(BookDto::getId).toList());
                    result.addAll(Stream.of(response.getData().getCdBookDtoList()).map(BookDto::getId).toList());
                    return result;
                })
                .flatMap(Stream::of)
                .toList();
    }

    @Override
    public Observable<List<String>> fetchHistory() {
        return Observable.fromCallable(this::createRequestForHistory)
                .flatMap(mRentalBookWebService::fetchHistory)
                .doOnNext(WebRepositoryUtils::checkResponse)
                .map(getHistoryBookIds());
    }

    private List<FetchHistoryRequest> createRequestForHistory() {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(rentalModuleId -> FetchHistoryRequest.newInstance(rentalModuleId,
                        mAuthRepository.getSessionToken().blockingSingle()))
                .toList();
    }

    private Function<FetchHistoryResponse, List<String>> getHistoryBookIds() {
        return responses -> Stream.of(responses)
                .map(BaseResponse::getData)
                .flatMap(Stream::of)
                .map(HistoryDto::getItemId)
                .toList();
    }

}
