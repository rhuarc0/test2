package com.zennex.trl3lg.presentation.common.di.presenterbindings;


import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nikita on 05.05.2017.
 */
@Module
public abstract class PresenterModule<Presenter> {

    protected final Presenter mPresenter;

    public PresenterModule(Presenter presenter) {
        mPresenter = presenter;
    }

    @Provides
    @PresenterScope
    public Presenter providePresenter() {
        return mPresenter;
    }
}
