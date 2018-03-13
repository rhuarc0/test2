package com.zennex.trl3lg.presentation.module.review.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.common.view.BaseListActivity;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.review.ReviewsModuleContract;
import com.zennex.trl3lg.presentation.module.review.presenter.ReviewsPresenter;
import com.zennex.trl3lg.presentation.module.review.router.ReviewsRouter;

import java.util.List;

import butterknife.BindView;

@Layout(R.layout.act_reviews_layout)
public class ReviewsViewActivity extends BaseListActivity<ReviewsModuleContract.AbstractReviewsPresenter,
        ReviewsModuleContract.AbstractReviewsRouter,
        Review,
        ReviewsModuleRecyclerAdapter,
        LinearLayoutManager>
        implements ReviewsModuleContract.IReviewsView {

    public static final String EXTRA_BOOK_TITLE = "book_title";
    public static final String EXTRA_BOOK_ID = "book_id";

    @InjectPresenter
    ReviewsModuleContract.AbstractReviewsPresenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.act_reviews_nested_scroll_view)
    NestedScrollView mScrollView;
    @BindView(R.id.act_reviews_recycler_review_list_pending_footer)
    View mRecyclerReviewListPendingFooter;

    public static Intent newIntent(Context context, String bookTitle, String bookId) {
        Intent intent = new Intent(context, ReviewsViewActivity.class);
        intent.putExtra(EXTRA_BOOK_ID, bookId);
        intent.putExtra(EXTRA_BOOK_TITLE, bookTitle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        resolveExtras();
    }

    @Override
    public void prepareScreen() {
        super.prepareScreen();
        mRecyclerReviewListPendingFooter.setVisibility(View.GONE);
    }

    @Override
    protected void initViews() {
        super.initViews();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setNestedScrollingEnabled(false);
        mScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, i, i1, i2, i3) -> {
            mPresenter.onScrolledReviews(((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findLastVisibleItemPosition());
        });
    }

    public void resolveExtras() {
        mPresenter.setBookTitle(getIntent().getStringExtra(EXTRA_BOOK_TITLE));
        mPresenter.setBookId(getIntent().getStringExtra(EXTRA_BOOK_ID));
    }

    @ProvidePresenter
    ReviewsModuleContract.AbstractReviewsPresenter providePresenter() {
        return new ReviewsPresenter(App.getHasPresenterSubcomponentBuilders(this));
    }

    @Override
    protected ReviewsModuleContract.AbstractReviewsRouter resolveRouter() {
        return new ReviewsRouter();
    }

    @NonNull
    @Override
    public ReviewsModuleContract.AbstractReviewsPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onRefreshLayout() {
        mPresenter.onRefreshView();
    }

    @NonNull
    @Override
    protected ReviewsModuleRecyclerAdapter createAdapter() {
        return new ReviewsModuleRecyclerAdapter();
    }

    @NonNull
    @Override
    protected LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void setVisibilityBackButton(boolean value) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(value);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                return mPresenter.onBackPressed();
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void setTitleText(String title) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
    }

    @Override
    public void showReviews(List<Review> reviews) {
        mAdapter.updateList(reviews);
    }

    @Override
    public void showEmptyViewReviews() {

    }

    @Override
    public void hideFooterPendingLoadReviews() {
        YoYo.with(Techniques.SlideOutDown)
                .delay(500)
                .withListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mRecyclerReviewListPendingFooter.setVisibility(View.GONE);
                    }
                })
                .playOn(mRecyclerReviewListPendingFooter);
    }

    @Override
    public void hidePendingLoadReviews(boolean isRefreshing) {
        if (isRefreshing) {
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mSwipeRefreshLayout.setEnabled(true);
        }
    }

    @Override
    public void showFooterPendingLoadReviews() {
        mRecyclerReviewListPendingFooter.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.SlideInDown).delay(500).playOn(mRecyclerReviewListPendingFooter);
    }

    @Override
    public void showPendingLoadReviews(boolean isRefreshing) {
        if (isRefreshing) {
            mSwipeRefreshLayout.setRefreshing(true);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mSwipeRefreshLayout.setEnabled(false);
        }
    }
}
