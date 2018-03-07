package com.zennex.trl3lg.data.repository.connection.signup;

import com.zennex.trl3lg.data.entity.rest.request.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.entity.rest.request.SignUpRequest;
import com.zennex.trl3lg.data.entity.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.entity.rest.response.SignUpResponse;
import com.zennex.trl3lg.data.repository.connection.signup.web.ISignUpWebRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public class SignUpRepository implements ISignUpRepository {

    @Inject
    protected ISignUpWebRepository mSignUpWebRepository;

    @Inject
    public SignUpRepository() {
    }

    @Override
    public Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(GetFieldsForSignUpRequest getFieldsForSignUpRequest) {
        return mSignUpWebRepository.getFieldsForSignUp(getFieldsForSignUpRequest);
    }

    @Override
    public Observable<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        return mSignUpWebRepository.signUp(signUpRequest);
    }
}
