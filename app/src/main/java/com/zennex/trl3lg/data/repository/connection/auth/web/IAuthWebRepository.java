package com.zennex.trl3lg.data.repository.connection.auth.web;

import com.zennex.trl3lg.data.entity.rest.request.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.entity.rest.request.LoginRequest;
import com.zennex.trl3lg.data.entity.rest.response.AuthResponse;
import com.zennex.trl3lg.data.entity.rest.response.IsValidSessionTokenResponse;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public interface IAuthWebRepository {

    Observable<AuthResponse> auth(LoginRequest authRequest);

    Observable<IsValidSessionTokenResponse> isValidSessionToken(IsValidSessionTokenRequest request);

}
