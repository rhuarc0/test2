package com.zennex.trl3lg.data.repository.connection.auth.local;

import java.util.Set;

import io.reactivex.Observable;

/**
 * Created by nikita on 02.06.17.
 */

public interface IAuthLocalRepository {

    Observable<Boolean> saveSessionToken(String sessionId);

    Observable<Boolean> saveModuleId(String moduleId);

    Observable<String> getSessionToken();

    Observable<String> getModuleId();

    Observable<Boolean> saveRentalModuleIds(Set<String> moduleIds);

    Observable<Set<String>> getRentalModuleIds();
}
