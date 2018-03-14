package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikit on 27.08.2017.
 */

public class FetchQueueRequest extends BaseRequest<FetchQueueRequest.Data> {

    public static FetchQueueRequest newInstance(String rentalModuleId, Data data) {
        FetchQueueRequest request = new FetchQueueRequest();
        request.fillDefaultFields();
        request.setModuleid(rentalModuleId);
        request.setData(data);
        request.setType("getQueue");
        return request;
    }


    private FetchQueueRequest() {
    }

    public static class Data {

        @SerializedName("SessionId")
        private String mSessionId;

        @SerializedName("audioapi")
        private String mAudioApi;

        public Data(String sessionId) {
            mSessionId = sessionId;
        }

        public Data(String sessionId, String audioApi) {
            mSessionId = sessionId;
            mAudioApi = audioApi;
        }

        public String getSessionId() {
            return mSessionId;
        }

        public void setSessionId(String sessionId) {
            mSessionId = sessionId;
        }

        public String getAudioApi() {
            return mAudioApi;
        }

        public void setAudioApi(String audioApi) {
            mAudioApi = audioApi;
        }

    }

}
