package com.zennex.trl3lg.domain.usecases.site;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.repository.connection.site.ISiteRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetSiteId extends UseCase<String, Void> {

    @Inject
    protected ISiteRepository mSiteRepository;


    @Inject
    public GetSiteId(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<String> buildObservable(Void aVoid) {
        return mSiteRepository.getSiteId();
    }
}
