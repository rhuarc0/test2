package com.zennex.trl3lg.data.datasource.auth;

import com.zennex.trl3lg.data.entity.rest.request.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.entity.rest.request.LoginRequest;
import com.zennex.trl3lg.data.entity.rest.response.AuthResponse;
import com.zennex.trl3lg.data.entity.rest.response.IsValidSessionTokenResponse;
import com.zennex.trl3lg.data.rest.IAuthWebService;

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
