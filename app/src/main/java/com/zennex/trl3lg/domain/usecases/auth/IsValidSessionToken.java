package com.zennex.trl3lg.domain.usecases.auth;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.domain.repository.ISiteRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class IsValidSessionToken extends UseCase<Boolean, Void> {

    @Inject
    IAuthRepository authRepository;

    @Inject
    ISiteRepository mSiteRepository;

    @Inject
    public IsValidSessionToken(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<Boolean> buildObservable(Void aVoid) {
        return authRepository.isValidSessionToken();
    }
}
