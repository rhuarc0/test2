package com.zennex.trl3lg.data.rest.request;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by nikita on 26.07.17.
 */

public class IsValidSessionTokenRequest extends BaseRequest<IsValidSessionTokenRequest.Data> {

    public static IsValidSessionTokenRequest newInstance(@NonNull String moduelId, @NonNull String siteId, @NonNull String sessionId) {
        IsValidSessionTokenRequest request = new IsValidSessionTokenRequest();
        request.fillDefaultFields();
        request.setModuleid(moduelId);
        request.setType("isValidSession");
        request.setData(new Data(sessionId, siteId));
        return request;
    }

    public static class Data {

        @NonNull
        @SerializedName("SessionId")
        private final String mSessionId;

        @NonNull
        @SerializedName("SiteId")
        private final String mSiteId;

        public Data(@NonNull String sessionId, @NonNull String siteId) {
            mSessionId = sessionId;
            mSiteId = siteId;
        }


        @NonNull
        public String getSessionId() {
            return mSessionId;
        }

        @NonNull
        public String getSiteId() {
            return mSiteId;
        }

    }


}
