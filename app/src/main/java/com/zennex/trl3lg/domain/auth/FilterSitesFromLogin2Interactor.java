package com.zennex.trl3lg.domain.auth;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.entity.Site;
import com.zennex.trl3lg.data.entity.rest.request.GetSitesRequest;
import com.zennex.trl3lg.domain.common.BaseInteractor;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by nikita on 03.06.17.
 */

public class FilterSitesFromLogin2Interactor extends BaseInteractor<List<Site>, FilterSitesFromLogin2Interactor.Params> {

    @Inject
    public FilterSitesFromLogin2Interactor(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<Site>> buildObservable(Params params) {
        return Observable.fromCallable(() -> Stream.of(params.getModuleIds())
                .map(membershipId -> Stream.of(params.mSites)
                        .filter(site -> Stream.of(site.getModules())
                                .map(module -> module.getType().equals(GetSitesRequest.Data.TYPE_MEMBERSHIP) &&
                                        module.getId().equals(membershipId))
                                .reduce((r, i) -> r || i).get())
                        .single())
                .toList());
    }




    public static class Params {

        private List<Site> mSites;
        private List<String> mModuleIds;


        public Params(List<Site> sites, List<String> moduleIds) {
            mSites = sites;
            mModuleIds = moduleIds;
        }

        public List<Site> getSites() {
            return mSites;
        }

        public void setSites(List<Site> sites) {
            mSites = sites;
        }

        public List<String> getModuleIds() {
            return mModuleIds;
        }

        public void setModuleIds(List<String> moduleIds) {
            mModuleIds = moduleIds;
        }
    }

}
