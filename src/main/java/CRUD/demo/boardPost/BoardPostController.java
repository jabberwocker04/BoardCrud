package CRUD.demo.boardPost;

import CRUD.demo.file.BoardFile;
import CRUD.demo.file.FileRepository;
import CRUD.demo.member.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardPostController {

    private final BoardPostService boardPostService;

    /**
    * Create
    * */

    @GetMapping(value = "/boardPost/new")
    public String createForm(Model model) {
        model.addAttribute("boardPostForm", new BoardPostForm());
        System.out.println("BoardPost Controller Create form 실행");
        return "boardPost/createPostForm"; // create.html 이동
    }

    // 입력 받은 데이터, 글 저장
    // BoardDto: 게시글 데이터를 담은 DTO 객체
    // 파일 불러 오기 (required=false) : 파일이 없을 때도 요청을 처리
//    @PostMapping("/save")
//    public String save(@AuthenticationPrincipal CustomUserDetails userDetails,
//                       @ModelAttribute BoardPostDto boardDto,
//                       @RequestParam (required=false) MultipartFile[] files) throws IOException {
//        if(userDetails == null) {throw new Exception401("로그인이 필요합니다.");}
//        Member member = userDetails.get();
//        boardService.save(user, boardDto, files);
//        return "redirect:/board/";
//    }

    @PostMapping("boardPost/new")
    public String create(@Valid BoardPostForm boardPostForm, BindingResult result){
        if(result.hasErrors()) {
            return "boardPost/createPostForm";
        }

//        BoardPost boardPost = new BoardPost(boardPostForm.getCategory(), boardPostForm.getTitle(), boardPostForm.getContent(), boardPostForm.getAuthor(), LocalDateTime.now(), boardPostForm.getFileExists());
        BoardPost boardPost = new BoardPost();
        LocalDateTime localDateTime = LocalDateTime.now();

        boardPost.setCategory(boardPostForm.getCategory());
        boardPost.setTitle(boardPostForm.getTitle());
        boardPost.setContent(boardPostForm.getContent());
        boardPost.setFileExists(boardPostForm.getFileExists());
        boardPost.setUpdatedTime(localDateTime);

        boardPostService.join(boardPost);
        return "redirect:/";

    }

    /**
//    @GetMapping(value = "/post/new") // 글 작성 컨트롤러
//    public String createPostForm (Model model) {
//        model.addAttribute("boardPostForm", new BoardPostForm());
//        //     <form role="form" action="/post/new" th:object="${BoardPostForm}" method="post"> 부분의 th:object를 잘 못 설정해주고 있어서 오류가 떳었다.
//        //      attributeName과 th:object는 항상 동일해야 올바르게 Model을 보내줄 수 있다.
//        System.out.println("Post Controller Create form 실행");
//        return "post/createPostForm";
//    }


    * */

    /**
    * Read
    * Select(Read Post) List
    * Error는 나오지 않으나 List가 제대로 불러와 지지 않음 -> 경로가 잘못되었었음.
    * Could not resolve root entity 'Board_Post' 에러가 발생함.
    * Repository에서 createQuery 부분의 Table이 boardPost로 되어있으면서 Entity Mapping이 되지 않았다.
    * -> BoardPost로 수정해주면서 해결(SQL상의 테이블 명은 board_post, domain부분은 BoardPost로 되어 있어 Domain에 맞춰 주어야 한다.
    *
    * List가 제대로 불러와 지지 않는다.
    *
    *  */


    @GetMapping("boardPost/boardPostList") // boardPost 목록 Get
    public String list(Model model){
        List<BoardPost> boardPostLists = boardPostService.findBoardPostLists();
        model.addAttribute("boardPostLists", boardPostLists);
        System.out.println("Board Post List Read");
        return "boardPost/boardPostList";
    }

    /**
     * Update
     * */

//    @GetMapping("post/{boardPostSequence}/edit")
//    public String updateBoardPost(@PathVariable("boardPostSequence")Long boardPostSequence, Model model){ // 다 되고나서 board_sequence를 int로 바꿔봐야겠다.(15:35)
//
//        BoardPost boardPost = boardPostService.findOne(boardPostSequence);
//
//        BoardPostForm boardPostForm = new BoardPostForm();
//        boardPostForm.setCategory(boardPostForm.getCategory());
//
//
//
//        model.addAttribute("boardPostForm", boardPostForm); // html의         <form th:object="${form}" method="post"> 부분과 attributeName 을 맞춰주어야 한다.
//
//        return "post/updatePostForm";
//    }

//    @PostMapping("post/{board_sequence}/edit")
//    public String updateMember(@PathVariable Long boardPostSequence, @ModelAttribute("form") BoardPost boardPost){
//
//        boardPostService.updateBoardPost(boardPostSequence, boardPost.getCategory(), boardPost.getTitle(), boardPost.getContent());
//
//        return "redirect:/post/PostList";
//    }

    /**
     * Delete
     * */

//    @RequestMapping(value = "post/{board_sequence}/delete", method = RequestMethod.GET)
//    public String DeleteController(@PathVariable int board_sequence){
//        System.out.println("Delete controller 실행");
//
//        boardPostService.delete(board_sequence);
//        return "redirect:/";
//    }

//    // 게시글 목록 페이지
//    // 페이징 정보를 담은 Pageable 객체 // 뷰에 전달할 데이터를 담은 Model 객체
//    @GetMapping(value = {"/boardPost/paging"})
//    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
//        Page<BoardPost> boards = boardPostService.paging(pageable);
//
//        int blockLimit = 3; // 한번에 보여줄 페이징 블록의 개수
//        int startPage = (int)Math.ceil((double)pageable.getPageNumber() / blockLimit - 1) * blockLimit + 1; // 1
//        int endPage = (startPage+ blockLimit - 1) < boards.getTotalPages() ? (startPage + blockLimit -1) : boards.getTotalPages(); // 3
//
//        // 뷰에 데이터 전달
//        model.addAttribute("boardList", boards);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        return "/boardPost/paging";
//    }
//
//    // 특정 게시글의 상세 정보를 보여주는 페이지로 이동
//    @GetMapping("/{id}") // URL에서 게시글의 ID를 가져와 저장     // 페이징 정보를 담고 있는 객체, 기본 페이지 번호를 1로 설정
//    public String paging(@PathVariable Long boardPostSequence, Model model, @PageableDefault(page = 1) Pageable pageable){
//        // 해당 ID의 게시글 정보를 BoardDto 객체에 저장
//        BoardPost boardPostDto = boardPostService.findOne(boardPostSequence);
//
//        // 뷰에 데이터 전달
//        model.addAttribute("boardPost", boardPostDto); // boardDto -> board(view)
//        model.addAttribute("page", pageable.getPageNumber()); // 현재 페이지 번호 -> page(view)
//
//        // 해당 ID의 게시글에 첨부된 파일 정보를 가져와 List<BoardFile> 객체에 저장
//        List<BoardFile> byBoardFiles = fileRepository.findByBoardPost_BoardPostSequence(boardPostSequence);
//        model.addAttribute("files",byBoardFiles); // byBoardFiles -> files(view)
//
//        return "detail"; // detail 뷰 반환
//    }

}

