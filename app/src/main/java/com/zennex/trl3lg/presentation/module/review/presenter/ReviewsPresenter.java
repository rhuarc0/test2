package com.zennex.trl3lg.presentation.module.review.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.domain.usecases.common.DefaultObserver;
import com.zennex.trl3lg.domain.usecases.review.FetchReviews;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.review.ReviewsModuleContract;
import com.zennex.trl3lg.presentation.module.review.presenter.assembly.IReviewsPresenterSubmodule;
import com.zennex.trl3lg.presentation.module.review.presenter.assembly.ReviewsPresenterModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class ReviewsPresenter extends ReviewsModuleContract.AbstractReviewsPresenter {

    private final short QUANTITY_REVIEWS_REQUESTED = 15;

    @Inject
    FetchReviews mFetchReviews;
    private List<Review> mReviews;
    private boolean mAllReviewsUploaded = false;

    public ReviewsPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    public boolean isAllReviewsUploaded() {
        return mAllReviewsUploaded;
    }

    @Override
    public void onScrolledReviews(int lastVisibleReviewPosition) {
        if (isCanLoadReviews(lastVisibleReviewPosition)) {
            mFetchReviews.execute(new FetchReviewsObserver(true, false),
                    new FetchReviews.Params(QUANTITY_REVIEWS_REQUESTED, mReviews.size(), mBookId));
        }
    }

    @Override
    public void onRefreshView() {
        mFetchReviews.execute(new FetchReviewsObserver(false, true),
                new FetchReviews.Params(QUANTITY_REVIEWS_REQUESTED, 0, mBookId));
        mAllReviewsUploaded = false;
        if (mReviews != null) mReviews.clear();
    }

    private boolean isCanLoadReviews(int lastVisibleReviewPosition) {
        return !mFetchReviews.isRun() &&
                ((mReviews.size() - lastVisibleReviewPosition) <= 5) &&
                !isAllReviewsUploaded();
    }

    @Override
    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        ((IReviewsPresenterSubmodule.Builder) presenterSubcomponentBuilders.getPresenterComponentBuilder(ReviewsPresenter.class))
                .presenterModule(new ReviewsPresenterModule(this))
                .build()
                .injectMembers(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mFetchReviews.execute(new FetchReviewsObserver(false, false),
                new FetchReviews.Params(QUANTITY_REVIEWS_REQUESTED, 0, mBookId));
    }

    @Override
    protected TitleModel getTitle() {
        return new TitleModel.Builder()
                .setTitleMessage(mBookTitle)
                .setVisibleBackButton(true)
                .build();
    }

    private class FetchReviewsObserver extends DefaultObserver<List<Review>> {

        private final boolean mLoadFromUserScrolled;
        private final boolean mLoadFromRefreshing;

        private FetchReviewsObserver(boolean loadFromUserScrolled, boolean loadFromRefreshing) {
            mLoadFromUserScrolled = loadFromUserScrolled;
            mLoadFromRefreshing = loadFromRefreshing;
        }

        @Override
        protected void onStart() {
            super.onStart();
            if (isLoadFromUserScrolled()) getViewState().showFooterPendingLoadReviews();
            else getViewState().showPendingLoadReviews(isLoadFromRefreshing());
        }

        @Override
        public void onNext(List<Review> reviews) {
            super.onNext(reviews);
            if (reviews.size() < QUANTITY_REVIEWS_REQUESTED) mAllReviewsUploaded = true;
            if (mReviews == null) mReviews = new ArrayList<>(reviews);
            else mReviews.addAll(reviews);
            if (reviews.isEmpty()) getViewState().showEmptyViewReviews();
            else getViewState().showReviews(new ArrayList<>(mReviews));
            hidePending();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            hidePending();
        }

        @Override
        protected String getTag() {
            return "FetchReviewsObserver";
        }

        private void hidePending() {
            if (isLoadFromUserScrolled()) getViewState().hideFooterPendingLoadReviews();
            else getViewState().hidePendingLoadReviews(isLoadFromRefreshing());
        }

        public boolean isLoadFromUserScrolled() {
            return mLoadFromUserScrolled;
        }

        public boolean isLoadFromRefreshing() {
            return mLoadFromRefreshing;
        }

    }

}
