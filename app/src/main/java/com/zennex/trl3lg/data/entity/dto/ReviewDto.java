package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 20.10.17.
 */

public class ReviewDto {

    @SerializedName("id")
    private String mId;
    @SerializedName("rating")
    private String mRating;
    @SerializedName("member_id")
    private String mMemberId;
    @SerializedName("useful_yes")
    private String mUseFulYes;
    @SerializedName("useful_total")
    private String mUseFulTotal;
    @SerializedName("create_date")
    private String mCreateDate;
    @SerializedName("member_name")
    private String mMemberName;
    @SerializedName("member_email")
    private String mMemberEmail;
    @SerializedName("member_ip")
    private String mMemberIp;
    @SerializedName("member_extra")
    private String mMemberExtra;
    @SerializedName("text")
    private String mText;
    @SerializedName("rated")
    private float mRated;


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getMemberId() {
        return mMemberId;
    }

    public void setMemberId(String memberId) {
        mMemberId = memberId;
    }

    public String getUseFulYes() {
        return mUseFulYes;
    }

    public void setUseFulYes(String useFulYes) {
        mUseFulYes = useFulYes;
    }

    public String getUseFulTotal() {
        return mUseFulTotal;
    }

    public void setUseFulTotal(String useFulTotal) {
        mUseFulTotal = useFulTotal;
    }

    public String getCreateDate() {
        return mCreateDate;
    }

    public void setCreateDate(String createDate) {
        mCreateDate = createDate;
    }

    public String getMemberName() {
        return mMemberName;
    }

    public void setMemberName(String memberName) {
        mMemberName = memberName;
    }

    public String getMemberEmail() {
        return mMemberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        mMemberEmail = memberEmail;
    }

    public String getMemberIp() {
        return mMemberIp;
    }

    public void setMemberIp(String memberIp) {
        mMemberIp = memberIp;
    }

    public String getMemberExtra() {
        return mMemberExtra;
    }

    public void setMemberExtra(String memberExtra) {
        mMemberExtra = memberExtra;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public float getRated() {
        return mRated;
    }

    public void setRated(float rated) {
        mRated = rated;
    }
}
