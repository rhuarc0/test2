package com.zennex.trl3lg.presentation.module.book.submodule.myreview.rating.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.book.submodule.myreview.rating.MyRatingModuleContract;

/**
 * Created by nikita on 28.09.17.
 */
@InjectViewState
public class MyRatingPresenter extends MyRatingModuleContract.AbstractMyRatingPresenter {

    private final float NOT_VALUE = 0.0f;
    private final float HATE = 1.0f;
    private final float DISLIKE = 2.0f;
    private final float OK = 3.0f;
    private final float LIKE = 4.0f;
    private final float LOVE = 5.0f;

    private boolean preapreForNextStep = false;
    private float ratingValue;

    public MyRatingPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    protected TitleModel getTitle() {
        return null;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().cancelPrepareViewForNextStep();
    }

    @Override
    public void onChangeRating(float newValue) {
        ratingValue = newValue;
        if (!preapreForNextStep) {
            getViewState().prepareViewForNextStep();
            preapreForNextStep = true;
        }

        if (newValue == HATE) getViewState().showRatingDescription("Hated it");
        else if (newValue == DISLIKE) getViewState().showRatingDescription("Disliked it");
        else if (newValue == OK) getViewState().showRatingDescription("It's OK");
        else if (newValue == LIKE) getViewState().showRatingDescription("Liked it");
        else if (newValue == LOVE) getViewState().showRatingDescription("Loved it");
        else if (newValue == NOT_VALUE) {
            getViewState().cancelPrepareViewForNextStep();
            preapreForNextStep = false;
        }
    }

    @Override
    public void onClickBtnNext() {
        if (mReviewRatingEventListener != null) mReviewRatingEventListener.onClickNext(ratingValue);
    }
}
