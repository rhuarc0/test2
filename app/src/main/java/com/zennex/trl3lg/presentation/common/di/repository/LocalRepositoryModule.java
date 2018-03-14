package com.zennex.trl3lg.presentation.common.di.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.datasource.auth.AuthDataSourceLocal;
import com.zennex.trl3lg.data.datasource.auth.IAuthDataSourceLocal;
import com.zennex.trl3lg.data.repository.local.ConnectionSettingsLocalRepository;
import com.zennex.trl3lg.data.repository.local.IConnectionSettingsLocalRepository;
import com.zennex.trl3lg.data.datasource.book.BookDataSourceLocal;
import com.zennex.trl3lg.data.datasource.book.RentalBookDataSourceLocal;
import com.zennex.trl3lg.data.datasource.site.ISiteDataSourceLocal;
import com.zennex.trl3lg.data.datasource.site.SiteLocalDataSource;
import com.zennex.trl3lg.presentation.common.di.objectbox.ObjectBoxModule;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;


/**
 * Created by nikita on 27.12.2016.
 */

@Module(includes = {ObjectBoxModule.class, LocalRepositoryModule.Declarations.class,})
public class LocalRepositoryModule {

    @NonNull
    @Provides
    public SharedPreferences provideSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    @Module
    public abstract class Declarations {

        @NonNull
        @Binds
        public abstract IConnectionSettingsLocalRepository bindConnectionSettingsLocalRepository(ConnectionSettingsLocalRepository connectionSettingsLocalRepository);

        @NonNull
        @Binds
        public abstract IAuthDataSourceLocal bindAuthLocalRepository(AuthDataSourceLocal authLocalRepository);

        @NonNull
        @Binds
        public abstract ISiteDataSourceLocal bindSiteLocalRepository(SiteLocalDataSource siteLocalRepository);

        @NonNull
        @Binds
        public abstract BookDataSourceLocal bindRentalBookLocalRepository(RentalBookDataSourceLocal rentalBookLocalRepository);

    }

}
