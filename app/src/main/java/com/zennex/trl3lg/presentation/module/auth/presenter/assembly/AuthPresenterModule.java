package com.zennex.trl3lg.presentation.module.auth.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.auth.presenter.AuthPresenter;

import dagger.Module;

/**
 * Created by nikita on 03.06.17.
 */
@Module
public class AuthPresenterModule  extends PresenterModule<AuthPresenter>{


    public AuthPresenterModule(AuthPresenter authPresenter) {
        super(authPresenter);
    }
}
