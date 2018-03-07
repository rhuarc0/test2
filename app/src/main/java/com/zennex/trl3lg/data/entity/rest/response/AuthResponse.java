package com.zennex.trl3lg.data.entity.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 02.06.17.
 */

public class AuthResponse {

    @SerializedName("ID")
    private String mId;
    @SerializedName("ErrorCode")
    private String mErrorCode;
    @SerializedName("ErrorText")
    private String mErrorText;
    @SerializedName("Data")
    private AuthDataResponse mMemberLoginResponse;

    public AuthResponse() {
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String errorCode) {
        mErrorCode = errorCode;
    }

    public String getErrorText() {
        return mErrorText;
    }

    public void setErrorText(String errorText) {
        mErrorText = errorText;
    }

    public AuthDataResponse getMemberLoginResponse() {
        return mMemberLoginResponse;
    }

    public void setMemberLoginResponse(AuthDataResponse memberLoginResponse) {
        mMemberLoginResponse = memberLoginResponse;
    }
}
