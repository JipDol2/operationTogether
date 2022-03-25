package com.yhproject.operation_together.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
/**
 * @MpaaerSuperclass : 공통 매핑 정보가 필요할때 사용한다. createDate 와 modifiedDate는 지속적으로 사용되는 변수여서 BaseEntity 로 선언한 뒤 상속을 받게 한다.
 * 조회 및 검색이 불가하고 직접 생성해서 사용할 일이 없기 때문에 추상 클래스(abstract class)로 만드는 것이 좋다.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}