package com.zennex.trl3lg.data.rest.request;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.presentation.helper.StringUtils;

/**
 * Created by nikita on 05.06.17.
 */

public class LoginRequest extends BaseRequest<LoginRequest.Data> {

    public static LoginRequest newInstance(LoginRequest.Data data, @Nullable String moduleId) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.fillDefaultFields();
        loginRequest.setType(!StringUtils.isNullOrEmpty(moduleId) ? "login" : "login2");
        loginRequest.setModuleid(moduleId);
        loginRequest.setData(data);
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

        public String getEmail() {
            return mEmail;
        }

        public void setEmail(String email) {
            mEmail = email;
        }

        public String getPass() {
            return mPass;
        }

        public void setPass(String pass) {
            mPass = pass;
        }

    }
}
