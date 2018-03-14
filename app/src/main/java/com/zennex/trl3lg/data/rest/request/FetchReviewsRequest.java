package com.zennex.trl3lg.data.rest.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 20.10.17.
 */

public class FetchReviewsRequest extends BaseRequest<FetchReviewsRequest.Data> {

    public static FetchReviewsRequest newInstance(String moduleId, Data data) {
        FetchReviewsRequest request = new FetchReviewsRequest();
        request.fillDefaultFields();
        request.setData(data);
        request.setModuleid(moduleId);
        request.setType("getItemReviews");
        return request;
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


        public Data(String sessionId, String itemId, String startPosition, String count) {
            mItemId = itemId;
            mSessionId = sessionId;
            mStartPosition = startPosition;
            mCount = count;
        }

        public String getItemId() {
            return mItemId;
        }

        public void setItemId(String itemId) {
            mItemId = itemId;
        }

        public String getSessionId() {
            return mSessionId;
        }

        public void setSessionId(String sessionId) {
            mSessionId = sessionId;
        }

        public String getStartPosition() {
            return mStartPosition;
        }

        public void setStartPosition(String startPosition) {
            mStartPosition = startPosition;
        }

        public String getCount() {
            return mCount;
        }

        public void setCount(String count) {
            mCount = count;
        }
    }

}
