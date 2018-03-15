package com.zennex.trl3lg.data.rest.request.auth;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikita on 03.06.17.
 */

public class GetSitesRequest extends BaseRequest<GetSitesRequest.Data> {

    private static final String API_METHOD = "getSitesAndModules";

    public static GetSitesRequest newInstance() {
        GetSitesRequest getSitesRequest = new GetSitesRequest();
        getSitesRequest.fillDefaultFields();
        getSitesRequest.setType(API_METHOD);
        getSitesRequest.setData(new Data());
        return getSitesRequest;
    }

    private GetSitesRequest() {

    }

    public static class Data {

        public static final String TYPE_RENTAL = "rental";
        public static final String TYPE_MEMBERSHIP = "membership";

        @SerializedName("ModuleTypes")
        private List<String> mItems;

        public Data() {
            mItems = new ArrayList<>();
            mItems.add(TYPE_RENTAL);
            mItems.add(TYPE_MEMBERSHIP);
        }

    }

}
