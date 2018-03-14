package com.zennex.trl3lg.domain.usecases.auth;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.data.rest.request.GetSitesRequest;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class GetSiteAndModuleIdByPosition extends UseCase<GetSiteAndModuleIdByPosition.Result, GetSiteAndModuleIdByPosition.Params> {

    @Inject
    public GetSiteAndModuleIdByPosition(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<Result> buildObservable(Params params) {
        return Observable.fromCallable(() -> {
            Result result = new Result();
            result.mSite = params.mSites.get(params.mPos);
            result.mModuleId = Stream.of(result.mSite.getModules())
                    .filter(module -> module.getType().equals(GetSitesRequest.Data.TYPE_MEMBERSHIP))
                    .reduce((m1, m2) -> m1)
                    .get()
                    .getId();
            return result;
        });
    }

    public static class Params {

        public final List<Site> mSites;
        public final int mPos;

        public Params(List<Site> sites, int pos) {
            mSites = sites;
            mPos = pos;
        }
    }


    public static class Result {

        private Site mSite;
        private String mModuleId;

        public Result() {
        }

        public Site getSite() {
            return mSite;
        }

        public void setSite(Site site) {
            mSite = site;
        }

        public String getModuleId() {
            return mModuleId;
        }

        public void setModuleId(String moduleId) {
            mModuleId = moduleId;
        }
    }

}
