package com.zennex.trl3lg.presentation.module.book.submodule.myreview.comemnt.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.book.submodule.myreview.comemnt.MyCommentModuleContract;

/**
 * Created by nikita on 20.10.17.
 */

@InjectViewState
public class MyCommentPresenter extends MyCommentModuleContract.AbstractMyCommentPresenter {

    public MyCommentPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    protected TitleModel getTitle() {
        return null;
    }
}
