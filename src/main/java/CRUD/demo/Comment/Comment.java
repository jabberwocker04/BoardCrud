package CRUD.demo.Comment;

import CRUD.demo.BoardPost.BoardPost;
import CRUD.demo.Member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CommentId;

    private String commenter; // nickname 대신 쓰는 고유 id(String)

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BoardPost_boardPostSequence")
    private BoardPost BoardPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_memberSequence")
    private Member member;

    private LocalDateTime createTime;

    @Builder
    public Comment(Long CommentId, String commenter, String contents, BoardPost boardPost, LocalDateTime createTime, Member member){
        this.CommentId = CommentId;
        this.commenter = commenter;
        this.content = contents;
        this.BoardPost = boardPost;
        this.createTime = createTime;
        this.member = member;
    }

    public void updateComment (CommentDto commentDto){
        this.content = commentDto.getContents();
    }
}
