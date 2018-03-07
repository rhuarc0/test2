package com.zennex.trl3lg.domain.usecases.rentalbook;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.data.entity.dto.BookDto;
import com.zennex.trl3lg.data.entity.rest.request.FetchBookListRequest;
import com.zennex.trl3lg.data.entity.rest.response.BaseResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchBookListResponse;
import com.zennex.trl3lg.data.mapper.BookDtoMapperWrapper;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.repository.connection.rental.book.IRentalBookRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;

public class FetchBooks extends UseCase<List<Book>, FetchBooks.Params> {

    @Inject
    IRentalBookRepository mRentalBookRepository;

    @Inject
    IAuthRepository mAuthRepository;

    @Inject
    BookDtoMapperWrapper mBookDtoMapper;

    private FetchBookListRequest.KeywordSearch mKeywordSearch;

    @Inject
    public FetchBooks(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<Book>> buildObservable(Params params) {
        return Observable.fromCallable(() -> createRequests(params))
                .flatMap(o -> mRentalBookRepository.fetchBooks(o))
                .map(getData())
                .map(transform());
    }

    public static class Params {

        public static final String TYPE_ALL = "11";
        public static final String TYPE_RENTAL = "11";
        public static final String TYPE_ON_DEMAND = "11";

        @Nullable
        private final String mKeyword;
        @Nullable
        private final String mType;
        private final short mQuantityBooksRequested;
        private final int mStartingLoadPosition;
        private final String mRentalGroupId;

        public Params(@Nullable String keyword,
                      @Nullable String type,
                      short quantityBooksRequested,
                      int startingLoadPosition,
                      String rentalGroupId) {
            mKeyword = keyword;
            mType = type;
            mQuantityBooksRequested = quantityBooksRequested;
            mStartingLoadPosition = startingLoadPosition;
            mRentalGroupId = rentalGroupId;
        }

        @Nullable
        public String getKeyword() {
            return mKeyword;
        }

        @Nullable
        public String getType() {
            return mType;
        }

        public short getQuantityBooksRequested() {
            return mQuantityBooksRequested;
        }

        public int getStartingLoadPosition() {
            return mStartingLoadPosition;
        }

        public String getRentalGroupId() {
            return mRentalGroupId;
        }
    }

    private List<FetchBookListRequest> createRequests(Params params) {
        return Stream.of(mAuthRepository.getRentalModuleIds().blockingSingle())
                .map(moduleId -> {

                    List<FetchBookListRequest.BookFilter> filters = new ArrayList<>();
                    filters.add(new FetchBookListRequest.BookFilter(FetchBookListRequest.BookFilter.FIELD_ACTIVE,
                            Stream.of("1").toList()));

                    if (!TextUtils.isEmpty(params.getRentalGroupId()) && !params.getRentalGroupId().equals("-1")) {
                        filters.add(new FetchBookListRequest.BookFilter(FetchBookListRequest.BookFilter.FIELD_GROUP_ID,
                                Stream.of(params.getRentalGroupId()).toList()));
                    }

                    FetchBookListRequest.Data data = new FetchBookListRequest.Data();
                    data.setFilters(filters);
                    data.setCountBooks(Short.toString(params.mQuantityBooksRequested));
                    data.setStartPosition(Integer.toString(params.mStartingLoadPosition));


                    if (mKeywordSearch == null) {
                        mKeywordSearch = new FetchBookListRequest.KeywordSearch();
                        mKeywordSearch = new FetchBookListRequest.KeywordSearch();
                        mKeywordSearch.setType(FetchBookListRequest.KeywordSearch.TYPE_AND);
                        mKeywordSearch.setFields("code,string0,text4,text5");
                    }

                    mKeywordSearch.setKeyword(params.getKeyword());


                    data.setKeywordSearch(mKeywordSearch);
                    return FetchBookListRequest.newInstance(moduleId, data);
                })
                .toList();
    }

    public String getKeywordSearch() {
        return mKeywordSearch != null ?
                mKeywordSearch.getKeyword() != null ? mKeywordSearch.getKeyword() : "" :
                "";
    }

    private Function<List<FetchBookListResponse>, List<List<BookDto>>> getData() {
        return responses -> Stream.of(responses)
                .map(BaseResponse::getData)
                .toList();
    }

    @SuppressWarnings("unchecked")
    private Function<List<List<BookDto>>, List<Book>> transform() {
        return bookDtos -> Stream.of(bookDtos)
                .map(bookDto -> mBookDtoMapper.execute(bookDto))
                .flatMap(Stream::of)
                .toList();
    }

}
