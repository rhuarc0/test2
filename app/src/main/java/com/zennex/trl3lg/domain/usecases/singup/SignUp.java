package com.zennex.trl3lg.domain.usecases.singup;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.repository.ISignUpRepository;
import com.zennex.trl3lg.domain.entities.Field;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class SignUp extends UseCase<String, SignUp.Params> {

    @Inject
    ISignUpRepository mSignUpRepository;

    @Inject
    public SignUp(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<String> buildObservable(Params params) {
        return mSignUpRepository.signUp(params.getModuleId(), params.getFieldModels())
                .delay(10, TimeUnit.SECONDS);
    }

    public static class Params {

        @NonNull
        private String mModuleId;
        @NonNull
        private List<Field> mFieldModels;

        public Params(@NonNull String moduleId, @NonNull List<Field> fieldModels) {
            mModuleId = moduleId;
            mFieldModels = fieldModels;
        }


        @NonNull
        public String getModuleId() {
            return mModuleId;
        }

        public void setModuleId(@NonNull String moduleId) {
            mModuleId = moduleId;
        }

        @NonNull
        public List<Field> getFieldModels() {
            return mFieldModels;
        }

        public void setFieldModels(@NonNull List<Field> fieldModels) {
            mFieldModels = fieldModels;
        }
    }


}
