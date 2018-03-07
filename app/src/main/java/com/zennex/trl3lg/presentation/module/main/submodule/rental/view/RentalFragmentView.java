package com.zennex.trl3lg.presentation.module.main.submodule.rental.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.rental.RentalScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.rental.presenter.RentalPresenter;

/**
 * Created by nikita on 22.07.17.
 */
@Layout(R.layout.frg_rental_screen)
public class RentalFragmentView extends RentalScreenContract.AbstractRentalView {

    public static final String TAG = "RentalFragmentView";

    @InjectPresenter
    RentalScreenContract.AbstractRentalPresenter mPresenter;


    public static RentalFragmentView newInstance() {
        return new RentalFragmentView();
    }

    @ProvidePresenter
    RentalScreenContract.AbstractRentalPresenter providePresenter() {
        return new RentalPresenter(App.getHasPresenterSubcomponentBuilders(getActivity()));
    }

    @Override
    public void prepareScreen() {

    }

    @NonNull
    @Override
    public RentalScreenContract.AbstractRentalPresenter getPresenter() {
        return mPresenter;
    }

    @NonNull
    @Override
    protected MainScreenContract.AbstractMainRouter getRouter() {
        return ((MainScreenContract.AbstractMainView) getActivity()).getPresenter().getRouter();
    }
}
