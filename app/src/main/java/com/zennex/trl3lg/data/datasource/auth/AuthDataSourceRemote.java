package com.zennex.trl3lg.data.datasource.auth;

import com.zennex.trl3lg.data.rest.IAuthWebService;
import com.zennex.trl3lg.data.rest.request.auth.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.rest.request.auth.LoginRequest;
import com.zennex.trl3lg.data.rest.response.auth.AuthResponse;
import com.zennex.trl3lg.data.rest.response.auth.IsValidSessionTokenResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public class AuthDataSourceRemote implements IAuthDataSourceRemote {

    @Inject
    protected IAuthWebService mAuthService;

    @Inject
    public AuthDataSourceRemote() {
    }

    @Override
    public Observable<AuthResponse> auth(LoginRequest authRequest) {
        return mAuthService.login(authRequest);
    }

    @Override
    public Observable<IsValidSessionTokenResponse> isValidSessionToken(IsValidSessionTokenRequest request) {
        return mAuthService.isValidSessionToken(request);
    }
}
