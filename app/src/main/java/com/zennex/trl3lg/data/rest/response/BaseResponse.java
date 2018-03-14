package com.zennex.trl3lg.data.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 12.06.17.
 */

public abstract class BaseResponse<T> {

    @SerializedName("ID")
    private String mId;
    @SerializedName("ErrorCode")
    private String mErrorCode;
    @SerializedName("ErrorText")
    private String mErrorText;
    @SerializedName("Data")
    private T mData;

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

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }
}
