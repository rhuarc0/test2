package com.zennex.trl3lg.presentation.module.book.submodule.myreview.comemnt;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.common.presenter.ViperBasePresenter;
import com.zennex.trl3lg.presentation.common.view.IBaseView;
import com.zennex.trl3lg.presentation.common.view.ViperBaseFragment;
import com.zennex.trl3lg.presentation.module.book.BookModuleContract;

/**
 * Created by nikita on 28.09.17.
 */

public class MyCommentModuleContract {

    private MyCommentModuleContract() {
        throw new RuntimeException(" no instance please!");
    }

    public interface IMyCommentView extends IBaseView {

    }

    public static abstract class AbstractMyCommentViewFragment extends ViperBaseFragment<AbstractMyCommentPresenter, BookModuleContract.AbstractBookRouter>
            implements IMyCommentView {

    }

    public static abstract class AbstractMyCommentPresenter extends ViperBasePresenter<IMyCommentView, BookModuleContract.AbstractBookRouter> {

        public AbstractMyCommentPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
            super(presenterSubcomponentBuilders);
        }
    }

}
