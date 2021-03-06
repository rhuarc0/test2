package com.zennex.trl3lg.data.rest;

import com.zennex.trl3lg.data.rest.request.auth.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.rest.request.auth.LoginRequest;
import com.zennex.trl3lg.data.rest.response.auth.AuthResponse;
import com.zennex.trl3lg.data.rest.response.auth.IsValidSessionTokenResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by nikita on 02.06.17.
 */

public interface IAuthWebService {

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<AuthResponse> login(@Body LoginRequest authRequest);

    @Headers("Content-Type: application/json")
    @POST("core/webservice")
    Observable<IsValidSessionTokenResponse> isValidSessionToken(@Body IsValidSessionTokenRequest request);
}
