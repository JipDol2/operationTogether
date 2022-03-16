package com.yhproject.operation_together.sign.entity;

import com.yhproject.operation_together.sign.dto.SignRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SignRepository {

    private final EntityManager em;

    public Long join(Sign member){
        em.persist(member);
        return member.getId();
    }

    public List<Sign> findByMember(SignRequestDto member) {
        return em.createQuery("SELECT s FROM Sign s WHERE s.userId=:userId",Sign.class)
                .setParameter("userId",member.getUserId())
                .getResultList();
    }
}
