package com.zennex.trl3lg.data.rest;

import com.zennex.trl3lg.data.entity.rest.request.FetchBookListRequest;
import com.zennex.trl3lg.data.entity.rest.request.FetchQueueRequest;
import com.zennex.trl3lg.data.entity.rest.request.FetchRentalGroupsRequest;
import com.zennex.trl3lg.data.entity.rest.response.FetchBookListResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchQueueBooksResponse;
import com.zennex.trl3lg.data.entity.rest.response.FetchRentalGroupsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IRentalBookWebService {

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchRentalGroupsResponse>> getGroups(@Body List<FetchRentalGroupsRequest> request);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchBookListResponse>> fetchBooks(@Body List<FetchBookListRequest> requests);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchQueueBooksResponse>> fetchQueue(@Body List<FetchQueueRequest> requests);


}
