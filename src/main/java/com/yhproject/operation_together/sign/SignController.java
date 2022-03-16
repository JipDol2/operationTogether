package com.yhproject.operation_together.sign;

import com.yhproject.operation_together.common.dto.EmptyJSON;
import com.yhproject.operation_together.sign.dto.SignRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/sign")
@RestController
@RequiredArgsConstructor
public class SignController {

    private final SignService signService;

    @PostMapping
    public ResponseEntity<EmptyJSON> joinMember(@RequestBody SignRequestDto signRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(signService.join(signRequestDto));
    }
}
