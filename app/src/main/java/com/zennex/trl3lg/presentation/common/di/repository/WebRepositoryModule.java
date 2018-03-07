package com.zennex.trl3lg.presentation.common.di.repository;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.repository.connection.auth.web.AuthWebRepository;
import com.zennex.trl3lg.data.repository.connection.auth.web.IAuthWebRepository;
import com.zennex.trl3lg.data.repository.connection.auth.web.service.IAuthWebService;
import com.zennex.trl3lg.data.repository.connection.rental.book.web.IRentalBookWebRepository;
import com.zennex.trl3lg.data.repository.connection.rental.book.web.RentalBookWebRepository;
import com.zennex.trl3lg.data.repository.connection.rental.book.web.service.IRentalBookWebService;
import com.zennex.trl3lg.data.repository.connection.review.web.IReviewWebRepository;
import com.zennex.trl3lg.data.repository.connection.review.web.ReviewWebRepository;
import com.zennex.trl3lg.data.repository.connection.review.web.service.IReviewWebService;
import com.zennex.trl3lg.data.repository.connection.signup.web.ISignUpWebRepository;
import com.zennex.trl3lg.data.repository.connection.signup.web.SignUpWebRepository;
import com.zennex.trl3lg.data.repository.connection.signup.web.service.ISignUpWebService;
import com.zennex.trl3lg.data.repository.connection.site.service.ISiteWebService;
import com.zennex.trl3lg.data.repository.connection.site.web.ISiteWebRepository;
import com.zennex.trl3lg.data.repository.connection.site.web.SiteWebRepository;
import com.zennex.trl3lg.presentation.common.di.network.NetworkModule;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


/**
 * Created by nikita on 28.12.2016.
 */
@Module(includes = {NetworkModule.class, WebRepositoryModule.Declarations.class})
public class WebRepositoryModule {


    @NonNull
    @Provides
    protected ISiteWebService provideSiteService(Retrofit retrofit) {
        return retrofit.create(ISiteWebService.class);
    }

    @NonNull
    @Provides
    protected IAuthWebService provideAuthService(Retrofit retrofit) {
        return retrofit.create(IAuthWebService.class);
    }

    @NonNull
    @Provides
    protected ISignUpWebService provideSignUpWebService(Retrofit retrofit) {
        return retrofit.create(ISignUpWebService.class);
    }

    @NonNull
    @Provides
    protected IRentalBookWebService provideRentalBookWebService(Retrofit retrofit) {
        return retrofit.create(IRentalBookWebService.class);
    }

    @NonNull
    @Provides
    protected IReviewWebService provideReviewWebService(Retrofit retrofit) {
        return retrofit.create(IReviewWebService.class);
    }

    @Module
    public static abstract class Declarations {

        @NonNull
        @Binds
        protected abstract ISiteWebRepository bindSiteWebRepository(SiteWebRepository siteWebRepository);

        @NonNull
        @Binds
        protected abstract IAuthWebRepository bindAuthWebRepository(AuthWebRepository authWebRepository);

        @NonNull
        @Binds
        protected abstract ISignUpWebRepository bindSignUpWebRepository(SignUpWebRepository signUpWebRepository);

        @NonNull
        @Binds
        protected abstract IRentalBookWebRepository bindRentalBookWebRepository(RentalBookWebRepository rentalBookWebRepository);

        @NonNull
        @Binds
        protected abstract IReviewWebRepository bindReviewWebRepository(ReviewWebRepository reviewWebRepository);

    }

}
