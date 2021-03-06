package com.zennex.trl3lg.domain.usecases.site;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.domain.repository.ISiteRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetSitesAndModules extends UseCase<List<Site>, Void> {

    @Inject
    protected ISiteRepository siteRepository;

    @Inject
    public GetSitesAndModules(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<Site>> buildObservable(Void aVoid) {
        return siteRepository.getSites();
    }


}
