package com.zennex.trl3lg.presentation.module.bookimagezoom.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.alexvasilkov.gestures.views.GestureImageView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.bookimagezoom.BookImageZoomModuleContract;
import com.zennex.trl3lg.presentation.module.bookimagezoom.presenter.BookZoomPresenter;
import com.zennex.trl3lg.presentation.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nikit on 10.09.2017.
 */

public class BookImageZoomActivity extends BookImageZoomModuleContract.AbstractBookZoomView
        implements BookImageZoomModuleContract.IBookZoomView {

    private static final String EXTRA_BOOK_IMAGE_PATH_KEY = "image_path_key";
    private static final String EXTRA_BOOK_TITLE_KEY = "title_key";


    @InjectPresenter
    BookImageZoomModuleContract.AbstractBookZoomPresenter mPresenter;
    @BindView(R.id.act_book_image_zoom_iv_book)
    GestureImageView mIvBook;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.act_book_zoom_root_layout)
    FrameLayout mRootLayout;

    public static Intent newIntent(Context context, String title, String imagePath) {
        Intent intent = new Intent(context, BookImageZoomActivity.class);
        intent.putExtra(EXTRA_BOOK_TITLE_KEY, title);
        intent.putExtra(EXTRA_BOOK_IMAGE_PATH_KEY, imagePath);
        return intent;
    }


    @ProvidePresenter
    BookImageZoomModuleContract.AbstractBookZoomPresenter providePresenter() {
        return new BookZoomPresenter(App.getHasPresenterSubcomponentBuilders(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setupWindowAnimations();
        setContentView(R.layout.act_book_image_zoom_layout);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        postponeEnterTransition();
        resilveExtras();
    }

    private void resilveExtras() {
        mPresenter.setBookTitle(getIntent().getStringExtra(EXTRA_BOOK_TITLE_KEY));
        mPresenter.setBookImagePath(getIntent().getStringExtra(EXTRA_BOOK_IMAGE_PATH_KEY));
    }

    @Override
    public void showImage(String imagePath) {
        GlideApp.with(this)
                .load(getIntent().getStringExtra(EXTRA_BOOK_IMAGE_PATH_KEY))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        scheduleStartPostponedTransition(mIvBook);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        scheduleStartPostponedTransition(mIvBook);
                        return false;
                    }
                })
                .into(mIvBook);
    }

    @Override
    public void setTitleText(String title) {
        super.setTitleText(title);
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
        for (int i = 0; i < mToolbar.getChildCount(); i++) {
            View viewToolbar = mToolbar.getChildAt(i);
            if (viewToolbar instanceof TextView) {
                viewToolbar.setTransitionName("title_transition");
                break;
            }
        }
    }

    @Override
    public void setVisibilityBackButton(boolean value) {
        super.setVisibilityBackButton(value);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(value);
        for (int i = 0; i < mToolbar.getChildCount(); i++) {
            View viewToolbar = mToolbar.getChildAt(i);
            if (viewToolbar instanceof ImageButton) {
                viewToolbar.setTransitionName("back_btn_transition");
                break;
            }
        }
    }

    @NonNull
    @Override
    public BookImageZoomModuleContract.AbstractBookZoomPresenter getPresenter() {
        return mPresenter;
    }

    private void setupWindowAnimations() {
        Transition open = TransitionInflater.from(this).inflateTransition(R.transition.open_book_image_zoom_transition);
        Transition close = TransitionInflater.from(this).inflateTransition(R.transition.close_book_image_zoom_transition);
        getWindow().setExitTransition(close);
        getWindow().setEnterTransition(open);
    }

    @Override
    public void onBackPressed() {
        if (!getPresenter().onBackPressed()) super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return mPresenter.onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }

                });
    }

    @Override
    public void prepareScreen() {

    }
}
