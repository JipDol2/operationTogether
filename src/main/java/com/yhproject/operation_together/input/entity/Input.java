package com.yhproject.operation_together.input.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.yhproject.operation_together.common.entity.BaseTimeEntity;
import com.yhproject.operation_together.member.entity.Member;
import com.yhproject.operation_together.operation.entity.Operation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Input extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "content_list", joinColumns = @JoinColumn(name = "id"))
    @Column(name ="content", length = 50, nullable = false)
    private List<String> contents;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "member_id")
    private Member member;

    /*@ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "operation_id")
    private Operation operation;*/

    @Builder
    private Input(String name, List<String> contents, Member member) {
        this.name = name;
        this.contents = contents;
        this.member = member;
        member.getInputs().add(this);
        //this.operation = operation;
    }
}
