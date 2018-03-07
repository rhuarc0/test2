package com.zennex.trl3lg.presentation.common.di.repository;


import com.zennex.trl3lg.data.repository.connection.auth.AuthRepository;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.repository.connection.rental.book.IRentalBookRepository;
import com.zennex.trl3lg.data.repository.connection.rental.book.RentalBookRepository;
import com.zennex.trl3lg.data.repository.connection.review.IReviewRepository;
import com.zennex.trl3lg.data.repository.connection.review.ReviewRepository;
import com.zennex.trl3lg.data.repository.connection.signup.ISignUpRepository;
import com.zennex.trl3lg.data.repository.connection.signup.SignUpRepository;
import com.zennex.trl3lg.data.repository.connection.site.ISiteRepository;
import com.zennex.trl3lg.data.repository.connection.site.SiteRepository;

import dagger.Binds;
import dagger.Module;


/**
 * Created by Nikita on 13.04.2017.
 */

@Module(includes = {LocalRepositoryModule.class, WebRepositoryModule.class})
public abstract class RepositoryModule {

    @Binds
    public abstract ISiteRepository bindSiteRepository(SiteRepository siteRepository);


    @Binds
    public abstract IAuthRepository bindAuthRepository(AuthRepository authRepository);

    @Binds
    public abstract ISignUpRepository bindSignUpRepository(SignUpRepository signUpRepository);

    @Binds
    public abstract IRentalBookRepository bindRentalBookRepository(RentalBookRepository rentalBookRepository);

    @Binds
    public abstract IReviewRepository bindReviewRepository(ReviewRepository reviewRepository);

}
