package com.zennex.trl3lg.presentation.common.router.fragmenttransaction;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.zennex.trl3lg.presentation.common.view.ViperBaseFragment;

/**
 * Created by zennex on 28.07.17.
 */

public class ReplaceFragmentTransaction extends FragmentTransaction {


    @Nullable
    private String mTag;
    private Fragment mFragment;
    private int mContainerForFragmentsId;
    private boolean mOnlyOneInstanceInStack;

    public ReplaceFragmentTransaction(
            ViperBaseFragment fragment,
            int containerForFragmentsId,
            boolean onlyOneInstanceInStack) {
        this(fragment, containerForFragmentsId, null, onlyOneInstanceInStack);
    }

    public ReplaceFragmentTransaction(
            ViperBaseFragment fragment,
            int containerForFragmentsId,
            @Nullable String tag,
            boolean onlyOneInstanceInStack) {
        mFragment = fragment;
        mTag = tag;
        mContainerForFragmentsId = containerForFragmentsId;
        mOnlyOneInstanceInStack = onlyOneInstanceInStack;
    }

    @Nullable
    public String getTag() {
        return mTag;
    }

    public void setTag(@Nullable String tag) {
        mTag = tag;
    }

    public Fragment getFragment() {
        return mFragment;
    }

    public void setFragment(Fragment fragment) {
        mFragment = fragment;
    }

    public int getContainerForFragmentsId() {
        return mContainerForFragmentsId;
    }

    public void setContainerForFragmentsId(int containerForFragmentsId) {
        mContainerForFragmentsId = containerForFragmentsId;
    }


    public boolean isOnlyOneInstanceInStack() {
        return mOnlyOneInstanceInStack;
    }

    public void setOnlyOneInstanceInStack(boolean onlyOneInstanceInStack) {
        mOnlyOneInstanceInStack = onlyOneInstanceInStack;
    }

    @Override
    public void execute(FragmentManager fragmentManager) {

        if (mContainerForFragmentsId == 0) {
            throw new RuntimeException("please set mContainerFragmentId");
        }

        if (isOnlyOneInstanceInStack()) {
            Fragment fragment = fragmentManager.findFragmentByTag(mTag);
            if (fragment != null) mFragment = fragment;
        }

        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mTag == null) transaction.replace(mContainerForFragmentsId, mFragment);
        else transaction.replace(mContainerForFragmentsId, mFragment, mTag);
        transaction.addToBackStack(mTag).commit();

    }

}
