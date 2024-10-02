package CRUD.demo.member;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class Member_Repository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findById(Long memberSequence){
        return em.find(Member.class, memberSequence);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // DB query와 비슷한데, from의 대상이 테이블이 아니라 Entity가 대상이 된다.
                .getResultList();
    }


    public List<Member> findByMemberSequence(Long memberSequence) {
        return em.createQuery("select m from Member m where m.memberSequence = :memberSequence", Member.class)
                .setParameter("memberSequence", memberSequence)
                .getResultList();
    }

    // Select 단 건 조회
    public Member findOne(Long memberSequence){
        return em.find(Member.class, memberSequence);
    }

    public List<Member> delete(Long memberSequence){

        return em.createQuery("delete m from Member m where m.memberSequence = :memberSequence", Member.class)
                .setParameter("memberSequence", memberSequence)
                .getResultList();

    }

}
