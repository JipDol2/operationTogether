package com.yhproject.operation_together.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginRequestDto {

    private String name;
    private String password;

    @Builder
    public LoginRequestDto(String name,String password){
        this.name=name;
        this.password=password;
    }
}
