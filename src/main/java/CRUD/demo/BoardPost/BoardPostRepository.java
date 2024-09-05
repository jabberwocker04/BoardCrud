package CRUD.demo.BoardPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BoardPostRepository extends JpaRepository<BoardPost, Integer> {


//    void deleteByBoardSequence(int board_sequence) ;

}
