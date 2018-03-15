package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikit on 27.08.2017.
 */

public class FetchQueueRequest extends BaseRequest<FetchQueueRequest.Data> {

    private static final String API_METHOD = "getQueue";

    public static FetchQueueRequest newInstance(String rentalModuleId, String sessionId) {
        FetchQueueRequest request = new FetchQueueRequest();
        request.fillDefaultFields();
        request.setModuleid(rentalModuleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId));
        return request;
    }

    private FetchQueueRequest() {
    }

    public static class Data {

        @SerializedName("SessionId")
        private String mSessionId;

        public Data(String sessionId) {
            mSessionId = sessionId;
        }

    }

}
