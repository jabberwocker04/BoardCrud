package CRUD.demo.member;

import CRUD.demo.boardPost.BoardPost;
import CRUD.demo.comment.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id // @Generate... value를 넣어줘서 오류가 떳다. 이미 PK인데 넣어줄 필요가 없다. 그러나 DB에서 PK로 지정해주지 않았다면 Generatedvalue를 넣어주어야 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "member_sequence")
    private Long memberSequence;

    private String memberId;

    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<BoardPost> boardPosts = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();



}
