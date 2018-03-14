package com.zennex.trl3lg.data.repository.connection.auth;

import com.zennex.trl3lg.data.rest.request.auth.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.rest.request.auth.LoginRequest;
import com.zennex.trl3lg.data.rest.response.auth.AuthResponse;
import com.zennex.trl3lg.data.rest.response.auth.IsValidSessionTokenResponse;

import java.util.Set;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public interface IAuthRepository {

    Observable<AuthResponse> auth(LoginRequest authRequest);

    Observable<Boolean> saveSessionToken(String sessionToken);

    Observable<Boolean> saveModuleId(String moduleId);

    Observable<String> getSessionToken();

    Observable<String> getModuleId();

    Observable<Boolean> saveRentalModuleIds(Set<String> moduleIds);

    Observable<Set<String>> getRentalModuleIds();

    Observable<IsValidSessionTokenResponse> isValidSessionToken(IsValidSessionTokenRequest request);
}
