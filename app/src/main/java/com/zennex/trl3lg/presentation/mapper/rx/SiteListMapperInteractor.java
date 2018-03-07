package com.zennex.trl3lg.presentation.mapper.rx;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.mapper.SiteMapper;
import com.zennex.trl3lg.presentation.model.SiteModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by nikita on 03.06.17.
 */

public class SiteListMapperInteractor extends UseCase<List<SiteModel>, List<Site>> {


    @Inject
    protected SiteMapper mSiteMapper;

    @Inject
    public SiteListMapperInteractor(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<SiteModel>> buildObservable(List<Site> sites) {
        return Observable.fromCallable(() -> mSiteMapper.execute(sites));
    }
}
