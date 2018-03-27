package com.zennex.trl3lg.presentation.module.search.router;

import android.app.ActivityOptions;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.presentation.module.book.view.BookViewActivity;
import com.zennex.trl3lg.presentation.module.search.SearchScreenContract;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

public class SearchScreenRouter extends SearchScreenContract.AbstractSearchScreenRouter {


    @Override
    public void showBookScreenWithAnim(Book book, PairArrayList<View, String> animateViews) {
        if (mAdapter != null) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(mAdapter.getActivity(),
                    animateViews.toArray());

            navigateToActivity(BookViewActivity.newIntent(mAdapter.getActivity(), book), options.toBundle());
        }
    }

    @Override
    public BaseRouterAdapter createAdapter(@NonNull AppCompatActivity appCompatActivity) {
        return new BaseRouterAdapter(appCompatActivity);
    }
}
