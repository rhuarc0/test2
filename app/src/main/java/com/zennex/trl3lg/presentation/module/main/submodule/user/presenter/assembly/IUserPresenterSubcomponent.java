package com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;
import com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.UserPresenter;

import dagger.Subcomponent;

@PresenterScope
@Subcomponent(modules = UserPresenterModule.class)
public interface IUserPresenterSubcomponent extends PresenterComponent<UserPresenter> {

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<UserPresenterModule, IUserPresenterSubcomponent> {

    }
    }
