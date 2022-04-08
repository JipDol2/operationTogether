package com.yhproject.operation_together.member.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yhproject.operation_together.common.entity.BaseTimeEntity;
import com.yhproject.operation_together.input.entity.Input;
import com.yhproject.operation_together.operation.entity.Operation;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 20, nullable = false)
    private String password;

    @OneToMany(mappedBy = "member")
    @JsonBackReference
    private List<Operation> operations;

    @Builder
    private Member(String name, String userId, String password){
        this.name=name;
        this.userId=userId;
        this.password=password;
    }
}
