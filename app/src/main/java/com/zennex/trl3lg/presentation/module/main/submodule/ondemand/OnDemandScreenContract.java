package com.zennex.trl3lg.presentation.module.main.submodule.ondemand;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.zennex.trl3lg.domain.entities.AudioBook;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.view.BaseListViperFragment;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.view.OnDemandBookListAdapter;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.List;

/**
 * Created by nikita on 22.07.17.
 */

public abstract class OnDemandScreenContract {

    private OnDemandScreenContract() {
        throw new RuntimeException("no instance please!");
    }


    public interface IOnDemandView extends IBaseView {

        void showBooks(List<AudioBook> audioBooks);

        void showPending(boolean isRefreshing);

    }

    public static abstract class AbstractOnDemandView extends BaseListViperFragment<
            AbstractOnDemandPresenter,
            MainScreenContract.AbstractMainRouter,
            AudioBook,
            OnDemandBookListAdapter,
            LinearLayoutManager> implements OnDemandScreenContract.IOnDemandView {

    }


    public static abstract class AbstractOnDemandPresenter extends ViperBasePresenter<IOnDemandView, MainScreenContract.AbstractMainRouter> {

        public AbstractOnDemandPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public abstract void onBookSelected(int position, PairArrayList<View, String> animaViews);

        public abstract void onBookActivated(int position);

        public abstract void onRefreshBooks();

    }


}
