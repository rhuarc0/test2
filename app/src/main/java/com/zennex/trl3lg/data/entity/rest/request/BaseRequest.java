package com.zennex.trl3lg.data.entity.rest.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 15.07.16.
 */
public class BaseRequest<T> {

    @SerializedName("Domain")
    private String mDomain;
    @SerializedName("Id")
    private String mId;
    @SerializedName("Type")
    private String mType;
    @SerializedName("Wfuserpassword")
    private String mWfuserpassword;
    @SerializedName("Wfuserlogin")
    private String mWfuserlogin;
    @SerializedName("Moduleid")
    private String mModuleid;
    @SerializedName("Data")
    private T mData;

    public BaseRequest() {

    }

    public String getDomain() {
        return mDomain;
    }

    public void setDomain(String domain) {
        mDomain = domain;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getWfuserpassword() {
        return mWfuserpassword;
    }

    public void setWfuserpassword(String wfuserpassword) {
        mWfuserpassword = wfuserpassword;
    }

    public String getWfuserlogin() {
        return mWfuserlogin;
    }

    public void setWfuserlogin(String wfuserlogin) {
        mWfuserlogin = wfuserlogin;
    }

    public String getModuleid() {
        return mModuleid;
    }

    public void setModuleid(String moduleid) {
        mModuleid = moduleid;
    }

    public T getData() {
        return mData;
    }

    public void setData(T data) {
        mData = data;
    }

    protected void fillDefaultFields() {
        mId = "1";
        mWfuserlogin = "3lg";
        mDomain = "websiteforge.com";
        mWfuserpassword = "D4RXOm1alO";
    }

}
