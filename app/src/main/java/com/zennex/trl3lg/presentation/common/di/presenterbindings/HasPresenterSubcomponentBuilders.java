package com.zennex.trl3lg.presentation.common.di.presenterbindings;


import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;

/**
 * Created by Nikita on 05.05.2017.
 */

public interface HasPresenterSubcomponentBuilders {

    PresenterComponentBuilder getPresenterComponentBuilder(Class<? extends ViperBasePresenter> presenterClass);

}
