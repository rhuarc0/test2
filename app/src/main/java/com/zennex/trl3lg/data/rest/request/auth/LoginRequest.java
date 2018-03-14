package com.zennex.trl3lg.data.rest.request.auth;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 05.06.17.
 */

public class LoginRequest extends BaseRequest<LoginRequest.Data> {

    public static LoginRequest newInstance(LoginRequest.Data data, @Nullable String moduleId) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.fillDefaultFields();
        loginRequest.setType(!TextUtils.isEmpty(moduleId) ? "login" : "login2");
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
