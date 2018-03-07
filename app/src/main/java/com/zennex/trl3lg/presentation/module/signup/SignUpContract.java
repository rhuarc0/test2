package com.zennex.trl3lg.presentation.module.signup;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.ViewCommand;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.BaseRouter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.common.view.ViperBaseActivity;
import com.zennex.trl3lg.presentation.model.FieldModel;

import java.util.Iterator;
import java.util.List;

public abstract class SignUpContract {

    public SignUpContract() {
        throw new RuntimeException("no instance please!");
    }


    public interface ISignUpView extends IBaseView {

        @StateStrategyType(value = SignUpViewPresenterStrategy.class, tag = SignUpViewPresenterStrategy.TAG_SHOW_PENDING_LOAD_FIELDS)
        void showPendingForLoadFields();

        @StateStrategyType(value = SignUpViewPresenterStrategy.class, tag = SignUpViewPresenterStrategy.TAG_HIDE_PENDING_LOAD_FIELDS)
        void hidePendingForLoadFields();

        @StateStrategyType(SkipStrategy.class)
        void setEnableInputFields(boolean state);

        @StateStrategyType(SkipStrategy.class)
        void showFields(List<FieldModel> fieldModels, boolean withAnimation);

        @StateStrategyType(SkipStrategy.class)
        void showErrorValueField(String uuidField, @StringRes int errorMessage);

        @StateStrategyType(SkipStrategy.class)
        void hideErrorValueField(String uuidField);
    }

    public static abstract class AbstractSignUpView extends ViperBaseActivity<AbstractSignUpPresenter, AbstractSignUpRouter>
            implements ISignUpView {

    }

    public static abstract class AbstractSignUpPresenter extends ViperBasePresenter<ISignUpView, AbstractSignUpRouter> {

        public AbstractSignUpPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public abstract void setModuleId(String moduleId);

        public abstract void setSiteId(String siteId);

        public abstract void onClickBackButton();

        public abstract void onFinishCreateFields();

        public abstract void onClickBtnSignIn();

        public abstract void onClickBtnSignUp(List<FieldModel> fields);

        public abstract void setFields(List<FieldModel> fields);

        public abstract void onFocusChangeField(FieldModel fieldModel, boolean hasFocus);

    }

    public static abstract class AbstractSignUpRouter extends BaseRouter<BaseRouter.BaseRouterAdapter> {

    }

    public static class SignUpViewPresenterStrategy implements StateStrategy {

        public static final String TAG_SHOW_PENDING_LOAD_FIELDS = "show_pending_load_fields";
        public static final String TAG_HIDE_PENDING_LOAD_FIELDS = "hide_pending_load_fields";

        @Override
        public <View extends MvpView> void beforeApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {

            if (incomingCommand.getTag().equals(TAG_SHOW_PENDING_LOAD_FIELDS)) {
                removePendingViewCommand(currentState);
                currentState.add(incomingCommand);
            } else if (incomingCommand.getTag().contains(TAG_HIDE_PENDING_LOAD_FIELDS)) {
                removePendingViewCommand(currentState);
            }

        }

        @Override
        public <View extends MvpView> void afterApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {
            //do nothing
        }

        private <View extends MvpView> void removePendingViewCommand(List<ViewCommand<View>> currentState) {
            for (Iterator<ViewCommand<View>> iterator = currentState.iterator(); iterator.hasNext(); ) {
                ViewCommand viewCommand = iterator.next();
                if ((viewCommand.getTag().equals(TAG_SHOW_PENDING_LOAD_FIELDS)) ||
                        (viewCommand.getTag().equals(TAG_HIDE_PENDING_LOAD_FIELDS))) {
                    iterator.remove();
                    break;
                }
            }
        }
    }


}
