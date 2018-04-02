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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

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
                .map(transformAudioBookDtos())
                .map(filterList())
                .map(sortList());
    }

    private List<FetchQueueRequest> createRequestsForFetchMyAudioBooks() {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(rentalModuleId -> FetchQueueRequest.newInstance(rentalModuleId,
                        mAuthRepository.getSessionToken().blockingSingle()))
                .toList();
    }

    private Function<List<FetchAudioBooksQueueResponse>, List<AudioBookDto>> getDataAudioBooks() {
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
                .filter(value -> value.getRentalEnd() == null || getDaysLeft(value) > 0)
                .toList();
    }

    private static final String FORMAT_DATE = "yyyy-MM-dd HH:mm:s";
    private static final String TIME_ZONE = "America/New_York"; // EDT - Серверное время
    private static final String FINISH_RENT_BOOK_TIME = " 23:59:59"; // Конец дня, завершение аренды книги

    private int getDaysLeft(AudioBook audioBook) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE, Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));

        if (audioBook.getRentalEnd() != null) {

            Date today = new Date();
            int days = 0;
            long difference;
            try {
                Date date = format.parse(audioBook.getRentalEnd() + FINISH_RENT_BOOK_TIME);
                difference = date.getTime() - today.getTime();
                days = (int) (difference / (1000 * 60 * 60 * 24));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return days + 1;
        }
        return 0;
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

}
