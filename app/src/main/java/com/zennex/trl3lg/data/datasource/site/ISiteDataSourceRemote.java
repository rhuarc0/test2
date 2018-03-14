package com.zennex.trl3lg.data.datasource.site;


import com.zennex.trl3lg.data.entity.rest.request.GetSitesRequest;
import com.zennex.trl3lg.data.entity.rest.response.GetSitesResponse;

import io.reactivex.Observable;

public interface ISiteDataSourceRemote {

    Observable<GetSitesResponse> getSites(GetSitesRequest getSitesRequest);

}
