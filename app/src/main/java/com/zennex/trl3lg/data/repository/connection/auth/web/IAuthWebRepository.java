package com.zennex.trl3lg.data.repository.connection.auth.web;

import com.zennex.trl3lg.data.rest.request.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.rest.request.LoginRequest;
import com.zennex.trl3lg.data.rest.response.AuthResponse;
import com.zennex.trl3lg.data.rest.response.IsValidSessionTokenResponse;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public interface IAuthWebRepository {

    Observable<AuthResponse> auth(LoginRequest authRequest);

    Observable<IsValidSessionTokenResponse> isValidSessionToken(IsValidSessionTokenRequest request);

}
