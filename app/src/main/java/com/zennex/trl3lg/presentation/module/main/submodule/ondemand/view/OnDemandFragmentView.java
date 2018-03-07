package com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.data.entity.AudioBook;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.OnDemandScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.OnDemandPresenter;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.drugandswipe.ItemTouchHelperListener;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.drugandswipe.OnDemandItemTouchHelperCallback;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.drugandswipe.OnStartDrugListener;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.List;

/**
 * Created by nikita on 22.07.17.
 */
public class OnDemandFragmentView extends OnDemandScreenContract.AbstractOnDemandView
        implements OnDemandBookListAdapter.IOnDemandBookListener,
        OnStartDrugListener,
        ItemTouchHelperListener {

    public static final String TAG = "OnDemandFragmentView";

    @InjectPresenter
    OnDemandScreenContract.AbstractOnDemandPresenter mPresenter;

    ItemTouchHelper.Callback mListItemTouchCallback;
    private ItemTouchHelper mItemTouchHelper;

    public static OnDemandFragmentView newInstance() {
        return new OnDemandFragmentView();
    }

    @ProvidePresenter
    OnDemandScreenContract.AbstractOnDemandPresenter providePresenter() {
        return new OnDemandPresenter(App.getHasPresenterSubcomponentBuilders(getActivity()));
    }

    @Override
    public void prepareScreen() {

    }

    @Override
    protected void initViews() {
        super.initViews();
        mAdapter.setOnClickItemListener(this);
        mListItemTouchCallback = new OnDemandItemTouchHelperCallback(this);
        mItemTouchHelper = new ItemTouchHelper(mListItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void showPending(boolean isRefreshing) {
        if (isRefreshing) mSwipeRefreshLayout.setRefreshing(true);
        else mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePending() {
        mSwipeRefreshLayout.setRefreshing(false);
        mProgressBar.setVisibility(View.GONE);
    }

    @NonNull
    @Override
    public OnDemandScreenContract.AbstractOnDemandPresenter getPresenter() {
        return mPresenter;
    }

    @NonNull
    @Override
    protected MainScreenContract.AbstractMainRouter getRouter() {
        return ((MainScreenContract.AbstractMainView) getActivity()).getPresenter().getRouter();
    }

    @Override
    protected void onRefreshLayout() {
        mPresenter.onRefreshBooks();
    }

    @NonNull
    @Override
    protected OnDemandBookListAdapter createAdapter() {
        return new OnDemandBookListAdapter(this);
    }

    @NonNull
    @Override
    protected LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void showBooks(List<AudioBook> audioBooks) {
        showData(audioBooks);
    }

    //region OnStartDrugListener

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    //endregion OnStartDrugListener

    //region ItemTouchHelperListener

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        mAdapter.notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public void onDragFinish(int fromPos, int toPos) {

    }

    //endregion ItemTouchHelperListener


    //region OnDemandBookListAdapter.IOnDemandBookListener

    @Override
    public void onBookSelected(int position, List<Pair<View, String>> animViews) {
        PairArrayList<View, String> pairs = new PairArrayList<>(animViews);
        View statusBar = getActivity().findViewById(android.R.id.statusBarBackground);
        View navigationBar = getActivity().findViewById(android.R.id.navigationBarBackground);
        View appBar = getActivity().findViewById(R.id.app_bar_layout);
        if (statusBar != null) {
            pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        }
        if (navigationBar != null) {
            pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }
        if (appBar != null) pairs.add(Pair.create(appBar, appBar.getTransitionName()));
        mPresenter.onBookSelected(position, pairs);
    }

    //endregion OnDemandBookListAdapter.IOnDemandBookListener
}
