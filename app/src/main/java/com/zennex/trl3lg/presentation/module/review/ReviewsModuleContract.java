package com.zennex.trl3lg.presentation.module.review;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.Review;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.BaseRouter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;

import java.util.List;

/**
 * Created by nikita on 22.10.17.
 */

public abstract class ReviewsModuleContract {

    private ReviewsModuleContract() {
        throw new RuntimeException(" no instance please!");
    }


    public interface IReviewsView extends IBaseView {

        void showReviews(List<Review> reviews);

        void showEmptyViewReviews();

        void hideFooterPendingLoadReviews();

        void hidePendingLoadReviews(boolean isRefreshing);

        void showFooterPendingLoadReviews();

        void showPendingLoadReviews(boolean isRefreshing);
    }

    public abstract static class AbstractReviewsPresenter extends ViperBasePresenter<IReviewsView, AbstractReviewsRouter> {

        protected String mBookTitle;
        protected String mBookId;

        public AbstractReviewsPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public String getBookTitle() {
            return mBookTitle;
        }

        public void setBookTitle(String bookTitle) {
            mBookTitle = bookTitle;
        }

        public String getBookId() {
            return mBookId;
        }

        public void setBookId(String bookId) {
            mBookId = bookId;
        }

        public abstract void onScrolledReviews(int lastVisibleReviewPosition);

        public abstract void onRefreshView();

    }


    public static abstract class AbstractReviewsRouter extends BaseRouter<BaseRouter.BaseRouterAdapter> {

    }

}


