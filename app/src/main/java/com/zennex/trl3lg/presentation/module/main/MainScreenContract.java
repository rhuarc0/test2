package com.zennex.trl3lg.presentation.module.main;

import android.support.annotation.NonNull;
import android.view.View;

import com.zennex.trl3lg.data.entity.AudioBook;
import com.zennex.trl3lg.data.entity.RentalGroup;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.SupportFragmentRouter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.common.view.ViperBaseActivity;
import com.zennex.trl3lg.presentation.module.main.router.MainRouter;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

/**
 * Created by nikita on 22.07.17.
 */

public abstract class MainScreenContract {

    private MainScreenContract() {
        throw new RuntimeException("no instance please!");
    }


    public interface IMainView extends IBaseView {

    }

    public static abstract class AbstractMainView extends ViperBaseActivity<AbstractMainPresenter, AbstractMainRouter>
            implements IMainView {

        @Override
        protected AbstractMainRouter resolveRouter() {
            return new MainRouter();
        }
    }

    public static abstract class AbstractMainPresenter extends ViperBasePresenter<IMainView, AbstractMainRouter> {


        public AbstractMainPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public abstract void onCatalogTabSelected();

        public abstract void onOnDemandTabSelected();

        public abstract void onRentalTabSelected();

        public abstract void onUserTabSelected();

        public abstract void onClickedBtnSearch(String query);

        public abstract void onRentalGroupSelected(RentalGroup rentalGroup, String query);

    }

    public static abstract class AbstractMainRouter extends SupportFragmentRouter<SupportFragmentRouter.SupportFragmentRouterAdapter> {

        public abstract void showCatalogScreen();

        public abstract void showOnDemandScren();

        public abstract void showOnRentalScreen();

        public abstract void showUserScreen();

        public abstract void showSearchBooksScreen(long rentalGroupId, String query);

        public abstract void showSearchBooksScreen(String query);

        public abstract void showBookScreenWithAnim(AudioBook audioBook, PairArrayList<View, String> animaViews);

    }
}
