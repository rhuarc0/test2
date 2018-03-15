package com.zennex.trl3lg.data.rest.request.signup;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 12.06.17.
 */

public class GetFieldsForSignUpRequest extends BaseRequest {

    private static final String API_METHOD = "getMemberFields";

    public static GetFieldsForSignUpRequest newInstance(@NonNull String moduleId) {
        GetFieldsForSignUpRequest request = new GetFieldsForSignUpRequest();
        request.fillDefaultFields();
        request.setType(API_METHOD);
        request.setModuleid(moduleId);
        return request;
    }

}
