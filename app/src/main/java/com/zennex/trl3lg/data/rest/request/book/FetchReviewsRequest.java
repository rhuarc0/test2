package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 20.10.17.
 */

public class FetchReviewsRequest extends BaseRequest<FetchReviewsRequest.Data> {

    private static final String API_METHOD = "getItemReviews";

    public static FetchReviewsRequest newInstance(String moduleId,
                                                  String sessionId,
                                                  String itemId,
                                                  String startPosition,
                                                  String count) {
        FetchReviewsRequest request = new FetchReviewsRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId, itemId, startPosition, count));
        return request;
    }

    private FetchReviewsRequest() {
    }

    public static class Data {

        @SerializedName("ItemId")
        private String mItemId;
        @SerializedName("SessionId")
        private String mSessionId;
        @SerializedName("start")
        private String mStartPosition;
        @SerializedName("count")
        private String mCount;


        public Data(String sessionId,
                    String itemId,
                    String startPosition,
                    String count) {
            mItemId = itemId;
            mSessionId = sessionId;
            mStartPosition = startPosition;
            mCount = count;
        }

    }

}
