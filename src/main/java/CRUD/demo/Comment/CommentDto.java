package CRUD.demo.Comment;

import CRUD.demo.BoardPost.BoardPost;
import CRUD.demo.Member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentDto {

    private Long commentId;

    private String commenter;

    private String contents;

    private Long boardPostSequence;

    private LocalDateTime createTime;

    private Long memberSequence;

    public Comment toEntity(BoardPost boardpost, Member member){
        return Comment.builder()
                .CommentId(commentId)
                .commenter(member.getMemberId())
                .contents(contents)
                .boardPost(boardpost)
                .createTime(LocalDateTime.now())
                .member(member)
                .build();
    }

    // Comment 객체를 CommentDto 객체로 변환하는 메서드
    public static CommentDto fromEntity(Comment comment){
        return new CommentDto(
                comment.getCommentId(),
                comment.getCommenter(),
                comment.getContent(),
                comment.getBoardPost().getBoardPostSequence(),
                comment.getCreateTime(),
                comment.getMember().getMemberSequence()
        );
    }
    // contents 필드만을 가진 생성자
    public CommentDto(String contents) {
        this.contents = contents;
    }
}
