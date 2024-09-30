package CRUD.demo.BoardPost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardPostRepository extends JpaRepository<BoardPost, Long> {

//    List<BoardPost> findByBoardPostSequence(Long BoardPostSequence);
    //CRUD 기능을 구현하는 JpaRepository, BoardPostEntity, Integer(Int)타입 BoardPost의 BoardPostId

    BoardPost findByBoardPostSequence(Long BoardPostSequence);

//    BoardPostDto findByBoardPostSequence(Long BoardPostSequence);


//    void deleteByBoardSequence(int board_sequence) ;

}
