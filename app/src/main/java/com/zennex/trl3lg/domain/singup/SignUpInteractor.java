package com.zennex.trl3lg.domain.singup;

import android.support.annotation.NonNull;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.entity.rest.request.SignUpRequest;
import com.zennex.trl3lg.data.entity.rest.response.SignUpResponse;
import com.zennex.trl3lg.data.repository.connection.signup.ISignUpRepository;
import com.zennex.trl3lg.domain.common.BaseInteractor;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.model.FieldModel;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created by nikita on 19.07.17.
 */

public class SignUpInteractor extends BaseInteractor<SignUpResponse, SignUpInteractor.Params> {

    @Inject
    ISignUpRepository mSignUpRepository;

    @Inject
    public SignUpInteractor(
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
