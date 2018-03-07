package com.zennex.trl3lg.presentation.module.auth.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;
import com.zennex.trl3lg.presentation.module.auth.presenter.AuthPresenter;

import dagger.Subcomponent;

/**
 * Created by nikita on 03.06.17.
 */
@PresenterScope
@Subcomponent(modules = AuthPresenterModule.class)
public interface IAuthPresenterSubcomponent extends PresenterComponent<AuthPresenter>{

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<AuthPresenterModule, IAuthPresenterSubcomponent> {

    }

}
