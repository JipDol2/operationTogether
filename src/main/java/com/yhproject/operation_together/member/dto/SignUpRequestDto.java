package com.yhproject.operation_together.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {

    private String name;
    private String userId;
    private String password;

    @Builder
    public SignUpRequestDto(String name, String userId, String password){
        this.name=name;
        this.userId=userId;
        this.password=password;
    }
}
