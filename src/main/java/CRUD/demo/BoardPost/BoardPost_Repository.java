package CRUD.demo.BoardPost;

import CRUD.demo.Member.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardPost_Repository {

    private final EntityManager em;

    public void save(BoardPostDto boardPost){
        em.persist(boardPost);
    }

//    public BoardPostDto findByBoardPostSequence(Long BoardPostSequence){
//        return em.find(BoardPostDto.class, BoardPostSequence);
//    }

    //전체 조회
    /*
    * 전체 조회가 HTMl에서 잘 불러와지지 않는다. 맵핑의 문제 같아서 계속 JPQL을 건드려 보는데, 이건 아닌 것 같다(상대적으로 SQL문이 쉽기 때문에)
    * 그래서 포기하고 Thymeleaf에서의 맵핑을 봐야겠다.
    * */
    public List<BoardPostDto> findAll(){
        return em.createQuery("select board_post from boardPostSequence b", BoardPostDto.class)
                .getResultList();

    }

    // Select 단 건 조회
    public BoardPostDto findOne(Long BoardPostSequence){
        return em.find(BoardPostDto.class, BoardPostSequence);
    }

    //Delete Method
//    public List<Member> delete(int board_sequence){
//
//        return em.createQuery("delete BoardPost from BoardPost b where b.BoardPost = :BoardPost", Member.class)
//                .setParameter("board_sequence", board_sequence)
//                .getResultList();
//
//    }



}
