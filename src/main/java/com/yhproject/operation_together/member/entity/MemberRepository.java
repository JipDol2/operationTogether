package com.yhproject.operation_together.member.entity;

import com.yhproject.operation_together.member.dto.LoginRequestDto;
import com.yhproject.operation_together.operation.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m WHERE m.userId=:#{#loginRequestDto.userId} AND m.password=:#{#loginRequestDto.password}")
    public Optional<Member> findByMemberOne(@Param("loginRequestDto") LoginRequestDto loginRequestDto);

    @Query("SELECT m FROM Member m WHERE m.link=:link")
    public Optional<Member> findByLink(@Param("link") String link);

    @Query("SELECT o FROM Member o WHERE o.id=:id AND o.link=:link")
    public Optional<Member> findByIdAAndLink(@Param("id") Long id, @Param("link") String link);

    //@Query("select m from Member m where m.id=:id AND m.password=:password")
    //public Optional<Member> findByMemberOne(@Param("id") String id,@Param("password") String password);
}
