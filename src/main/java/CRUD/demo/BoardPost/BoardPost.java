package CRUD.demo.BoardPost;

import CRUD.demo.Comment.Comment;
import CRUD.demo.Member.Member;
import CRUD.demo.file.BoardFile;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
@Table(name = "board_post")
public class BoardPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "board_post_sequence")
    private Long boardPostSequence; // 게시글 순서(BoardPostId)

    private String category; // 게시글 카테고리

    private String title; // 게시글 제목

    private String author; //게시글 글쓴이

    private String content; // 게시글 내용

    //20240909 추가
    @Setter
    private Boolean fileExists;

    // mappedBy = "board": comment 클래스에 있는 변수 명(board)
    // cascade = CascadeType.REMOVE: 게시물이 삭제 되면 댓글을 자동 으로 지워 줌
    // orphanRemoval = true: 연결 관계가 끊어 지면 자동 삭제
    // fetch = FetchType.LAZY: 지연 로딩(성능 최적화)
    @OneToMany(mappedBy = "BoardPost", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comment = new ArrayList<>(); // 09 23 지금까지 자동 생성된 Query를 mappedBy가 board_post가 아닌 boardpost로 받고 있었기 때문에 문제가 되었다.

    @OneToMany(mappedBy = "BoardPost", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFile> boardFiles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_sequence")
    private Member member;

   
    @Builder
    public BoardPost(Long boardPostSequence, String category, String title, String author, String content, Boolean fileExists, Member member){
        this.boardPostSequence = boardPostSequence;
        this.category = category;
        this.title = title;
        this.author = author;
        this.content = content;
        this.fileExists = fileExists;
        this.member = member;

        //this.comment = comment;
    }



    // 변경 사항 Dto에 세팅
    public void updateFromDto(BoardPostDto boardPostDto){
        this.boardPostSequence = boardPostDto.getBoardPostSequence();
        this.category = boardPostDto.getCategory();
        this.title = boardPostDto.getTitle();
        this.author = boardPostDto.getAuthor();
        this.content = boardPostDto.getContent();
    }

}


