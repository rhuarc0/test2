package com.zennex.trl3lg.data.datasource.auth;

import com.zennex.trl3lg.data.rest.request.auth.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.rest.request.auth.LoginRequest;
import com.zennex.trl3lg.data.rest.response.auth.AuthResponse;
import com.zennex.trl3lg.data.rest.response.auth.IsValidSessionTokenResponse;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public interface IAuthDataSourceRemote {

    Observable<AuthResponse> auth(LoginRequest authRequest);

    Observable<IsValidSessionTokenResponse> isValidSessionToken(IsValidSessionTokenRequest request);

}
