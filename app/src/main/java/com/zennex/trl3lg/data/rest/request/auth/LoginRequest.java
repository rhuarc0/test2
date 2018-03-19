package com.zennex.trl3lg.data.rest.request.auth;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 05.06.17.
 */

public class LoginRequest extends BaseRequest<LoginRequest.Data> {

    private static final String API_METHOD_LOGIN = "login";
    private static final String API_METHOD_LOGIN2 = "login2";

    public static LoginRequest newInstance(String email, String password, @Nullable String moduleId) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.fillDefaultFields();
        loginRequest.setType(!TextUtils.isEmpty(moduleId) ? API_METHOD_LOGIN : API_METHOD_LOGIN2);
        loginRequest.setModuleid(moduleId);
        loginRequest.setData(new Data(email, password));
        return loginRequest;
    }

    public static class Data {

        @SerializedName("Email")
        private String mEmail;
        @SerializedName("Password")
        private String mPass;

        public Data() {
        }

        public Data(String email, String pass) {
            mEmail = email;
            mPass = pass;
        }

    }
}
