package com.yhproject.operation_together.member;

import com.yhproject.operation_together.common.auth.jwt.JwtTokenProvider;
import com.yhproject.operation_together.common.dto.EmptyJSON;
import com.yhproject.operation_together.member.dto.LoginRequestDto;
import com.yhproject.operation_together.member.dto.LoginResponseDto;
import com.yhproject.operation_together.member.dto.SignUpRequestDto;
import com.yhproject.operation_together.member.entity.Member;
import com.yhproject.operation_together.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public EmptyJSON join(SignUpRequestDto member){
        validateDuplicateMember(member);
        memberRepository.join(Member.builder()
                .name(member.getName())
                .userId(member.getUserId())
                .password(member.getPassword())
                .build());
        return new EmptyJSON();
    }
    @Transactional
    public void validateDuplicateMember(SignUpRequestDto signUpRequestDto){
        List<Member> findMembers = memberRepository.findByMemberID(signUpRequestDto);
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 로그인을 하면 Token을 받는다
     */
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member findMember = memberRepository.findByMemberOne(loginRequestDto).orElseThrow(() -> new IllegalArgumentException("회원아이디 혹은 비밀번호를 잘못 입력하셨습니다."));
        if(findMember.getUserId().equals(loginRequestDto.getUserId()) && findMember.getPassword().equals(loginRequestDto.getPassword())){
            String jwtToken = jwtTokenProvider.createJwtToken(findMember.getId());
            return new LoginResponseDto(jwtToken);
        }
        return new LoginResponseDto(null);
    }
}
