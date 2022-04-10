package com.yhproject.operation_together.operation.dto;

import com.yhproject.operation_together.operation.entity.Operation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class OperationSaveRequestDto {

    private Long id;

    @Builder
    public OperationSaveRequestDto(Long id) {
        this.id=id;
    }

    /*public void setLink(String link) {
        this.link = link;
    }*/

    // DTO -> Entity
    /*public Operation toEntity() {
        return Operation.builder()
                .name(name)
                .password(password)
                .link(link)
                .operationDate(operationDate)
                .build();
    }*/

}
