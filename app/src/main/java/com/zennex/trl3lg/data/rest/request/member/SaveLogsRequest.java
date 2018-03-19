package com.zennex.trl3lg.data.rest.request.member;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

/**
 * Created by nikita on 15.03.18.
 */

public class SaveLogsRequest  extends BaseRequest<SaveLogsRequest.Data> {

    private static final String API_METHOD = "saveLogs";

    public static SaveLogsRequest newInstance(String moduleId, String logs, String idMember) {
        SaveLogsRequest saveLogsRequest = new SaveLogsRequest();
        saveLogsRequest.fillDefaultFields();
        saveLogsRequest.setModuleid(moduleId);
        saveLogsRequest.setType(API_METHOD);
        saveLogsRequest.setData(new Data(logs, idMember));
        return saveLogsRequest;
    }

    public static class Data {

        @SerializedName("content")
        private String mLogs;

        @SerializedName("id")
        private String mIdMember;

        public Data(String logs, String idMember) {
            mLogs = logs;
            mIdMember = idMember;
        }

    }

}
