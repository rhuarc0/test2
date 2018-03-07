package com.zennex.trl3lg.presentation.module.bookdescription;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.router.BaseRouter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.common.view.ViperBaseActivity;
import com.zennex.trl3lg.presentation.module.bookdescription.router.BookDescriptionRouter;

/**
 * Created by nikita on 08.09.17.
 */

public class BookDescriptionModuleContract {


    private BookDescriptionModuleContract() {
        throw new RuntimeException(" no instance please!");
    }

    public interface IBookDescriptionView extends IBaseView {

        void showDescription(String description);

    }

    public static abstract class AbstractBookDescriptionView extends ViperBaseActivity<AbstractBookDescriptionPresenter, AbstractBookDescriptionRouter>
            implements IBookDescriptionView {
        @Override
        protected AbstractBookDescriptionRouter resolveRouter() {
            return new BookDescriptionRouter();
        }
    }

    public static abstract class AbstractBookDescriptionPresenter extends ViperBasePresenter<IBookDescriptionView, AbstractBookDescriptionRouter> {
        protected String mBookTitle;
        protected String mBookDescription;

        public AbstractBookDescriptionPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }

        public String getBookTitle() {
            return mBookTitle;
        }

        public void setBookTitle(String bookTitle) {
            mBookTitle = bookTitle;
        }

        public String getBookDescription() {
            return mBookDescription;
        }

        public void setBookDescription(String bookDescription) {
            mBookDescription = bookDescription;
        }

        public abstract void onClickCollapse();

    }


    public abstract static class AbstractBookDescriptionRouter extends BaseRouter<BaseRouter.BaseRouterAdapter> {

    }
}
