package com.zennex.trl3lg.data.datasource.site;

import com.zennex.trl3lg.data.entity.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.entity.rest.response.GetSitesResponse;
import com.zennex.trl3lg.data.rest.ISiteWebService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 03.06.17.
 */

public class SiteDataSourceRemote implements ISiteDataSourceRemote {

    @Inject
    protected ISiteWebService mSiteService;

    @Inject
    public SiteDataSourceRemote() {
    }

    @Override
    public Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest) {
        return mSiteService.getSites(getSitesRequest);
    }
}
