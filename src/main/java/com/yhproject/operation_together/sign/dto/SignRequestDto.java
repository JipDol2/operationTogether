package com.yhproject.operation_together.sign.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignRequestDto {

    private String name;
    private String userId;
    private String password;

    @Builder
    public SignRequestDto(String name, String userId, String password){
        this.name=name;
        this.userId=userId;
        this.password=password;
    }
}
