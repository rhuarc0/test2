package com.zennex.trl3lg.presentation.module.signup.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.module.signup.presenter.SignUpPresenter;

import dagger.Subcomponent;

/**
 * Created by nikita on 24.06.17.
 */
@Subcomponent(modules = SignUpPresenterModule.class)
public interface ISignUpPresenterSubcomponent extends PresenterComponent<SignUpPresenter> {

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<SignUpPresenterModule, ISignUpPresenterSubcomponent> {

    }

}
