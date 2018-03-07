package com.zennex.trl3lg.data.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.Member;
import com.zennex.trl3lg.data.entity.dto.MemberDto;

import javax.inject.Inject;

/**
 * Created by nikita on 10.06.17.
 */

public class MemberDtoMapper extends Mapper<MemberDto, Member> {

    @Inject
    public MemberDtoMapper() {
        super(Member::new);
    }

    @NonNull
    @Override
    protected Member transform(@NonNull MemberDto memberDto, Member member) {
        member.setIdMember(memberDto.getIdMember());
        member.setGroupId(memberDto.getGroupId());
        member.setGroupeName(memberDto.getGroupeName());
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());
        member.setName1(memberDto.getName1());
        member.setName2(memberDto.getName2());
        member.setCompany(memberDto.getCompany());
        member.setCountry(memberDto.getCountry());
        member.setState(memberDto.getState());
        member.setCity(memberDto.getCity());
        member.setAddress1(memberDto.getAddress1());
        member.setAddress2(memberDto.getAddress2());
        member.setZip(memberDto.getZip());
        member.setPhone(memberDto.getPhone());
        member.setOptOut(memberDto.getOptOut());
        member.setNotes(memberDto.getNotes());
        member.setFieldSet(memberDto.getFieldSet());
        member.setCreated(memberDto.getCreated());
        member.setModified(memberDto.getModified());
        member.setValidFrom(memberDto.getValidFrom());
        member.setValidTo(memberDto.getValidTo());
        member.setSvalidFrom(memberDto.getSvalidFrom());
        member.setSvalidTo(memberDto.getSvalidTo());
        return member;
    }

}
