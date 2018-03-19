package com.zennex.trl3lg.data.rest;

import com.zennex.trl3lg.data.rest.request.member.ChangeAvailMediaTypeRequest;
import com.zennex.trl3lg.data.rest.request.member.FetchMemberRequest;
import com.zennex.trl3lg.data.rest.request.member.SaveLogsRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.member.FetchMemberResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nikita on 15.03.18.
 */

public interface IMemberWebService {

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<BaseResponse>> saveLogs(@Body List<SaveLogsRequest> request);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<FetchMemberResponse> fetchMember(@Body FetchMemberRequest request);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<List<BaseResponse>> changeAvailMediaType(@Body List<ChangeAvailMediaTypeRequest> requests);

}
