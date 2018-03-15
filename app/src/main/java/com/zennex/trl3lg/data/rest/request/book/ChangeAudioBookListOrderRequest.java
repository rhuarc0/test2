package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class ChangeAudioBookListOrderRequest extends BaseRequest<ChangeAudioBookListOrderRequest.Data> {

    private static final String API_METHOD = "changeLiveItemListOrder";

    public static ChangeAudioBookListOrderRequest newInstance(String moduleId,
                                                    String sessionId,
                                                    String sourceId,
                                                    String targetId) {

        ChangeAudioBookListOrderRequest request = new ChangeAudioBookListOrderRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId, sourceId, targetId));
        return request;
    }

    private ChangeAudioBookListOrderRequest() {}

    public static class Data {

        @SerializedName("SessionId")
        private String mSessionId;

        @SerializedName("SourceId")
        private String mSourceId;

        @SerializedName("TargetId")
        private String mTargetId;

        public Data(String sessionId, String sourceId, String targetId) {
            mSessionId = sessionId;
            mSourceId = sourceId;
            mTargetId = targetId;
        }

    }



}