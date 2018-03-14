package com.zennex.trl3lg.data.repository.connection.site;

import com.zennex.trl3lg.data.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.rest.response.GetSitesResponse;

import io.reactivex.Observable;

/**
 * Created by nikita on 03.06.17.
 */

public interface ISiteRepository {

    Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest);

    Observable<Boolean> saveSiteId(String siteId);

    Observable<String> getSiteId();

}
