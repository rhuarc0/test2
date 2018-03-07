package com.zennex.trl3lg.presentation.module.review.router;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zennex.trl3lg.presentation.module.review.ReviewsModuleContract;

/**
 * Created by nikita on 22.10.17.
 */

public class ReviewsRouter extends ReviewsModuleContract.AbstractReviewsRouter {
    @Override
    public BaseRouterAdapter createAdapter(@NonNull AppCompatActivity appCompatActivity) {
        return new BaseRouterAdapter(appCompatActivity) {
            @Override
            public void finish() {
                mActivity.finishAfterTransition();
            }
        };
    }


}
