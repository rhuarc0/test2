package com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.auth.presenter.AuthPresenter;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.CatalogPresenter;

import dagger.Module;

/**
 * Created by zennex on 27.07.17.
 */

@Module
public class CatalogPresenterModule extends PresenterModule<CatalogPresenter> {

    public CatalogPresenterModule(CatalogPresenter catalogPresenter) {
        super(catalogPresenter);
    }
}
