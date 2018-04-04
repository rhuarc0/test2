package com.zennex.trl3lg.presentation.module.search.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.annimon.stream.Stream;
import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.domain.usecases.common.DefaultObserver;
import com.zennex.trl3lg.domain.usecases.rentalbook.FetchBooks;
import com.zennex.trl3lg.domain.usecases.rentalbook.FetchHistory;
import com.zennex.trl3lg.domain.usecases.rentalbook.FetchQueueIdsAndHistory;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.search.SearchScreenContract;
import com.zennex.trl3lg.presentation.module.search.presenter.assembly.ISearchPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.search.presenter.assembly.SearchPresenterModule;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@InjectViewState
public class SearchPresenter extends SearchScreenContract.AbstractSearchPresenter {
    private final short QUANTITY_BOOKS_REQUESTED = 20;

    @Inject
    FetchBooks mFetchBooks;
    @Inject
    FetchQueueIdsAndHistory fetchQueueIdsAndHistory;
    @Inject
    FetchHistory fetchHistory;

    private boolean mAllUploaded = false;

    private List<Book> mShownBooks = new ArrayList<>();
    private List<String> queueIds = new ArrayList<>();
    private List<String> history = new ArrayList<>();

    private String mKeywordSearch;
    private FetchBooks.TypeBooks typeBookFilter = FetchBooks.TypeBooks.All;
    private String mRentalGroupId;

    public SearchPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    public void setKeywordSearch(String keywordSearch) {
        mKeywordSearch = keywordSearch;
    }

    @Override
    public FetchBooks.TypeBooks getCurrentTypeBookFilter() {
        return typeBookFilter;
    }

    @Override
    public void setTypeBookFilter(FetchBooks.TypeBooks typeBookFilter) {
        this.typeBookFilter = typeBookFilter;
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

        mFetchBooks.execute(
                new FetchBookListObserver(false, false),
                new FetchBooks.Params(mKeywordSearch,
                        typeBookFilter,
                        QUANTITY_BOOKS_REQUESTED,
                        0,
                        mRentalGroupId));

        fetchQueueIdsAndHistory.execute(new FetchQueueAndHistoryObserver(), null);
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
        mShownBooks.clear();
        mAllUploaded = false;
        mFetchBooks.execute(
                new FetchBookListObserver(false, true),
                new FetchBooks.Params(mKeywordSearch,
                        typeBookFilter,
                        QUANTITY_BOOKS_REQUESTED,
                        0,
                        mRentalGroupId)
        );
    }

    @Override
    public void onBooksScrolled(int lastVisibleBook) {
        if (сanLoadBooks(lastVisibleBook)) {
            mFetchBooks.execute(
                new FetchBookListObserver(true, false),
                new FetchBooks.Params(mKeywordSearch,
                        typeBookFilter,
                        QUANTITY_BOOKS_REQUESTED,
                        mShownBooks.size(),
                        mRentalGroupId)
            );
        }
    }

    private boolean сanLoadBooks(int lastVisibleBook) {
        return !mFetchBooks.isRun() &&
                ((mShownBooks.size() - lastVisibleBook) <= 5) &&
                !isAllUploaded();
    }

    @Override
    public void onClickedBtnSearch(String keywordSearch) {
        mKeywordSearch = keywordSearch;
        getViewState().clearData();

        mShownBooks.clear();
        mAllUploaded = false;
        mFetchBooks.execute(
            new FetchBookListObserver(false, false),
            new FetchBooks.Params(keywordSearch,
                    typeBookFilter,
                    QUANTITY_BOOKS_REQUESTED,
                    0,
                    mRentalGroupId)
        );

    }

    private void refreshStatusFields() {
        Stream.of(mShownBooks)
                .filter(value -> queueIds.contains(value.getId()))
                .forEach(book -> book.setAddedToQueue(true));

        Stream.of(mShownBooks)
                .filter(value -> history.contains(value.getId()))
                .forEach(book -> book.setPreviouslyRented(true));

        getViewState().refreshStatusFields();
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
            if (books.size() < QUANTITY_BOOKS_REQUESTED)
                mAllUploaded = true;

            mShownBooks.addAll(books);
            getViewState().showBooks(new ArrayList<>(mShownBooks));
            if (!fetchQueueIdsAndHistory.isRun())
                refreshStatusFields();
            hidePending();
        }

        @Override
        public void onError(Throwable e) {
            hidePending();
        }

        @Override
        protected void onStart() {
            if (isLoadFromUserScrolled())
                getViewState().showFooterPendingLoadBooks();
            else
                getViewState().showPendingLoadBooks(isLoadFromRefreshing());
        }

        private void hidePending() {
            if (isLoadFromUserScrolled())
                getViewState().hideFooterPendingLoadBooks();
            else
                getViewState().hidePendingLoadBooks(isLoadFromRefreshing());
        }

        @Override
        protected String getTag() {
            return "FetchBookListObserver";
        }

    }
    private class FetchQueueAndHistoryObserver extends DefaultObserver<Pair<List<String>, List<String>>> {

        @Override
        public void onNext(Pair<List<String>, List<String>> queueAndHistoryPair) {
            super.onNext(queueAndHistoryPair);
            queueIds.clear();
            queueIds.addAll(queueAndHistoryPair.first);
            history.clear();
            history.addAll(queueAndHistoryPair.second);

            if (!mFetchBooks.isRun())
                refreshStatusFields();
        }
        @Override
        protected String getTag() {
            return FetchQueueAndHistoryObserver.class.getSimpleName();
        }

    }
}
