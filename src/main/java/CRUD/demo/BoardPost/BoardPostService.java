package CRUD.demo.BoardPost;

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
        return boardPost.getBoardSequence();
    }

    //게시글 전체 조회
    @Transactional
    public List<BoardPost> findBoardPostList(){
        return boardPost_Repository.findAll();
    }

    //게시글 생성(Create)
    @Transactional
    public void createBoardPost(int board_sequence, String category, String title, String author, int views, String content) {
        BoardPost findBoardPost = boardPost_Repository.findByBoard_sequence(board_sequence);
        findBoardPost.setCategory(category);
        findBoardPost.setBoardSequence(board_sequence);
        findBoardPost.setTitle(title);
        findBoardPost.setAuthor(author);
        findBoardPost.setContent(content);
        findBoardPost.setViews(views);

    }

    //Update를 하기 위한 board_sequence 단 건 조회(select)
    @Transactional(readOnly = true)
    public BoardPost findOne(int board_sequence){
        return boardPost_Repository.findOne(board_sequence);
    }

//    //맴버 전체 조회(sequence), select
//    @Transactional(readOnly = true)
//    public List<Member> findMembers(Long sequence){return member_Repository.findBySequence(sequence);}

    //맴버 수정(update)
    @Transactional
    public void updateBoardPost(int board_sequence, String category, String title, String content) {
        BoardPost findBoardPost = boardPost_Repository.findOne(board_sequence);
        findBoardPost.setCategory(category);
        findBoardPost.setTitle(title);
        findBoardPost.setContent(content);
    }

    //맴버 삭제(delete)
//    @Transactional
//    public void deleteMember(int board_sequence){
//        //맴버 엔티티 조회
//        BoardPost boardPost = boardPost_Repository.findOne(board_sequence);
//        //맴버 삭제
//
//        boardPost_Repository.delete(board_sequence);
//        //
//    }
//
//    @Transactional
//    public void delete(int board_sequence){
//        boardPostRepository.deleteByBoardSequence(board_sequence);
//        System.out.println(board_sequence+"is Deleted!");
//    }

}
