package com.yhproject.operation_together.input;

import com.yhproject.operation_together.input.dto.*;
import com.yhproject.operation_together.common.dto.EmptyJSON;
import com.yhproject.operation_together.input.entity.Input;
import com.yhproject.operation_together.input.entity.InputRepository;
import com.yhproject.operation_together.member.entity.Member;
import com.yhproject.operation_together.member.entity.MemberRepository;
import com.yhproject.operation_together.operation.entity.Operation;
import com.yhproject.operation_together.operation.entity.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InputService {

    //private final OperationRepository operationRepository;
    private final MemberRepository memberRepository;
    private final InputRepository inputRepository;

    @Transactional
    public EmptyJSON createInput(String link, InputSaveRequestDto dto) {
        Member member = memberRepository.findByLink(link)
                .orElseThrow(() -> new IllegalArgumentException("해당 작전이 없습니다."));
        inputRepository.save(Input.builder()
                .name(dto.getName())
                .contents(dto.getContents())
                .member(member)
                .build());
        return new EmptyJSON();
    }

    @Transactional(readOnly = true)
    public InputResponseDto getInputs(Long operationId, String link) {
        Member member = getAuthOperation(operationId, link);
        List<InputResponseForm> inputs = member.getInputs()
                .stream()
                .map(this::transformEntityToDto)
                .collect(Collectors.toList());
        return new InputResponseDto(inputs);
    }

    private Member getAuthOperation(Long operationId, String link) {
        return memberRepository.findByIdAAndLink(operationId, link)
                .orElseThrow(() -> new IllegalArgumentException("해당 작전이 없습니다."));
    }

    private InputResponseForm transformEntityToDto(Input input) {
        return InputResponseForm.builder()
                .id(input.getId())
                .name(input.getName())
                .contents(input.getContents())
                .build();
    }

/*    @Transactional(readOnly = true)
    public ResultDto getResponse(Long operationId, String link) {
        Operation operation = getAuthOperation(operationId, link);
        List<Input> inputs = operation.getInputs();
        int length = operation.getInputs().size();
        List<ResultForm> result = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Input input = inputs.get((int) (Math.random() * length));
            result.add(ResultForm.builder()
                    .name(input.getName())
                    .content(input.getContents().get(i))
                    .build()
            );
        }
        return new ResultDto(result);
    }*/
}
