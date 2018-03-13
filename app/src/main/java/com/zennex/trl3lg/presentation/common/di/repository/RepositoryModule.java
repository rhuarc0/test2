package com.zennex.trl3lg.presentation.common.di.repository;


import com.zennex.trl3lg.data.repository.connection.auth.AuthRepository;
import com.zennex.trl3lg.data.repository.connection.auth.IAuthRepository;
import com.zennex.trl3lg.data.repository.connection.book.BookRepositoryImpl;
import com.zennex.trl3lg.data.repository.connection.review.IReviewRepository;
import com.zennex.trl3lg.data.repository.connection.review.ReviewRepository;
import com.zennex.trl3lg.data.repository.connection.signup.ISignUpRepository;
import com.zennex.trl3lg.data.repository.connection.signup.SignUpRepository;
import com.zennex.trl3lg.data.repository.connection.site.ISiteRepository;
import com.zennex.trl3lg.data.repository.connection.site.SiteRepository;
import com.zennex.trl3lg.domain.repository.BookRepository;

import dagger.Binds;
import dagger.Module;


@Module(includes = {LocalRepositoryModule.class, WebRepositoryModule.class})
public abstract class RepositoryModule {

    @Binds
    public abstract ISiteRepository bindSiteRepository(SiteRepository siteRepository);


    @Binds
    public abstract IAuthRepository bindAuthRepository(AuthRepository authRepository);

    @Binds
    public abstract ISignUpRepository bindSignUpRepository(SignUpRepository signUpRepository);

    @Binds
    public abstract IReviewRepository bindReviewRepository(ReviewRepository reviewRepository);

    @Binds
    public abstract BookRepository bindBookRepository(BookRepositoryImpl bookRepository);


}
