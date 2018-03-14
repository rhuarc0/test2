package com.zennex.trl3lg.data.rest.request;

import android.support.annotation.NonNull;

import java.util.Map;

/**
 * Created by nikita on 16.07.17.
 */

public class SignUpRequest extends BaseRequest<Map<String, String>> {

    public static SignUpRequest newInstance(@NonNull String moduleId, @NonNull Map<String, String> fields) {
        SignUpRequest request = new SignUpRequest();
        request.fillDefaultFields();
        request.setType("addMember");
        request.setModuleid(moduleId);
        request.setData(fields);
        return request;
    }

}
