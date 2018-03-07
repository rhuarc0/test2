package com.zennex.trl3lg.presentation.module.book.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.book.presenter.BookPresenter;

import dagger.Module;

/**
 * Created by nikita on 20.10.17.
 */
@Module
public class BookPresenterModule extends PresenterModule<BookPresenter> {
    public BookPresenterModule(BookPresenter bookPresenter) {
        super(bookPresenter);
    }
}
