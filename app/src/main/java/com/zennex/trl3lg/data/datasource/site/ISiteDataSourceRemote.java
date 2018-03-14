package com.zennex.trl3lg.data.datasource.site;


import com.zennex.trl3lg.data.rest.request.auth.GetSitesRequest;
import com.zennex.trl3lg.data.rest.response.auth.GetSitesResponse;

import io.reactivex.Observable;

public interface ISiteDataSourceRemote {

    Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest);

}
