package com.zennex.trl3lg.data.entity.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nikita on 26.07.16.
 */
public class SiteItemDto {

    @SerializedName("Id")
    private String mId;
    @SerializedName("Domain")
    private String mDomain;
    @SerializedName("URL")
    private String mUrl;
    @SerializedName("modules")
    private List<ModuleDto> mModuleDtos;


    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getDomain() {
        return mDomain;
    }

    public void setDomain(String domain) {
        mDomain = domain;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public List<ModuleDto> getModuleDtos() {
        return mModuleDtos;
    }

    public void setModuleDtos(List<ModuleDto> moduleDtos) {
        mModuleDtos = moduleDtos;
    }
}
