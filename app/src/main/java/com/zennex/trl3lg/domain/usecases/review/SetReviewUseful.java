package com.zennex.trl3lg.domain.usecases.review;

import android.support.annotation.NonNull;
import android.util.Pair;

import com.zennex.trl3lg.domain.repository.IReviewRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class SetReviewUseful extends UseCase<Pair<String, Boolean>, SetReviewUseful.Params> {

    @Inject
    IReviewRepository reviewRepository;

    @Inject
    public SetReviewUseful(@Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
                           @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }


    @Override
    protected Observable<Pair<String, Boolean>> buildObservable(Params params) {
        return reviewRepository.setReviewUseful(params.getReviewId(), params.isUseful());
    }

    public static class Params {
        private final String reviewId;
        private final boolean isUseful;

        public Params(String reviewId, boolean isUseful) {
            this.reviewId = reviewId;
            this.isUseful = isUseful;
        }

        public String getReviewId() {
            return reviewId;
        }

        public boolean isUseful() {
            return isUseful;
        }
    }

}
