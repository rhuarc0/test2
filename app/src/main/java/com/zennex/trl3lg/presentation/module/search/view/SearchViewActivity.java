package com.zennex.trl3lg.presentation.module.search.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Pair;
import android.view.View;
import android.view.Window;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.common.view.BaseListActivity;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.search.SearchScreenContract;
import com.zennex.trl3lg.presentation.module.search.presenter.SearchPresenter;
import com.zennex.trl3lg.presentation.module.search.router.SearchScreenRouter;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import org.cryse.widget.persistentsearch.PersistentSearchView;

import java.util.List;

import butterknife.BindView;

@Layout(R.layout.act_search_layout)
public class SearchViewActivity extends BaseListActivity<
        SearchScreenContract.AbstractSearchPresenter,
        SearchScreenContract.AbstractSearchScreenRouter,
        Book,
        SearchListRecyclerAdapter,
        StaggeredGridLayoutManager>
        implements SearchScreenContract.ISearchView,
        SearchListRecyclerAdapter.ISearchBookListAdapterListener {

    public static final String TAG = "SearchViewActivity";

    private static String EXTRA_RENTAL_GROUP_ID_KEY = "rental_group_id";
    private static String EXTRA_KEYWORD_KEY = "keyword";

    public static Intent newIntent(Context context, long rentalGroupId, String keyword) {
        Intent intent = new Intent(context, SearchViewActivity.class);
        intent.putExtra(EXTRA_KEYWORD_KEY, keyword);
        intent.putExtra(EXTRA_RENTAL_GROUP_ID_KEY, rentalGroupId);
        return intent;
    }

    public static Intent newIntent(Context context, String keyword) {
        Intent intent = new Intent(context, SearchViewActivity.class);
        intent.putExtra(EXTRA_KEYWORD_KEY, keyword);
        return intent;
    }

    @InjectPresenter
    SearchScreenContract.AbstractSearchPresenter mPresenter;

    @ProvidePresenter
    SearchScreenContract.AbstractSearchPresenter providePresenter() {
        return new SearchPresenter(App.getHasPresenterSubcomponentBuilders(this));
    }

    @BindView(R.id.act_search_searchview)
    PersistentSearchView mSearchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseExtraData();
    }

    @Override
    protected SearchScreenContract.AbstractSearchScreenRouter resolveRouter() {
        return new SearchScreenRouter();
    }

    @Override
    protected void initViews() {
        super.initViews();
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mSearchView.setSearchButtonListener(query -> mPresenter.onClickedBtnSearch(query));
        mSearchView.setHomeButtonListener(() -> mPresenter.onBackPressed());
    }

    private void parseExtraData() {
        mPresenter.setKeywordSearch(getIntent().getStringExtra(EXTRA_KEYWORD_KEY));
        mPresenter.setRentalGroupId(String.valueOf(getIntent().getLongExtra(EXTRA_RENTAL_GROUP_ID_KEY, -1)));
    }

    @Override
    public void prepareScreen() {

    }

    @Override
    protected void onRefreshLayout() {
        mPresenter.onRefreshView();
    }

    @NonNull
    @Override
    protected SearchListRecyclerAdapter createAdapter() {
        SearchListRecyclerAdapter adapter = new SearchListRecyclerAdapter();
        adapter.setSearchBookListAdapterListener(this);
        return adapter;
    }

    @NonNull
    @Override
    public SearchScreenContract.AbstractSearchPresenter getPresenter() {
        return mPresenter;
    }


    @NonNull
    @Override
    protected StaggeredGridLayoutManager createLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    public void showBooks(@NonNull List<Book> bookList) {
        mAdapter.updateList(bookList);
    }

    @Override
    public void clearData() {
        mAdapter.clearList();
    }

    //region  RecyclerView.OnScrollListener

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            int[] lastVisibleBooksPositions = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager())
                    .findLastVisibleItemPositions(new int[2]);
            mPresenter.onBooksScrolled(lastVisibleBooksPositions[1]);
        }
    };

    //endregion  RecyclerView.OnScrollListener


    @Override
    public void hidePendingLoadBooks(boolean isRefreshing) {
        if (isRefreshing) {
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void showPendingLoadBooks(boolean isRefreshing) {
        if (isRefreshing) {
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setEnabled(false);
        }
    }

    @Override
    public void showFooterPendingLoadBooks() {
        mAdapter.showPendingFooter();
        }


    @Override
    public void hideFooterPendingLoadBooks() {
        mAdapter.hidePendingFooter();
    }

    //region SearchListRecyclerAdapter.ISearchBookListAdapterListener

    @Override
    public void onBookSelected(int position, List<Pair<View, String>> animateViews) {

        View statusBar = findViewById(android.R.id.statusBarBackground);
        if (statusBar != null) {
            animateViews.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        }

        View navigationBar = findViewById(android.R.id.navigationBarBackground);
        if (navigationBar != null) {
            animateViews.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }

        View appBar = findViewById(R.id.app_bar_layout);
        if (appBar != null) {
            animateViews.add(Pair.create(appBar, appBar.getTransitionName()));
        }

        mPresenter.onBookSelected(position, new PairArrayList<>(animateViews));
    }

    //endregion SearchListRecyclerAdapter.ISearchBookListAdapterListener
}
