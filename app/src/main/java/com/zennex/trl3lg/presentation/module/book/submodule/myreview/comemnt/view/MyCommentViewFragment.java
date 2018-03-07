package com.zennex.trl3lg.presentation.module.book.submodule.myreview.comemnt.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.book.BookModuleContract;
import com.zennex.trl3lg.presentation.module.book.submodule.myreview.comemnt.MyCommentModuleContract;
import com.zennex.trl3lg.presentation.module.book.submodule.myreview.comemnt.presenter.MyCommentPresenter;

/**
 * Created by nikita on 20.10.17.
 */

@Layout(R.layout.frg_my_review_rating_comment_layout)
public class MyCommentViewFragment extends MyCommentModuleContract.AbstractMyCommentViewFragment {

    @InjectPresenter
    MyCommentModuleContract.AbstractMyCommentPresenter mPresenter;

    @ProvidePresenter
    MyCommentModuleContract.AbstractMyCommentPresenter providePresenter() {
        return new MyCommentPresenter(App.getHasPresenterSubcomponentBuilders(getActivity()));
    }

    public static MyCommentViewFragment newInstance() {
        return new MyCommentViewFragment();
    }

    @NonNull
    @Override
    public MyCommentModuleContract.AbstractMyCommentPresenter getPresenter() {
        return mPresenter;
    }

    @NonNull
    @Override
    protected BookModuleContract.AbstractBookRouter getRouter() {
        return ((BookModuleContract.AbstractBookView) getActivity()).getPresenter().getRouter();
    }
}