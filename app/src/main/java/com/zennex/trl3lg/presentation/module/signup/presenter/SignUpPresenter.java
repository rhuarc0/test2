package com.zennex.trl3lg.presentation.module.signup.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.annimon.stream.Stream;
import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.data.rest.response.signup.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.rest.response.signup.SignUpResponse;
import com.zennex.trl3lg.domain.entities.Field;
import com.zennex.trl3lg.domain.usecases.common.DefaultObserver;
import com.zennex.trl3lg.domain.usecases.singup.GetFieldsForSignUp;
import com.zennex.trl3lg.domain.usecases.singup.SignUp;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.mapper.rx.FieldModelsMapperInteractor;
import com.zennex.trl3lg.presentation.model.FieldModel;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.signup.SignUpContract;
import com.zennex.trl3lg.presentation.module.signup.presenter.assembly.ISignUpPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.signup.presenter.assembly.SignUpPresenterModule;

import java.util.List;

import javax.inject.Inject;


@InjectViewState
public class SignUpPresenter extends SignUpContract.AbstractSignUpPresenter {

    @Inject
    protected GetFieldsForSignUp mGetFieldsForSignUp;

    @Inject
    protected FieldModelsMapperInteractor mFieldModelsMapperInteractor;

    @Inject
    protected SignUp mSignUp;

    private String mModuleId;
    private String mSiteId;

    private List<FieldModel> mFieldModels;
    private boolean mFieldsCreated;
    private boolean mViewPrepared;


    public SignUpPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        ((ISignUpPresenterSubcomponent.Builder) presenterSubcomponentBuilders.getPresenterComponentBuilder(SignUpPresenter.class))
                .presenterModule(new SignUpPresenterModule(this))
                .build()
                .injectMembers(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mGetFieldsForSignUp.execute(new FetchFieldsForSignUpObserver(), mModuleId);
    }

    @Override
    public void attachView(SignUpContract.ISignUpView view) {
        super.attachView(view);
        if (!isFieldsCreated() && mFieldModels != null) {
            getViewState().showFields(mFieldModels, false);
        }
    }

    public boolean isFieldsCreated() {
        return mFieldsCreated;
    }

    @Override
    public void setModuleId(String moduleId) {
        mModuleId = moduleId;
    }

    @Override
    public void setSiteId(String siteId) {
        mSiteId = siteId;
    }


    @Override
    protected TitleModel getTitle() {
        return new TitleModel.Builder()
                .setTitleMessageRes(R.string.sign_up_title)
                .setVisibleBackButton(true)
                .build();
    }

    @Override
    public void onFinishCreateFields() {
        mFieldsCreated = true;
        getViewState().hidePendingForLoadFields();
    }

    @Override
    public void destroyView(SignUpContract.ISignUpView view) {
        super.destroyView(view);
        mFieldsCreated = false;
    }

    @Override
    public void onClickBackButton() {
        getRouter().close();
    }

    @Override
    public void onClickBtnSignIn() {
        getRouter().onBackPressed();
    }

    @Override
    public void onClickBtnSignUp(List<FieldModel> fields) {
        mFieldModels = fields;
        if (checkFields()) {
            mSignUp.execute(new SignUpObserver(), new SignUp.Params(mModuleId, mFieldModels));
        }
    }

    private boolean checkFields() {
        return Stream.of(mFieldModels)
                .map(fieldModel -> {
                    boolean resultCheckField = checkField(fieldModel);
                    if (!resultCheckField) {
                        getViewState().showErrorValueField(fieldModel.getUuid(), R.string.error_required_field);
                        fieldModel.setErrorEnable(true);
                        fieldModel.setErrorMessage(R.string.error_required_field);
                    }
                    return resultCheckField;
                })
                .reduce((value1, value2) -> value1 && value2)
                .get();
    }

    private boolean checkField(FieldModel field) {
        return !field.isRequired() || !TextUtils.isEmpty(field.getValue());
    }


    @Override
    public void onFocusChangeField(FieldModel fieldModel, boolean hasFocus) {
        if (hasFocus) {

            Stream.of(mFieldModels)
                    .forEach(fieldModel1 -> {
                        if ((fieldModel1.getUuid().equals(fieldModel.getUuid())) && fieldModel1.isErrorEnable()) {
                            fieldModel1.setErrorEnable(false);
                            getViewState().hideErrorValueField(fieldModel1.getUuid());
                        }
                    });
        }
    }

    @Override
    public void setFields(List<FieldModel> fields) {
        mFieldModels = fields;
    }

    //region FetchFieldsForSignUpObserver

    private class FetchFieldsForSignUpObserver extends DefaultObserver<List<Field>> {

        @Override
        public void onNext(List<Field> dataMemberFields) {
            getViewState().hidePendingForLoadFields();
            mFieldModelsMapperInteractor.execute(new FieldModelsMapperObserver(), dataMemberFields);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePendingForLoadFields();
        }

        @Override
        protected void onStart() {
            getViewState().showPendingForLoadFields();
        }

        @Override
        protected String getTag() {
            return "FetchFieldsForSignUpObserver";
        }
    }

    //endregion FetchFieldsForSignUpObserver

    //region FieldModelsMapperObserver

    private class FieldModelsMapperObserver extends DefaultObserver<List<FieldModel>> {

        @Override
        public void onNext(List<FieldModel> fieldModels) {
            mFieldModels = fieldModels;
            getViewState().hidePendingForLoadFields();
            getViewState().showFields(fieldModels, true);

        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePendingForLoadFields();
        }

        @Override
        protected void onStart() {
            getViewState().showPendingForLoadFields();
        }

        @Override
        protected String getTag() {
            return "FieldModelsMapperObserver";
        }
    }

    //endregion FieldModelsMapperObserver

    //region SignUpObserver

    private class SignUpObserver extends DefaultObserver<SignUpResponse> {

        @Override
        protected void onStart() {
            getViewState().setEnableInputFields(false);
            getViewState().showPending(0);
        }

        @Override
        public void onNext(SignUpResponse signUpResponse) {
            getViewState().setEnableInputFields(true);
            getViewState().hidePending();
        }

        @Override
        public void onError(Throwable e) {
            getViewState().setEnableInputFields(true);
            getViewState().hidePending();
        }

        @Override
        protected String getTag() {
            return "SignUpObserver";
        }
    }

    //endregion SignUpObserver

}
