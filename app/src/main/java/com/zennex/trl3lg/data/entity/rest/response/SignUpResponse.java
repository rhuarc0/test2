package com.zennex.trl3lg.data.entity.rest.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 16.07.17.
 */

public class SignUpResponse extends BaseResponse<SignUpResponse.SignUpResponseData> {

    public static class SignUpResponseData{

        @SerializedName("id")
        String mId;

        public String getId() {
            return mId;
        }

        public void setId(String id) {
            mId = id;
        }

    }

}
