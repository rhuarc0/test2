package com.zennex.trl3lg.presentation.module.main.submodule.rental;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.view.ViperBaseFragment;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;

/**
 * Created by nikita on 22.07.17.
 */

public abstract class RentalScreenContract {

    private RentalScreenContract() {
        throw new RuntimeException("no instance please!");
    }


    public interface IRentalView extends IBaseView {


    }

    public static abstract class AbstractRentalView extends ViperBaseFragment<AbstractRentalPresenter, MainScreenContract.AbstractMainRouter>
            implements IRentalView {

    }

    public static abstract class AbstractRentalPresenter extends ViperBasePresenter<IRentalView, MainScreenContract.AbstractMainRouter> {

        public AbstractRentalPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

    }


}
