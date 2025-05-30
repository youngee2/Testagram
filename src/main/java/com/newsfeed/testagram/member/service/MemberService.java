package com.newsfeed.testagram.member.service;

import com.newsfeed.testagram.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.member.dto.response.MyProfileResponseDto;

public interface MemberService {
    MemberResponseDto getMemberById(Long id);

    Object signup(String email, String password, String memberName);
    MyProfileResponseDto getMyProfileById(Long id);
}
