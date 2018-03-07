package com.zennex.trl3lg.data.repository.connection.signup.web;

import com.zennex.trl3lg.data.entity.rest.request.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.entity.rest.request.SignUpRequest;
import com.zennex.trl3lg.data.entity.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.entity.rest.response.SignUpResponse;
import com.zennex.trl3lg.data.repository.connection.signup.web.service.ISignUpWebService;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public class SignUpWebRepository implements ISignUpWebRepository {

    @Inject
    protected ISignUpWebService mSignUpWebService;

    @Inject
    public SignUpWebRepository() {
    }

    @Override
    public Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(GetFieldsForSignUpRequest getFieldsForSignUpRequest) {
        return mSignUpWebService.getFieldsForSignUp(getFieldsForSignUpRequest);
    }

    @Override
    public Observable<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        return mSignUpWebService.signUp(signUpRequest);
    }
}
