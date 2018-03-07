package com.zennex.trl3lg.domain.entities;

import java.util.List;

public class Site {

    private String mId;
    private String mDomain;
    private String mUrl;
    private List<Module> mModules;


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

    public List<Module> getModules() {
        return mModules;
    }

    public void setModules(List<Module> modules) {
        mModules = modules;
    }
}
