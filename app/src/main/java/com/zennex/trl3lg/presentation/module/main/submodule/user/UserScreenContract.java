package com.zennex.trl3lg.presentation.module.main.submodule.user;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.view.ViperBaseFragment;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;

/**
 * Created by nikita on 22.07.17.
 */

public abstract class UserScreenContract {

    private UserScreenContract() {
        throw new RuntimeException("no instance please!");
    }


    public interface IUserView extends IBaseView {


    }

    public static abstract class AbstractUserView extends ViperBaseFragment<AbstractUserPresenter, MainScreenContract.AbstractMainRouter>
            implements IUserView {

    }

    public static abstract class AbstractUserPresenter extends ViperBasePresenter<IUserView, MainScreenContract.AbstractMainRouter> {

        public AbstractUserPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

    }


}
