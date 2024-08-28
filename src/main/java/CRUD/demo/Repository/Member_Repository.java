package CRUD.demo.Repository;

import CRUD.demo.domain.Member;
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

    public Member findById(String memberId){
        return em.find(Member.class, memberId);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class) // DB query와 비슷한데, from의 대상이 테이블이 아니라 Entity가 대상이 된다.
                .getResultList();
    }


    public List<Member> findBySequence(Long sequence) {
        return em.createQuery("select m from Member m where m.sequence = :sequence", Member.class)
                .setParameter("sequence", sequence)
                .getResultList();
    }

    public Member findOne(Long sequence){
        return em.find(Member.class, sequence);
    }

    public List<Member> delete(Long sequence){

        return em.createQuery("delete m from Member m where m.sequence = :sequence", Member.class)
                .setParameter("Sequence", sequence)
                .getResultList();

    }

}
