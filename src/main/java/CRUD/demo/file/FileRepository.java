package CRUD.demo.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<BoardFile, Long> {

//    @Query("SELECT b FROM boardFile b WHERE b.boardPost.boardPostSequence = :boardPostSequence") // Repository의 Method만으로는 Query가 잘 못 생성된다. boardPost에서 boardPostSequence를 나눠서 찾기 때문에 Query를 직접 지정해주었다.
//    List<BoardFile> findByBoardPostSequence(Long BoardPostSequence);

//        List<BoardFile> findByBoardPost_BoardPostSequence(Long boardPostSequence);

        // JPQL 쿼리 사용
//        @Query("SELECT b FROM BoardFile b WHERE b.boardPostSequence = :boardPostSequence")
//        List<BoardFile> findByBoardPost_boardPostSequence(Long boardPostSequence);

        // JPQL을 사용하여 BoardPost의 boardPostSequence로 조회
//        @Query("SELECT b FROM BoardFile b WHERE b.boardPost.boardPostSequence = :boardPostSequence")
//        List<BoardFile> findByBoardPost_boardPostSequence(@Param("boardPostSequence") Long boardPostSequence);


}
