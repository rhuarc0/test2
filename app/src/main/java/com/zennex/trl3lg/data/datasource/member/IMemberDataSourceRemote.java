package com.zennex.trl3lg.data.datasource.member;

import com.zennex.trl3lg.data.rest.request.member.FetchMemberRequest;
import com.zennex.trl3lg.data.rest.response.member.FetchMemberResponse;

import io.reactivex.Observable;

public interface IMemberDataSourceRemote {
    Observable<FetchMemberResponse> fetchMember(FetchMemberRequest fetchMemberRequest);
}
