package com.zennex.trl3lg.data.repository.connection.auth;

import com.zennex.trl3lg.data.entity.rest.request.IsValidSessionTokenRequest;
import com.zennex.trl3lg.data.entity.rest.request.LoginRequest;
import com.zennex.trl3lg.data.entity.rest.response.AuthResponse;
import com.zennex.trl3lg.data.entity.rest.response.IsValidSessionTokenResponse;
import com.zennex.trl3lg.data.repository.connection.auth.local.IAuthLocalRepository;
import com.zennex.trl3lg.data.repository.connection.auth.web.IAuthWebRepository;

import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public class AuthRepository implements IAuthRepository {

    @Inject
    protected IAuthLocalRepository mAuthLocalRepository;

    @Inject
    protected IAuthWebRepository mAuthWebRepository;

    @Inject
    public AuthRepository() {

    }

    @Override
    public Observable<AuthResponse> auth(LoginRequest authRequest) {
        return mAuthWebRepository.auth(authRequest);
    }

    @Override
    public Observable<Boolean> saveSessionToken(String sessionToken) {
        return mAuthLocalRepository.saveSessionToken(sessionToken);
    }

    @Override
    public Observable<Boolean> saveModuleId(String moduleId) {
        return mAuthLocalRepository.saveModuleId(moduleId);
    }

    @Override
    public Observable<String> getSessionToken() {
        return mAuthLocalRepository.getSessionToken();
    }

    @Override
    public Observable<String> getModuleId() {
        return mAuthLocalRepository.getModuleId();
    }

    @Override
    public Observable<Boolean> saveRentalModuleIds(Set<String> moduleIds) {
        return mAuthLocalRepository.saveRentalModuleIds(moduleIds);
    }

    @Override
    public Observable<Set<String>> getRentalModuleIds() {
        return mAuthLocalRepository.getRentalModuleIds();
    }

    @Override
    public Observable<IsValidSessionTokenResponse> isValidSessionToken(IsValidSessionTokenRequest request) {
        return mAuthWebRepository.isValidSessionToken(request);
    }

}
