package com.zennex.trl3lg.presentation.module.bookdescription.router;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zennex.trl3lg.presentation.module.bookdescription.BookDescriptionModuleContract;

/**
 * Created by nikit on 09.09.2017.
 */

public class BookDescriptionRouter extends BookDescriptionModuleContract.AbstractBookDescriptionRouter {


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
