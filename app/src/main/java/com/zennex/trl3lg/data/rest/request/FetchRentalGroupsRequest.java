package com.zennex.trl3lg.data.rest.request;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 26.07.17.
 */

public class FetchRentalGroupsRequest extends BaseRequest<FetchRentalGroupsRequest.Data> {

    public static FetchRentalGroupsRequest newInstance(@NonNull String moduleId, @NonNull Data data) {

        FetchRentalGroupsRequest getRentalGroupsRequest = new FetchRentalGroupsRequest();
        getRentalGroupsRequest.fillDefaultFields();
        getRentalGroupsRequest.setModuleid(moduleId);
        getRentalGroupsRequest.setType("getRentalGroups");
        getRentalGroupsRequest.setData(data);
        return getRentalGroupsRequest;
    }

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


        public String getMode() {
            return mMode;
        }

        public void setMode(String mode) {
            mMode = mode;
        }

        public String getOnlyActive() {
            return mOnlyActive;
        }

        public void setOnlyActive(String onlyActive) {
            mOnlyActive = onlyActive;
        }

        public String getKeyword() {
            return mKeyword;
        }

        public void setKeyword(String keyword) {
            mKeyword = keyword;
        }
    }
}
