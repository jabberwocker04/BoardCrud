package CRUD.demo.Repository;

import CRUD.demo.domain.BoardPost;
import CRUD.demo.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardPost_Repository {

    private final EntityManager em;

    public void save(BoardPost boardPost){
        em.persist(boardPost);
    }

    public BoardPost findByBoardId(int boardId){
        return em.find(BoardPost.class, boardId);
    }

    //전체 조회
    /*
    * 전체 조회가 HTMl에서 잘 불러와지지 않는다. 맵핑의 문제 같아서 계속 JPQL을 건드려 보는데, 이건 아닌 것 같다(상대적으로 SQL문이 쉽기 때문에)
    * 그래서 포기하고 Thymeleaf에서의 맵핑을 봐야겠다.
    * */
    public List<BoardPost> findAll(){
        return em.createQuery("select b from BoardPost b", BoardPost.class)
                .getResultList();

    }

//    public List<BoardPost> findByBoardId(Long board_id) {
//        return em.createQuery("select b from BoardPost b where b.board_Id = :board_Id", BoardPost.class)
//                .setParameter("board_id", board_id)
//                .getResultList();
//    }

    public BoardPost findById(int boardId){
        return em.find(BoardPost.class, boardId);
    }

    // Select 단 건 조회
    public BoardPost findOne(int BoardId){
        return em.find(BoardPost.class, BoardId);
    }

    //Delete Method
    public List<Member> delete(int boardId){

        return em.createQuery("delete BoardPost from BoardPost b where b.BoardPost = :BoardPost", Member.class)
                .setParameter("BoardId", boardId)
                .getResultList();

    }



}
