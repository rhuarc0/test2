package com.zennex.trl3lg.presentation.common.di.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.repository.connection.auth.local.AuthLocalRepository;
import com.zennex.trl3lg.data.repository.connection.auth.local.IAuthLocalRepository;
import com.zennex.trl3lg.data.repository.connection.local.ConnectionSettingsLocalRepository;
import com.zennex.trl3lg.data.repository.connection.local.IConnectionSettingsLocalRepository;
import com.zennex.trl3lg.data.datasource.book.BookDataSourceLocal;
import com.zennex.trl3lg.data.datasource.book.RentalBookDataSourceLocal;
import com.zennex.trl3lg.data.repository.connection.site.local.ISiteLocalRepository;
import com.zennex.trl3lg.data.repository.connection.site.local.SiteLocalRepository;
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
        public abstract IAuthLocalRepository bindAuthLocalRepository(AuthLocalRepository authLocalRepository);

        @NonNull
        @Binds
        public abstract ISiteLocalRepository bindSiteLocalRepository(SiteLocalRepository siteLocalRepository);

        @NonNull
        @Binds
        public abstract BookDataSourceLocal bindRentalBookLocalRepository(RentalBookDataSourceLocal rentalBookLocalRepository);

    }

}
