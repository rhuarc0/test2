package com.zennex.trl3lg.presentation.module.book.presenter;

import android.support.annotation.NonNull;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.data.entity.Review;
import com.zennex.trl3lg.domain.common.DefaultObserver;
import com.zennex.trl3lg.domain.review.FetchReviewsInteractor;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.book.BookModuleContract;
import com.zennex.trl3lg.presentation.module.book.presenter.assembly.BookPresenterModule;
import com.zennex.trl3lg.presentation.module.book.presenter.assembly.IBookPresenterSubcomponent;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nikit on 03.09.2017.
 */

@InjectViewState
public class BookPresenter extends BookModuleContract.AbstractBookPresenter {

    @Inject
    FetchReviewsInteractor mFetchReviewsInteractor;

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
        mFetchReviewsInteractor.execute(new FetchReviewsObserver(), new FetchReviewsInteractor.Params(3, 0, mBook.getId()));
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

    private class FetchReviewsObserver extends DefaultObserver<List<Review>> {

        @Override
        protected void onStart() {
            getViewState().showPendingLoadReviews();
        }

        @Override
        public void onNext(List<Review> reviews) {
            getViewState().hidePendingLoadReviews();
            if (reviews.isEmpty()) getViewState().showEmptyViewReviews();
            else getViewState().showReviews(reviews);
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


}
