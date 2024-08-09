package CRUD.demo.Repository;

import CRUD.demo.domain.BoardPost;
import CRUD.demo.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardPostRepository{

    private final EntityManager em;

    public BoardPost findById(String boardId){
        return em.find(BoardPost.class, boardId);
    }



}
