package com.zennex.trl3lg.domain.common;

import android.util.Log;

import com.zennex.trl3lg.BuildConfig;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by nikita on 02.06.17.
 */

public abstract class DefaultObserver<T> extends DisposableObserver<T> {

    private boolean mEnableLog;

    public DefaultObserver() {
        mEnableLog = BuildConfig.DEBUG;
    }

    public DefaultObserver(boolean enableLog) {
        mEnableLog = enableLog;
    }

    @Override
    public void onNext(T t) {
        if (isEnableLog()) Log.d(getTag(), "onNext");
        //todo override
    }

    @Override
    public void onError(Throwable e) {
        if (isEnableLog()) Log.d(getTag(), "onError.message = " + e.getMessage());
        //todo override
    }

    @Override
    public void onComplete() {
        if (isEnableLog()) Log.d(getTag(), "onComplete");
        //todo override
    }

    @Override
    protected void onStart() {
        if (isEnableLog()) Log.d(getTag(), "onStart");
        //todo override
    }

    public boolean isEnableLog() {
        return mEnableLog;
    }

    public void setEnableLog(boolean enableLog) {
        mEnableLog = enableLog;
    }

    protected abstract String getTag();
}