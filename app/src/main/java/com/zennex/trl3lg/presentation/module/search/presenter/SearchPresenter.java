package com.zennex.trl3lg.presentation.module.search.presenter;

import android.support.annotation.NonNull;
import android.view.View;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.domain.usecases.common.DefaultObserver;
import com.zennex.trl3lg.domain.usecases.rentalbook.FetchBooks;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.search.SearchScreenContract;
import com.zennex.trl3lg.presentation.module.search.presenter.assembly.ISearchPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.search.presenter.assembly.SearchPresenterModule;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by nikit on 01.08.2017.
 */

@InjectViewState
public class SearchPresenter extends SearchScreenContract.AbstractSearchPresenter {
    private final short QUANTITY_BOOKS_REQUESTED = 20;

    @Inject
    FetchBooks mFetchBooks;
    private boolean mAllUploaded = false;
    private List<Book> mShownBooks;
    private String mKeywordSearch;
    private String mRentalGroupId;

    public SearchPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    public void setKeywordSearch(String keywordSearch) {
        mKeywordSearch = keywordSearch;
    }

    @Override
    public void setRentalGroupId(String rentalGroupId) {
        mRentalGroupId = rentalGroupId;
    }

    @Override
    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        ((ISearchPresenterSubcomponent.Builder) presenterSubcomponentBuilders.getPresenterComponentBuilder(SearchPresenter.class))
                .presenterModule(new SearchPresenterModule(this))
                .build()
                .injectMembers(this);
    }

    private boolean isAllUploaded() {
        return mAllUploaded;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mAllUploaded = false;
        mFetchBooks.execute(new FetchBookListObserver(false, false),
                new FetchBooks.Params(mKeywordSearch, null, QUANTITY_BOOKS_REQUESTED, 0, mRentalGroupId));
    }

    @Override
    public void onBookSelected(int position, PairArrayList<View, String> animViews) {
        mRouter.showBookScreenWithAnim(mShownBooks.get(position), animViews);
    }

    @Override
    protected TitleModel getTitle() {
        return null;
    }

    @Override
    public void onRefreshView() {
        mAllUploaded = false;
        mFetchBooks.execute(new FetchBookListObserver(false, true),
                new FetchBooks.Params(mKeywordSearch, null, QUANTITY_BOOKS_REQUESTED, 0, mRentalGroupId));
        if (mShownBooks != null) mShownBooks.clear();
    }

    @Override
    public void onBooksScrolled(int lastVisibleBook) {
        if (isCanLoadBooks(lastVisibleBook)) {
            mFetchBooks.execute(new FetchBookListObserver(true, false),
                    new FetchBooks.Params(mKeywordSearch,
                            null,
                            QUANTITY_BOOKS_REQUESTED,
                            mShownBooks == null ? 0 : mShownBooks.size(), mRentalGroupId));
        }
    }

    private boolean isCanLoadBooks(int lastVisibleBook) {
        return !mFetchBooks.isRun() &&
                ((mShownBooks.size() - lastVisibleBook) <= 5) &&
                !isAllUploaded();
    }

    @Override
    public void onClickedBtnSearch(String keywordSearch) {
        mKeywordSearch = keywordSearch;
        getViewState().clearData();
        if (mShownBooks != null) mShownBooks.clear();
        mAllUploaded = false;
        mFetchBooks.execute(new FetchBookListObserver(false, false),
                new FetchBooks.Params(keywordSearch, null, QUANTITY_BOOKS_REQUESTED, 0, mRentalGroupId));

    }

    private class FetchBookListObserver extends DefaultObserver<List<Book>> {

        private final boolean mLoadFromUserScrolled;
        private final boolean mLoadFromRefreshing;

        private FetchBookListObserver(boolean loadFromUserScrolled, boolean loadFromRefreshing) {
            mLoadFromUserScrolled = loadFromUserScrolled;
            mLoadFromRefreshing = loadFromRefreshing;
        }

        boolean isLoadFromUserScrolled() {
            return mLoadFromUserScrolled;
        }

        public boolean isLoadFromRefreshing() {
            return mLoadFromRefreshing;
        }

        @Override
        public void onNext(List<Book> books) {
            if (books.size() < QUANTITY_BOOKS_REQUESTED) mAllUploaded = true;
            if (mShownBooks != null) mShownBooks.addAll(books);
            else mShownBooks = new ArrayList<>(books);
            getViewState().showBooks(new ArrayList<>(mShownBooks));
            hidePending();
        }

        @Override
        public void onError(Throwable e) {
            hidePending();
        }

        @Override
        protected void onStart() {
            if (isLoadFromUserScrolled()) getViewState().showFooterPendingLoadBooks();
            else getViewState().showPendingLoadBooks(isLoadFromRefreshing());
        }

        private void hidePending() {
            if (isLoadFromUserScrolled()) getViewState().hideFooterPendingLoadBooks();
            else getViewState().hidePendingLoadBooks(isLoadFromRefreshing());
        }

        @Override
        protected String getTag() {
            return "FetchBookListObserver";
        }
    }
}
