package com.zennex.trl3lg.data.repository.connection.site.web;


import com.zennex.trl3lg.data.entity.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.entity.rest.response.GetSitesResponse;

import io.reactivex.Observable;

public interface ISiteWebRepository {

    Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest);

}
