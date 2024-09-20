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
    private Long id;

    private String member_id; // nickname 대신 쓰는 고유 id(String)

    @Column(nullable = false)
    private String contents;

    private LocalDateTime createDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardSequence")
    private BoardPost boardPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberSequence")
    private Member member;

    @Builder
    public Comment(Long id, String member_id, String contents, BoardPost boardPost, LocalDateTime createDateTime, Member member){
        this.id = id;
        this.member_id = member_id;
        this.contents = contents;
        this.boardPost = boardPost;
        this.createDateTime = createDateTime;
        this.member = member;
    }

    public void updateComment (CommentDto commentDto){
        this.contents = commentDto.getContents();
    }
}
