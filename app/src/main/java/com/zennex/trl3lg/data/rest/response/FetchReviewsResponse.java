package com.zennex.trl3lg.data.rest.response;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.entity.dto.ReviewDto;

import java.util.List;

/**
 * Created by nikita on 20.10.17.
 */

public class FetchReviewsResponse extends BaseResponse<List<ReviewDto>> {

    @SerializedName("id")
    private String mReviewId;
    @SerializedName("rating")
    private String mRating;
    @SerializedName("useful_yes")
    private long mUsefulYes;
    @SerializedName("useful_total")
    private long mUsefulTotal;
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
    private String mRated;

    public String getReviewId() {
        return mReviewId;
    }

    public void setReviewId(String reviewId) {
        mReviewId = reviewId;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public long getUsefulYes() {
        return mUsefulYes;
    }

    public void setUsefulYes(long usefulYes) {
        mUsefulYes = usefulYes;
    }

    public long getUsefulTotal() {
        return mUsefulTotal;
    }

    public void setUsefulTotal(long usefulTotal) {
        mUsefulTotal = usefulTotal;
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

    public String getRated() {
        return mRated;
    }

    public void setRated(String rated) {
        mRated = rated;
    }
}
