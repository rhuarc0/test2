package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 14.03.18.
 */

public class SetReviewUsefulRequest extends BaseRequest<SetReviewUsefulRequest.Data> {

    private static final String API_METHOD = "setReviewUseful";

    public static SetReviewUsefulRequest newInstance(String moduleId,
                                                     String sessionId,
                                                     String reviewId,
                                                     String useful) {
        SetReviewUsefulRequest request = new SetReviewUsefulRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId, reviewId, useful));
        return request;
    }

    private SetReviewUsefulRequest() {  }

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
