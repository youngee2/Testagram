package com.newsfeed.testagram.member.service;

import com.newsfeed.testagram.member.dto.request.MyProfileUpdateRequestDto;
import com.newsfeed.testagram.member.dto.request.PasswordRequestDto;
import com.newsfeed.testagram.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.member.dto.response.MyProfileResponseDto;
import com.newsfeed.testagram.member.dto.response.MyProfileUpdateResponseDto;

public interface MemberService {
    MemberResponseDto getMemberById(Long id);

    Object signup(String email, String password, String memberName);
    MyProfileResponseDto getMyProfileById(Long id);

    MyProfileUpdateResponseDto editMyProfileById(Long id, MyProfileUpdateRequestDto dto);

    void editPasswordById(Long id, PasswordRequestDto dto);
}
