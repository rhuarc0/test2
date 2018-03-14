package com.zennex.trl3lg.presentation.common.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.zennex.trl3lg.BuildConfig;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.BaseRouter;

import java.lang.annotation.Annotation;

import butterknife.ButterKnife;

/**
 * Created by nikit on 03.09.2017.
 */

public abstract class ViperBaseActivity<Presenter extends ViperBasePresenter, Router extends BaseRouter>
        extends BaseActivity
        implements IBaseView,
        IBaseFragmentListener {

    public static final String TAG = "BaseActivity";


    protected ProgressDialog mProgressDialog;

    //region Lifecycle

    protected boolean isAutoBindLayout() {
        return true;
    }


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getPresenter().getRouter() == null) getPresenter().setRouter(resolveRouter());
        getPresenter().getRouter().refreshActivityInAdapter(this);
    }

    protected abstract Router resolveRouter();

/*
    protected void bindLayout() {
        Class cls = getClass();
        if (!cls.isAnnotationPresent(Layout.class)) return;
        Annotation annotation = cls.getAnnotation(Layout.class);
        Layout layout = (Layout) annotation;
        setContentView(layout.value());
        ButterKnife.bind(this);
    }
*/

    //endregion Lifecycle

    @NonNull
    public abstract Presenter getPresenter();

    @Override
    public void showLongToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLongToast(@StringRes int stringRes) {
        Toast.makeText(this, stringRes, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showShortToast(@StringRes int stringRes) {
        Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTitleText(@StringRes int titleText) {
        //do override
    }

    @Override
    public void setTitleText(String title) {
        //do override
    }

    @Override
    public void setTitleImage(String url) {
        //do override
    }

    @Override
    public void setVisibilityBackButton(boolean value) {
        //do override
    }

    @Override
    public void onBackPressed() {
        if (!getPresenter().onBackPressed()) super.onBackPressed();
    }

    @Override
    public void showPending(String message) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.dismiss();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void showPending(@StringRes int id) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.dismiss();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(id));
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
    }

    @Override
    public void hidePending() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) mProgressDialog.dismiss();
    }

    //region IBaseFragmentListener

    @Override
    public void changeTitle(@StringRes int title) {
        setTitleText(title);
    }

    @Override
    public void changeTitle(String title) {
        setTitleText(title);
    }

    @Override
    public void changeTitleImage(@NonNull String url) {
        setTitleImage(url);
    }

    @Override
    public void changeVisibilityBackButton(boolean value) {
        setVisibilityBackButton(value);
    }

    //endregion IBaseFragmentListener

    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}

