package com.zennex.trl3lg.presentation.module.main.router;

import android.app.ActivityOptions;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.presentation.module.book.view.BookViewActivity;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.view.CatalogFragmentView;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.OnDemandFragmentView;
import com.zennex.trl3lg.presentation.module.main.submodule.rental.view.RentalFragmentView;
import com.zennex.trl3lg.presentation.module.main.submodule.user.view.UserFragmentView;
import com.zennex.trl3lg.presentation.module.search.view.SearchViewActivity;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

/**
 * Created by nikita on 22.07.17.
 */

public class MainRouter extends MainScreenContract.AbstractMainRouter {


    @Override
    public void showCatalogScreen() {
        if (mAdapter != null) {
            replaceFragment(CatalogFragmentView.newInstance(),
                    mAdapter.getContainerForFragmentsId(),
                    CatalogFragmentView.TAG,
                    true);
        }
    }

    @Override
    public void showOnDemandScren() {
        if (mAdapter != null) {
            replaceFragment(OnDemandFragmentView.newInstance(),
                    mAdapter.getContainerForFragmentsId(),
                    OnDemandFragmentView.TAG,
                    true);
        }
    }

    @Override
    public void showOnRentalScreen() {
        if (mAdapter != null) {
            replaceFragment(RentalFragmentView.newInstance(),
                    mAdapter.getContainerForFragmentsId(),
                    RentalFragmentView.TAG,
                    true);
        }
    }

    @Override
    public void showUserScreen() {
        if (mAdapter != null) {
            replaceFragment(UserFragmentView.newInstance(),
                    mAdapter.getContainerForFragmentsId(),
                    UserFragmentView.TAG,
                    true);
        }
    }


    @Override
    public void showSearchBooksScreen(long rentalGroupId, String query) {
        if (mAdapter != null) {
            navigateToActivity(SearchViewActivity.newIntent(mAdapter.getActivity(), rentalGroupId, query));
        }
    }

    @Override
    public void showSearchBooksScreen(String query) {
        if (mAdapter != null) {
            navigateToActivity(SearchViewActivity.newIntent(mAdapter.getActivity(), query));
        }
    }


    @Override
    public void showBookScreenWithAnim(AudioBook audioBook, PairArrayList<View, String> animViews) {
        if (mAdapter != null) {
            navigateToActivity(BookViewActivity.newIntent(mAdapter.getActivity(), audioBook),
                    ActivityOptions.makeSceneTransitionAnimation(mAdapter.getActivity(), animViews.toArray()).toBundle());
        }
    }

    @Override
    public SupportFragmentRouterAdapter createAdapter(@NonNull AppCompatActivity appCompatActivity) {
        return new SupportFragmentRouterAdapter(appCompatActivity, R.id.act_main_container);
    }
}
