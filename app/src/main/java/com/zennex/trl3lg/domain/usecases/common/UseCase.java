package com.zennex.trl3lg.domain.usecases.common;

import android.support.annotation.NonNull;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public abstract class UseCase<Result, Param> {

    @NonNull
    protected Scheduler mSubscribeScheduler;
    @NonNull
    protected Scheduler mObserverScheduler;
    private CompositeDisposable mCompositeDisposable;

    protected Observable<Result> mResultObservable;

    private UseCase() {
    }

    public UseCase(@NonNull Scheduler subscriberScheduler,
                   @NonNull Scheduler observerScheduler) {
        mSubscribeScheduler = subscriberScheduler;
        mObserverScheduler = observerScheduler;
        mCompositeDisposable = new CompositeDisposable();
    }

    public void execute(DisposableObserver<Result> disposableObserver, Param param) {
        Preconditions.checkNotNull(disposableObserver);
        mResultObservable = buildObservable(param)
                .compose(applySchedulers())
                .doFinally(() -> mResultObservable = null);
        addDisposable(mResultObservable.subscribeWith(disposableObserver));
    }

    protected abstract Observable<Result> buildObservable(Param param);

    public void dispose() {
        if (mCompositeDisposable.isDisposed()) mCompositeDisposable.dispose();
    }

    protected void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(mCompositeDisposable);
        mCompositeDisposable.add(disposable);
    }

    protected <T> ObservableTransformer<T, T> applySchedulers() {
        return upstream -> upstream.subscribeOn(mSubscribeScheduler)
                .observeOn(mObserverScheduler);
    }

    public boolean isRun() {
        return mResultObservable != null;
    }

}
