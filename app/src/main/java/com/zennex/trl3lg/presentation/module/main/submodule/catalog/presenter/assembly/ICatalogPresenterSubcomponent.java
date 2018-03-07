package com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.AuthPresenterModule;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.IAuthPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.CatalogPresenter;

import dagger.Subcomponent;

/**
 * Created by zennex on 27.07.17.
 */

@PresenterScope
@Subcomponent(modules = CatalogPresenterModule.class)
public interface ICatalogPresenterSubcomponent extends PresenterComponent<CatalogPresenter> {

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<CatalogPresenterModule, ICatalogPresenterSubcomponent> {

    }

}
