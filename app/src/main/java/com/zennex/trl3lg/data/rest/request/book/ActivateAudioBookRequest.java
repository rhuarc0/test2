package com.zennex.trl3lg.data.rest.request.book;

import android.os.Bundle;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class ActivateAudioBookRequest extends BaseRequest<ActivateAudioBookRequest.Data> {

    private static final String API_METHOD = "rentLiveItem";

    public static ActivateAudioBookRequest newInstance(String moduleId, String sessionId, String queueId) {
        ActivateAudioBookRequest request = new ActivateAudioBookRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId, queueId));
        return request;
    }

    private ActivateAudioBookRequest() {}

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
