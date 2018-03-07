package com.zennex.trl3lg.data.entity.rest.response;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.entity.dto.SiteItemDto;

import java.util.ArrayList;

/**
 * Created by nikita on 03.06.17.
 */

public class GetSitesResponse {

    @SerializedName("ID")
    private String mId;
    @SerializedName("ErrorCode")
    private String mErrorCode;
    @SerializedName("ErrorText")
    private String mErrorText;
    @SerializedName("Data")
    private Sites mData;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(String errorCode) {
        mErrorCode = errorCode;
    }

    public String getErrorText() {
        return mErrorText;
    }

    public void setErrorText(String errorText) {
        mErrorText = errorText;
    }

    public Sites getData() {
        return mData;
    }

    public void setData(Sites data) {
        mData = data;
    }

    public static class Sites {
        @SerializedName("Sites")
        private ArrayList<SiteItemDto> mSiteItemDtos;


        public ArrayList<SiteItemDto> getSiteItemDtos() {
            return mSiteItemDtos;
        }

        public void setSiteItemDtos(ArrayList<SiteItemDto> siteItemDtos) {
            mSiteItemDtos = siteItemDtos;
        }
    }

}
