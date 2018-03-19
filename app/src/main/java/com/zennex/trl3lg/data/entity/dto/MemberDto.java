package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

public class MemberDto {

    @SerializedName("id")
    private long mIdMember;
    @SerializedName("group_id")
    private long mGroupId;
    @SerializedName("groupName")
    private String mGroupeName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("password")
    private String mPassword;
    @SerializedName("name1")
    private String mName1;
    @SerializedName("name2")
    private String mName2;
    @SerializedName("company")
    private String mCompany;
    @SerializedName("country")
    private String mCountry;
    @SerializedName("state")
    private String mState;
    @SerializedName("city")
    private String mCity;
    @SerializedName("address1")
    private String mAddress1;
    @SerializedName("address2")
    private String mAddress2;
    @SerializedName("zip")
    private String mZip;
    @SerializedName("phone")
    private String mPhone;
    @SerializedName("opt_out")
    private int mOptOut;
    @SerializedName("notes")
    private String mNotes;
    @SerializedName("field_set")
    private int mFieldSet;
    @SerializedName("created")
    private String mCreated;
    @SerializedName("modified")
    private String mModified;
    @SerializedName("valid_from")
    private String mValidFrom;
    @SerializedName("valid_to")
    private String mValidTo;
    @SerializedName("svalid_from")
    private String mSvalidFrom;
    @SerializedName("svalid_to")
    private String mSvalidTo;


    public long getIdMember() {
        return mIdMember;
    }

    public void setIdMember(long idMember) {
        mIdMember = idMember;
    }

    public int getOptOut() {
        return mOptOut;
    }

    public void setOptOut(int optOut) {
        mOptOut = optOut;
    }

    public int getFieldSet() {
        return mFieldSet;
    }

    public void setFieldSet(int fieldSet) {
        mFieldSet = fieldSet;
    }

    public long getGroupId() {
        return mGroupId;
    }

    public void setGroupId(long groupId) {
        mGroupId = groupId;
    }

    public String getGroupeName() {
        return mGroupeName;
    }

    public void setGroupeName(String groupeName) {
        mGroupeName = groupeName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getName1() {
        return mName1;
    }

    public void setName1(String name1) {
        mName1 = name1;
    }

    public String getName2() {
        return mName2;
    }

    public void setName2(String name2) {
        mName2 = name2;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getAddress1() {
        return mAddress1;
    }

    public void setAddress1(String address1) {
        mAddress1 = address1;
    }

    public String getAddress2() {
        return mAddress2;
    }

    public void setAddress2(String address2) {
        mAddress2 = address2;
    }

    public String getZip() {
        return mZip;
    }

    public void setZip(String zip) {
        mZip = zip;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getNotes() {
        return mNotes;
    }

    public void setNotes(String notes) {
        mNotes = notes;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getModified() {
        return mModified;
    }

    public void setModified(String modified) {
        mModified = modified;
    }

    public String getValidFrom() {
        return mValidFrom;
    }

    public void setValidFrom(String validFrom) {
        mValidFrom = validFrom;
    }

    public String getValidTo() {
        return mValidTo;
    }

    public void setValidTo(String validTo) {
        mValidTo = validTo;
    }

    public String getSvalidFrom() {
        return mSvalidFrom;
    }

    public void setSvalidFrom(String svalidFrom) {
        mSvalidFrom = svalidFrom;
    }

    public String getSvalidTo() {
        return mSvalidTo;
    }

    public void setSvalidTo(String svalidTo) {
        mSvalidTo = svalidTo;
    }
}
