package com.yhproject.operation_together.sign.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Entity
@RequiredArgsConstructor
public class Sign {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 20, nullable = false)
    private String password;

    @Builder
    private Sign(String name,String userId,String password){
        this.name=name;
        this.userId=userId;
        this.password=password;
    }
}
