package com.zennex.trl3lg.presentation.module.auth.router;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.zennex.trl3lg.presentation.module.auth.AuthScreenContract;
import com.zennex.trl3lg.presentation.module.main.view.MainActivity;
import com.zennex.trl3lg.presentation.module.signup.view.SignUpActivity;

/**
 * Created by nikita on 02.06.17.
 */

public class AuthRouter extends AuthScreenContract.AbstractAuthRouter {


    @Override
    public void navigateToSignUpScreen(@NonNull String moduleId, @NonNull String siteId) {
        if (mAdapter != null) {
            mAdapter.navigateToActivity(SignUpActivity
                    .newIntent(mAdapter.getActivity(), moduleId, siteId));
        }
    }

    @Override
    public void navigateToMainScreen() {
        if (mAdapter != null) {
            mAdapter.navigateToActivity(MainActivity.newIntent(mAdapter.getActivity()));
        }
    }

    @Override
    public BaseRouterAdapter createAdapter(@NonNull AppCompatActivity appCompatActivity) {
        return new BaseRouterAdapter(appCompatActivity);
    }
}
