package com.zennex.trl3lg.presentation.common.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.SupportFragmentRouter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by nikita on 22.07.17.
 */

public abstract class BaseListViperFragment<Presenter extends ViperBasePresenter,
        Router extends SupportFragmentRouter,
        Entity,
        Adapter extends BaseRecyclerViewAdapter<? extends RecyclerView.ViewHolder, Entity>,
        Manager extends RecyclerView.LayoutManager>
        extends ViperBaseFragment<Presenter, Router> {


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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


}
