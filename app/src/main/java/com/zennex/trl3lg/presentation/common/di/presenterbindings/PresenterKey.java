package com.zennex.trl3lg.presentation.common.di.presenterbindings;


import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;

import dagger.MapKey;

/**
 * Created by Nikita on 05.05.2017.
 */

@MapKey
public @interface PresenterKey {

    Class<? extends ViperBasePresenter> value();

}
