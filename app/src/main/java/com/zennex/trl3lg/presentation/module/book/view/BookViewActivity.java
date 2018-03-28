package com.zennex.trl3lg.presentation.module.book.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.domain.entities.Review;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.book.BookModuleContract;
import com.zennex.trl3lg.presentation.module.book.presenter.BookPresenter;
import com.zennex.trl3lg.presentation.module.book.router.BookRouter;
import com.zennex.trl3lg.presentation.module.book.submodule.myreview.rating.MyRatingModuleContract;
import com.zennex.trl3lg.presentation.utils.GlideApp;
import com.zennex.trl3lg.presentation.utils.PairArrayList;
import com.zennex.trl3lg.presentation.utils.view.WrapContentViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nikit on 02.09.2017.
 */

public class BookViewActivity
        extends BookModuleContract.AbstractBookView
        implements MyRatingModuleContract.IMyReviewRatingEventListener {

    public static final String TAG = "BookViewActivity";
    public static final String EXTRA_BOOK_KEY = "book";

    @InjectPresenter
    BookModuleContract.AbstractBookPresenter mPresenter;

    @BindView(R.id.act_book_iv_book)
    ImageView mIvBook;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.act_book_tv_title)
    TextView mTvTitle;
    @BindView(R.id.act_book_tv_release_date)
    TextView mTvReleaseDate;
    @BindView(R.id.act_book_tv_available)
    TextView mTvAvailable;
    @BindView(R.id.act_book_tv_approx_length)
    TextView mTvApproxLength;
    @BindView(R.id.act_book_tv_author)
    TextView mTvAuthor;
    @BindView(R.id.act_book_tv_language)
    TextView mTvLanguage;
    @BindView(R.id.act_book_tv_rating)
    TextView mTvRating;
    @BindView(R.id.act_book_root_view)
    ConstraintLayout mRootLayout;
    @BindView(R.id.act_book_tv_description)
    AppCompatTextView mTvDescription;
    @BindView(R.id.act_book_btn_read_more_desc)
    Button mBtnReadMore;
    @BindView(R.id.act_book_rating_bar)
    RatingBar mRatingBar;
    @BindView(R.id.act_book_my_rating_view_pager)
    WrapContentViewPager mMyReviewViewPager;
    @BindView(R.id.act_book_btn_chapters)
    Button mBtnChapters;
    @BindView(R.id.act_book_btn_renew)
    Button mBtnRenew;
    @BindView(R.id.act_book_btn_play)
    FloatingActionButton mBtnPlay;
    @BindView(R.id.act_book_container_buttons)
    ConstraintLayout mBtnContainers;
    @BindView(R.id.act_book_recycler_view_reviews)
    RecyclerView mRecyclerViewReviews;

    @BindView(R.id.act_book_reviews_pb)
    ProgressBar mProgressBarLoadReviews;

    @BindView(R.id.act_book_reviews_empty_view)
    View mReviewsEmptyView;

    BookViewMyReviewFragmentAdapter mMyReviewFragmentAdapter;
    BookViewRecyclerViewReviewsAdapter mReviewsAdapter;

    public static Intent newIntent(Context context, Book book) {
        Intent intent = new Intent(context, BookViewActivity.class);
        intent.putExtra(EXTRA_BOOK_KEY, book);
        return intent;
    }

    @ProvidePresenter
    public BookModuleContract.AbstractBookPresenter providePresenter() {
        return new BookPresenter(App.getHasPresenterSubcomponentBuilders(this));
    }

    @Override
    protected BookModuleContract.AbstractBookRouter resolveRouter() {
        return new BookRouter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS); // must be called before adding content
        setupWindowAnimations();
        setContentView(R.layout.act_book_layout);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        resolveRouter();
        parseExtraData();
        postponeEnterTransition();
    }

    @Override
    public void prepareScreen() {
        mMyReviewFragmentAdapter = new BookViewMyReviewFragmentAdapter(getSupportFragmentManager());
        mMyReviewViewPager.setAdapter(mMyReviewFragmentAdapter);
        mRecyclerViewReviews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mReviewsAdapter = new BookViewRecyclerViewReviewsAdapter();
        mRecyclerViewReviews.setAdapter(mReviewsAdapter);
        mRecyclerViewReviews.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerViewReviews.setHasFixedSize(true);
        mRecyclerViewReviews.setNestedScrollingEnabled(false);
       /* YoYo.with(Techniques.FadeIn)
                .duration(400)
                .withListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        mBtnPlay.setVisibility(View.VISIBLE);
                    }
                })
                .playOn(mBtnPlay);*/
    }

    @Override
    protected void onStart() {
        super.onStart();


      /*  mBtnPlay.setOnClickListener(v -> {
            ValueAnimator animator = ValueAnimator.ofInt(0, (int) getResources().getDimension(R.dimen.button_height_normal));
            animator.addUpdateListener(valueAnimator -> {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mBtnContainers.getLayoutParams();
                layoutParams.height = val;
                mBtnContainers.setLayoutParams(layoutParams);
                //    mBtnContainers.setVisibility(View.VISIBLE);
            });

            animator.setDuration(300);
            animator.addListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);

                    YoYo.with(Techniques.SlideInLeft)
                            .duration(300)
                            .withListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    mBtnChapters.setVisibility(View.VISIBLE);
                                }
                            })
                            .playOn(mBtnChapters);

                    YoYo.with(Techniques.SlideInRight)
                            .duration(300)
                            .withListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationStart(Animator animation) {
                                    mBtnRenew.setVisibility(View.VISIBLE);
                                }
                            })
                            .playOn(mBtnRenew);
                }
            });
            animator.start();

        });*/
    }

    @OnClick(R.id.act_book_btn_read_more_desc)
    public void onClickBtnReadMoreDesc() {
        PairArrayList<View, String> pairs = new PairArrayList<>();
        View statusBar = findViewById(android.R.id.statusBarBackground);
        View navigationBar = findViewById(android.R.id.navigationBarBackground);
        View appBar = findViewById(R.id.app_bar_layout);
        if (statusBar != null) {
            pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        }

        if (navigationBar != null) {
            pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }

        pairs.addAll(Stream.of(findViewById(R.id.act_book_cv_desc),
                appBar,
                mTvDescription,
                findViewById(R.id.act_book_tv_title_description),
                findViewById(R.id.act_book_btn_read_more_desc))
                .map(view -> Pair.create(view, view.getTransitionName()))
                .toList());
        mPresenter.onClickBtnReadMoreDescription(pairs);
    }


    @OnClick(R.id.act_book_btn_read_all_reviews)
    public void onClickBtnReadAllReviews() {
        PairArrayList<View, String> pairs = new PairArrayList<>();
        View statusBar = findViewById(android.R.id.statusBarBackground);
        View navigationBar = findViewById(android.R.id.navigationBarBackground);
        View appBar = findViewById(R.id.app_bar_layout);
        if (statusBar != null) {
            pairs.add(Pair.create(statusBar, Window.STATUS_BAR_BACKGROUND_TRANSITION_NAME));
        }

        if (navigationBar != null) {
            pairs.add(Pair.create(navigationBar, Window.NAVIGATION_BAR_BACKGROUND_TRANSITION_NAME));
        }

        pairs.addAll(Stream.of(appBar,
                findViewById(R.id.act_book_tv_title_description),
                findViewById(R.id.act_book_btn_read_more_desc))
                .map(view -> Pair.create(view, view.getTransitionName()))
                .toList());

        mPresenter.onClickReadAllRevews(pairs);
    }

    private void setupWindowAnimations() {
        Transition open = TransitionInflater.from(this).inflateTransition(R.transition.open_book_transition);
        Transition close = TransitionInflater.from(this).inflateTransition(R.transition.close_book_transition);
        getWindow().setExitTransition(close);
        getWindow().setEnterTransition(open);
        ChangeBounds changeBounds = new ChangeBounds();
        getWindow().setSharedElementExitTransition(changeBounds);
    }

    @Override
    public void onBackPressed() {
        View view = mRootLayout.getRootView();
        view.setBackgroundColor(ContextCompat.getColor(this, R.color.md_grey_200));
        if (!getPresenter().onBackPressed()) super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                View view = mRootLayout.getRootView();
                view.setBackgroundColor(ContextCompat.getColor(this, R.color.md_grey_200));
                return mPresenter.onBackPressed();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showBook(Book book) {
        mTvTitle.setText(book.getTitle());
        mTvReleaseDate.setText(getString(R.string.act_book_tv_release_date, book.getCreated()));
        mTvAvailable.setText(getString(R.string.act_book_available, book.getAvailable()));
        mTvApproxLength.setText(getString(R.string.act_book_approx_length, book.getCopyRight()));
        mTvAuthor.setText(getString(R.string.act_book_author,
                TextUtils.isEmpty(book.getAuthor()) ? book.getAuthor2() : book.getAuthor()));
        mTvLanguage.setText(getString(R.string.act_book_language, book.getLanguage()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mTvDescription.setText(Html.fromHtml(book.getDescr(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            //noinspection deprecation
            mTvDescription.setText(Html.fromHtml(book.getDescr()));
        }
        mRatingBar.setRating(book.getRating());

        GlideApp.with(this)
                .load(book.getImage())
                .skipMemoryCache(true)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        scheduleStartPostponedTransition(mIvBook);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        scheduleStartPostponedTransition(mIvBook);
                        Log.d(TAG, "onResourceReady");
                        return false;
                    }
                })
                .into(mIvBook);
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                Log.d(TAG, "sharedElement: onPreDraw");
                sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                startPostponedEnterTransition();
                return true;
            }
        });
    }

    @OnClick(R.id.act_book_iv_book)
    public void onClickImageViewBook() {
        View rootView = mRootLayout.getRootView();
        rootView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        PairArrayList<View, String> animViews = new PairArrayList<>();
        for (int i = 0; i < mToolbar.getChildCount(); i++) {
            View viewToolbar = mToolbar.getChildAt(i);
            if (viewToolbar instanceof TextView) {
                viewToolbar.setTransitionName("title_transition");
                animViews.add(Pair.create(viewToolbar, viewToolbar.getTransitionName()));
                continue;
            }

            if (viewToolbar instanceof ImageButton) {
                viewToolbar.setTransitionName("back_btn_transition");
                animViews.add(Pair.create(viewToolbar, viewToolbar.getTransitionName()));
            }
        }

        animViews.add(Pair.create(mIvBook, mIvBook.getTransitionName()));
        mPresenter.onClickImageBook(animViews);
    }

    private void parseExtraData() {
        mPresenter.setBook(getIntent().getParcelableExtra(EXTRA_BOOK_KEY));
    }

    @NonNull
    public BookModuleContract.AbstractBookPresenter getPresenter() {
        return mPresenter;
    }


    @Override
    public void setTitleText(String title) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
    }

    @Override
    public void setVisibilityBackButton(boolean value) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(value);
    }

    @Override
    public void showPendingLoadReviews() {
        mProgressBarLoadReviews.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePendingLoadReviews() {
        mProgressBarLoadReviews.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyViewReviews() {
        mRecyclerViewReviews.setVisibility(View.GONE);
        mReviewsEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showReviews(List<Review> reviews) {
        mRecyclerViewReviews.setVisibility(View.VISIBLE);
        mReviewsEmptyView.setVisibility(View.GONE);
        mReviewsAdapter.updateList(reviews);
    }

    @Override
    public void showMyReviewSendScreen() {
        mMyReviewViewPager.setCurrentItem(1, true);
    }

    //region IMyReviewRatingEventListener

    @Override
    public void onClickNext(float valueRating) {
        mPresenter.onClickNextRateBook();
    }

    //endregion IMyReviewRatingEventListener
}
