package com.zennex.trl3lg.data.repository.connection.site.web;


import com.zennex.trl3lg.data.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.rest.response.GetSitesResponse;

import io.reactivex.Observable;

public interface ISiteWebRepository {

    Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest);

}
