package CRUD.demo.BoardPost;

import CRUD.demo.Member.Member;
import CRUD.demo.Member.MemberRepository;
import CRUD.demo.file.BoardFile;
import CRUD.demo.file.FileDto;
import CRUD.demo.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardPostService {

    private final BoardPost_Repository boardPost_Repository; // Class Repository
    private final BoardPostRepository boardPostRepository; // Interface Repository
    private final MemberRepository memberRepository;
    private final FileRepository fileRepository;
    private final String filePath = "";

    @Transactional
    public void save(Member member, BoardPostDto boardPostDto, MultipartFile[] files) throws IOException{


        Path uploadPath = Paths.get(filePath);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Long id = boardPostRepository.save(boardPostDto.toEntity(member)).getBoardSequence();
        BoardPost boardPost = boardPostRepository.findById(id).get();

        if (files != null) {
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    // 파일명 추출
                    String originalFileName = file.getOriginalFilename();
                    // 확장자 추출
                    String formatType = originalFileName.substring(
                            originalFileName.lastIndexOf("."));

                    // UUID 생성
                    String uuid = UUID.randomUUID().toString();

                    // 경로 지정 // C:/Users/G/Desktop/demo/boardFile/{uuid + originalFileName}
                    String path = filePath + uuid + originalFileName;

                    // 경로에 파일을 저장. (NO DB)
                    file.transferTo(new File(path));

                    BoardFile boardFile = BoardFile.builder()
                            .filePath(filePath)
                            .fileName(originalFileName)
                            .fileType(formatType)
                            .uuid(uuid)
                            .fileSize(file.getSize())
                            .boardpost(boardPost)
                            .build();
                    fileRepository.save(boardFile);
                }
            }
            // 파일이 하나 이상 추가 되었다면 fileExists를 true로 설정하여 DB에 저장
            if (files.length > 0 && files[0] != null && files[0].getSize() > 0) {
                boardPost.setFileExists(true);
                boardPostRepository.save(boardPost);  // 변경된 상태를 DB에 저장
            }
        }
    }

    @Transactional
    public Long join(BoardPostDto boardPost){

        boardPost_Repository.save(boardPost); // 게시글 작성(save, BoardPost Create)을 위해 BoardPost 매핑
        return boardPost.getBoardSequence();
    }

    //게시글 전체 조회
    @Transactional
    public List<BoardPostDto> findBoardPostDtoList(){
        return boardPost_Repository.findAll();
    }

    //게시글 생성(Create)
    @Transactional
    public void createBoardPost(Long board_sequence, String category, String title, String author, int views, String content) {
        BoardPostDto findBoardPost = boardPost_Repository.findByBoard_sequence(board_sequence);
        findBoardPost.setCategory(category);
        findBoardPost.setBoardSequence(board_sequence);
        findBoardPost.setTitle(title);
        findBoardPost.setAuthor(author);
        findBoardPost.setContent(content);
        findBoardPost.setViews(views);

    }

    //Update를 하기 위한 board_sequence 단 건 조회(select)
    @Transactional(readOnly = true)
    public BoardPost findOne(long boardSequence){
        return boardPostRepository.findByBoardSequence(boardSequence);
    }

//    //맴버 전체 조회(sequence), select
//    @Transactional(readOnly = true)
//    public List<Member> findMembers(Long sequence){return member_Repository.findBySequence(sequence);}

    //맴버 수정(update)
    @Transactional
    public void updateBoardPostDto(long board_sequence, String category, String title, String content) {
        BoardPostDto findBoardPost = boardPost_Repository.findOne(board_sequence);
        findBoardPost.setCategory(category);
        findBoardPost.setTitle(title);
        findBoardPost.setContent(content);
    }

    // 기존 게시글을 업데이트, 새로운 파일이 전송 되면 이를 업로드 하고 기존 파일을 삭제
    @Transactional
    public void update(Member member, BoardPostDto boardPostDto, MultipartFile[] newFiles) throws IOException {
        // ID로 게시글을 조회, 해당 게시글이 존재하면 업데이트를 수행
        if(boardPostRepository.findById(boardPostDto.getMemberSequence()).isPresent()){
            Optional<BoardPost> boardOptional = boardPostRepository.findById(boardPostDto.getMemberSequence());
            BoardPost boardPost = boardOptional.get();
//            boardPostDto.setUpdateTime(LocalDateTime.now());

            if(boardPost.getMember().getMember_id().equals(member.getMember_id())){
                // 새로운 파일이 있다면 기존 파일을 삭제 하고 새 파일을 업로드
                if (newFiles != null && newFiles.length > 0 && !newFiles[0].isEmpty()) {
                    // 기존에 첨부 되어 있던 파일 삭제
                    List<BoardFile> oldFiles = fileRepository.findByBoardId(boardPostDto.getMemberSequence());
                    for (BoardFile oldFile : oldFiles) {
                        deleteFile(boardPost.getBoardSequence(), member.getMemberSequence());  // 기존 파일 삭제
                    }
                    // 새로운 파일을 저장 하고 DB에 저장
                    save(member, boardPostDto, newFiles);
                }
                // 게시글 정보를 업데이트 하고 DB에 저장
                boardPost.updateFromDto(boardPostDto);
                boardPostRepository.save(boardPost);
            }
        }
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

    // 특정 id 게시글 삭제
    @Transactional
    public void delete(Long boardId, Long userId) {
        Optional<BoardPost> boardPost = boardPostRepository.findById(boardId);
        Long boardUserId = boardPost.get().getMember().getMemberSequence();
        if(boardUserId.equals(userId)){
            boardPostRepository.deleteById(boardId);
        }
    }

    // 특정 게시글 id에 해당 하는 파일 조회
    public BoardFile getFileByBoardId(Long boardId) {
        List<BoardFile> boardFiles = fileRepository.findByBoardId(boardId);
        if (!boardFiles.isEmpty()) {
            return boardFiles.get(0);  // 첫 번째 파일만 반환
        } else {
            return null;
        }
    }

    // 특정 게시글 id에 해당하는 파일 삭제
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
                fileRepository.deleteById(boardFile.getId());
                // 게시글에 첨부된 모든 파일이 삭제 되었다면 fileExists를 false로 설정
                BoardPost boardPost = boardFile.getBoardPost();
                if (fileRepository.findByBoardId(boardPost.getBoardSequence()).isEmpty()) {
                    boardPost.setFileExists(false);
                    boardPostRepository.save(boardPost);  // 변경된 상태를 DB에 저장
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("DB에서 파일 정보를 삭제 하는 데 실패 했습니다.");
            }
        }
    }


    //Page 기능
//    public Page<BoardPostDto> paging(Pageable pageable){
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
//            return new BoardPostDto(
//                    boardPost.get(),
//                    boardPost.getTitle(),
//                    boardPost.getUser().getNickname(),
//                    boardPost.getContents(),
//                    boardPost.getCreateTime(),
//                    boardPost.getUpdateTime(),
//                    boardPost.getFileExists(),
//                    fileDtos,
//                    boardPost.getUser().getId()
//            );
//        });
//    }


}
