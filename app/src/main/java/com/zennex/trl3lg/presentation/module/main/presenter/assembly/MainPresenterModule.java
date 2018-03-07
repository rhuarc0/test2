package com.zennex.trl3lg.presentation.module.main.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.auth.presenter.AuthPresenter;
import com.zennex.trl3lg.presentation.module.main.presenter.MainPresenter;

import dagger.Module;

/**
 * Created by nikita on 22.07.17.
 */

@Module
public class MainPresenterModule extends PresenterModule<MainPresenter> {
    public MainPresenterModule(MainPresenter mainPresenter) {
        super(mainPresenter);
    }
}
