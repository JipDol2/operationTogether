package com.yhproject.operation_together.member;

import com.yhproject.operation_together.common.dto.EmptyJSON;
import com.yhproject.operation_together.member.dto.LoginRequestDto;
import com.yhproject.operation_together.member.dto.LoginResponseDto;
import com.yhproject.operation_together.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public ResponseEntity<EmptyJSON> joinMember(@RequestBody SignUpRequestDto signUpRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.join(signUpRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginMember(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(memberService.login(loginRequestDto));
    }
}
