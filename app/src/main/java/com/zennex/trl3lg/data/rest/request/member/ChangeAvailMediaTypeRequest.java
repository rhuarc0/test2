package com.zennex.trl3lg.data.rest.request.member;

import android.os.Bundle;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class ChangeAvailMediaTypeRequest extends BaseRequest<ChangeAvailMediaTypeRequest.Data> {

    private static final String API_METHOD = "editMember";

    public static ChangeAvailMediaTypeRequest newInstance(String moduleId, String memberId, String availMediaType) {
        ChangeAvailMediaTypeRequest request = new ChangeAvailMediaTypeRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(memberId, availMediaType));
        return request;
    }

    private ChangeAvailMediaTypeRequest() {}

    public static class Data {

        @SerializedName("id")
        private String mMemberId;

        @SerializedName("avail_media_type_client")
        private String mAvailMediaType;

        public Data(String memberId, String availMediaType) {
            mMemberId = memberId;
            mAvailMediaType = availMediaType;
        }

    }

}
