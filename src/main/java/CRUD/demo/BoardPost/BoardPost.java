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
@Entity
@Getter
public class BoardPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardSequence; // 게시글 순서(BoardPostId)

    private String category; // 게시글 카테고리

    private String title; // 게시글 제목

    private String author; //게시글 글쓴이

    private String content; // 게시글 내용

    private int views; // 게시글 이미지


    //20240909 추가
    @Setter
    private Boolean fileExists;


    //
    @OneToMany(mappedBy = "boardPost", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "boardPost", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFile> boardFiles = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberSequence")
    private Member member;

   
    @Builder
    public BoardPost(long boardSequence, String category, String title, String author, String content, int views){
        this.boardSequence = boardSequence;
        this.category = category;
        this.title = title;
        this.author = author;
        this.content = content;
        this.views = views;

        //this.comment = comment;
    }



    // 변경 사항 Dto에 세팅
    public void updateFromDto(BoardPostDto boardPostDto){
        this.boardSequence = boardPostDto.getBoardSequence();
        this.category = boardPostDto.getCategory();
        this.title = boardPostDto.getTitle();
        this.author = boardPostDto.getAuthor();
        this.content = boardPostDto.getContent();
        this.views = boardPostDto.getViews();
    }


}
