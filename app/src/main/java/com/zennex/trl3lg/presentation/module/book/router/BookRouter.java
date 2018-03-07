package com.zennex.trl3lg.presentation.module.book.router;

import android.app.ActivityOptions;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zennex.trl3lg.presentation.module.book.BookModuleContract;
import com.zennex.trl3lg.presentation.module.bookdescription.view.BookDescriptionActivity;
import com.zennex.trl3lg.presentation.module.bookimagezoom.view.BookImageZoomActivity;
import com.zennex.trl3lg.presentation.module.review.view.ReviewsViewActivity;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

/**
 * Created by nikit on 03.09.2017.
 */

public class BookRouter extends BookModuleContract.AbstractBookRouter {

    @Override
    public void showBookDescriptionScreen(String bookTitle, String bookDescription, PairArrayList<View, String> animViews) {
        if (mAdapter != null) {
            mAdapter.navigateToActivity(BookDescriptionActivity.newIntent(mAdapter.getActivity(),
                    bookTitle, bookDescription),
                    ActivityOptions.makeSceneTransitionAnimation(mAdapter.getActivity(), animViews.toArray()).toBundle());
        }
    }

    @Override
    public void showBookImageZoomScreen(String bookTitle, String imagePath, PairArrayList<View, String> animViews) {
        if (mAdapter != null) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mAdapter.getActivity(), animViews.toArray());
            mAdapter.navigateToActivity(BookImageZoomActivity.newIntent(mAdapter.getActivity(), bookTitle, imagePath), options.toBundle());
        }
    }

    @Override
    public SupportFragmentRouterAdapter createAdapter(@NonNull AppCompatActivity appCompatActivity) {
        return new SupportFragmentRouterAdapter(appCompatActivity) {
            @Override
            public void finish() {
                mActivity.finishAfterTransition();
            }
        };
    }

    @Override
    public void showAllReviewsScreen(String bookTitle, String bookId, PairArrayList<View, String> animViews) {
        if (mAdapter != null) {
            mAdapter.navigateToActivity(ReviewsViewActivity.newIntent(mAdapter.getActivity(), bookTitle, bookId),
                    ActivityOptions.makeSceneTransitionAnimation(mAdapter.getActivity(), animViews.toArray()).toBundle());
        }
    }
}
