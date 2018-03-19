package com.zennex.trl3lg.presentation.common.di.presenterbindings;


import com.zennex.trl3lg.presentation.module.auth.presenter.AuthPresenter;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.IAuthPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.book.presenter.BookPresenter;
import com.zennex.trl3lg.presentation.module.book.presenter.assembly.IBookPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.CatalogPresenter;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.assembly.ICatalogPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.OnDemandPresenter;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.assembly.IOnDemandPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.UserPresenter;
import com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.assembly.IUserPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.review.presenter.ReviewsPresenter;
import com.zennex.trl3lg.presentation.module.review.presenter.assembly.IReviewsPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.search.presenter.SearchPresenter;
import com.zennex.trl3lg.presentation.module.search.presenter.assembly.ISearchPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.signup.presenter.SignUpPresenter;
import com.zennex.trl3lg.presentation.module.signup.presenter.assembly.ISignUpPresenterSubcomponent;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


/**
 * Created by Nikita on 05.05.2017.
 */

@Module(subcomponents = {
        IAuthPresenterSubcomponent.class,
        ISignUpPresenterSubcomponent.class,
        ICatalogPresenterSubcomponent.class,
        ISearchPresenterSubcomponent.class,
        IOnDemandPresenterSubcomponent.class,
        IBookPresenterSubcomponent.class,
        IUserPresenterSubcomponent.class,
        IReviewsPresenterSubcomponent.class})
public abstract class PresenterBindingModule {

    @Binds
    @IntoMap
    @PresenterKey(AuthPresenter.class)
    public abstract PresenterComponentBuilder bindAuthPresenterComponent(IAuthPresenterSubcomponent.Builder impl);

    @Binds
    @IntoMap
    @PresenterKey(SignUpPresenter.class)
    public abstract PresenterComponentBuilder bindSignUContentInputPresenterComponent(ISignUpPresenterSubcomponent.Builder impl);

    @Binds
    @IntoMap
    @PresenterKey(CatalogPresenter.class)
    public abstract PresenterComponentBuilder bindCatalogPresenterComponent(ICatalogPresenterSubcomponent.Builder impl);

    @Binds
    @IntoMap
    @PresenterKey(SearchPresenter.class)
    public abstract PresenterComponentBuilder bindSearchPresenterComponent(ISearchPresenterSubcomponent.Builder impl);

    @Binds
    @IntoMap
    @PresenterKey(OnDemandPresenter.class)
    public abstract PresenterComponentBuilder bindOnDemandPresenterComponent(IOnDemandPresenterSubcomponent.Builder impl);

    @Binds
    @IntoMap
    @PresenterKey(BookPresenter.class)
    public abstract PresenterComponentBuilder bindBookPresenterComponent(IBookPresenterSubcomponent.Builder impl);

    @Binds
    @IntoMap
    @PresenterKey(ReviewsPresenter.class)
    public abstract PresenterComponentBuilder bindReviewsPresenterComponent(IReviewsPresenterSubcomponent.Builder impl);

    @Binds
    @IntoMap
    @PresenterKey(UserPresenter.class)
    public abstract PresenterComponentBuilder bindUserPresenterComponent(IUserPresenterSubcomponent.Builder impl);
}
