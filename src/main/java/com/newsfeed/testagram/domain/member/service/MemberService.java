package com.newsfeed.testagram.domain.member.service;

import com.newsfeed.testagram.common.exception.member.*;
import com.newsfeed.testagram.common.security.PasswordEncoder;
import com.newsfeed.testagram.common.util.JwtUtil;
import com.newsfeed.testagram.common.valid.PasswordValid;
import com.newsfeed.testagram.domain.member.dto.request.MyProfileDeleteRequestDto;
import com.newsfeed.testagram.domain.member.dto.request.MyProfileUpdateRequestDto;
import com.newsfeed.testagram.domain.member.dto.request.PasswordRequestDto;
import com.newsfeed.testagram.domain.member.dto.response.MemberResponseDto;
import com.newsfeed.testagram.domain.member.dto.response.MemberSignUpResponse;
import com.newsfeed.testagram.domain.member.dto.response.MyProfileResponseDto;
import com.newsfeed.testagram.domain.member.dto.response.MyProfileUpdateResponseDto;
import com.newsfeed.testagram.domain.member.entity.Member;
import com.newsfeed.testagram.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final JwtUtil jwtUtil;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponse signup(String email, String password, String nickname) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistsException();
        }

        Member member = new Member(email, passwordEncoder.encode(password), nickname);
        memberRepository.save(member);
        return MemberSignUpResponse.toDto("회원가입이 완료되었습니다.", member);
    }

    /**
     * @param id 조회할 회원의 id
     * @return 회원 정보 DTO
     * @throws MemberNotFoundException 해당 ID의 회원이 존재하지 않을 경우 예외 발생합니다.
     */
    public MemberResponseDto getMemberById(Long id) {
        return MemberResponseDto.of(memberRepository.findByIdOrThrow(id));
    }
    /**
     *
     *로그인한 사용자의 프로필 정보를 조회합니다.
     * @return 프로필 정보 DTO
     * @throws MemberNotMatchedException 사용자가 존재하지 않는 경우
     */
    public MyProfileResponseDto getMemberById(String authHead) {
        long id = jwtUtil.getMemberIdFormToken(authHead);
        return MyProfileResponseDto.of(memberRepository.findByIdOrThrow(id));
    }

    /**
     * 로그인한 사용자 프로필을 수정합니다.
     *
     * @param dto 내 프로필 수정 요청 DTO
     * @return 수정된 프로필 정보 DTO
     * @throws MemberNotMatchedException 로그인 사용자와 요청 대상 사용자가 일치하지 않을 때 발생
     */
    @Transactional
    public MyProfileUpdateResponseDto editMyProfileById(String token, MyProfileUpdateRequestDto dto) {
        Member member = memberRepository.findById(jwtUtil.getMemberIdFormToken(token))
                .orElseThrow(MemberNotMatchedException::new);

        member.updateProfile(dto.getNickname(), dto.getImage());
        return MyProfileUpdateResponseDto.of(member);
    }

    /**
     * 로그인한 사용자 비밀번호를 수정합니다.
     *
     * @param dto 내 비밀번호 수정 요청 DTO
     * @throws MemberNotMatchedException 로그인 사용자와 요청 대상 사용자가 일치하지 않을 때 발생
     * @throws PasswordNotMatchedException 비밀번호 불일치
     * @throws SamePasswordException 현재 비밀번호와 새로운 비밀번호가 일치하는 경우
     * @throws InvalidPasswordFormatException 비밀번호 형식에 맞지 않는 경우
     */
    @Transactional
    public void editPasswordById(String token, PasswordRequestDto dto) {
        Member member = memberRepository.findById(jwtUtil.getMemberIdFormToken(token))
                .orElseThrow(MemberNotMatchedException::new);

        validateCurrentPassword(dto.getCurrentPassword(), member.getPassword());
        validateDifferentFromOldPassword(dto.getNewPassword(), member.getPassword());
        validatePasswordFormat(dto.getNewPassword());

        member.updatePassword(passwordEncoder.encode(dto.getNewPassword()));
    }


    /**
     * 로그인한 사용자 탈퇴 처리합니다.
     * @param dto 탈퇴 요청 DTO
     * @throws MemberNotMatchedException 로그인 사용자와 요청 대상 사용자가 일치하지 않을 때 발생
     * @throws PasswordNotMatchedException 현재 비밀번호와 불일치
     */
    @Transactional
    public void deleteProfileById(String token, MyProfileDeleteRequestDto dto) {
        Member member = memberRepository.findById(jwtUtil.getMemberIdFormToken(token))
                .orElseThrow(PasswordNotMatchedException::new);

        validateCurrentPassword(dto.getPassword(), member.getPassword());
        member.deleteProfile();
    }


    // 현재 비밀번호 불일치
    private void validateCurrentPassword(String currentPassword, String password) {
        if (!passwordEncoder.matches(currentPassword, password)) {
            throw new PasswordNotMatchedException();
        }
    }

    // 현재 비밀번호와 새 비밀번호가 동일한 경우
    private void validateDifferentFromOldPassword(String newPassword, String password) {
        if (passwordEncoder.matches(newPassword, password)) {
            throw new SamePasswordException();
        }
    }

    // 새 비밀번호가 형식에 안맞는 경우
    private void validatePasswordFormat(String newPassword) {
        if (!newPassword.matches(PasswordValid.passwordRegex)) {
            throw new InvalidPasswordFormatException();
        }
    }

}
