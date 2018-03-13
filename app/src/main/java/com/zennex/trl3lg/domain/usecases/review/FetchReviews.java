package com.zennex.trl3lg.domain.usecases.review;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.data.repository.connection.review.IReviewRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class FetchReviews extends UseCase<List<Review>, FetchReviews.Params> {

    @Inject
    IReviewRepository mReviewRepository;

    @Inject
    public FetchReviews(@Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
                        @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<Review>> buildObservable(Params params) {
        return mReviewRepository.fetchReviews(params.bookId,
                params.mStartingLoadPosition,
                params.mReviewCount)
                .delay(5, TimeUnit.SECONDS);
    }

    public static class Params {
        private final int mReviewCount;
        private final int mStartingLoadPosition;
        @NonNull
        private final String bookId;

        public Params(int reviewCount, int startingLoadPosition, @NonNull String bookId) {
            mReviewCount = reviewCount;
            mStartingLoadPosition = startingLoadPosition;
            this.bookId = bookId;
        }
    }


}
