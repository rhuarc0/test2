package com.zennex.trl3lg.presentation.common.di.presenterbindings;


import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;

import dagger.MembersInjector;

/**
 * Created by Nikita on 05.05.2017.
 */

public interface PresenterComponent<Presenter extends ViperBasePresenter> extends MembersInjector<Presenter> {
}
