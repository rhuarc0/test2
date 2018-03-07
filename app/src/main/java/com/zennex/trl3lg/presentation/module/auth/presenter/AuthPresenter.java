package com.zennex.trl3lg.presentation.module.auth.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.data.entity.AuthData;
import com.zennex.trl3lg.data.entity.Site;
import com.zennex.trl3lg.domain.auth.FilterSitesFromLogin2Interactor;
import com.zennex.trl3lg.domain.auth.GetSiteAndModuleIdByPosition;
import com.zennex.trl3lg.domain.auth.IsValidSessionTokenInteractor;
import com.zennex.trl3lg.domain.auth.LoginInteractor;
import com.zennex.trl3lg.domain.common.DefaultObserver;
import com.zennex.trl3lg.domain.site.GetSiteIdInteractor;
import com.zennex.trl3lg.domain.site.GetSitesAndModulesInteractor;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.mapper.rx.SiteListMapperInteractor;
import com.zennex.trl3lg.presentation.mapper.rx.SiteModelMapperInteraction;
import com.zennex.trl3lg.presentation.model.SiteModel;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.auth.AuthScreenContract;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.AuthPresenterModule;
import com.zennex.trl3lg.presentation.module.auth.presenter.assembly.IAuthPresenterSubcomponent;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nikita on 02.06.17.
 */
@InjectViewState
public class AuthPresenter extends AuthScreenContract.AbstractAuthPresenter {

    @Inject
    LoginInteractor mLoginInteractor;

    @Inject
    GetSitesAndModulesInteractor mGetSitesAndModulesInteractor;

    @Inject
    SiteListMapperInteractor mSiteListMapperInteractor;

    @Inject
    FilterSitesFromLogin2Interactor mFilterSitesFromLogin2Interactor;

    @Inject
    SiteModelMapperInteraction mSiteModelMapperInteraction;

    @Inject
    GetSiteAndModuleIdByPosition mGetSiteAndModuleIdByPosition;

    @Inject
    IsValidSessionTokenInteractor mValidSessionTokenInteractor;

    private List<Site> mSites;

    @Inject
    GetSiteIdInteractor mGetSiteIdInteractor;

    private boolean mViewPrepared;

