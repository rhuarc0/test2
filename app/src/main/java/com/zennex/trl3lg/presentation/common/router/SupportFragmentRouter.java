package com.zennex.trl3lg.presentation.common.router;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.zennex.trl3lg.presentation.common.router.fragmenttransaction.AddFragmentTransaction;
import com.zennex.trl3lg.presentation.common.router.fragmenttransaction.FragmentTransaction;
import com.zennex.trl3lg.presentation.common.router.fragmenttransaction.RemoveFragmentTransaction;
import com.zennex.trl3lg.presentation.common.router.fragmenttransaction.ReplaceFragmentTransaction;
import com.zennex.trl3lg.presentation.common.router.fragmenttransaction.TransactionRemoveFragmentByTag;
import com.zennex.trl3lg.presentation.common.view.ViperBaseFragment;

import java.util.Stack;

/**
 * Created by nikita on 16.09.17.
 */
public abstract class SupportFragmentRouter<Adapter extends SupportFragmentRouter.SupportFragmentRouterAdapter>
        extends BaseRouter<Adapter>
        implements LifecycleObserver {

    public static final String TAG = "SupportFragmentRouter";

    private boolean mActivityStateLoss = false;
    private Stack<FragmentTransaction> mTransStack;

    protected SupportFragmentRouter() {
        mTransStack = new Stack<>();
    }

    @Override
    public void refreshActivityInAdapter(AppCompatActivity appCompatActivity) {
        super.refreshActivityInAdapter(appCompatActivity);
        if (mAdapter != null) mAdapter.registerLifecycleObserver(this);
    }

    protected void addFragment(@NonNull ViperBaseFragment fragment, int container, String tag) {
        executeTransaction(new AddFragmentTransaction(fragment, container, tag));
    }

    protected void replaceFragment(
            @NonNull ViperBaseFragment fragment,
            int container,
            String tag,
            boolean onlyOneInstanceInStack) {
        executeTransaction(new ReplaceFragmentTransaction(fragment, container, tag, onlyOneInstanceInStack));
    }

    private void executeTransaction(FragmentTransaction transaction) {
        if (mActivityStateLoss || (mAdapter == null)) mTransStack.push(transaction);
        else mAdapter.executeFragmentTransaction(transaction);
    }

    protected void removeFragment(String tag) {
        executeTransaction(new TransactionRemoveFragmentByTag(tag));
    }


    protected void removeFragment(@NonNull ViperBaseFragment baseFragment) {
        executeTransaction(new RemoveFragmentTransaction(baseFragment));
    }

    @Override
    public void onBackPressed() {
        if (mAdapter != null) {
            int count = mAdapter.getBackStackEntryCount();
            if (count == 1 || count == 0) {
                super.onBackPressed();
            } else {
                Fragment fragment = mAdapter
                        .getLastFragmentFromContainer(mAdapter.getContainerForFragmentsId());
                if (fragment != null) removeFragment((ViperBaseFragment) fragment);
            }
        }
    }


    //region LifecycleObserver

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart() {
        mActivityStateLoss = false;
        if (!mTransStack.isEmpty() && mAdapter != null) {
            while (!mTransStack.isEmpty()) mAdapter.executeFragmentTransaction(mTransStack.pop());
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop() {
        mActivityStateLoss = true;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        if (mAdapter != null) mAdapter.unregisterLifecycleObserver(this);
    }

    //endregion LifecycleObserver


    public static class SupportFragmentRouterAdapter extends BaseRouter.BaseRouterAdapter
            implements FragmentManager.OnBackStackChangedListener {

        @IdRes
        private int mContainerForFragmentsId;


        public SupportFragmentRouterAdapter(AppCompatActivity appCompatActivity) {
            super(appCompatActivity);
        }

        public SupportFragmentRouterAdapter(AppCompatActivity appCompatActivity, @IdRes int containerForFragmentsId) {
            super(appCompatActivity);
            mContainerForFragmentsId = containerForFragmentsId;
            appCompatActivity.getSupportFragmentManager().addOnBackStackChangedListener(this);
        }


        public int getContainerForFragmentsId() {
            return mContainerForFragmentsId;
        }


        public void registerLifecycleObserver(LifecycleObserver observer) {
            if (mActivity != null) mActivity.getLifecycle().addObserver(observer);
        }

        public void unregisterLifecycleObserver(LifecycleObserver observer) {
            if (mActivity != null) mActivity.getLifecycle().removeObserver(observer);
        }

        public void executeFragmentTransaction(@NonNull FragmentTransaction transaction) {
            if (mActivity != null) transaction.execute(mActivity.getSupportFragmentManager());
        }

        public int getBackStackEntryCount() {
            return mActivity == null ? 0 : mActivity.getSupportFragmentManager().getBackStackEntryCount();
        }

        @Nullable
        public Fragment getLastFragmentFromContainer(int container) {
            if (container == 0 || mActivity == null) return null;
            return mActivity.getSupportFragmentManager().findFragmentById(mContainerForFragmentsId);
        }

        @Override
        public void onBackStackChanged() {
            Fragment fragment = getLastFragmentFromContainer(mContainerForFragmentsId);
            if ((fragment != null) && (fragment instanceof ViperBaseFragment)) {
                ((ViperBaseFragment) fragment).onBackStackChanged((ViperBaseFragment) fragment);
            }
        }
    }

}