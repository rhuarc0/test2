package com.zennex.trl3lg.data.rest;

import com.zennex.trl3lg.data.rest.request.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.rest.request.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.rest.response.SignUpResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nikita on 12.06.17.
 */

public interface ISignUpWebService {


    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(@Body GetFieldsForSignUpRequest getFieldsForSignUpRequest);


    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<SignUpResponse> signUp(@Body SignUpRequest signUpRequest);


}
