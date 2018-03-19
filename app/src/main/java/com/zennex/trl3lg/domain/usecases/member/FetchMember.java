package com.zennex.trl3lg.domain.usecases.member;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.Member;
import com.zennex.trl3lg.domain.repository.IAuthRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;


public class FetchMember extends UseCase<Member, Void> {

    @Inject
    IAuthRepository authRepository;

    @Inject
    public FetchMember(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {

        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<Member> buildObservable(Void aVoid) {
        return authRepository.fetchMember();
    }
}
