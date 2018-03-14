package com.zennex.trl3lg.data.rest.request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 14.03.18.
 */

public class StorePositionRequest extends BaseRequest<StorePositionRequest.Data> {

    public static StorePositionRequest newInstance(String moduleId, Data data) {
        StorePositionRequest request = new StorePositionRequest();
        request.fillDefaultFields();
        request.setData(data);
        request.setModuleid(moduleId);
        request.setType("storeAudioPosition");
        return request;
    }

    public static class Data {

        @SerializedName("ItemId")
        private String mItemId;
        @SerializedName("SessionId")
        private String mSessionId;
        @SerializedName("TrackId")
        private String mTrackId;
        @SerializedName("Position")
        private String mPosition;


        public Data(String sessionId, String itemId, String trackId, String position) {
            mItemId = itemId;
            mSessionId = sessionId;
            mTrackId = trackId;
            mPosition = position;
        }
    }

}
