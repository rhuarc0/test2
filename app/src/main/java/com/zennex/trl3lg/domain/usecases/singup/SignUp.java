package com.zennex.trl3lg.domain.usecases.singup;

import android.support.annotation.NonNull;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.rest.request.signup.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.signup.SignUpResponse;
import com.zennex.trl3lg.data.repository.connection.signup.ISignUpRepository;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.model.FieldModel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class SignUp extends UseCase<SignUpResponse, SignUp.Params> {

    @Inject
    ISignUpRepository mSignUpRepository;

    @Inject
    public SignUp(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<SignUpResponse> buildObservable(Params params) {
        return mSignUpRepository.signUp(createSignUpRequest(params))
                .delay(10, TimeUnit.SECONDS);
    }

    private SignUpRequest createSignUpRequest(Params params) {
        Map<String, String> fields = Stream.of(params.getFieldModels())
                .collect(Collectors.toMap(FieldModel::getAlias, FieldModel::getValue));
        return SignUpRequest.newInstance(params.mModuleId, fields);
    }

    public static class Params {

        @NonNull
        private String mModuleId;
        @NonNull
        private List<FieldModel> mFieldModels;

        public Params(@NonNull String moduleId, @NonNull List<FieldModel> fieldModels) {
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
        public List<FieldModel> getFieldModels() {
            return mFieldModels;
        }

        public void setFieldModels(@NonNull List<FieldModel> fieldModels) {
            mFieldModels = fieldModels;
        }
    }


}
