package com.zennex.trl3lg.data.rest.request.book;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 26.07.17.
 */

public class FetchRentalGroupsRequest extends BaseRequest<FetchRentalGroupsRequest.Data> {

    private static final String API_METHOD = "getRentalGroups";

    public static FetchRentalGroupsRequest newInstance(@NonNull String moduleId, String mode, String onlyActive) {
        FetchRentalGroupsRequest getRentalGroupsRequest = new FetchRentalGroupsRequest();
        getRentalGroupsRequest.fillDefaultFields();
        getRentalGroupsRequest.setModuleid(moduleId);
        getRentalGroupsRequest.setType(API_METHOD);
        getRentalGroupsRequest.setData(new Data(mode, onlyActive));
        return getRentalGroupsRequest;
    }

    private FetchRentalGroupsRequest() {    }

    public static class Data {
        public static final String MODE_TREE = "tree";
        public static final String MODE_SEARCH = "search";
        public static final String MODE_LIST = "list";

        @SerializedName("mode")
        private String mMode;
        @SerializedName("onlyActive")
        private String mOnlyActive;
        @SerializedName("keyword")
        private String mKeyword;

        public Data(String mode, String onlyActive) {
            mMode = mode;
            mOnlyActive = onlyActive;
        }

        /**
         * Only Search mode
         */
        public Data(String mode, String onlyActive, String keyword) {
            mMode = mode;
            mOnlyActive = onlyActive;
            mKeyword = keyword;
        }
    }
}
