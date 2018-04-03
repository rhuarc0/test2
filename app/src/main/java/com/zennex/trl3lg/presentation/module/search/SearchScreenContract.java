package com.zennex.trl3lg.presentation.module.search;

import android.support.annotation.NonNull;
import android.view.View;

import com.zennex.trl3lg.domain.entities.Book;
import com.zennex.trl3lg.domain.usecases.rentalbook.FetchBooks;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;

import com.zennex.trl3lg.presentation.common.router.BaseRouter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.utils.PairArrayList;

import java.util.List;

public abstract class SearchScreenContract {

    private SearchScreenContract() {
        throw new RuntimeException("no instance please!");
    }

    public interface ISearchView extends IBaseView {

        void showBooks(@NonNull List<Book> bookList);

        void showPendingLoadBooks(boolean isRefreshing);

        void hidePendingLoadBooks(boolean isRefreshing);

        void showFooterPendingLoadBooks();

        void hideFooterPendingLoadBooks();

        void clearData();

        void refreshStatusFields();
    }

    public static abstract class AbstractSearchPresenter extends ViperBasePresenter<ISearchView, AbstractSearchScreenRouter> {

        public AbstractSearchPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public abstract void setKeywordSearch(String keywordSearch);

        public abstract FetchBooks.TypeBooks getCurrentTypeBookFilter();
        public abstract void setTypeBookFilter(FetchBooks.TypeBooks typeBookFilter);

        public abstract void setRentalGroupId(String rentalGroupId);

        public abstract void onRefreshView();

        public abstract void onBooksScrolled(int lastVisibleBook);

        public abstract void onClickedBtnSearch(String keywordSearch);

        public abstract void onBookSelected(int position, PairArrayList<View, String> animViews);

    }

    public static abstract class AbstractSearchScreenRouter extends BaseRouter<BaseRouter.BaseRouterAdapter> {


        public abstract void showBookScreenWithAnim(Book book, PairArrayList<View, String> animView);

    }


}
