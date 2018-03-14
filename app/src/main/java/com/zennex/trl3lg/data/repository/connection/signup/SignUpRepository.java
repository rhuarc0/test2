package com.zennex.trl3lg.data.repository.connection.signup;

import com.zennex.trl3lg.data.rest.request.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.rest.request.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.rest.response.SignUpResponse;
import com.zennex.trl3lg.data.datasource.signup.ISignUpDataSource;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public class SignUpRepository implements ISignUpRepository {

    @Inject
    protected ISignUpDataSource signUpDataSource;

    @Inject
    public SignUpRepository() {
    }

    @Override
    public Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(GetFieldsForSignUpRequest getFieldsForSignUpRequest) {
        return signUpDataSource.getFieldsForSignUp(getFieldsForSignUpRequest);
    }

    @Override
    public Observable<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        return signUpDataSource.signUp(signUpRequest);
    }
}
