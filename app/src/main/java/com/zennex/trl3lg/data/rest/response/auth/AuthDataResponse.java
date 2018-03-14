package com.zennex.trl3lg.data.rest.response.auth;

import com.google.gson.annotations.SerializedName;
import com.zennex.trl3lg.data.entity.dto.MemberDto;

import java.util.List;

/**
 * Created by nikita on 02.06.17.
 */

public class AuthDataResponse {

    @SerializedName("SessionId")
    private String mSession;
    @SerializedName("Member")
    private MemberDto mMember;
    @SerializedName("ModuleIds")
    private List<String> mModuleIds;
    @SerializedName("ModuleId")
    private String mModuleId;

    public AuthDataResponse() {

    }

    public String getSession() {
        return mSession;
    }

    public void setSession(String session) {
        mSession = session;
    }

    public MemberDto getMember() {
        return mMember;
    }

    public void setMember(MemberDto member) {
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
