package com.zennex.trl3lg.data.repository.signup;

import android.support.annotation.NonNull;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.zennex.trl3lg.data.mapper.dtomapper.FieldDtoMapper;
import com.zennex.trl3lg.data.rest.request.signup.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.rest.request.signup.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.BaseResponse;
import com.zennex.trl3lg.data.rest.response.signup.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.datasource.signup.ISignUpDataSource;
import com.zennex.trl3lg.data.util.repository.WebRepositoryUtils;
import com.zennex.trl3lg.domain.entities.Field;
import com.zennex.trl3lg.domain.repository.ISignUpRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by nikita on 12.06.17.
 */

public class SignUpRepository implements ISignUpRepository {

    @Inject
    protected ISignUpDataSource signUpDataSource;

    @Inject
    protected FieldDtoMapper fieldDtoMapper;

    @Inject
    public SignUpRepository() {
    }

    @Override
    public Observable<List<Field>> getFieldsForSignUp(String moduleId) {
        return signUpDataSource.getFieldsForSignUp(createRequest(moduleId))
                .doOnNext(WebRepositoryUtils::checkResponse)
                .map(BaseResponse::getData)
                .map(filterFields())
                .map(addConfirmPasswordField())
                .map(transform());
    }

    @Override
    public Observable<String> signUp(String moduleId, List<Field> fields) {
        return signUpDataSource.signUp(createSignUpRequest(moduleId, fields));
    }

    // aux methods

    private SignUpRequest createSignUpRequest(String moduleId, List<Field> fields) {
        Map<String, String> fieldsMap = Stream.of(fields)
                .collect(Collectors.toMap(Field::getAlias, Field::getValue));
        return SignUpRequest.newInstance(moduleId, fieldsMap);
    }

    private GetFieldsForSignUpRequest createRequest(@NonNull String moduleId) {
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

    private Function<List<GetFieldsForSignUpResponse.DataMemberField>, List<Field>> transform() {
        return memberFields -> Stream.of(memberFields)
                .map(bookDto -> fieldDtoMapper.execute(bookDto))
                .toList();
    }
}
