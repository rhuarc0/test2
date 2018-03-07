package com.zennex.trl3lg.presentation.module.main.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.data.entity.RentalGroup;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;

/**
 * Created by nikita on 22.07.17.
 */
@InjectViewState
public class MainPresenter extends MainScreenContract.AbstractMainPresenter {

    public MainPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    protected TitleModel getTitle() {
        return null;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getRouter().showCatalogScreen();
    }

    @Override
    public void onClickedBtnSearch(String query) {
        getRouter().showSearchBooksScreen(query);
    }

    @Override
    public void onCatalogTabSelected() {
        getRouter().showCatalogScreen();
    }

    @Override
    public void onOnDemandTabSelected() {
        getRouter().showOnDemandScren();
    }

    @Override
    public void onRentalTabSelected() {
        getRouter().showOnRentalScreen();
    }

    @Override
    public void onUserTabSelected() {
        getRouter().showUserScreen();
    }

    @Override
    public void onRentalGroupSelected(RentalGroup rentalGroup, String query) {
        getRouter().showSearchBooksScreen(rentalGroup.getId(), query);
    }
}
