package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 26.07.16.
 */
public class ModuleDto {
    @SerializedName("Id")
    private String mId;
    @SerializedName("Number")
    private String mNumber;
    @SerializedName("Name")
    private String mName;
    @SerializedName("Type")
    private String mType;
    @SerializedName("MembershipId")
    private String mMembershipId;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getMembershipId() {
        return mMembershipId;
    }

    public void setMembershipId(String membershipId) {
        mMembershipId = membershipId;
    }
}
