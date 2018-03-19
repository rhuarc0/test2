package com.zennex.trl3lg.presentation.common.di.repository;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.datasource.book.BookDataSourceRemote;
import com.zennex.trl3lg.data.datasource.book.RentalBookDataSourceRemote;
import com.zennex.trl3lg.data.datasource.auth.AuthDataSourceRemote;
import com.zennex.trl3lg.data.datasource.auth.IAuthDataSourceRemote;
import com.zennex.trl3lg.data.datasource.member.IMemberDataSourceRemote;
import com.zennex.trl3lg.data.datasource.member.MemberDataSourceRemote;
import com.zennex.trl3lg.data.datasource.site.ISiteDataSourceRemote;
import com.zennex.trl3lg.data.datasource.site.SiteDataSourceRemote;
import com.zennex.trl3lg.data.datasource.review.IReviewDataSource;
import com.zennex.trl3lg.data.datasource.review.ReviewDataSource;
import com.zennex.trl3lg.data.rest.IAuthWebService;
import com.zennex.trl3lg.data.datasource.signup.ISignUpDataSource;
import com.zennex.trl3lg.data.datasource.signup.SignUpDataSource;
import com.zennex.trl3lg.data.rest.IMemberWebService;
import com.zennex.trl3lg.data.rest.IRentalBookWebService;
import com.zennex.trl3lg.data.rest.IReviewWebService;
import com.zennex.trl3lg.data.rest.ISignUpWebService;
import com.zennex.trl3lg.data.rest.ISiteWebService;
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
    protected IMemberWebService provideMemberService(Retrofit retrofit) {
        return retrofit.create(IMemberWebService.class);
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
        protected abstract ISiteDataSourceRemote bindSiteWebRepository(SiteDataSourceRemote siteWebRepository);

        @NonNull
        @Binds
        protected abstract IAuthDataSourceRemote bindAuthWebRepository(AuthDataSourceRemote authWebRepository);

        @NonNull
        @Binds
        protected abstract IMemberDataSourceRemote bindMemberWebRepository(MemberDataSourceRemote memberDataSourceRemote);

        @NonNull
        @Binds
        protected abstract ISignUpDataSource bindSignUpWebRepository(SignUpDataSource signUpWebRepository);

        @NonNull
        @Binds
        protected abstract BookDataSourceRemote bindRentalBookWebRepository(RentalBookDataSourceRemote rentalBookWebRepository);

        @NonNull
        @Binds
        protected abstract IReviewDataSource bindReviewWebRepository(ReviewDataSource reviewWebRepository);

    }

}
