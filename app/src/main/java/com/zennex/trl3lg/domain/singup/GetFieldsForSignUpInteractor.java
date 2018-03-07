package com.zennex.trl3lg.domain.singup;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.entity.rest.request.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.entity.rest.response.BaseResponse;
import com.zennex.trl3lg.data.entity.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.repository.connection.signup.ISignUpRepository;
import com.zennex.trl3lg.domain.common.BaseInteractor;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by nikita on 12.06.17.
 */

public class GetFieldsForSignUpInteractor extends BaseInteractor<List<GetFieldsForSignUpResponse.DataMemberField>, String> {

    @Inject
    protected ISignUpRepository mSignUpRepository;

    @Inject
    public GetFieldsForSignUpInteractor(
            @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
            @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
    }

    @Override
    protected Observable<List<GetFieldsForSignUpResponse.DataMemberField>> buildObservable(String moduleId) {
        return mSignUpRepository.getFieldsForSignUp(createRequest(moduleId))
                .doOnNext(checkResponse())
                .map(BaseResponse::getData)
                .map(filterFields())
                .map(addConfirmPasswordField());
    }

    private GetFieldsForSignUpRequest createRequest(@NonNull String moduleId) {
        return GetFieldsForSignUpRequest.newInstance(moduleId);
    }

    private Consumer<GetFieldsForSignUpResponse> checkResponse() {
        return getSitesResponse -> {
            if (Integer.parseInt(getSitesResponse.getErrorCode()) != 0) {
                throw new RuntimeException(getSitesResponse.getErrorText());
            }
        };
    }

    private Function<List<GetFieldsForSignUpResponse.DataMemberField>, List<GetFieldsForSignUpResponse.DataMemberField>> filterFields() {
        return dataMemberFields -> Stream.of(dataMemberFields)
                .filter(value -> !value.getActive().equals("0") && !value.getAlias().equalsIgnoreCase("active"))
                .toList();
    }

    private Function<List<GetFieldsForSignUpResponse.DataMemberField>, List<GetFieldsForSignUpResponse.DataMemberField>> addConfirmPasswordField() {
        return fields -> Stream.of(fields)
                .collect(ArrayList::new, (list, field) -> {
                    list.add(field);
                    if (field.getTitle().equalsIgnoreCase("password")) {
                        GetFieldsForSignUpResponse.DataMemberField memberField1 = GetFieldsForSignUpResponse.DataMemberField.clone(field);
                        memberField1.setTitle("Confirm password");
                        list.add(memberField1);
                    }
                });
    }

}
