package com.zennex.trl3lg.data.datasource.signup;

import com.zennex.trl3lg.data.rest.request.signup.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.rest.request.signup.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.signup.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.rest.response.signup.SignUpResponse;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public interface ISignUpDataSource {

    Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(GetFieldsForSignUpRequest getFieldsForSignUpRequest);

    Observable<String> signUp(SignUpRequest signUpRequest);
}
