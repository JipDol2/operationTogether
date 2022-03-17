package com.yhproject.operation_together.member.entity;

import com.yhproject.operation_together.member.dto.LoginRequestDto;
import com.yhproject.operation_together.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long join(Member member){
        em.persist(member);
        return member.getId();
    }

    public List<Member> findByMemberID(SignUpRequestDto memberDto) {
        return em.createQuery("SELECT m FROM Member m WHERE m.userId=:userId", Member.class)
                .setParameter("userId",memberDto.getUserId())
                .getResultList();
    }

    /**
     * Optional + JPQL
     */
    public Optional<Member> findByMemberOne(LoginRequestDto memberDto){
        Member member = em.createQuery("SELECT m FROM Member m WHERE m.userId=:userId AND m.password=:password",Member.class)
                .setParameter("userId",memberDto.getUserId())
                .setParameter("password",memberDto.getPassword())
                .getSingleResult();
        return Optional.ofNullable(member);
    }
}
