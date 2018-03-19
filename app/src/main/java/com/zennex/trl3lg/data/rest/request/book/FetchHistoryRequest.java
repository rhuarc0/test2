package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class FetchHistoryRequest  extends BaseRequest<FetchHistoryRequest.Data> {

    private static final String API_METHOD = "getHistory";

    public static FetchHistoryRequest newInstance(String moduleId, String sessionId) {

        FetchHistoryRequest request = new FetchHistoryRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId));
        return request;
    }

    private FetchHistoryRequest() {}

    public static class Data {

        @SerializedName("SessionId")
        private String mSessionId;

        public Data(String sessionId) {
            mSessionId = sessionId;
        }

    }

}