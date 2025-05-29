package com.newsfeed.testagram.member.service;

import com.newsfeed.testagram.member.dto.response.MemberResponseDto;

public interface MemberService {
    MemberResponseDto getMemberById(Long id);
}
