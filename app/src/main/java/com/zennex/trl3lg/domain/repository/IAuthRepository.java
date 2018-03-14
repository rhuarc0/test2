package com.zennex.trl3lg.domain.repository;

import com.zennex.trl3lg.domain.entities.AuthData;
import com.zennex.trl3lg.domain.entities.Site;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public interface IAuthRepository {

    Observable<AuthData> auth(String email, String password, String moduleId, List<Site> sites);

    Observable<Boolean> saveSessionToken(String sessionToken);

    Observable<Boolean> saveModuleId(String moduleId);

    Observable<Boolean> saveRentalModuleIds(Set<String> moduleIds);

    Observable<String> getSessionToken();

    Observable<String> getModuleId();

    Observable<Set<String>> getRentalModuleIds();

    Observable<Boolean> isValidSessionToken();
}
