package com.zennex.trl3lg.data.repository.connection.review.web.service;

import com.zennex.trl3lg.data.entity.rest.request.FetchReviewsRequest;
import com.zennex.trl3lg.data.entity.rest.response.FetchReviewsResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nikita on 20.10.17.
 */

public interface IReviewWebService {

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<FetchReviewsResponse>> fetchItemReviews(@Body List<FetchReviewsRequest> requests);
}
