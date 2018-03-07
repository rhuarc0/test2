package com.zennex.trl3lg.data.repository.connection.auth;

import com.zennex.trl3lg.data.entity.rest.request.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.entity.rest.request.LoginRequest;
import com.zennex.trl3lg.data.entity.rest.response.AuthResponse;
import com.zennex.trl3lg.data.entity.rest.response.IsValidSessionTokenResponse;

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
