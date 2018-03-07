package com.zennex.trl3lg.presentation.module.auth.view;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.annimon.stream.Stream;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.model.SiteModel;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.auth.AuthScreenContract;
import com.zennex.trl3lg.presentation.module.auth.presenter.AuthPresenter;
import com.zennex.trl3lg.presentation.module.auth.router.AuthRouter;
import com.zennex.trl3lg.presentation.utils.UiUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by nikita on 02.06.17.
 */
@Layout(R.layout.act_auth_layout)
public class AuthScreenActivity extends AuthScreenContract.AbstractAuthView implements Animation.AnimationListener {

    @InjectPresenter
    AuthScreenContract.AbstractAuthPresenter mPresenter;

    @BindView(R.id.act_auth_root_layout)
    ConstraintLayout mRootLayout;
    @BindView(R.id.act_auth_ti_et_login)
    TextInputEditText mTiEtLogin;
    @BindView(R.id.act_auth_ti_et_password)
    TextInputEditText mTiEtPassword;
    @BindView(R.id.act_auth_image_view_logo)
    ImageView mViewLogo;
    @BindView(R.id.act_auth_content_input_layout)
    LinearLayout mContentInputLayout;
    @BindView(R.id.act_auth_progress_bar)
    ProgressBar mHorizontalProgressBar;
    @BindView(R.id.act_auth_btn_sign_in)
    Button mBtnSignIn;
    @BindView(R.id.act_auth_btn_sign_up)
    Button mBtnSignUp;

    Animation mAnimInitLogo;
    Animation mAnimInitContent;

    @ProvidePresenter
    AuthScreenContract.AbstractAuthPresenter provideAuthPresenter() {
        return new AuthPresenter(App.getHasPresenterSubcomponentBuilders(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAnimInitLogo = AnimationUtils.loadAnimation(this, R.anim.logo_init);
        mAnimInitContent = AnimationUtils.loadAnimation(this, R.anim.content_auth_init);
        mAnimInitLogo.setAnimationListener(this);
        mAnimInitContent.setAnimationListener(this);
    }

    @Override
    protected AuthScreenContract.AbstractAuthRouter resolveRouter() {
        return new AuthRouter();
    }

    @Override
    public void prepareScreen() {
        hideContent();
        if (isDebug()) {
            mTiEtLogin.setText("test10@test.com");
            mTiEtPassword.setText("test");
        }
    }

    //region AuthScreenContract.AbstractAuthView

    @Override
    public void showPending(@StringRes int id) {
        mContentInputLayout.setEnabled(false);
        mHorizontalProgressBar.setVisibility(View.VISIBLE);
        mBtnSignIn.setEnabled(false);
        mBtnSignUp.setEnabled(false);
        UiUtils.hideKeyboard(this, mRootLayout);
    }

    @Override
    public void showPending(String message) {
        showPending(0);
    }

    @Override
    public void hidePending() {
        mContentInputLayout.setEnabled(true);
        mHorizontalProgressBar.setVisibility(View.INVISIBLE);
        mBtnSignIn.setEnabled(true);
        mBtnSignUp.setEnabled(true);
    }

    @Override
    public void showContent() {
        mViewLogo.setVisibility(View.VISIBLE);
        mContentInputLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContent() {
        mViewLogo.setVisibility(View.GONE);
        mContentInputLayout.setVisibility(View.GONE);
    }

    @Override
    public void prepareContent() {
        mViewLogo.setVisibility(View.VISIBLE);
        mViewLogo.startAnimation(mAnimInitLogo);
    }

    @NonNull
    @Override
    public AuthScreenContract.AbstractAuthPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void showDialogSelectSite(List<SiteModel> siteModels) {
        final CharSequence domainList[] = new CharSequence[siteModels.size()];
        Stream.of(siteModels)
                .forEachIndexed((index, siteModel) -> domainList[index] = siteModel.getDomain());

        new AlertDialog.Builder(this)
                .setTitle(R.string.act_auth_select_a_domain)
                .setItems(domainList, (d, i) -> getPresenter()
                        .onSelectDomain(mTiEtLogin.getText().toString(), mTiEtPassword.getText().toString(), i))
                .setOnCancelListener(dialog -> mPresenter.onClickCancelDialogSelectSite())
                .show();
    }

    @Override
    public void showDialogSelectSiteForNewUser(List<SiteModel> siteModels) {
        final CharSequence domainList[] = new CharSequence[siteModels.size()];
        Stream.of(siteModels)
                .forEachIndexed((index, siteModel) -> domainList[index] = siteModel.getDomain());

        new AlertDialog.Builder(this)
                .setTitle(R.string.act_auth_select_a_domain)
                .setItems(domainList, (d, i) -> getPresenter().onSelectDomainForNewUser(i))
                .setOnCancelListener(dialog -> mPresenter.onClickCancelDialogSelectSite())
                .show();
    }

    @Override
    public void hideDialogSelectSite() {
        //do nothing
    }

    //endregion AuthScreenContract.AbstractAuthView

    //region methods

    @OnClick(R.id.act_auth_btn_sign_up)
    public void onClickBtnSignUp() {
        mPresenter.onClickSignUp();
    }

    @OnClick(R.id.act_auth_btn_sign_in)
    public void onClickBtnSignIn() {
        mPresenter.onClickSignIn(mTiEtLogin.getText().toString(), mTiEtPassword.getText().toString());
    }

    //endregion methods

    //region Animation.AnimationListener

    @Override
    public void onAnimationStart(Animation animation) {
        //do nothing
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == mAnimInitLogo) {
            mContentInputLayout.setVisibility(View.VISIBLE);
            mContentInputLayout.startAnimation(mAnimInitContent);
        } else if (animation == mAnimInitContent) {
            mPresenter.onFinishedPrepareContent();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        //do nothing
    }

    //endregion
}
