package com.zennex.trl3lg.data.rest;

import com.zennex.trl3lg.data.rest.request.book.FetchReviewsRequest;
import com.zennex.trl3lg.data.rest.request.book.SetReviewUsefulRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.book.FetchReviewsResponse;
import com.zennex.trl3lg.data.rest.response.book.SetReviewUsefulResponse;

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

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<SetReviewUsefulResponse> setReviewUseful(@Body List<SetReviewUsefulRequest> requests);

}
