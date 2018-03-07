package com.zennex.trl3lg.presentation.common.di.app;

import android.content.Context;
import android.support.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @NonNull
    private final Context mContext;

    public AppModule(@NonNull Context context) {
        this.mContext = context;
    }

    @NonNull
    @Provides
    Context provideContext() {
        return mContext;
    }

}
