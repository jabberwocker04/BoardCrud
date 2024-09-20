package CRUD.demo.BoardPost;

import CRUD.demo.Member.Member;
import CRUD.demo.file.FileRepository;
import CRUD.demo.security.CustomUserDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardPostController {

    private final BoardPostService boardPostService;
    private final FileRepository fileRepository;

    /*
    * Create
    * */

    @GetMapping(value = "/post/new") // 글 작성 컨트롤러
    public String createPostForm (Model model) {
        model.addAttribute("boardPostForm", new BoardPostForm());
        //     <form role="form" action="/post/new" th:object="${BoardPostForm}" method="post"> 부분의 th:object를 잘 못 설정해주고 있어서 오류가 떳었다.
        //      attributeName과 th:object는 항상 동일해야 올바르게 Model을 보내줄 수 있다.
        System.out.println("Post Controller Create form 실행");
        return "post/createPostForm";
    }

    @PostMapping("post/new")
    public String create(@Valid BoardPostForm boardPostForm, BindingResult result){
        if(result.hasErrors()) {
            return "post/createPostForm";
        }

        BoardPostDto boardPost = new BoardPostDto();
        boardPost.setCategory(boardPostForm.getCategory());
        boardPost.setTitle(boardPostForm.getTitle());
        boardPost.setAuthor(boardPostForm.getAuthor());
        boardPost.setContent(boardPostForm.getContent());


        boardPostService.join(boardPost);
        return "redirect:/";


    }

    /*
    * Select(Read Post) List
    * Error는 나오지 않으나 List가 제대로 불러와 지지 않음 -> 경로가 잘못되었었음.
    * Could not resolve root entity 'Board_Post' 에러가 발생함.
    * Repository에서 createQuery 부분의 Table이 boardPost로 되어있으면서 Entity Mapping이 되지 않았다.
    * -> BoardPost로 수정해주면서 해결(SQL상의 테이블 명은 board_post, domain부분은 BoardPost로 되어 있어 Domain에 맞춰 주어야 한다.
    *
    * List가 제대로 불러와 지지 않는다.
    *
    *  */


    @GetMapping("post/PostList")
    public String list(Model model){
        List<BoardPostDto> boardPostList = boardPostService.findBoardPostDtoList();
        model.addAttribute("boardPostList", boardPostList);
        System.out.println("Board Post List Read");
        return "post/PostList";
    }

    /*
     * Update
     * */

    @GetMapping("post/{board_sequence}/edit")
    public String updateBoardPost(@PathVariable("board_sequence")int board_sequence, Model model){ // 다 되고나서 board_sequence를 int로 바꿔봐야겠다.(15:35)

        BoardPost boardPost = boardPostService.findOne(board_sequence);

        BoardPostForm boardPostForm = new BoardPostForm();
        boardPostForm.setCategory(boardPostForm.getCategory());



        model.addAttribute("form", boardPostForm); // html의         <form th:object="${form}" method="post"> 부분과 attributeName 을 맞춰주어야 한다.

        return "post/updatePostForm";
    }

    @PostMapping("post/{board_sequence}/edit")
    public String updateMember(@AuthenticationPrincipal CustomUserDetails userDetails,
                               @ModelAttribute BoardPostDto boardDto,
                               @RequestParam(value = "newFiles", required = false) MultipartFile[] newFiles
    ) throws IOException {
        Member member = userDetails.getMember();
        boardPostService.update(member, boardDto, newFiles);
        return "redirect:/post/PostList";
    }

    /*
     * Delete
     * */

//    @RequestMapping(value = "post/{board_sequence}/delete", method = RequestMethod.GET)
//    public String DeleteController(@PathVariable int board_sequence){
//        System.out.println("Delete controller 실행");
//
//        boardPostService.delete(board_sequence);
//        return "redirect:/";
//    }


    // 게시글 목록 페이지
    // 페이징 정보를 담은 Pageable 객체 // 뷰에 전달할 데이터를 담은 Model 객체
//    @GetMapping(value = {"/paging", "/"})
//    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
//        Page<BoardPostDto> boards = boardPostService.paging(pageable);
//
//        int blockLimit = 3; // 한번에 보여줄 페이징 블록의 개수
//        int startPage = (int)Math.ceil((double)pageable.getPageNumber() / blockLimit - 1) * blockLimit + 1; // 1
//        int endPage = (startPage+ blockLimit - 1) < boards.getTotalPages() ? (startPage + blockLimit -1) : boards.getTotalPages(); // 3
//
//        // 뷰에 데이터 전달
//        model.addAttribute("boardList", boards);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        return "paging";
//    }

}

