package com.yhproject.operation_together.operation;

import antlr.StringUtils;
import com.yhproject.operation_together.member.entity.Member;
import com.yhproject.operation_together.member.entity.MemberJpaRepository;
import com.yhproject.operation_together.member.entity.MemberRepository;
import com.yhproject.operation_together.operation.dto.*;
import com.yhproject.operation_together.common.auth.jwt.JwtTokenProvider;
import com.yhproject.operation_together.operation.entity.Operation;
import com.yhproject.operation_together.operation.entity.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperationService {

    private final MemberRepository memberRepository;
    private final OperationRepository operationRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public OperationSaveResponseDto createOperation(OperationSaveRequestDto dto) {
        Member member = memberRepository.findById(dto.getId()).orElseThrow(()->new IllegalArgumentException("해당 회원을 찾을 수 없습니다."));
        member.setLink(createLink());
        return new OperationSaveResponseDto(member.getLink());
    }

    /**
     * link 생성
     */
    private String createLink() {
        // 난수 링크 생성
        String candidate = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder link = new StringBuilder();
        while (true) {
            for (int i = 0; i < 16; i++) {
                int index = (int) (Math.random() * candidate.length());
                link.append(candidate.charAt(index));
            }
            Optional<Member> member = memberRepository.findByLink(link.toString());
            if (member.isPresent()) {
                link = new StringBuilder();
                continue;
            }
            return link.toString();
        }
    }

    public OperationResponseDto getOperation(String link) {
        //Operation operation = operationRepository.findByLink(link).orElseThrow(() -> new IllegalArgumentException("해당 작전이 없습니다."));
        Member member = memberRepository.findByLink(link).orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다."));
        return OperationResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .link(member.getLink())
                .build();
    }

    public OperationResponseDto getOperation(Long id) {
        Operation operation = operationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 작전이 없습니다."));
        return OperationResponseDto.builder()
                .id(operation.getId())
                .name(operation.getName())
                .link(operation.getLink())
                .build();
    }

    public List<OperationResponseDto> getOperationList(Long id){
        List<Operation> operationsList = operationRepository.findByOperations(id);
        if(operationsList.size()<=0){
            throw new IllegalArgumentException("해당 작전이 없습니다");
        }
        return operationsList.stream()
                .map(opearation->OperationResponseDto.builder()
                        .id(opearation.getId())
                        .name(opearation.getName())
                        .link(opearation.getLink())
                        .build()
                )
                .collect(Collectors.toList());
    }

    /**
     * password 체킹
     */
    public PasswordResponseDto checkPassword(String link, PasswordRequestDto dto) {
        //Operation operation = operationRepository.findByLink(link).orElseThrow(() -> new IllegalArgumentException("해당 작전이 없습니다."));
        Member member = memberRepository.findByLink(link).orElseThrow(()->new IllegalArgumentException("해당 회원이 없습니다."));
        String correctPassword = member.getPassword();
        boolean isCorrect = correctPassword.equals(dto.getPassword());
        //boolean isCorrect = Objects.equals(correctPassword, dto.getPassword());
        if (isCorrect) {
            //String jwtToken = jwtTokenProvider.createJwtToken(member.getId());
            return new PasswordResponseDto("true");
        }
        return new PasswordResponseDto(null);
    }
}
