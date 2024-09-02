package CRUD.demo.controller;

import CRUD.demo.Service.BoardPostService;
import CRUD.demo.domain.BoardPost;
import CRUD.demo.domain.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardPostController {

    private final BoardPostService boardPostService;

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

        BoardPost boardPost = new BoardPost();
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
        List<BoardPost> boardPostList = boardPostService.findBoardPostList();
        model.addAttribute("boardPostList", boardPostList);
        System.out.println("Board Post List Read");
        return "post/PostList";
    }

    /*
     * Update
     * */

    @GetMapping("post/{boardId}/edit")
    public String updateBoardPost(@PathVariable("boardId")int boardId, Model model){ // 다 되고나서 BoardId를 int로 바꿔봐야겠다.(15:35)

        BoardPost boardPost = boardPostService.findOne(boardId);

        BoardPostForm boardPostForm = new BoardPostForm();
        boardPostForm.setCategory(boardPostForm.getCategory());



        model.addAttribute("form", boardPostForm); // html의         <form th:object="${form}" method="post"> 부분과 attributeName 을 맞춰주어야 한다.

        return "post/updatePostForm";
    }

    @PostMapping("post/{boardId}/edit")
    public String updateMember(@PathVariable int boardId, @ModelAttribute("form") BoardPost boardPost){

        boardPostService.updateBoardPost(boardId, boardPost.getCategory(), boardPost.getTitle(), boardPost.getContent());

        return "redirect:/post/PostList";
    }

    /*
     * Delete
     * */

    @RequestMapping(value = "post/{boardId}/delete", method = RequestMethod.GET)
    public String DeleteController(@PathVariable int boardId){
        System.out.println("Delete controller 실행");

        boardPostService.delete(boardId);
        return "redirect:/";
    }


}

