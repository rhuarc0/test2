package com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view;

import android.app.AlertDialog;
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
import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.OnDemandScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.OnDemandPresenter;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.dragandswipe.ItemTouchHelperListener;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.dragandswipe.OnDemandItemTouchHelperCallback;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.dragandswipe.OnStartDragListener;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.List;

/**
 * Created by nikita on 22.07.17.
 */
public class OnDemandFragmentView extends OnDemandScreenContract.AbstractOnDemandView
        implements OnDemandBookListAdapter.IOnDemandBookListener,
        OnStartDragListener {

    public static final String TAG = "OnDemandFragmentView";

    @InjectPresenter
    OnDemandScreenContract.AbstractOnDemandPresenter mPresenter;

    ItemTouchHelper.Callback mListItemTouchCallback;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelperListener listener;

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
        listener = new ItemTouchHelperListenerImpl();
        mListItemTouchCallback = new OnDemandItemTouchHelperCallback(listener);
        mItemTouchHelper = new ItemTouchHelper(mListItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void showPending(boolean isRefreshing) {
        if (isRefreshing)
            mSwipeRefreshLayout.setRefreshing(true);
        else
            mProgressBar.setVisibility(View.VISIBLE);
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

    //region OnStartDragListener

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    //endregion OnStartDragListener

     //region OnDemandBookListAdapter.IOnDemandBookListener

    @Override
    public void onBookSelected(int position, List<Pair<View, String>> animViews) {
        PairArrayList<View, String> pairs = new PairArrayList<>(animViews);

        View statusBar = getActivity().findViewById(android.R.id.statusBarBackground);
        if (statusBar != null) {
            pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        }

        View navigationBar = getActivity().findViewById(android.R.id.navigationBarBackground);
        if (navigationBar != null) {
            pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }

        View appBar = getActivity().findViewById(R.id.app_bar_layout);
        if (appBar != null) {
            pairs.add(Pair.create(appBar, appBar.getTransitionName()));
        }

        mPresenter.onBookSelected(position, pairs);
    }

    @Override
    public void onBookActivated(int position, String bookTitle) {
        showDialogRentBook(position, bookTitle);
    }

    private void showDialogRentBook(int position, String bookTitle) {
        new AlertDialog.Builder(getContext())
            .setMessage(getString(R.string.ask_rent_book, bookTitle))
            .setPositiveButton(android.R.string.ok, (dialog, which) -> getPresenter().onBookActivated(position))
            .setNegativeButton(getString(android.R.string.cancel), null)
            .create()
            .show();
    }


    //endregion OnDemandBookListAdapter.IOnDemandBookListener

    class ItemTouchHelperListenerImpl implements ItemTouchHelperListener {

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
/*
          if (mAdapter.getLiveQueueItem(toPosition).getBillDate() != null)
                return false;
            Collections.swap(mAdapter.getOnDemandItems(), fromPosition, toPosition);
*/

            mAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemDismiss(RecyclerView.ViewHolder viewHolder) {
/*
            int position = viewHolder.getAdapterPosition();
            LiveQueueItem removeItem = mAdapter.getLiveQueueItem(position);
            mAdapter.removeLiveQueueItem(position);
            mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            AlertDialog.Builder builder = new AlertDialog.Builder(OnDemandActivity.this);
            builder.setMessage(getString(R.string.dialog_message_remove_book));
            builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                if (NetUtils.isNetworkConnected(OnDemandActivity.this)) {
                    showLoadingProgress(getString(R.string.removing_book));
                    removeTrack(removeItem.getId());
                } else {
                    showDialog("Error!", getString(R.string.dlg_message_no_connection));
                    cancelRemove(position, removeItem);
                }

            });
            builder.setNeutralButton(getString(R.string.cancel), (dialog2, which) -> {
                cancelRemove(position, removeItem);
            }).setOnCancelListener(dialog3 -> {
                cancelRemove(position, removeItem);
            });
            builder.create().show();
*/

        }

/*
        private void cancelRemove(int position, LiveQueueItem removeItem) {
            mAdapter.cancelRemove(position, removeItem);
            mAdapter.notifyItemInserted(position);
            mRecyclerView.smoothScrollToPosition(position);
        }
*/


        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public void onDragFinish(int fromPos, int toPos) {
/*
            mAdapter.setLiveQueueItemList(mLiveQueueItems);
            changeLiveItemListOrder(mOnDemandAdapter.getLiveQueueItem(fromPos).getQueueId(),
                    mOnDemandAdapter.getLiveQueueItem(toPos).getQueueId());

*/
        }

/*
        private void changeLiveItemListOrder(String sourceId, String targetid) {
            mBooksLoaderWeb.changeLiveItemListOrder(getDataBaseHelper(), sourceId, targetid)
                    .doOnNext(cacheLiveQueue())
                    .map(filterList())
                    .map(sortList())
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(getNewSubscriberOnGetLiveQueueObservable());
        }
*/

    }

}
