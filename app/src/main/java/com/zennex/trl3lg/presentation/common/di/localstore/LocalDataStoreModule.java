package com.zennex.trl3lg.presentation.common.di.localstore;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.store.LocalDataStore;
import com.zennex.trl3lg.presentation.common.di.repository.LocalRepositoryModule;

import dagger.Binds;
import dagger.Module;


/**
 * Created by nikita on 25.12.2016.
 */
@Module(includes = LocalDataStoreModule.Declarations.class)
public class LocalDataStoreModule {

    @Module(includes = LocalRepositoryModule.class)
    public abstract class Declarations {

        @NonNull
        @Binds
        public abstract LocalDataStore bindLocalDataStore(LocalDataStore localDataStore);

    }
}
