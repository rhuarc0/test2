package com.zennex.trl3lg.presentation.module.bookdescription.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.bookdescription.BookDescriptionModuleContract;

/**
 * Created by nikit on 09.09.2017.
 */

@InjectViewState
public class BookDescriptionPresenter extends BookDescriptionModuleContract.AbstractBookDescriptionPresenter {

    public BookDescriptionPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }


    @Override
    public void setBookDescription(String bookDescription) {
        super.setBookDescription(bookDescription);
        getViewState().showDescription(bookDescription);

    }

    @Override
    protected TitleModel getTitle() {
        return new TitleModel.Builder()
                .setTitleMessage(mBookTitle)
                .setVisibleBackButton(true)
                .build();
    }

    @Override
    public void onClickCollapse() {
        mRouter.close();
    }
}