    public AuthPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);

    }

    private boolean isViewPrepared() {
        return mViewPrepared;
    }

    //region AuthScreenContract.AbstractAuthPresenter

    @Override
    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        ((IAuthPresenterSubcomponent.Builder) presenterSubcomponentBuilders.getPresenterComponentBuilder(AuthPresenter.class))
                .presenterModule(new AuthPresenterModule(this))
                .build()
                .injectMembers(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().prepareContent();
    }

    @Override
    public void attachView(AuthScreenContract.IAuthView view) {
        super.attachView(view);
        if (!isViewPrepared()) getViewState().prepareContent();
    }

    @Override
    protected TitleModel getTitle() {
        return new TitleModel.Builder()
                .setTitleMessageRes(R.string.app_name)
                .build();
    }

    @Override
    public void onFinishedPrepareContent() {
        mViewPrepared = true;
        getViewState().showContent();
        mValidSessionTokenInteractor.execute(new IsValidSessionTokenInteractorObserver(), null);
    }

    @Override
    public void onClickSignIn(String email, String password) {
        mLoginInteractor.execute(new LoginInteractorObserver(), new LoginInteractor.Params(email, password, null, mSites));
    }

    @Override
    public void onClickSignUp() {
        mSiteListMapperInteractor.execute(new SiteListMapperInteractorForSelectDomainForNewUserObserver(), mSites);
    }

    @Override
    public void onSelectDomain(String email, String password, int pos) {
        getViewState().hideDialogSelectSite();
        mLoginInteractor.execute(new LoginInteractorObserver(),
                new LoginInteractor.Params(email, password, mLoginInteractor.getModuleIdsFromLogin2().get(pos), mSites));
    }

    @Override
    public void onSelectDomainForNewUser(int pos) {
        getViewState().hideDialogSelectSite();
        mGetSiteAndModuleIdByPosition.execute(new FetchSiteAndModuleByPositionObserver(), new GetSiteAndModuleIdByPosition.Params(mSites, pos));
    }

    @Override
    public void onClickCancelDialogSelectSite() {
        getViewState().hideDialogSelectSite();
    }

    //endregion AuthScreenContract.AbstractAuthPresenter

    //region LoginInteractorObserver

    private class LoginInteractorObserver extends DefaultObserver<AuthData> {

        @Override
        protected void onStart() {
            getViewState().showPending(R.string.please_wait);
        }

        @Override
        public void onNext(AuthData authData) {
            if (authData.getModuleIds() != null) {
                mFilterSitesFromLogin2Interactor.execute(new FilterSitesFromLogin2InteractorObserver(),
                        new FilterSitesFromLogin2Interactor.Params(mSites, authData.getModuleIds()));
            } else {
                getRouter().navigateToMainScreen();
                getRouter().close();
            }
            getViewState().hidePending();
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected String getTag() {
            return "LoginInteractorObserver";
        }
    }

    //endregion LoginInteractorObserver

    //region FetchSitesInteractorObserver

    private class FetchSitesInteractorObserver extends DefaultObserver<List<Site>> {

        @Override
        protected void onStart() {
            getViewState().showPending(null);
        }

        @Override
        public void onNext(List<Site> sites) {
            mSites = sites;
            getViewState().hidePending();
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected String getTag() {
            return "FetchSitesInteractorObserver";
        }
    }

    //endregion FetchSitesInteractorObserver

    //region FilterSitesFromLogin2InteractorObserver

    private class FilterSitesFromLogin2InteractorObserver extends DefaultObserver<List<Site>> {

        @Override
        protected void onStart() {
            getViewState().showPending(0);
        }

        @Override
        public void onNext(List<Site> sites) {
            mSiteListMapperInteractor.execute(new SiteListMapperInteractorForSelectDomainObserver(), sites);
            getViewState().hidePending();
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected String getTag() {
            return "FilterSitesFromLogin2InteractorObserver";
        }
    }

    //endregion FilterSitesFromLogin2InteractorObserver

    //region SiteListMapperInteractorForSelectDomainObserver

    private class SiteListMapperInteractorForSelectDomainObserver extends DefaultObserver<List<SiteModel>> {

        @Override
        protected void onStart() {
            getViewState().showPending(0);
        }

        @Override
        public void onNext(List<SiteModel> siteModels) {
            getViewState().showDialogSelectSite(siteModels);
            getViewState().hidePending();
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected String getTag() {
            return "SiteListMapperInteractorForSelectDomainObserver";
        }
    }

    //endregion SiteListMapperInteractorForSelectDomainObserver

    //region SiteListMapperInteractorForSelectDomainForNewUserObserver

    private class SiteListMapperInteractorForSelectDomainForNewUserObserver extends DefaultObserver<List<SiteModel>> {

        @Override
        public void onNext(List<SiteModel> siteModels) {
            if (siteModels != null && !siteModels.isEmpty()) {
                getViewState().showDialogSelectSiteForNewUser(siteModels);
            }
            getViewState().hidePending();
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected void onStart() {
            getViewState().showPending(0);
        }

        @Override
        protected String getTag() {
            return "SiteListMapperInteractorForSelectDomainForNewUserObserver";
        }
    }

    //endregion SiteListMapperInteractorForSelectDomainForNewUserObserver

    //region FetchSiteAndModuleByPositionObserver

    private class FetchSiteAndModuleByPositionObserver extends DefaultObserver<GetSiteAndModuleIdByPosition.Result> {

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected void onStart() {
            getViewState().showPending(0);
        }

        @Override
        public void onNext(GetSiteAndModuleIdByPosition.Result result) {
            getRouter().navigateToSignUpScreen(result.getModuleId(), result.getSite().getId());
            getViewState().hidePending();
        }

        @Override
        protected String getTag() {
            return "FetchSiteAndModuleByPositionObserver";
        }
    }

    //endregion FetchSiteAndModuleByPositionObserver

    //region IsValidSessionTokenInteractorObserver

    private class IsValidSessionTokenInteractorObserver extends DefaultObserver<Boolean> {

        @Override
        public void onNext(Boolean aBoolean) {
            if (aBoolean) {
                getRouter().navigateToMainScreen();
                getRouter().close();
            } else mGetSitesAndModulesInteractor.execute(new FetchSitesInteractorObserver(), null);
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        protected void onStart() {
        }

        @Override
        protected String getTag() {
            return "IsValidSessionTokenInteractorObserver";
        }
    }

    //endregion IsValidSessionTokenInteractorObserver

}
