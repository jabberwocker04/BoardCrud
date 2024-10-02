package CRUD.demo.comment;


import CRUD.demo.boardPost.BoardPost;
import CRUD.demo.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String commenter; // nickname 대신 쓰는 고유 id(String)

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardPostSequence") // boardPost의 boardPostSequence와 Mapping한다.
    private BoardPost boardPost; // Comment의 Comment Builder에서 필드를 가져간다.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberSequence")
    private Member member;

    private LocalDateTime createTime;

    @Builder
    public Comment(Long commentId, String commenter, String content, BoardPost boardPost, Member member, LocalDateTime createTime){
        this.commentId = commentId;
        this.commenter = commenter;
        this.content = content;
        this.boardPost = boardPost;
        this.member = member;
        this.createTime = createTime;
    }

}