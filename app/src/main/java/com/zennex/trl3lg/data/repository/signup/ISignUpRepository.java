package com.zennex.trl3lg.data.repository.signup;

import com.zennex.trl3lg.data.rest.request.signup.SignUpRequest;
import com.zennex.trl3lg.data.rest.response.signup.SignUpResponse;
import com.zennex.trl3lg.domain.entities.Field;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public interface ISignUpRepository {

    Observable<List<Field>> getFieldsForSignUp(String moduleId);

    Observable<SignUpResponse> signUp(SignUpRequest signUpRequest);
}
