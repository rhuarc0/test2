package com.zennex.trl3lg.domain.usecases.singup;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.rest.request.signup.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.signup.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.repository.signup.ISignUpRepository;
import com.zennex.trl3lg.data.util.repository.WebRepositoryUtils;
import com.zennex.trl3lg.domain.entities.Field;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Function;

public class GetFieldsForSignUp extends UseCase<List<Field>, String> {

    @Inject
    protected ISignUpRepository mSignUpRepository;

    @Inject
    public GetFieldsForSignUp(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<Field>> buildObservable(String moduleId) {
        return mSignUpRepository.getFieldsForSignUp(moduleId);
    }

}
