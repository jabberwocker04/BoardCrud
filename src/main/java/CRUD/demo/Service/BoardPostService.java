package CRUD.demo.Service;

import CRUD.demo.Repository.BoardPostRepository;
import CRUD.demo.domain.BoardPost;
import CRUD.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardPostService {

    private final BoardPostRepository boardPostRepository;

    @Transactional
    public int join(BoardPost boardPost){

        boardPostRepository.save(boardPost);
        return boardPost.getBoardId();
    }

    @Transactional
    public void createBoardPost(int boardId, String category, String title, String author, int views, String content) {
        BoardPost findBoardPost = boardPostRepository.findById(boardId);
        findBoardPost.setBoardId(boardId);
        findBoardPost.setTitle(title);
        findBoardPost.setAuthor(author);
        findBoardPost.setContent(content);
        findBoardPost.setViews(views);

    }

    //맴버 단 건 조회(select)
    @Transactional(readOnly = true)
    public BoardPost findOne(Long boardId){
        return boardPostRepository.findOne(boardId);
    }

}
