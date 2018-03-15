package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 14.03.18.
 */

public class StorePositionRequest extends BaseRequest<StorePositionRequest.Data> {

    private static final String API_METHOD = "storeAudioPosition";

    public static StorePositionRequest newInstance(String moduleId,
                                                   String sessionId,
                                                   String itemId,
                                                   String trackId,
                                                   String position) {
        StorePositionRequest request = new StorePositionRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId, itemId, trackId, position));
        return request;
    }

    private StorePositionRequest() {    }

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
