package com.zennex.trl3lg.data.entity.rest.request;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by nikita on 03.06.17.
 */

public class GetSitesRequest extends BaseRequest<GetSitesRequest.Data> {


    public static GetSitesRequest newInstance(@NonNull GetSitesRequest.Data data) {
        GetSitesRequest getSitesRequest = new GetSitesRequest();
        getSitesRequest.fillDefaultFields();
        getSitesRequest.setType("getSitesAndModules");
        getSitesRequest.setData(data);
        return getSitesRequest;
    }

    public static class Data {

        public static final String TYPE_RENTAL = "rental";
        public static final String TYPE_MEMBERSHIP = "membership";

        @SerializedName("ModuleTypes")
        private ArrayList<String> mItems;

        public ArrayList<String> getItems() {
            return mItems;
        }

        public void setItems(ArrayList<String> items) {
            mItems = items;
        }

        public boolean addType(String type) {
            if (mItems == null) mItems = new ArrayList<>();
            return mItems.add(type);
        }


    }

}
