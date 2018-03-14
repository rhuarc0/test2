package com.zennex.trl3lg.data.rest.request;

import android.support.annotation.NonNull;

/**
 * Created by nikita on 12.06.17.
 */

public class GetFieldsForSignUpRequest extends BaseRequest {

    public static GetFieldsForSignUpRequest newInstance(@NonNull String moduleId) {
        GetFieldsForSignUpRequest request = new GetFieldsForSignUpRequest();
        request.fillDefaultFields();
        request.setType("getMemberFields");
        request.setModuleid(moduleId);
        return request;
    }

}
