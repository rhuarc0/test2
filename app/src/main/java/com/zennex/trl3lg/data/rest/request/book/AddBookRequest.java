package com.zennex.trl3lg.data.rest.request.book;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

import java.util.List;

/**
 * Created by nikita on 14.03.18.
 */

public class AddBookRequest extends BaseRequest<AddBookRequest.Data> {

    public static final String API_METHOD_ADD_TO_QUEUE = "addToQueue";
    public static final String API_METHOD_ADD_TO_LIVE_QUEUE = "addToLiveQueue";

    /**
     * @param type может быть только addToQueue или addToLiveQueue
     *
     * !!! Параметров многовато, но это сделано для того, чтобы объект Data не создавался
     *             в разных метах отдельно.
     */
    public static AddBookRequest newInstance(String moduleId,
                                             String type,
                                             String sessionId,
                                             List<String> items,
                                             String noRent) {
        AddBookRequest request = new AddBookRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(type);
        request.setData(new Data(sessionId, items, noRent));
        return request;
    }

    private AddBookRequest() {  }

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
