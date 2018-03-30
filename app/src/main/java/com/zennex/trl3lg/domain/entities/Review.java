package com.zennex.trl3lg.domain.entities;

import java.util.Objects;

public class Review {

    private String mId;
    private float mRating;
    private String mMemberId;
    private String mUseFulYes;
    private String mUseFulTotal;
    private String mCreateDate;
    private String mMemberName;
    private String mMemberEmail;
    private String mMemberIp;
    private String mMemberExtra;
    private String mText;
    private boolean mRated;


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public float getRating() {
        return mRating;
    }

    public void setRating(float rating) {
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

    public boolean isRated() {
        return mRated;
    }

    public void setRated(boolean rated) {
        mRated = rated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return Float.compare(review.mRating, mRating) == 0 &&
                mRated == review.mRated &&
                Objects.equals(mId, review.mId) &&
                Objects.equals(mMemberId, review.mMemberId) &&
                Objects.equals(mUseFulYes, review.mUseFulYes) &&
                Objects.equals(mUseFulTotal, review.mUseFulTotal) &&
                Objects.equals(mCreateDate, review.mCreateDate) &&
                Objects.equals(mMemberName, review.mMemberName) &&
                Objects.equals(mMemberEmail, review.mMemberEmail) &&
                Objects.equals(mMemberIp, review.mMemberIp) &&
                Objects.equals(mMemberExtra, review.mMemberExtra) &&
                Objects.equals(mText, review.mText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mId, mRating, mMemberId, mUseFulYes, mUseFulTotal, mCreateDate, mMemberName, mMemberEmail, mMemberIp, mMemberExtra, mText, mRated);
    }
}
