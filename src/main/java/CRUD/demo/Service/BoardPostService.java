package CRUD.demo.Service;

import CRUD.demo.Repository.BoardPostRepository;
import CRUD.demo.Repository.BoardPost_Repository;
import CRUD.demo.domain.BoardPost;
import CRUD.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardPostService {

    private final BoardPost_Repository boardPost_Repository; // Class Repository
    private final BoardPostRepository boardPostRepository; // Interface Repository


    @Transactional
    public int join(BoardPost boardPost){

        boardPost_Repository.save(boardPost); // 게시글 작성(save, BoardPost Create)을 위해 BoardPost 매핑
        return boardPost.getBoardId();
    }

    //게시글 전체 조회
    @Transactional
    public List<BoardPost> findBoardPostList(){
        return boardPost_Repository.findAll();
    }

    //게시글 생성(Create)
    @Transactional
    public void createBoardPost(int boardId, String category, String title, String author, int views, String content) {
        BoardPost findBoardPost = boardPost_Repository.findById(boardId);
        findBoardPost.setCategory(category);
        findBoardPost.setBoardId(boardId);
        findBoardPost.setTitle(title);
        findBoardPost.setAuthor(author);
        findBoardPost.setContent(content);
        findBoardPost.setViews(views);

    }

    //Update를 하기 위한 BoardId 단 건 조회(select)
    @Transactional(readOnly = true)
    public BoardPost findOne(int BoardId){
        return boardPost_Repository.findOne(BoardId);
    }

//    //맴버 전체 조회(sequence), select
//    @Transactional(readOnly = true)
//    public List<Member> findMembers(Long sequence){return member_Repository.findBySequence(sequence);}

    //맴버 수정(update)
    @Transactional
    public void updateBoardPost(int boardId, String category, String title, String content) {
        BoardPost findBoardPost = boardPost_Repository.findOne(boardId);
        findBoardPost.setCategory(category);
        findBoardPost.setTitle(title);
        findBoardPost.setContent(content);
    }

    //맴버 삭제(delete)
    @Transactional
    public void deleteMember(int boardId){
        //맴버 엔티티 조회
        BoardPost boardPost = boardPost_Repository.findOne(boardId);
        //맴버 삭제

        boardPost_Repository.delete(boardId);
        //
    }

    @Transactional
    public void delete(int boardId){
        boardPostRepository.deleteByBoardId(boardId);
        System.out.println(boardId+"is Deleted!");
    }

}
