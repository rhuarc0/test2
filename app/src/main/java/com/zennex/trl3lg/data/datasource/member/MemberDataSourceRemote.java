package com.zennex.trl3lg.data.datasource.member;

import com.zennex.trl3lg.data.rest.IMemberWebService;
import com.zennex.trl3lg.data.rest.request.member.FetchMemberRequest;
import com.zennex.trl3lg.data.rest.response.member.FetchMemberResponse;

import javax.inject.Inject;

import io.reactivex.Observable;

public class MemberDataSourceRemote implements IMemberDataSourceRemote {

    @Inject
    IMemberWebService memberWebService;

    @Inject
    public MemberDataSourceRemote() {}

    @Override
    public Observable<FetchMemberResponse> fetchMember(FetchMemberRequest fetchMemberRequest) {
        return memberWebService.fetchMember(fetchMemberRequest);

    }
}
