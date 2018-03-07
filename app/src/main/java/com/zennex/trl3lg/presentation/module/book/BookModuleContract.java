package com.zennex.trl3lg.presentation.module.book;

import android.support.annotation.NonNull;
import android.view.View;

import com.zennex.trl3lg.data.entity.Book;
import com.zennex.trl3lg.data.entity.Review;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.SupportFragmentRouter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.common.view.ViperBaseActivity;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.List;

/**
 * Created by nikit on 02.09.2017.
 */

public abstract class BookModuleContract {

    private BookModuleContract() {

    }

    public interface IBookView extends IBaseView {

        void showBook(Book book);

        void showPendingLoadReviews();

        void hidePendingLoadReviews();

        void showEmptyViewReviews();

        void showReviews(List<Review> reviews);

        void showMyReviewSendScreen();

    }

    public static abstract class AbstractBookView extends ViperBaseActivity<BookModuleContract.AbstractBookPresenter, AbstractBookRouter>
            implements BookModuleContract.IBookView {


    }

    public static abstract class AbstractBookPresenter extends ViperBasePresenter<IBookView, AbstractBookRouter> {

        protected Book mBook;

        public AbstractBookPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public abstract void onClickBtnReadMoreDescription(PairArrayList<View, String> animViews);

        public abstract void onClickImageBook(PairArrayList<View, String> animViews);

        public abstract void onClickReadAllRevews(PairArrayList<View, String> animViews);

        public abstract void onClickNextRateBook();

        public abstract void onClickSendReview();

        public Book getBook() {
            return mBook;
        }

        public void setBook(Book book) {
            mBook = book;
        }
    }

    public static abstract class AbstractBookRouter extends SupportFragmentRouter<SupportFragmentRouter.SupportFragmentRouterAdapter> {


        public abstract void showBookDescriptionScreen(String bookTitle, String bookDescription, PairArrayList<View, String> animViews);

        public abstract void showBookImageZoomScreen(String bookTitle, String imagePath, PairArrayList<View, String> animViews);

        public abstract void showAllReviewsScreen(String bookTitle, String bookId, PairArrayList<View, String> animViews);

    }

}
