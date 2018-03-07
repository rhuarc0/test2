package com.zennex.trl3lg.presentation.module.signup.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.common.DefaultObserver;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.common.di.activitybindings.HasActivitySubcomponentBuilders;
import com.zennex.trl3lg.presentation.helper.StringUtils;
import com.zennex.trl3lg.presentation.model.FieldModel;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.signup.SignUpContract;
import com.zennex.trl3lg.presentation.module.signup.presenter.SignUpPresenter;
import com.zennex.trl3lg.presentation.module.signup.router.SignUpRouter;
import com.zennex.trl3lg.presentation.module.signup.view.assembly.ISignUpActivitySubcomponent;
import com.zennex.trl3lg.presentation.module.signup.view.assembly.SignUpActivityModule;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by nikita on 12.06.17.
 */
@Layout(R.layout.act_sign_up_layout)
public class SignUpActivity extends SignUpContract.AbstractSignUpView
        implements Animation.AnimationListener,
        View.OnFocusChangeListener {

    public static final String TAG = "SignUpActivity";

    public static final String KEY_EXTRA_MEMBERSHIP_ID = "membership_id";
    public static final String KEY_EXTRA_SITE_ID = "site_id";

    private final static String KEY_SCROLL_VIEW_Y = "scroll_view_fields_scroll_Y";

    @InjectPresenter
    SignUpContract.AbstractSignUpPresenter mPresenter;


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.act_sing_up_root_layout_for_fields)
    LinearLayout mRootLayout;
    @BindView(R.id.act_sign_up_content_input_layout)
    LinearLayout mContentInputLayout;
    @BindView(R.id.act_sign_up_btn_layout)
    LinearLayout mBtnsLayout;
    @BindView(R.id.act_sing_up_nested_scroll_view)
    NestedScrollView mScrollViewFields;
    @BindView(R.id.act_sign_up_progress_view_load_fields)
    ProgressBar mProgressBarLoadFields;
    @BindView(R.id.act_sign_up_progress_bar_sign_up)
    ProgressBar mProgressBarSignUp;

    @Inject
    @Named("ContentInputInit")
    Animation mContentInputInitAnim;

    @Inject
    @Named("BtnInit")
    Animation mBtnInitAnim;

    @Inject
    ViewCreator mViewCreator;

    private int mScrollViewScrollY;

    private Map<FieldModel, View> mFieldModelViewMap;


    public static Intent newIntent(@NonNull Context context,
                                   @NonNull String membershipId,
                                   @NonNull String siteId) {

        Intent intent = new Intent(context, SignUpActivity.class);
        intent.putExtra(KEY_EXTRA_MEMBERSHIP_ID, membershipId);
        intent.putExtra(KEY_EXTRA_SITE_ID, siteId);
        return intent;
    }

    @ProvidePresenter
    SignUpContract.AbstractSignUpPresenter providePresenter() {
        return new SignUpPresenter(App.getHasPresenterSubcomponentBuilders(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        resolveExtraData();
        mContentInputInitAnim.setAnimationListener(this);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCROLL_VIEW_Y, mScrollViewFields.getScrollY());
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mScrollViewScrollY = savedInstanceState.getInt(KEY_SCROLL_VIEW_Y);
    }

    @Override
    protected void inject(HasActivitySubcomponentBuilders hasActivitySubcomponentBuilders) {
        ((ISignUpActivitySubcomponent.Builder) hasActivitySubcomponentBuilders.getActivityComponentBuilder(SignUpActivity.class))
                .presenterModule(new SignUpActivityModule(this))
                .build()
                .injectMembers(this);
    }


    private void resolveExtraData() {
        mPresenter.setModuleId(getIntent().getStringExtra(KEY_EXTRA_MEMBERSHIP_ID));
        mPresenter.setSiteId(getIntent().getStringExtra(KEY_EXTRA_SITE_ID));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        getPresenter().onClickBackButton();
    }

    @NonNull
    @Override
    public SignUpContract.AbstractSignUpPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected SignUpContract.AbstractSignUpRouter resolveRouter() {
        return new SignUpRouter();
    }


    @Override
    public void setTitleText(@StringRes int titleText) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(titleText);
    }

    @Override
    public void setVisibilityBackButton(boolean value) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(value);
    }

    @Override
    public void showPending(@StringRes int id) {
        mProgressBarSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPending(String message) {
        mProgressBarSignUp.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePending() {
        mProgressBarSignUp.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showPendingForLoadFields() {
        mProgressBarLoadFields.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePendingForLoadFields() {
        mProgressBarLoadFields.setVisibility(View.GONE);
    }

    @Override
    public void setEnableInputFields(boolean state) {
        Stream.of(mFieldModelViewMap)
                .forEach(entry -> {
                    entry.getKey().setEnable(state);
                    entry.getValue().setEnabled(state);
                });
    }

    @Override
    public void showErrorValueField(String uuidField, @StringRes int errorMessage) {
        Stream.of(mFieldModelViewMap)
                .forEach(entry -> {
                    if (entry.getKey().getUuid().equals(uuidField)) {
                        if (isFieldModelTypeEditText(entry.getKey())) {
                            ((TextInputLayout) entry.getValue()).setError(getString(errorMessage));
                        }
                    }
                });
    }

    @Override
    public void hideErrorValueField(String uuidField) {
        Stream.of(mFieldModelViewMap)
                .forEach(entry -> {
                    if (entry.getKey().getUuid().equals(uuidField)) {
                        if (isFieldModelTypeEditText(entry.getKey())) {
                            ((TextInputLayout) entry.getValue()).setErrorEnabled(false);
                        }
                    }
                });
    }

    @OnClick(R.id.act_sign_up_btn_sign_in)
    public void onClickBtnSignIn() {
        getPresenter().onClickBtnSignIn();
    }

    @OnClick(R.id.act_sign_up_btn_sign_up)
    public void onClickBtnSignUp() {
        getPresenter().onClickBtnSignUp(Stream.of(mFieldModelViewMap)
                .map(this::applyViewValue)
                .toList());
    }


    @Override
    public void showFields(List<FieldModel> fieldModels, boolean withAnimation) {
        mViewCreator.execute(new ViewCreatorObserver(withAnimation), fieldModels);
    }


    private class ViewCreatorObserver extends DefaultObserver<Map<FieldModel, View>> {

        private final boolean isWithAnimation;

        private ViewCreatorObserver(boolean isWithAnimation) {
            this.isWithAnimation = isWithAnimation;
        }

        @Override
        public void onNext(Map<FieldModel, View> fieldModelViewMap) {
            Stream.of(fieldModelViewMap).forEach(SignUpActivity.this::applyFieldModelValue);
            mFieldModelViewMap = fieldModelViewMap;
            mPresenter.onFinishCreateFields();
            mRootLayout.setVisibility(View.VISIBLE);
            if (isWithAnimation) mContentInputLayout.startAnimation(mContentInputInitAnim);
            else {
                mScrollViewFields.post(() -> mScrollViewFields.scrollTo(0, mScrollViewScrollY));
                mBtnsLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected String getTag() {
            return "ViewCreatorObserver";
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().setFields(Stream.of(mFieldModelViewMap)
                .map(this::applyViewValue)
                .toList());
    }

    private void applyFieldModelValue(Map.Entry<FieldModel, View> entry) {
        if (isFieldModelTypeEditText(entry.getKey())) {
            EditText editText = ((TextInputLayout) entry.getValue()).getEditText();
            if (editText != null) {
                editText.setText(entry.getKey().getValue());
                editText.setOnFocusChangeListener(this);
            }
        } else if (entry.getKey().isTypeCheckBox()) {
            ((CheckBox) entry.getValue()).setChecked(Boolean.parseBoolean(entry.getKey().getValue()));
        } else if (entry.getKey().isTypeText()) {
            ((TextView) entry.getValue()).setText(entry.getKey().getValue());
        } else if (entry.getKey().isTypeSpinner()) {
            String value = entry.getKey().getValue();
            if (!StringUtils.isNullOrEmpty(value)) {
                ((Spinner) entry.getValue()).setSelection(Integer.parseInt(value));
            }
        }

        mRootLayout.addView(entry.getValue());

        if (entry.getKey().isErrorEnable() && isFieldModelTypeEditText(entry.getKey())) {
            ((TextInputLayout) entry.getValue()).setError(getString(entry.getKey().getErrorMessage()));
        }

        entry.getValue().setEnabled(entry.getKey().isEnable());
    }


    private FieldModel applyViewValue(Map.Entry<FieldModel, View> entry) {

        if (isFieldModelTypeEditText(entry.getKey())) {
            EditText editText = ((TextInputLayout) entry.getValue()).getEditText();
            if (editText != null) entry.getKey().setValue(editText.getText().toString());
        } else if (entry.getKey().isTypeCheckBox()) {
            entry.getKey().setValue(String.valueOf(((CheckBox) entry.getValue()).isChecked()));
        } else if (entry.getKey().isTypeText()) {
            entry.getKey().setValue(((TextView) entry.getValue()).getText().toString());
        } else if (entry.getKey().isTypeSpinner()) {
            entry.getKey().setValue(String.valueOf(((Spinner) entry.getValue()).getSelectedItemPosition()));
        }
        return entry.getKey();
    }


    //region Animation.AnimationListener

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mBtnsLayout.setVisibility(View.VISIBLE);
        if (animation == mContentInputInitAnim) mBtnsLayout.startAnimation(mBtnInitAnim);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    //endregion Animation.AnimationListener


    //region View.OnFocusChangeListener

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Stream.of(mFieldModelViewMap)
                .filter(entry -> {
                    boolean result = false;
                    if (isFieldModelTypeEditText(entry.getKey())) {
                        EditText editText = ((TextInputLayout) entry.getValue()).getEditText();
                        if (editText != null && (editText.getId() == v.getId())) result = true;
                    }
                    return result;
                })
                .forEach(entry -> getPresenter().onFocusChangeField(entry.getKey(), hasFocus));
    }

    //endregion View.OnFocusChangeListener

    private boolean isFieldModelTypeEditText(FieldModel fieldModel) {
        return fieldModel.isTypeVarcharText() || fieldModel.isTypeVarcharPassword() ||
                fieldModel.isTypeDecimal() || fieldModel.isTypeInteger();
    }

}
