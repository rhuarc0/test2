package com.zennex.trl3lg.presentation.module.signup.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.signup.presenter.SignUpPresenter;

import dagger.Module;

/**
 * Created by nikita on 24.06.17.
 */

@Module
public class SignUpPresenterModule extends PresenterModule<SignUpPresenter> {

    public SignUpPresenterModule(SignUpPresenter presenter) {
        super(presenter);
    }
}
