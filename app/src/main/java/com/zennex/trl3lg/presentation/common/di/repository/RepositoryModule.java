package com.zennex.trl3lg.presentation.common.di.repository;


import com.zennex.trl3lg.data.repository.auth.AuthRepository;
import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.data.repository.book.BookRepository;
import com.zennex.trl3lg.domain.repository.IReviewRepository;
import com.zennex.trl3lg.data.repository.review.ReviewRepository;
import com.zennex.trl3lg.domain.repository.ISignUpRepository;
import com.zennex.trl3lg.data.repository.signup.SignUpRepository;
import com.zennex.trl3lg.domain.repository.ISiteRepository;
import com.zennex.trl3lg.data.repository.site.SiteRepository;
import com.zennex.trl3lg.domain.repository.IBookRepository;

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
    public abstract IBookRepository bindBookRepository(BookRepository bookRepository);


}
