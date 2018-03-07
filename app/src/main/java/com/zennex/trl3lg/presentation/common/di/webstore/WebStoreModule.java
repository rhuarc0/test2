package com.zennex.trl3lg.presentation.common.di.webstore;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.store.WebDataStore;
import com.zennex.trl3lg.presentation.common.di.repository.WebRepositoryModule;

import dagger.Binds;
import dagger.Module;


/**
 * Created by nikita on 25.12.2016.
 */

@Module(includes = WebStoreModule.Declarations.class)
public class WebStoreModule {

    @NonNull
    private final Context mContext;

    public WebStoreModule(@NonNull Context context) {
        mContext = context;
    }

    @Module(includes = WebRepositoryModule.class)
    public abstract class Declarations {

        @NonNull
        @Binds
        public abstract WebDataStore bindWebDataStore(WebDataStore webDataStore);

    }


}
