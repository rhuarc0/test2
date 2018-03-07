package com.zennex.trl3lg.presentation.model;

/**
 * Created by nikita on 03.06.17.
 */

public class SiteModel {

    private String mUrl;
    private String mDomain;

    public SiteModel() {
    }

    public SiteModel(String url, String domain) {
        mUrl = url;
        mDomain = domain;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDomain() {
        return mDomain;
    }

    public void setDomain(String domain) {
        mDomain = domain;
    }
}
