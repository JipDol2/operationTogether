package com.yhproject.operation_together.member;

import com.yhproject.operation_together.common.dto.EmptyJSON;
import com.yhproject.operation_together.member.dto.SignUpRequestDto;
import com.yhproject.operation_together.member.entity.Member;
import com.yhproject.operation_together.member.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

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
    public void validateDuplicateMember(SignUpRequestDto member){
        List<Member> findMembers = memberRepository.findByMember(member);
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
