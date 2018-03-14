package com.zennex.trl3lg.data.repository.connection.site.web;


import com.zennex.trl3lg.data.rest.request.auth.GetSitesRequest;
import com.zennex.trl3lg.data.rest.response.auth.GetSitesResponse;

import io.reactivex.Observable;

public interface ISiteWebRepository {

    Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest);

}
