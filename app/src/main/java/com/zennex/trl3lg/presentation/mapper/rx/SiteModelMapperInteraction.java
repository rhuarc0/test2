package com.zennex.trl3lg.presentation.mapper.rx;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.mapper.SiteModelMapper;
import com.zennex.trl3lg.presentation.model.SiteModel;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by nikita on 05.06.17.
 */

public class SiteModelMapperInteraction extends UseCase<Site, SiteModel> {

    @Inject
    protected SiteModelMapper mSiteModelMapper;

    @Inject
    public SiteModelMapperInteraction(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<Site> buildObservable(SiteModel siteModel) {
        return Observable.fromCallable(() -> mSiteModelMapper.execute(siteModel));
    }

}
