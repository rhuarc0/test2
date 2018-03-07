package com.zennex.trl3lg.presentation.module.signup.router;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zennex.trl3lg.presentation.module.signup.SignUpContract;

/**
 * Created by nikita on 12.06.17.
 */

public class SignUpRouter extends SignUpContract.AbstractSignUpRouter {


    @Override
    public BaseRouterAdapter createAdapter(@NonNull AppCompatActivity appCompatActivity) {
        return new BaseRouterAdapter(appCompatActivity);
    }
}
