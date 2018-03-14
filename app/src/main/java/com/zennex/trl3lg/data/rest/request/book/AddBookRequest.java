package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

import java.util.List;

/**
 * Created by nikita on 14.03.18.
 */

public class AddBookRequest extends BaseRequest<AddBookRequest.Data> {

    /**
     * @param type может быть только addToQueue или addToLiveQueue
     */
    public static AddBookRequest newInstance(String moduleId, Data data, String type) {
        AddBookRequest request = new AddBookRequest();
        request.fillDefaultFields();
        request.setData(data);
        request.setModuleid(moduleId);
        request.setType(type);
        return request;
    }

    public static class Data {

        @SerializedName("SessionId")
        private String mSessionId;
        @SerializedName("Items")
        private List<String> mItemsIds;
        @SerializedName("NoRent")
        private String mNoRent;


        public Data(String sessionId, List<String> itemIds, String noRent) {
            mSessionId = sessionId;
            mItemsIds = itemIds;
            mNoRent = noRent;
        }

    }

}
