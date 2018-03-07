package com.zennex.trl3lg.presentation.module.bookimagezoom;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.BaseRouter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.common.view.ViperBaseActivity;
import com.zennex.trl3lg.presentation.module.bookimagezoom.router.BookZoomRouter;

/**
 * Created by nikit on 10.09.2017.
 */

public class BookImageZoomModuleContract {

    private BookImageZoomModuleContract() {
    }


    public interface IBookZoomView extends IBaseView {

        void showImage(String imagePath);

    }

    public abstract static class AbstractBookZoomView extends ViperBaseActivity<AbstractBookZoomPresenter, AbstractBookZoomRouter>
            implements IBookZoomView {

        @Override
        protected AbstractBookZoomRouter resolveRouter() {
            return new BookZoomRouter();
        }
    }

    public abstract static class AbstractBookZoomPresenter extends ViperBasePresenter<IBookZoomView, AbstractBookZoomRouter> {
        protected String mBookTitle;
        protected String mBookImagePath;

        public AbstractBookZoomPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public String getBookTitle() {
            return mBookTitle;
        }

        public void setBookTitle(String bookTitle) {
            mBookTitle = bookTitle;
        }

        public String getBookImagePath() {
            return mBookImagePath;
        }

        public void setBookImagePath(String bookImagePath) {
            mBookImagePath = bookImagePath;
        }
    }


    public abstract static class AbstractBookZoomRouter extends BaseRouter<BaseRouter.BaseRouterAdapter> {

    }

}
