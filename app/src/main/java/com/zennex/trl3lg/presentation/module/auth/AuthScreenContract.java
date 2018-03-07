package com.zennex.trl3lg.presentation.module.auth;

import android.support.annotation.NonNull;

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
import com.zennex.trl3lg.presentation.model.SiteModel;

import java.util.Iterator;
import java.util.List;

/**
 * Created by nikita on 02.06.17.
 */

public class AuthScreenContract {

    public AuthScreenContract() {
        throw new RuntimeException("No instance please!");
    }


    public interface IAuthView extends IBaseView {

        @StateStrategyType(value = SkipStrategy.class)
        void prepareContent();

        void showContent();

        void hideContent();

        @StateStrategyType(value = AuthViewPresenterStrategy.class, tag = AuthViewPresenterStrategy.TAG_SHOW_DIALOG_SELECT_SITE)
        void showDialogSelectSite(List<SiteModel> siteModels);

        @StateStrategyType(value = AuthViewPresenterStrategy.class, tag = AuthViewPresenterStrategy.TAG_SHOW_DIALOG_SELECT_SITE)
        void showDialogSelectSiteForNewUser(List<SiteModel> siteModels);

        @StateStrategyType(value = AuthViewPresenterStrategy.class, tag = AuthViewPresenterStrategy.TAG_HIDE_DIALOG_SELECT_SITE)
        void hideDialogSelectSite();
    }

    public static abstract class AbstractAuthView extends ViperBaseActivity<AbstractAuthPresenter, AbstractAuthRouter>
            implements IAuthView {
    }

    public static abstract class AbstractAuthPresenter extends ViperBasePresenter<IAuthView, AbstractAuthRouter> {

        public AbstractAuthPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public abstract void onFinishedPrepareContent();

        public abstract void onClickSignIn(String email, String password);

        public abstract void onClickSignUp();

        public abstract void onSelectDomain(String email, String password, int pos);

        public abstract void onSelectDomainForNewUser(int pos);

        public abstract void onClickCancelDialogSelectSite();

    }

    public static abstract class AbstractAuthRouter extends BaseRouter<BaseRouter.BaseRouterAdapter> {


        public abstract void navigateToSignUpScreen(@NonNull String moduleId, @NonNull String siteId);

        public abstract void navigateToMainScreen();

    }

    public static class AuthViewPresenterStrategy implements StateStrategy {


        public static final String TAG_SHOW_DIALOG_SELECT_SITE = "show_dialog_select_site";
        public static final String TAG_HIDE_DIALOG_SELECT_SITE = "hide_dialog_select_site";


        @Override
        public <View extends MvpView> void beforeApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {

            if (incomingCommand.getTag().equals(TAG_SHOW_DIALOG_SELECT_SITE)) {

                removeViewCommandShowDialogSelectSite(currentState);
                currentState.add(incomingCommand);

            } else if (incomingCommand.getTag().equals(TAG_HIDE_DIALOG_SELECT_SITE)) {

                removeViewCommandShowDialogSelectSite(currentState);

            }
        }

        @Override
        public <View extends MvpView> void afterApply(List<ViewCommand<View>> currentState, ViewCommand<View> incomingCommand) {

        }

        private <View extends MvpView> void removeViewCommandShowDialogSelectSite(List<ViewCommand<View>> currentState) {
            for (Iterator<ViewCommand<View>> iterator = currentState.iterator(); iterator.hasNext(); ) {
                ViewCommand<View> viewCommand = iterator.next();
                if (viewCommand.getTag().equals(TAG_SHOW_DIALOG_SELECT_SITE)) {
                    iterator.remove();
                    break;
                }
            }
        }

    }


}
