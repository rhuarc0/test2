package com.zennex.trl3lg.presentation.module.review.presenter.assembly;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponent;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.PresenterComponentBuilder;
import com.zennex.trl3lg.presentation.common.di.scope.PresenterScope;
import com.zennex.trl3lg.presentation.module.review.presenter.ReviewsPresenter;

import dagger.Subcomponent;

/**
 * Created by nikita on 22.10.17.
 */
@PresenterScope
@Subcomponent(modules = ReviewsPresenterModule.class)
public interface IReviewsPresenterSubmodule extends PresenterComponent<ReviewsPresenter> {

    @Subcomponent.Builder
    interface Builder extends PresenterComponentBuilder<ReviewsPresenterModule, IReviewsPresenterSubmodule> {

    }

}
