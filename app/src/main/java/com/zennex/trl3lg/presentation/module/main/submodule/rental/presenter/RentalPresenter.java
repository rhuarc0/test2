package com.zennex.trl3lg.presentation.module.main.submodule.rental.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.main.submodule.rental.RentalScreenContract;

/**
 * Created by nikita on 22.07.17.
 */
@InjectViewState
public class RentalPresenter extends RentalScreenContract.AbstractRentalPresenter {

    public RentalPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    protected TitleModel getTitle() {
        return null;
    }
}
