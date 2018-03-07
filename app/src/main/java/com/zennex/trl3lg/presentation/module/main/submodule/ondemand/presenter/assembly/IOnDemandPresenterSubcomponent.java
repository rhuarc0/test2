package com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;
import com.zennex.trl3lg.presentation.module.main.presenter.assembly.IMainPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.main.presenter.assembly.MainPresenterModule;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.OnDemandPresenter;

import dagger.Subcomponent;

/**
 * Created by nikit on 27.08.2017.
 */
@PresenterScope
@Subcomponent(modules = OnDemandPresenterModule.class)
public interface IOnDemandPresenterSubcomponent extends PresenterComponent<OnDemandPresenter> {

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<OnDemandPresenterModule, IOnDemandPresenterSubcomponent> {

    }
}
