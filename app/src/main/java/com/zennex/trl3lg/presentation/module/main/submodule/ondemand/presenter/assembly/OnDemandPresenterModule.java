package com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.OnDemandPresenter;

import dagger.Module;

/**
 * Created by nikit on 27.08.2017.
 */

@Module
public class OnDemandPresenterModule extends PresenterModule<OnDemandPresenter>{
    public OnDemandPresenterModule(OnDemandPresenter onDemandPresenter) {
        super(onDemandPresenter);
    }
}
