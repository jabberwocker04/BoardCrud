package CRUD.demo.Repository;

import CRUD.demo.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member, Integer> {

    void deleteBySequence(int sequence);


}
