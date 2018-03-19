package com.zennex.trl3lg.data.rest.request.member;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class FetchMemberRequest extends BaseRequest<FetchMemberRequest.Data> {

    private static final String API_METHOD = "getMember";

    public static FetchMemberRequest newInstance(@NonNull String moduleId, @NonNull String sessionId) {
        FetchMemberRequest request = new FetchMemberRequest();
        request.fillDefaultFields();
        request.setModuleid(moduleId);
        request.setType(API_METHOD);
        request.setData(new Data(sessionId));
        return request;
    }

    private FetchMemberRequest() {  }

    public static class Data {

        @NonNull
        @SerializedName("SessionId")
        private final String mSessionId;

        public Data(@NonNull String sessionId) {
            mSessionId = sessionId;
        }

    }

}
