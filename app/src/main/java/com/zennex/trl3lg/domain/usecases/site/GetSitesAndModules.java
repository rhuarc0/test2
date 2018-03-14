package com.zennex.trl3lg.domain.usecases.site;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.Site;
import com.zennex.trl3lg.data.rest.request.auth.GetSitesRequest;
import com.zennex.trl3lg.data.rest.response.auth.GetSitesResponse;
import com.zennex.trl3lg.data.mapper.dtomapper.SiteDtoMapper;
import com.zennex.trl3lg.data.repository.connection.site.ISiteRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class GetSitesAndModules extends UseCase<List<Site>, Void> {

    @Inject
    protected ISiteRepository mSiteRepository;

    @Inject
    protected SiteDtoMapper mGetSitesResponseMapper;

    @Inject
    public GetSitesAndModules(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<Site>> buildObservable(Void aVoid) {
        return mSiteRepository.getSites(createGetSitesRequest())
                .doOnNext(checkResponse())
                .map(transform());
    }

    private GetSitesRequest createGetSitesRequest() {
        GetSitesRequest.Data data = new GetSitesRequest.Data();
        data.addType(GetSitesRequest.Data.TYPE_RENTAL);
        data.addType(GetSitesRequest.Data.TYPE_MEMBERSHIP);
        return GetSitesRequest.newInstance(data);
    }

    private Consumer<GetSitesResponse> checkResponse() {
        return getSitesResponse -> {

            if (Integer.parseInt(getSitesResponse.getErrorCode()) != 0) {
                throw new RuntimeException(getSitesResponse.getErrorText());
            }
        };
    }

    private Function<GetSitesResponse, List<Site>> transform() {
        return getSitesResponse -> {
            if (getSitesResponse.getData() == null) return new ArrayList<>();
            return mGetSitesResponseMapper.execute(getSitesResponse.getData().getSiteDtos());
        };
    }


}
