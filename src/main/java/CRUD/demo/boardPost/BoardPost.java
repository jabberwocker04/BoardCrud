package CRUD.demo.boardPost;

import CRUD.demo.comment.Comment;
import CRUD.demo.file.BoardFile;
import CRUD.demo.member.Member;
import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class BoardPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "boardPostSequence")
    private Long boardPostSequence; // 게시글 순서

    private String category; // 게시글 카테고리

    private String title; // 게시글 제목

    private String content; // 게시글 내용

    private String author;

    private LocalDateTime updatedTime;

    private Boolean fileExists;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_sequence")
    private Member member;

    // mappedBy = "board": comment 클래스에 있는 변수 명(board)
    // cascade = CascadeType.REMOVE: 게시물이 삭제 되면 댓글을 자동 으로 지워 줌
    // orphanRemoval = true: 연결 관계가 끊어 지면 자동 삭제
    // fetch = FetchType.LAZY: 지연 로딩(성능 최적화)
    @OneToMany(mappedBy = "boardPost", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>(); // 09 23 지금까지 자동 생성된 Query를 mappedBy가 board_post가 아닌 boardpost로 받고 있었기 때문에 문제가 되었다.

    @OneToMany(mappedBy = "boardPost", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<BoardFile> boardFiles = new ArrayList<>();

    @Builder
    public BoardPost( String category, String title, String content, String memberId, LocalDateTime updatedTime, Boolean fileExists){
        this.category = category;
        this.title = title;
        this.content = content;
        this.author = memberId;
        this.updatedTime = updatedTime;
        this.fileExists = fileExists;
    }

}
