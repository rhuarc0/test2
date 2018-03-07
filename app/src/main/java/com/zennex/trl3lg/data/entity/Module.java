package com.zennex.trl3lg.data.entity;

/**
 * Created by nikita on 03.06.17.
 */

public class Module {

    private String mId;
    private String mNumber;
    private String mName;
    private String mType;
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
