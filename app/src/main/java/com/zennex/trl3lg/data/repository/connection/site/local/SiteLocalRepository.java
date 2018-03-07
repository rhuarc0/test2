package com.zennex.trl3lg.data.repository.connection.site.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.store.LocalRepository;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public class SiteLocalRepository extends LocalRepository implements ISiteLocalRepository {

    private static final String SITE_ID_KEY = "site_id";

    @Inject
    public SiteLocalRepository(@NonNull Context context,
                               @NonNull SharedPreferences sharedPreferences) {
        super(context, sharedPreferences);
    }

    @Override
    public Observable<Boolean> saveSiteId(String siteId) {
        return Observable.fromCallable(() -> mSharedPreferences.edit().putString(SITE_ID_KEY, siteId).commit());
    }

    @Override
    public Observable<String> getSiteId() {
        return Observable.fromCallable(() -> mSharedPreferences.getString(SITE_ID_KEY, ""));
    }

}
