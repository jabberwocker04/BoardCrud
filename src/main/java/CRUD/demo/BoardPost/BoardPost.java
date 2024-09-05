package CRUD.demo.BoardPost;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Table(name = "board_post")
@Entity
@Getter @Setter
public class BoardPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_sequence")
    private int boardSequence; // 게시글 순서
    private String category; // 게시글 카테고리
    private String title; // 게시글 제목
    private String author; //게시글 글쓴이
    private String content; // 게시글 내용
    private int views; // 게시글 이미지
    private String comment;


    //
   



}
