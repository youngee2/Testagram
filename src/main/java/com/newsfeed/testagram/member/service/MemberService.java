package com.newsfeed.testagram.member.service;

import com.newsfeed.testagram.member.dto.request.MyProfileDeleteRequestDto;
import com.newsfeed.testagram.member.dto.request.MyProfileUpdateRequestDto;
import com.newsfeed.testagram.member.dto.request.PasswordRequestDto;
import com.newsfeed.testagram.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.member.dto.response.MyProfileResponseDto;
import com.newsfeed.testagram.member.dto.response.MyProfileUpdateResponseDto;

public interface MemberService {
    /**
     * 사용자 ID로 사용자 정보를 조회합니다.
     *
     * @param id 사용자 고유 아이디
     * @return 사용자 정보 DTO
     */
    MemberResponseDto getMemberById(Long id);


    /**
     *로그인한 사용자의 프로필 정보를 조회합니다.
     * @param id 로그인한 사용자 ID
     * @return 내 프로필 정보 DTO
     */
    MyProfileResponseDto getMyProfileById(Long id);

    /**
     * 로그인한 사용자의 프로필 정보를 수정합니다.
     * @param id 로그인한 사용자 ID
     * @param dto 내 프로필 수정 요청 DTO
     * @return  수정된 프로필 정보 DTO
     */
    MyProfileUpdateResponseDto editMyProfileById(Long id, MyProfileUpdateRequestDto dto);

    /**
     * 로그인한 사용자의 비밀번호를 수정합니다.
     * @param id 로그인한 사용자 ID
     * @param dto 비밀번호 수정 요청 DTO
     */

    void editPasswordById(Long id, PasswordRequestDto dto);

    /**
     * 로그인한 사용자를 탈퇴(soft delete)합니다.
     * @param id 로그인한 사용자 ID
     * @param dto 탈퇴 요청 DTO
     */
    void deleteProfileById(Long id, MyProfileDeleteRequestDto dto);
}
