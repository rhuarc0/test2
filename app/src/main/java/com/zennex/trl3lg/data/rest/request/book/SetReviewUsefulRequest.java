package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 14.03.18.
 */

public class SetReviewUsefulRequest extends BaseRequest<SetReviewUsefulRequest.Data> {

    public static SetReviewUsefulRequest newInstance(String moduleId, Data data) {
        SetReviewUsefulRequest request = new SetReviewUsefulRequest();
        request.fillDefaultFields();
        request.setData(data);
        request.setModuleid(moduleId);
        request.setType("setReviewUseful");
        return request;
    }

    public static class Data {

        @SerializedName("SessionId")
        private String mSessionId;
        @SerializedName("Id")
        private String mReviewId;
        @SerializedName("Useful")
        private String mUseful;


        public Data(String sessionId, String reviewId, String useful) {
            mSessionId = sessionId;
            mReviewId = reviewId;
            mUseful = useful;
        }

    }

}
