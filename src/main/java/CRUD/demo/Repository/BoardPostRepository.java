package CRUD.demo.Repository;

import CRUD.demo.domain.BoardPost;
import CRUD.demo.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardPostRepository{

    private final EntityManager em;

    public void save(BoardPost boardPost){
        em.persist(boardPost);
    }

    public BoardPost findById(int boardId){
        return em.find(BoardPost.class, boardId);
    }

    public List<BoardPost> findBoardPost(Long board_id) {
        return em.createQuery("select m from board_post m where m.board_post = :board_id", BoardPost.class)
                .setParameter("board_id", board_id)
                .getResultList();
    }

    public BoardPost findOne(Long sequence){
        return em.find(BoardPost.class, sequence);
    }

}
