package com.zennex.trl3lg.presentation.module.book.presenter;

import android.support.annotation.NonNull;
import android.util.Pair;
import android.view.View;

import com.annimon.stream.Stream;
import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.domain.usecases.common.DefaultObserver;
import com.zennex.trl3lg.domain.usecases.review.FetchReviews;
import com.zennex.trl3lg.domain.usecases.review.SetReviewUseful;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.book.BookModuleContract;
import com.zennex.trl3lg.presentation.module.book.presenter.assembly.BookPresenterModule;
import com.zennex.trl3lg.presentation.module.book.presenter.assembly.IBookPresenterSubcomponent;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by nikit on 03.09.2017.
 */

@InjectViewState
public class BookPresenter extends BookModuleContract.AbstractBookPresenter {

    private final static int REVIEW_COUNT = 3;

    @Inject
    FetchReviews mFetchReviewsUseCase;

    @Inject
    SetReviewUseful setReviewUseful;

    List<Review> reviews = new ArrayList<>();

    public BookPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        ((IBookPresenterSubcomponent.Builder) presenterSubcomponentBuilders.getPresenterComponentBuilder(BookPresenter.class))
                .presenterModule(new BookPresenterModule(this))
                .build()
                .injectMembers(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().showBook(mBook);
        fetchReviews();
    }

    private void fetchReviews() {
        mFetchReviewsUseCase.execute(new FetchReviewsObserver(),
                new FetchReviews.Params(REVIEW_COUNT, 0, mBook.getId()));
    }

    @Override
    public void onClickBtnReadMoreDescription(PairArrayList<View, String> animViews) {
        mRouter.showBookDescriptionScreen(mBook.getTitle(), mBook.getDescr(), animViews);
    }

    @Override
    public void onClickImageBook(PairArrayList<View, String> animViews) {
        mRouter.showBookImageZoomScreen(mBook.getTitle(), mBook.getImage(), animViews);
    }

    @Override
    protected TitleModel getTitle() {
        return new TitleModel.Builder()
                .setTitleMessage(mBook.getTitle())
                .setVisibleBackButton(true)
                .build();
    }

    @Override
    public void onClickReadAllRevews(PairArrayList<View, String> animViews) {
        mRouter.showAllReviewsScreen(mBook.getTitle(), mBook.getId(), animViews);
    }

    @Override
    public void onClickNextRateBook() {
        getViewState().showMyReviewSendScreen();
    }

    @Override
    public void onClickSendReview() {

    }

    @Override
    public void onReviewRateButtonClicked(String reviewId, boolean isUseful) {
        setReviewUseful.execute(new SetReviewUsefulObserver(), new SetReviewUseful.Params(reviewId, isUseful));
    }

    private class FetchReviewsObserver extends DefaultObserver<List<Review>> {

        @Override
        protected void onStart() {
            getViewState().showPendingLoadReviews();
        }

        @Override
        public void onNext(List<Review> reviews) {
            getViewState().hidePendingLoadReviews();
            BookPresenter.this.reviews = reviews;
            if (reviews.isEmpty())
                getViewState().showEmptyViewReviews();
            else
                getViewState().showReviews(reviews);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePendingLoadReviews();
        }

        @Override
        protected String getTag() {
            return "FetchReviewsObserver";
        }
    }

    private class SetReviewUsefulObserver extends DefaultObserver<Pair<String, Boolean>> {

        private SetReviewUsefulObserver() {
        }

        @Override
        public void onNext(Pair<String, Boolean> idAndStatus) {
            super.onNext(idAndStatus);

            Stream.of(reviews)
                    .filter(review -> review.getId().equals(idAndStatus.first))
                    .forEach(review -> review.setRated(idAndStatus.second));
            getViewState().showReviews(reviews);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        protected String getTag() {
            return SetReviewUsefulObserver.class.getSimpleName();
        }
    }

}
