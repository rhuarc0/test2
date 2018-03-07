package com.zennex.trl3lg.presentation.module.main.submodule.catalog.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.data.entity.RentalGroup;
import com.zennex.trl3lg.presentation.common.view.IOnClickItemListener;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.CatalogScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.CatalogPresenter;

import java.util.List;


public class CatalogFragmentView extends CatalogScreenContract.AbstractCatalogView
        implements  IOnClickItemListener {

    public static final String TAG = "CatalogFragmentView";

    private CatalogScreenContract.ICatalogEventListener mCatalogEventListener;


    @InjectPresenter
    CatalogScreenContract.AbstractCatalogPresenter mPresenter;

    public static CatalogFragmentView newInstance() {
        return new CatalogFragmentView();
    }

    @ProvidePresenter
    CatalogScreenContract.AbstractCatalogPresenter providePresenter() {
        return new CatalogPresenter(App.getHasPresenterSubcomponentBuilders(getActivity()));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCatalogEventListener = (CatalogScreenContract.ICatalogEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement CatalogScreenContract.ICatalogEventListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            try {
                mCatalogEventListener = (CatalogScreenContract.ICatalogEventListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " must implement CatalogScreenContract.ICatalogEventListener");
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setCatalogEventListener(mCatalogEventListener);
    }

    @Override
    public void prepareScreen() {

    }


    @Override
    protected void initViews() {
        super.initViews();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), mManager.getOrientation()));
    }

    @NonNull
    @Override
    public CatalogScreenContract.AbstractCatalogPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onRefreshLayout() {
        getPresenter().onRefreshLayout();
    }

    @NonNull
    @Override
    protected CatalogRecyclerViewAdapter createAdapter() {
        CatalogRecyclerViewAdapter adapter = new CatalogRecyclerViewAdapter();
        adapter.setClickItemListener(this);
        return adapter;
    }

    @NonNull
    @Override
    protected LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void showCatalog(List<RentalGroup> rentalGroups) {
        showData(rentalGroups);
    }


    //region IOnClickItemListener

    @Override
    public void onClickItem(int position) {
            mPresenter.onSelectedRentalGroup(position);
    }


    //endregion IOnClickItemListener

}
