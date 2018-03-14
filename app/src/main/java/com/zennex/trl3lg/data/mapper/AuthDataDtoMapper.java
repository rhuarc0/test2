package com.zennex.trl3lg.data.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.domain.entities.AuthData;
import com.zennex.trl3lg.data.entity.rest.response.AuthDataResponse;

import javax.inject.Inject;

/**
 * Created by nikita on 03.06.17.
 */

public class AuthDataDtoMapper extends Mapper<AuthDataResponse, AuthData> {

    @Inject
    protected MemberDtoMapper mMemberDtoMapper;

    @Inject
    public AuthDataDtoMapper() {
        super(AuthData::new);
    }

    @NonNull
    @Override
    protected AuthData transform(@NonNull AuthDataResponse authDataResponse, AuthData authData) {
        authData.setMember(authDataResponse.getMember() == null ?
                null :
                mMemberDtoMapper.execute(authDataResponse.getMember()));
        authData.setModuleId(authDataResponse.getModuleId());
        authData.setSession(authDataResponse.getSession());
        authData.setModuleIds(authDataResponse.getModuleIds());
        return authData;
    }
}
