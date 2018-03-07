package com.zennex.trl3lg.presentation.module.book.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;
import com.zennex.trl3lg.presentation.module.book.presenter.BookPresenter;

import dagger.Subcomponent;

/**
 * Created by nikita on 20.10.17.
 */
@PresenterScope
@Subcomponent(modules = BookPresenterModule.class)
public interface IBookPresenterSubcomponent extends PresenterComponent<BookPresenter> {

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<BookPresenterModule, IBookPresenterSubcomponent> {

    }

}
