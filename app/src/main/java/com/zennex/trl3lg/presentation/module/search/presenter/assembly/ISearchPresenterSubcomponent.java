package com.zennex.trl3lg.presentation.module.search.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.AuthPresenterModule;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.IAuthPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.search.presenter.SearchPresenter;

import dagger.Subcomponent;

/**
 * Created by nikit on 02.08.2017.
 */
@PresenterScope
@Subcomponent(modules = SearchPresenterModule.class)
public interface ISearchPresenterSubcomponent extends PresenterComponent<SearchPresenter> {

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<SearchPresenterModule, ISearchPresenterSubcomponent> {

    }

}
