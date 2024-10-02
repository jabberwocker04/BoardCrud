package CRUD.demo.boardPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {


    void deleteByBoardPostSequence(Long boardPostSequence) ;

}
