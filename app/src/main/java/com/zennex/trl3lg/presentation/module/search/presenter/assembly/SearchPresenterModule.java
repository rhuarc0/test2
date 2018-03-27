package com.zennex.trl3lg.presentation.module.search.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.search.presenter.SearchPresenter;

import dagger.Module;

@Module
public class SearchPresenterModule extends PresenterModule<SearchPresenter> {
    public SearchPresenterModule(SearchPresenter searchPresenter) {
        super(searchPresenter);
    }
}
