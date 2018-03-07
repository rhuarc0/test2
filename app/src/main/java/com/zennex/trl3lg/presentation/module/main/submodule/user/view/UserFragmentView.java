package com.zennex.trl3lg.presentation.module.main.submodule.user.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.user.UserScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.UserPresenter;

/**
 * Created by nikita on 22.07.17.
 */
@Layout(R.layout.frg_user_layout)
public class UserFragmentView extends UserScreenContract.AbstractUserView {

    public static final String TAG = "UserFragmentView";

    @InjectPresenter
    UserScreenContract.AbstractUserPresenter mPresenter;


    public static UserFragmentView newInstance() {
        return new UserFragmentView();
    }

    @ProvidePresenter
    UserScreenContract.AbstractUserPresenter providePresenter() {
        return new UserPresenter(App.getHasPresenterSubcomponentBuilders(getActivity()));
    }

    @Override
    public void prepareScreen() {

    }

    @NonNull
    @Override
    public UserScreenContract.AbstractUserPresenter getPresenter() {
        return mPresenter;
    }

    @NonNull
    @Override
    protected MainScreenContract.AbstractMainRouter getRouter() {
        return ((MainScreenContract.AbstractMainView) getActivity()).getPresenter().getRouter();
    }
}
