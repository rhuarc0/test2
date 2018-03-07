package com.zennex.trl3lg.presentation.common.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.BaseRouter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by nikit on 01.08.2017.
 */

public abstract class BaseListActivity<Presenter extends ViperBasePresenter,
        Router extends BaseRouter,
        Entity,
        Adapter extends BaseRecyclerViewAdapter<? extends RecyclerView.ViewHolder, Entity>,
        Manager extends RecyclerView.LayoutManager>
        extends ViperBaseActivity<Presenter, Router> {


    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar)
    protected ProgressBar mProgressBar;
    @BindView(R.id.tv_empty_message)
    protected TextView mTvEmptyMessage;

    protected Adapter mAdapter;
    protected Manager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    protected void initViews() {
        mSwipeRefreshLayout.setOnRefreshListener(this::onRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mManager = createLayoutManager();
        mRecyclerView.setLayoutManager(mManager);
        mAdapter = createAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public void showData(List<Entity> models) {
        if (mAdapter == null) mAdapter = createAdapter();
        mAdapter.updateList(models);
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    protected abstract void onRefreshLayout();

    @NonNull
    protected abstract Adapter createAdapter();

    @NonNull
    protected abstract Manager createLayoutManager();


    @Override
    public void showPending(String message) {
        mProgressBar.setVisibility(mSwipeRefreshLayout.isRefreshing() ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showPending(@StringRes int id) {
        mProgressBar.setVisibility(mSwipeRefreshLayout.isRefreshing() ? View.GONE : View.VISIBLE);
    }

    @Override
    public void hidePending() {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
