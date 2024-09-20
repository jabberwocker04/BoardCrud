package CRUD.demo.Member;

import CRUD.demo.BoardPost.BoardPost;
import CRUD.demo.Comment.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "member")
@Entity
@Getter @Setter
public class Member {

    @Id // @Generate... value를 넣어줘서 오류가 떳다. 이미 PK인데 넣어줄 필요가 없다. 그러나 DB에서 PK로 지정해주지 않았다면 Generatedvalue를 넣어주어야 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_sequence")
    private Long memberSequence;

    private String member_id; // 닉네임이자 ID

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String email;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<BoardPost> boards = new ArrayList<>();

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Comment> Comments = new ArrayList<>();

    @Convert(converter = StringArrayConverter.class)
    private List<String> roles = new ArrayList<>();

    @Builder
    public Member(Long memberSequence, String member_id, String email, String password, List<String> roles) {
        this.memberSequence = memberSequence;
        this.member_id = member_id;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
