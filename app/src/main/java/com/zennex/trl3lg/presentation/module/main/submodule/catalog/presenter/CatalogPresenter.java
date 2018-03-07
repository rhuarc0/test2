package com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.data.entity.RentalGroup;
import com.zennex.trl3lg.domain.common.DefaultObserver;
import com.zennex.trl3lg.domain.rental.book.FetchRentalBookGroupsInteractor;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.CatalogScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.assembly.CatalogPresenterModule;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.presenter.assembly.ICatalogPresenterSubcomponent;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nikita on 22.07.17.
 */
@InjectViewState
public class CatalogPresenter extends CatalogScreenContract.AbstractCatalogPresenter {

    @Inject
    FetchRentalBookGroupsInteractor mGetRentalBookGroupsInteractor;

    private List<RentalGroup> mShownRentalGroups;
    private CatalogScreenContract.ICatalogEventListener mCatalogEventListener;


    public CatalogPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        ((ICatalogPresenterSubcomponent.Builder) presenterSubcomponentBuilders.getPresenterComponentBuilder(CatalogPresenter.class))
                .presenterModule(new CatalogPresenterModule(this))
                .build()
                .injectMembers(this);
    }


    @Override
    public void onRefreshLayout() {
        getGroups();
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getGroups();
    }

    private void getGroups() {
        mGetRentalBookGroupsInteractor.execute(new FetchRentalBookGroupsObserver(), null);
    }


    @Override
    protected TitleModel getTitle() {
        return new TitleModel.Builder()
                .setTitleMessageRes(R.string.catalog_title)
                .build();
    }


    @Override
    public void onSelectedRentalGroup(int position) {
        mCatalogEventListener.onRentalGroupSelected(mShownRentalGroups.get(position));
    }

    @Override
    public void setCatalogEventListener(CatalogScreenContract.ICatalogEventListener catalogEventListener) {
        mCatalogEventListener = catalogEventListener;
    }

    @Override
    public CatalogScreenContract.ICatalogEventListener getCatalogEventListener() {
        return mCatalogEventListener;
    }

    //region FetchRentalBookGroupsObserver

    private class FetchRentalBookGroupsObserver extends DefaultObserver<List<RentalGroup>> {

        @Override
        public void onNext(List<RentalGroup> rentalGroups) {
            mShownRentalGroups = rentalGroups;
            getViewState().showCatalog(rentalGroups);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected void onStart() {
        }

        @Override
        protected String getTag() {
            return "FetchRentalBookGroupsObserver";
        }
    }

    //endregion FetchRentalBookGroupsObserver

}
