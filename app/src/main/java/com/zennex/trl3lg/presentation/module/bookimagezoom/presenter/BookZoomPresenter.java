package com.zennex.trl3lg.presentation.module.bookimagezoom.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.bookimagezoom.BookImageZoomModuleContract;

/**
 * Created by nikita on 12.09.17.
 */
@InjectViewState
public class BookZoomPresenter extends BookImageZoomModuleContract.AbstractBookZoomPresenter {

    public BookZoomPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }


    @Override
    public void setBookImagePath(String bookImagePath) {
        super.setBookImagePath(bookImagePath);
        getViewState().showImage(mBookImagePath);
    }

    @Override
    protected TitleModel getTitle() {
        return new TitleModel.Builder()
                .setTitleMessage(mBookTitle)
                .setVisibleBackButton(true)
                .build();
    }
}
