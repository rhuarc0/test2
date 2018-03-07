package com.zennex.trl3lg.presentation.module.main.submodule.user.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.main.submodule.user.UserScreenContract;

/**
 * Created by nikita on 22.07.17.
 */
@InjectViewState
public class UserPresenter extends UserScreenContract.AbstractUserPresenter {

    public UserPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    protected TitleModel getTitle() {
        return null;
    }
}
