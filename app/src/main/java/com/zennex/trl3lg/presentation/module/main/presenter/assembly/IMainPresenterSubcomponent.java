package com.zennex.trl3lg.presentation.module.main.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;
import com.zennex.trl3lg.presentation.module.auth.presenter.AuthPresenter;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.AuthPresenterModule;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.IAuthPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.main.presenter.MainPresenter;

import dagger.Subcomponent;

/**
 * Created by nikita on 22.07.17.
 */
@PresenterScope
@Subcomponent(modules = MainPresenterModule.class)
public interface IMainPresenterSubcomponent extends PresenterComponent<MainPresenter> {

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<MainPresenterModule, IMainPresenterSubcomponent> {

    }

}
