package com.zennex.trl3lg.data.rest.request.signup;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.rest.request.BaseRequest;

import java.util.Map;

/**
 * Created by nikita on 16.07.17.
 */

public class SignUpRequest extends BaseRequest<Map<String, String>> {

    private static final String API_METHOD = "addMethod";

    public static SignUpRequest newInstance(@NonNull String moduleId, @NonNull Map<String, String> fields) {
        SignUpRequest request = new SignUpRequest();
        request.fillDefaultFields();
        request.setType(API_METHOD);
        request.setModuleid(moduleId);
        request.setData(fields);
        return request;
    }

}
