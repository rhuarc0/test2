package com.zennex.trl3lg.data.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import io.objectbox.Box;


/**
 * Created by nikita on 27.12.2016.
 */

public abstract class LocalRepository<T> {

    @NonNull
    protected final Context mContext;

    @NonNull
    protected Box<T> mBox;

    @NonNull
    protected final SharedPreferences mSharedPreferences;


    public LocalRepository(@NonNull Context context, @NonNull SharedPreferences sharedPreferences) {
        mContext = context;
        mSharedPreferences = sharedPreferences;
    }

    public LocalRepository(@NonNull Context context,
                           @NonNull Box<T> box,
                           @NonNull SharedPreferences sharedPreferences) {
        mContext = context;
        mBox = box;
        mSharedPreferences = sharedPreferences;
    }
}
