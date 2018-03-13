package com.zennex.trl3lg.data.datasource.signup;

import com.zennex.trl3lg.data.entity.rest.request.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.entity.rest.request.SignUpRequest;
import com.zennex.trl3lg.data.entity.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.entity.rest.response.SignUpResponse;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public interface ISignUpDataSource {

    Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(GetFieldsForSignUpRequest getFieldsForSignUpRequest);

    Observable<SignUpResponse> signUp(SignUpRequest signUpRequest);
}
