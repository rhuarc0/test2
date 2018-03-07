package com.zennex.trl3lg.presentation.module.bookdescription.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.bookdescription.BookDescriptionModuleContract;
import com.zennex.trl3lg.presentation.module.bookdescription.presenter.BookDescriptionPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nikita on 08.09.17.
 */

public class BookDescriptionActivity extends BookDescriptionModuleContract.AbstractBookDescriptionView
        implements BookDescriptionModuleContract.IBookDescriptionView {

    private static final String EXTRA_BOOK_DESCRIPTION_KEY = "description_key";
    private static final String EXTRA_BOOK_TITLE_KEY = "title_key";

    @InjectPresenter
    BookDescriptionModuleContract.AbstractBookDescriptionPresenter mPresenter;

    @BindView(R.id.act_book_description_tv_description)
    TextView mTvDescription;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static Intent newIntent(Context context, String title, String description) {
        Intent intent = new Intent(context, BookDescriptionActivity.class);
        intent.putExtra(EXTRA_BOOK_DESCRIPTION_KEY, description);
        intent.putExtra(EXTRA_BOOK_TITLE_KEY, title);
        return intent;
    }

    @ProvidePresenter
    BookDescriptionModuleContract.AbstractBookDescriptionPresenter providePresenter() {
        return new BookDescriptionPresenter(App.getHasPresenterSubcomponentBuilders(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.act_book_description_layout);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setupWindowAnimations();
        resolveExtras();
        postponeEnterTransition();

        final View decor = getWindow().getDecorView();
        decor.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                decor.getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });
    }

    private void resolveExtras() {
        mPresenter.setBookTitle(getIntent().getStringExtra(EXTRA_BOOK_TITLE_KEY));
        mPresenter.setBookDescription(getIntent().getStringExtra(EXTRA_BOOK_DESCRIPTION_KEY));
    }

    @Override
    public void showDescription(String description) {
        //noinspection deprecation
        mTvDescription.setText(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ?
                Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT) :
                Html.fromHtml(description));
    }

    @NonNull
    @Override
    public BookDescriptionModuleContract.AbstractBookDescriptionPresenter getPresenter() {
        return mPresenter;
    }

    private void setupWindowAnimations() {
        Transition open = TransitionInflater.from(this).inflateTransition(R.transition.open_book_description_transition);
        Transition close = TransitionInflater.from(this).inflateTransition(R.transition.close_book_description_transition);
        getWindow().setExitTransition(close);
        getWindow().setEnterTransition(open);
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.setDuration(400);
        getWindow().setSharedElementEnterTransition(changeBounds);
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

    @Override
    public void prepareScreen() {

    }

    @Override
    public void setVisibilityBackButton(boolean value) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(value);
    }

    @Override
    public void setTitleText(String title) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
    }

    @OnClick(R.id.act_book_description_btn_collapse)
    public void onClickCollapse() {
        mPresenter.onClickCollapse();
    }
}
