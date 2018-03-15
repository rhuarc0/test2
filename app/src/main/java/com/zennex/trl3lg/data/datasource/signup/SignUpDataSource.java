package com.zennex.trl3lg.data.datasource.signup;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.rest.request.signup.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.rest.request.signup.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.signup.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.rest.response.signup.SignUpResponse;
import com.zennex.trl3lg.data.rest.ISignUpWebService;
import com.zennex.trl3lg.data.util.repository.WebRepositoryUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by nikita on 12.06.17.
 */

public class SignUpDataSource implements ISignUpDataSource {

    @Inject
    protected ISignUpWebService signUpWebService;

    @Inject
    public SignUpDataSource() {
    }

    protected Observable<List<GetFieldsForSignUpResponse.DataMemberField>> buildObservable(String moduleId) {
        GetFieldsForSignUpRequest request = createGetFieldsForSignUpRequest(moduleId);

        return signUpWebService.getFieldsForSignUp(request)
                .doOnNext(WebRepositoryUtils::checkResponse)
                .map(BaseResponse::getData)
                .map(filterFields())
                .map(addConfirmPasswordField());
    }

    private GetFieldsForSignUpRequest createGetFieldsForSignUpRequest(@NonNull String moduleId) {
        return GetFieldsForSignUpRequest.newInstance(moduleId);
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



    @Override
    public Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(GetFieldsForSignUpRequest getFieldsForSignUpRequest) {
        return signUpWebService.getFieldsForSignUp(getFieldsForSignUpRequest);
    }

    @Override
    public Observable<String> signUp(SignUpRequest signUpRequest) {
        return signUpWebService.signUp(signUpRequest)
                .doOnNext(WebRepositoryUtils::checkResponse)
                .map(response -> response.getData().getId());
    }
}
