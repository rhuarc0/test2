package com.zennex.trl3lg.presentation.module.book.submodule.myreview.rating;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.common.view.ViperBaseFragment;
import com.zennex.trl3lg.presentation.module.book.BookModuleContract;

/**
 * Created by nikita on 28.09.17.
 */

public abstract class MyRatingModuleContract {

    private MyRatingModuleContract() {
    }

    public interface IMyReviewRatingEventListener {
        void onClickNext(float valueRating);
    }

    public interface IMyRatingBookView extends IBaseView {

        void showRatingDescription(String text);

        void prepareViewForNextStep();

        void cancelPrepareViewForNextStep();

    }

    public static abstract class AbstractMyRatingViewFragment extends ViperBaseFragment<AbstractMyRatingPresenter, BookModuleContract.AbstractBookRouter>
            implements IMyRatingBookView {

    }

    public static abstract class AbstractMyRatingPresenter extends ViperBasePresenter<IMyRatingBookView, BookModuleContract.AbstractBookRouter> {

        protected MyRatingModuleContract.IMyReviewRatingEventListener mReviewRatingEventListener;

        public AbstractMyRatingPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public IMyReviewRatingEventListener getReviewRatingEventListener() {
            return mReviewRatingEventListener;
        }

        public void setReviewRatingEventListener(IMyReviewRatingEventListener reviewRatingEventListener) {
            mReviewRatingEventListener = reviewRatingEventListener;
        }

        public abstract void onChangeRating(float newValue);

        public abstract void onClickBtnNext();

    }

}
