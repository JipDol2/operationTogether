package com.yhproject.operation_together.operation.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {

    @Query("SELECT o FROM Operation o WHERE o.link=:link")
    public Optional<Operation> findByLink(@Param("link") String link);

    @Query("SELECT o FROM Operation o WHERE o.id=:id AND o.link=:link")
    public Optional<Operation> findByIdAAndLink(@Param("id") Long id,@Param("link") String link);

    @Query("SELECT o FROM Operation o WHERE o.id=:id")
    public List<Operation> findByOperations(@Param("id") Long id);
}
