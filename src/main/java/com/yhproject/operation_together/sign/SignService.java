package com.yhproject.operation_together.sign;

import com.yhproject.operation_together.common.dto.EmptyJSON;
import com.yhproject.operation_together.sign.dto.SignRequestDto;
import com.yhproject.operation_together.sign.entity.Sign;
import com.yhproject.operation_together.sign.entity.SignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignService {

    private final SignRepository signRepository;

    @Transactional
    public EmptyJSON join(SignRequestDto member){
        validateDuplicateMember(member);
        signRepository.join(Sign.builder()
                .name(member.getName())
                .userId(member.getUserId())
                .password(member.getPassword())
                .build());
        return new EmptyJSON();
    }
    @Transactional
    public void validateDuplicateMember(SignRequestDto member){
        List<Sign> findSigns = signRepository.findByMember(member);
        if(!findSigns.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
