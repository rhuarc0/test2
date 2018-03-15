package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class RenewAudioBookRequest extends BaseRequest<RenewAudioBookRequest.Data> {

    private static final String API_METHOD = "renewLiveItem";

    public static RenewAudioBookRequest newInstance(String moduleId,
                                                    String sessionId,
                                                    String queueId,
                                                    String disable) {

        RenewAudioBookRequest request = new RenewAudioBookRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId, queueId, disable));
        return request;
    }

    private RenewAudioBookRequest() {}

    public static class Data {

        @SerializedName("SessionId")
        private String mSessionId;

        @SerializedName("QueueId")
        private String mQueueId;

        @SerializedName("Disable")
        private String mDisable;

        public Data(String sessionId, String queueId, String disable) {
            mSessionId = sessionId;
            mQueueId = queueId;
            mDisable = disable;
        }

    }



}

