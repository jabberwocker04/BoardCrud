package CRUD.demo.BoardPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {
    BoardPost findByBoardSequence(Long boardSequence);
    //CRUD 기능을 구현하는 JpaRepository, BoardPostEntity, Integer(Int)타입 BoardPost의 BoardPostId


//    void deleteByBoardSequence(int board_sequence) ;

}
