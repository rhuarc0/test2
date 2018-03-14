package com.zennex.trl3lg.data.datasource.signup;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.rest.request.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.rest.request.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.rest.response.SignUpResponse;
import com.zennex.trl3lg.data.rest.ISignUpWebService;

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

    private static final int NONE_ERROR = 0;

    @Inject
    protected ISignUpWebService signUpWebService;

    @Inject
    public SignUpDataSource() {
    }

    protected Observable<List<GetFieldsForSignUpResponse.DataMemberField>> buildObservable(String moduleId) {
        GetFieldsForSignUpRequest request = createGetFieldsForSignUpRequest(moduleId);

        return signUpWebService.getFieldsForSignUp(request)
                .doOnNext(checkResponse())
                .map(BaseResponse::getData)
                .map(filterFields())
                .map(addConfirmPasswordField());
    }

    private GetFieldsForSignUpRequest createGetFieldsForSignUpRequest(@NonNull String moduleId) {
        return GetFieldsForSignUpRequest.newInstance(moduleId);
    }

    private Consumer<GetFieldsForSignUpResponse> checkResponse() {
        return getSitesResponse -> {
            if (Integer.parseInt(getSitesResponse.getErrorCode()) != NONE_ERROR) {
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



    @Override
    public Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(GetFieldsForSignUpRequest getFieldsForSignUpRequest) {
        return signUpWebService.getFieldsForSignUp(getFieldsForSignUpRequest);
    }

    @Override
    public Observable<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        return signUpWebService.signUp(signUpRequest);
    }
}
