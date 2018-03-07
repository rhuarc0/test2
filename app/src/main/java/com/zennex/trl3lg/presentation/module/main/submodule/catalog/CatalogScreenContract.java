package com.zennex.trl3lg.presentation.module.main.submodule.catalog;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.zennex.trl3lg.data.entity.RentalGroup;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.view.BaseListViperFragment;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.view.CatalogRecyclerViewAdapter;

import java.util.List;

/**
 * Created by nikita on 22.07.17.
 */

public abstract class CatalogScreenContract {

    private CatalogScreenContract() {
        throw new RuntimeException("no instance please!");
    }

    public interface ICatalogEventListener {

        void onRentalGroupSelected(RentalGroup group);

    }

    public interface ICatalogView extends IBaseView {

        void showCatalog(List<RentalGroup> rentalGroups);
    }


    public static abstract class AbstractCatalogView extends BaseListViperFragment<AbstractCatalogPresenter,
            MainScreenContract.AbstractMainRouter,
            RentalGroup,
            CatalogRecyclerViewAdapter,
            LinearLayoutManager> implements CatalogScreenContract.ICatalogView {

        @NonNull
        @Override
        protected MainScreenContract.AbstractMainRouter getRouter() {
            return ((MainScreenContract.AbstractMainView) getActivity()).getPresenter().getRouter();
        }

    }


    public static abstract class AbstractCatalogPresenter extends ViperBasePresenter<ICatalogView, MainScreenContract.AbstractMainRouter> {

        public AbstractCatalogPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public abstract void onRefreshLayout();

        public abstract void onSelectedRentalGroup(int position);

        public abstract void setCatalogEventListener(ICatalogEventListener catalogEventListener);

        public abstract ICatalogEventListener getCatalogEventListener();

    }


}
