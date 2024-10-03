package CRUD.demo.boardPost;

import CRUD.demo.file.BoardFile;
import CRUD.demo.file.FileDto;
import CRUD.demo.file.FileRepository;
import CRUD.demo.member.Member;
import CRUD.demo.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardPostService {

    private final BoardPost_Repository boardPost_Repository; // Class Repository
    private final BoardPostRepository boardPostRepository; // Interface Repository
    private final FileRepository fileRepository;
    private final MemberRepository memberRepository;
    private final String filePath = "J:\\Java\\BoardCrud\\src\\main\\java\\CRUD\\demo\\file";




    @Transactional
    public Long join(BoardPost boardPost){
        boardPost_Repository.save(boardPost); // 게시글 작성(save, BoardPost Create)을 위해 BoardPost 매핑
        return boardPost.getBoardPostSequence();
    }

    //게시글 전체 조회
    @Transactional
    public List<BoardPost> findBoardPostLists(){
        return boardPost_Repository.findAll();
    }

    //게시글 생성(Create)
    @Transactional
    public void createBoardPost(Long boardPostSequence, String category, String title, String author, String views, String content) {
        BoardPost findBoardPost = boardPost_Repository.findByBoardPostSequence(boardPostSequence);
        findBoardPost.setCategory(category);
        findBoardPost.setBoardPostSequence(boardPostSequence);
        findBoardPost.setTitle(title);
        findBoardPost.setContent(content);

    }

    //Update를 하기 위한 board_sequence 단 건 조회(select)
    @Transactional(readOnly = true)
    public BoardPost findOne(Long boardPostSequence){
        return boardPost_Repository.findOne(boardPostSequence);
    }

//    //맴버 전체 조회(sequence), select
//    @Transactional(readOnly = true)
//    public List<Member> findMembers(Long sequence){return member_Repository.findBySequence(sequence);}

    //맴버 수정(update)
    @Transactional
    public void updateBoardPost(Long boardPostSequence, String category, String title, String content) {
        BoardPost findBoardPost = boardPost_Repository.findOne(boardPostSequence);
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

    @Transactional
    public void deleteFile(Long boardPostSequence, Long memberSequence){
        // 게시글 ID로 파일을 조회, 파일이 존재하면 해당 파일을 삭제
        BoardFile boardFile = getFileByBoardId(boardPostSequence);
        Optional<Member> member = memberRepository.findById(memberSequence);
        Long foundUserId = member.get().getMemberSequence();
        if(boardFile.getBoardPost().getMember().getMemberSequence().equals(foundUserId)){
            // 실제 파일 시스템에서의 파일 경로
            String deleteFilePath = filePath + boardFile.getUuid()+ boardFile.getFileName();
            File file = new File(deleteFilePath);
            // 파일이 존재 한다면 삭제 // log 또는 예외 처리 바꾸기
            if(file.exists()){
                if(file.delete()){
                    System.out.println("파일이 삭제 되었습니다.");
                }else{
                    System.out.println("파일 삭제 실패");
                }
            }else{
                System.out.println("파일이 존재 하지 않습니다.");
            }
            // DB 에서의 파일 정보 삭제
            try {
                fileRepository.deleteById(boardFile.getBoardFileId());
                // 게시글에 첨부된 모든 파일이 삭제 되었다면 fileExists를 false로 설정
                BoardPost boardPost = boardFile.getBoardPost();
                if (fileRepository.findByBoardPost_BoardPostSequence(boardPost.getBoardPostSequence()).isEmpty()) {
                    boardPost.setFileExists(false);
                    boardPostRepository.save(boardPost);  // 변경된 상태를 DB에 저장
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DB에서 파일 정보를 삭제 하는 데 실패 했습니다.");
            }
        }
    }

    // 페이지 리스트를 가져 와서 보여 줌
//    public Page<BoardPost> paging(Pageable pageable){
//        // 페이지 시작 번호 세팅
//        int page = pageable.getPageNumber() -1 ;
//        // 페이지에 포함 될 게시물 개수
//        int size = 5;
//        // 전체 게시물 불러옴
//        Page<BoardPost> boardPosts = boardPostRepository.findAll(
//                // 정렬 처리 해서 가져 옴(자동)
//                PageRequest.of(page, size));
//        // Board를 BoardDto로 매핑 하면서, boardFiles도 FileDto 리스트로 변환하여 처리
//        return boardPosts.map(boardPost -> {
//            List<FileDto> fileDtos = boardPost.getBoardFiles().stream()
//                    .map(FileDto::toFileDto)
//                    .collect(Collectors.toList());
//
//            // BoardDto 객체 생성시 boardFiles도 함께 생성자에 넘겨줌
////            return new BoardPostForm(
////                    board.getId(),
////                    board.getTitle(),
////                    board.getUser().getNickname(),
////                    board.getContents(),
////                    board.getCreateTime(),
////                    board.getUpdateTime(),
////                    board.getFileExists(),
////                    fileDtos,
////                    board.getUser().getId()
////            );
//
//        return new BoardPost(
//                boardPost.getCategory(),
//                boardPost.getTitle(),
//                boardPost.getContent(),
//                boardPost.getMember().getMemberId(),
//                boardPost.getUpdatedTime(),
//                boardPost.getFileExists()
//        );
//    });
//    }

    public BoardFile getFileByBoardId(Long boardPostSequence) {
        List<BoardFile> boardFiles = fileRepository.findByBoardPost_BoardPostSequence(boardPostSequence);
        if (!boardFiles.isEmpty()) {
            return boardFiles.get(0);  // 첫 번째 파일만 반환
        } else {
            return null;
        }
    }

}
