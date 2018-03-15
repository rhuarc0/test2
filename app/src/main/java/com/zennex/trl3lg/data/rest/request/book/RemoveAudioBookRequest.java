package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class RemoveAudioBookRequest extends BaseRequest<RemoveAudioBookRequest.Data> {

    private static final String API_METHOD = "removeFromLiveQueue";

    public static RemoveAudioBookRequest newInstance(String moduleId,
                                                    String sessionId,
                                                    String queueId) {

        RemoveAudioBookRequest request = new RemoveAudioBookRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId, queueId));
        return request;
    }

    private RemoveAudioBookRequest() {}

    public static class Data {

        @SerializedName("SessionId")
        private String mSessionId;

        @SerializedName("QueueId")
        private String mQueueId;

        public Data(String sessionId, String queueId) {
            mSessionId = sessionId;
            mQueueId = queueId;
        }

    }

}
