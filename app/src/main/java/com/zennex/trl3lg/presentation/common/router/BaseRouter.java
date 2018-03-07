package com.zennex.trl3lg.presentation.common.router;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by nikita on 11.03.2017.
 */
public abstract class BaseRouter<Adapter extends BaseRouter.BaseRouterAdapter> {

    public static final String TAG = "BaseRouter";

    @Nullable
    public Adapter mAdapter;

    public abstract Adapter createAdapter(@NonNull AppCompatActivity appCompatActivity);

    public void refreshActivityInAdapter(@NonNull AppCompatActivity appCompatActivity) {
        if (mAdapter == null) mAdapter = createAdapter(appCompatActivity);
        mAdapter.replaceActivity(appCompatActivity);
    }

    public void onBackPressed() {
        if (mAdapter != null) mAdapter.finish();
    }

    public void close() {
        if (mAdapter != null) mAdapter.finish();
    }

    public void navigateToActivity(@NonNull Intent intent) {
        if (mAdapter != null) mAdapter.navigateToActivity(intent);
    }

    public void navigateToActivity(@NonNull Intent intent, Bundle bundle) {
        if (mAdapter != null) mAdapter.navigateToActivity(intent, bundle);
    }

    public static class BaseRouterAdapter {

        protected AppCompatActivity mActivity;

        public void replaceActivity(AppCompatActivity appCompatActivity) {
            this.mActivity = appCompatActivity;
        }

        public BaseRouterAdapter(AppCompatActivity appCompatActivity) {
            mActivity = appCompatActivity;
        }

        public void navigateToActivity(@NonNull Intent intent) {
            mActivity.startActivity(intent);
        }

        public void navigateToActivity(@NonNull Intent intent, Bundle bundle) {
            if (mActivity != null) mActivity.startActivity(intent, bundle);
        }

        public void navigateToActivity(@NonNull Class<?> c) {
            if (mActivity != null) mActivity.startActivity(new Intent(mActivity, c));
        }

        public void finish() {
            if (mActivity != null) mActivity.finish();
        }

        public AppCompatActivity getActivity() {
            return mActivity;
        }
    }


}
