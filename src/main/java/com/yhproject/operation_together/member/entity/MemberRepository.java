package com.yhproject.operation_together.member.entity;

import com.yhproject.operation_together.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public Long join(Member member){
        em.persist(member);
        return member.getId();
    }

    public List<Member> findByMember(SignUpRequestDto member) {
        return em.createQuery("SELECT m FROM Member m WHERE m.userId=:userId", Member.class)
                .setParameter("userId",member.getUserId())
                .getResultList();
    }
}
