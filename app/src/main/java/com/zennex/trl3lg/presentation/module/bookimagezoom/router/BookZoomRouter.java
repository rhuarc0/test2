package com.zennex.trl3lg.presentation.module.bookimagezoom.router;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zennex.trl3lg.presentation.module.bookimagezoom.BookImageZoomModuleContract;

/**
 * Created by nikita on 12.09.17.
 */

public class BookZoomRouter extends BookImageZoomModuleContract.AbstractBookZoomRouter {

    @Override
    public BaseRouterAdapter createAdapter(@NonNull AppCompatActivity appCompatActivity) {
        return new BaseRouterAdapter(appCompatActivity){
            @Override
            public void finish() {
                mActivity.finishAfterTransition();
            }
        };
    }
}
