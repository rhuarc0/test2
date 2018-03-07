package com.zennex.trl3lg.presentation.module.book.submodule.myreview.rating.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.book.BookModuleContract;
import com.zennex.trl3lg.presentation.module.book.submodule.myreview.rating.MyRatingModuleContract;
import com.zennex.trl3lg.presentation.module.book.submodule.myreview.rating.presenter.MyRatingPresenter;

import butterknife.BindView;

/**
 * Created by nikita on 28.09.17.
 */
@Layout(R.layout.frg_my_review_rating_layout)
public class MyRatingViewFragment extends MyRatingModuleContract.AbstractMyRatingViewFragment {

    @InjectPresenter
    MyRatingModuleContract.AbstractMyRatingPresenter mPresenter;

    @BindView(R.id.frg_my_review_rating_bar)
    RatingBar mRbBook;
    @BindView(R.id.frg_my_review_rating_tv_status)
    TextView mTvRateDescription;
    @BindView(R.id.frg_my_review_btn_next)
    Button mBtnNext;

    MyRatingModuleContract.IMyReviewRatingEventListener mReviewRatingEventListener;

    @ProvidePresenter
    MyRatingModuleContract.AbstractMyRatingPresenter providePresenter() {
        return new MyRatingPresenter(App.getHasPresenterSubcomponentBuilders(getActivity()));
    }

    public static MyRatingViewFragment newInstance() {
        return new MyRatingViewFragment();
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mReviewRatingEventListener = (MyRatingModuleContract.IMyReviewRatingEventListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement MyRatingModuleContract.IMyReviewRatingEventListener");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            try {
                mReviewRatingEventListener = (MyRatingModuleContract.IMyReviewRatingEventListener) context;
            } catch (ClassCastException e) {
                throw new ClassCastException(context.toString() + " must implement MyRatingModuleContract.IMyReviewRatingEventListener");
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setReviewRatingEventListener(mReviewRatingEventListener);
    }


    @Override
    public void prepareScreen() {
        mBtnNext.setOnClickListener(v -> mPresenter.onClickBtnNext());
        mRbBook.setOnRatingBarChangeListener((ratingBar, v, b) -> {
                    if (b) mPresenter.onChangeRating(v);
                }
        );
    }

    @Override
    public void prepareViewForNextStep() {
        mBtnNext.setEnabled(true);
        mTvRateDescription.setVisibility(View.VISIBLE);
    }

    @Override
    public void cancelPrepareViewForNextStep() {
        mBtnNext.setEnabled(false);
        mTvRateDescription.setVisibility(View.GONE);
    }

    @Override
    public void showRatingDescription(String text) {
        mTvRateDescription.setText(text);
    }

    @NonNull
    @Override
    public MyRatingModuleContract.AbstractMyRatingPresenter getPresenter() {
        return mPresenter;
    }

    @NonNull
    @Override
    protected BookModuleContract.AbstractBookRouter getRouter() {
        return ((BookModuleContract.AbstractBookView) getActivity()).getPresenter().getRouter();
    }
}
