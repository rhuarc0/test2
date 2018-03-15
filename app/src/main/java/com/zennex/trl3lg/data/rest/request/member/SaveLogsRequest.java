package com.zennex.trl3lg.data.rest.request.member;

import android.os.Bundle;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.rest.request.BaseRequest;

import io.reactivex.annotations.NonNull;

/**
 * Created by nikita on 15.03.18.
 */

public class SaveLogsRequest  extends BaseRequest<SaveLogsRequest.Data> {

    public static SaveLogsRequest newInstance(String moduleId, SaveLogsRequest.Data data) {
        SaveLogsRequest saveLogsRequest = new SaveLogsRequest();
        saveLogsRequest.fillDefaultFields();
        saveLogsRequest.setModuleid(moduleId);
        saveLogsRequest.setType("saveLogs");
        saveLogsRequest.setData(data);
        return saveLogsRequest;
    }

    public static class Data {

        @SerializedName("saveLogs")
        private String mContent;

        public Data(String content) {
            mContent = content;
        }
    }

}
