package com.zennex.trl3lg.data.repository.connection.site;

import com.zennex.trl3lg.data.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.rest.response.GetSitesResponse;
import com.zennex.trl3lg.data.repository.connection.site.local.ISiteLocalRepository;
import com.zennex.trl3lg.data.repository.connection.site.web.ISiteWebRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 03.06.17.
 */

public class SiteRepository implements ISiteRepository {

    @Inject
    protected ISiteWebRepository mSiteWebRepository;

    @Inject
    protected ISiteLocalRepository mSiteLocalRepository;

    @Inject
    public SiteRepository() {
    }

    @Override
    public Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest) {
        return mSiteWebRepository.getSites(getSitesRequest);
    }

    @Override
    public Observable<Boolean> saveSiteId(String siteId) {
        return mSiteLocalRepository.saveSiteId(siteId);
    }

    @Override
    public Observable<String> getSiteId() {
        return mSiteLocalRepository.getSiteId();
    }

}
