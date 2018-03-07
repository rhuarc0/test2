package com.zennex.trl3lg.data.repository.connection.site.web;

import com.zennex.trl3lg.data.entity.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.entity.rest.response.GetSitesResponse;
import com.zennex.trl3lg.data.repository.connection.site.service.ISiteWebService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 03.06.17.
 */

public class SiteWebRepository implements ISiteWebRepository {

    @Inject
    protected ISiteWebService mSiteService;

    @Inject
    public SiteWebRepository() {
    }

    @Override
    public Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest) {
        return mSiteService.getSites(getSitesRequest);
    }
}
