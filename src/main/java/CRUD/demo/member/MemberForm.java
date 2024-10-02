package CRUD.demo.member;

import CRUD.demo.boardPost.BoardPost;
import CRUD.demo.comment.Comment;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MemberForm {

    private Long memberSequence;

    private String memberId;

    private String password;

    private String role;

    @Column(length = 100, nullable = false)
    private String email;

//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
//    private List<BoardPost> boardPosts = new ArrayList<>();
//
//    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
//    private List<Comment> Comments = new ArrayList<>();
//
//    @Convert(converter = StringArrayConverter.class)
//    private List<String> roles = new ArrayList<>();

}
