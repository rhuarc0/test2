package com.zennex.trl3lg.presentation.common.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.arellomobile.mvp.MvpPresenter;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.router.BaseRouter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.model.TitleModel;

public abstract class ViperBasePresenter<View extends IBaseView, Router extends BaseRouter>
        extends MvpPresenter<View> {

    public static final String TAG = "ViperBasePresenter";

    protected Router mRouter;


    public ViperBasePresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        inject(presenterSubcomponentBuilders);
    }

    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().prepareScreen();
        updateTitle();
    }

    public void setRouter(Router router) {
        mRouter = router;
    }

    public Router getRouter() {
        return mRouter;
    }

    public boolean onBackPressed() {
        mRouter.onBackPressed();
        return true;
    }

    protected abstract TitleModel getTitle();

    protected void onBindTitle(TitleModel title) {
        if (title == null || getViewState() == null) return;
        if (title.getTitleMessageRes() != 0) {
            getViewState().setTitleText(title.getTitleMessageRes());
        }

        if (!TextUtils.isEmpty(title.getTitleMessage())) {
            getViewState().setTitleText(title.getTitleMessage());
        }

        getViewState().setVisibilityBackButton(title.isVisibleBackButton());
        if (!TextUtils.isEmpty(title.getImageWeb())) {
            getViewState().setTitleImage(title.getImageWeb());
        }
    }

    public void updateTitle() {
        onBindTitle(getTitle());
    }

}
