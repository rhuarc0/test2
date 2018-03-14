package com.zennex.trl3lg.data.repository.connection.auth.web;

import com.zennex.trl3lg.data.rest.request.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.rest.request.LoginRequest;
import com.zennex.trl3lg.data.rest.response.AuthResponse;
import com.zennex.trl3lg.data.rest.response.IsValidSessionTokenResponse;
import com.zennex.trl3lg.data.repository.connection.auth.web.service.IAuthWebService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public class AuthWebRepository implements IAuthWebRepository {

    @Inject
    protected IAuthWebService mAuthService;

    @Inject
    public AuthWebRepository() {
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
