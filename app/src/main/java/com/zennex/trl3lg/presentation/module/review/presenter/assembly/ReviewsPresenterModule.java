package com.zennex.trl3lg.presentation.module.review.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterModule;
import com.zennex.trl3lg.presentation.module.review.presenter.ReviewsPresenter;

import dagger.Module;

/**
 * Created by nikita on 22.10.17.
 */
@Module
public class ReviewsPresenterModule extends PresenterModule<ReviewsPresenter> {

    public ReviewsPresenterModule(ReviewsPresenter reviewsPresenter) {
        super(reviewsPresenter);
    }

}
