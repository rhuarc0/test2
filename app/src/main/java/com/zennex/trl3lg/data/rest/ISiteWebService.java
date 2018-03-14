package com.zennex.trl3lg.data.rest;

import com.zennex.trl3lg.data.rest.request.auth.GetSitesRequest;
import com.zennex.trl3lg.data.rest.response.auth.GetSitesResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nikita on 03.06.17.
 */

public interface ISiteWebService {


    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<GetSitesResponse> getSites(@Body GetSitesRequest getSitesRequest);
}
