package com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter;

import android.support.annotation.NonNull;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.data.entity.AudioBook;
import com.zennex.trl3lg.domain.common.DefaultObserver;
import com.zennex.trl3lg.domain.rental.book.FetchMyAudioBooksInteractor;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.OnDemandScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.assembly.IOnDemandPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.main.submodule.ondemand.presenter.assembly.OnDemandPresenterModule;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by nikita on 22.07.17.
 */
@InjectViewState
public class OnDemandPresenter extends OnDemandScreenContract.AbstractOnDemandPresenter {

    @Inject
    FetchMyAudioBooksInteractor mFetchMyAudioBooksInteractor;
    private List<AudioBook> mBookList;

    public OnDemandPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        ((IOnDemandPresenterSubcomponent.Builder) presenterSubcomponentBuilders.getPresenterComponentBuilder(OnDemandPresenter.class))
                .presenterModule(new OnDemandPresenterModule(this))
                .build()
                .injectMembers(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mFetchMyAudioBooksInteractor.execute(new FetchMyAudioBooksObserver(false), null);
    }

    @Override
    protected TitleModel getTitle() {
        return new TitleModel.Builder()
                .setTitleMessageRes(R.string.on_demand_title)
                .build();
    }

    @Override
    public void onRefreshBooks() {
        mFetchMyAudioBooksInteractor.execute(new FetchMyAudioBooksObserver(true), null);
    }

    @Override
    public void onBookSelected(int position, PairArrayList<View, String> animaViews) {
        mRouter.showBookScreenWithAnim(mBookList.get(position), animaViews);
    }

    private class FetchMyAudioBooksObserver extends DefaultObserver<List<AudioBook>> {

        private final boolean isRefreshing;

        private FetchMyAudioBooksObserver(boolean isRefreshing) {
            this.isRefreshing = isRefreshing;
        }

        @Override
        public void onNext(List<AudioBook> audioBooks) {
            mBookList = new ArrayList<>(audioBooks);
            getViewState().showBooks(audioBooks);
            getViewState().hidePending();
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected void onStart() {
            getViewState().showPending(isRefreshing);
        }

        @Override
        protected String getTag() {
            return "FetchMyAudioBooksObserver";
        }
    }

}
