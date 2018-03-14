package com.zennex.trl3lg.data.entity;


import com.zennex.trl3lg.domain.entities.Member;

import java.util.List;

/**
 * Created by nikita on 03.06.17.
 */

public class AuthData {

    private String mSession;
    private Member mMember;
    private List<String> mModuleIds;
    private String mModuleId;

    public String getSession() {
        return mSession;
    }

    public void setSession(String session) {
        mSession = session;
    }

    public Member getMember() {
        return mMember;
    }

    public void setMember(Member member) {
        mMember = member;
    }

    public List<String> getModuleIds() {
        return mModuleIds;
    }

    public void setModuleIds(List<String> moduleIds) {
        mModuleIds = moduleIds;
    }

    public String getModuleId() {
        return mModuleId;
    }

    public void setModuleId(String moduleId) {
        mModuleId = moduleId;
    }
}
