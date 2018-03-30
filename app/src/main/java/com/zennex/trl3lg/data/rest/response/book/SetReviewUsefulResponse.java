package com.zennex.trl3lg.data.rest.response.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.response.BaseResponse;

public class SetReviewUsefulResponse extends BaseResponse<String> {
    @SerializedName("mReviewId")
    private String mReviewId;

    @SerializedName("mUseful")
    private boolean mUseful;

    public String getReviewId() {
        return mReviewId;
    }

    public void setReviewId(String mReviewId) {
        this.mReviewId = mReviewId;
    }

    public boolean isUseful() {
        return mUseful;
    }

    public void setUseful(boolean mUseful) {
        this.mUseful = mUseful;
    }
}
