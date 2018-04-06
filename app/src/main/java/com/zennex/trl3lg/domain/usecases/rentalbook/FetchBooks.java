package com.zennex.trl3lg.domain.usecases.rentalbook;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.domain.entities.TypeBooks;
import com.zennex.trl3lg.domain.repository.IBookRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class FetchBooks extends UseCase<List<Book>, FetchBooks.Params> {

    @Inject
    IBookRepository bookRepository;

    @Inject
    public FetchBooks(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<Book>> buildObservable(Params params) {
        return bookRepository.fetchBooks(params.getKeyword(),
                params.getType(),
                params.getQuantityBooksRequested(),
                params.getStartingLoadPosition(),
                params.getRentalGroupId());
    }

    public static class Params {

        @Nullable
        private final String mKeyword;

        private final TypeBooks mType;
        private final short mQuantityBooksRequested;
        private final int mStartingLoadPosition;
        private final String mRentalGroupId;

        public Params(@Nullable String keyword,
                      TypeBooks type,
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
            return mType.getValue();
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
}
