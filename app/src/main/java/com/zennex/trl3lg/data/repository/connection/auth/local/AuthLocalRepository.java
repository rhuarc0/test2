package com.zennex.trl3lg.data.repository.connection.auth.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.store.LocalRepository;

import java.util.Collections;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AuthLocalRepository extends LocalRepository implements IAuthLocalRepository {

    private static final String SESSION_TOKEN_KEY = "session_token";
    private static final String MODULE_ID_KEY = "module_id_key";
    private static final String MODULE_IDS_PREF_KEY = "module_ids_pref_key";


    @Inject
    public AuthLocalRepository(@NonNull Context context,
                               @NonNull SharedPreferences sharedPreferences) {
        super(context, sharedPreferences);
    }

    @Override
    public Observable<Boolean> saveSessionToken(String sessionToken) {
        return Observable.fromCallable(() -> mSharedPreferences.edit()
                .putString(SESSION_TOKEN_KEY, sessionToken)
                .commit());
    }

    @Override
    public Observable<Boolean> saveModuleId(String moduleId) {
        return Observable.fromCallable(() -> mSharedPreferences.edit()
                .putString(MODULE_ID_KEY, moduleId)
                .commit());
    }

    @Override
    public Observable<String> getSessionToken() {
        return Observable.fromCallable(() -> mSharedPreferences.getString(SESSION_TOKEN_KEY, ""));
    }

    @Override
    public Observable<String> getModuleId() {
        return Observable.fromCallable(() -> mSharedPreferences.getString(MODULE_ID_KEY, ""));
    }

    @Override
    public Observable<Boolean> saveRentalModuleIds(Set<String> moduleIds) {
        return Observable.fromCallable(() -> mSharedPreferences.edit()
                .putStringSet(MODULE_IDS_PREF_KEY, moduleIds)
                .commit());
    }

    @Override
    public Observable<Set<String>> getRentalModuleIds() {
        return Observable.fromCallable(() -> mSharedPreferences.getStringSet(MODULE_IDS_PREF_KEY, Collections.emptySet()));
    }
}
