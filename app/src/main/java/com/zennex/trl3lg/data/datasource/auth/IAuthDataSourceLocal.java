package com.zennex.trl3lg.data.datasource.auth;

import java.util.Set;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public interface IAuthDataSourceLocal {

    Observable<Boolean> saveSessionToken(String sessionId);

    Observable<Boolean> saveModuleId(String moduleId);

    Observable<Boolean> saveRentalModuleIds(Set<String> moduleIds);

    Observable<String> getSessionToken();

    Observable<String> getModuleId();

    Observable<Set<String>> getRentalModuleIds();
}
