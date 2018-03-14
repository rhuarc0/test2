package com.zennex.trl3lg.data.repository.connection.signup;

import com.zennex.trl3lg.data.rest.request.GetFieldsForSignUpRequest;
import com.zennex.trl3lg.data.rest.request.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.rest.response.SignUpResponse;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public interface ISignUpRepository {

    Observable<GetFieldsForSignUpResponse> getFieldsForSignUp(GetFieldsForSignUpRequest getFieldsForSignUpRequest);

    Observable<SignUpResponse> signUp(SignUpRequest signUpRequest);
}
