package com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.UserPresenter;

import dagger.Module;

@Module
public class UserPresenterModule extends PresenterModule<UserPresenter> {
    public UserPresenterModule(UserPresenter userPresenter) {
        super(userPresenter);
    }
}
