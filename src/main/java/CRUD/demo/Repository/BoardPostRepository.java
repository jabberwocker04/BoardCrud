package CRUD.demo.Repository;

import CRUD.demo.domain.BoardPost;
import CRUD.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface BoardPostRepository extends JpaRepository<BoardPost, Integer> {


    void deleteByBoardId(int boardId) ;

}
