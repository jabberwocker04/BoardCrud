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

    private Long id;

    private String member_id;

    private String contents;

    private Long boardSequence;

    private LocalDateTime createDateTime;

    private Long memberSequence;

    public Comment toEntity(BoardPost boardpost, Member member){
        return Comment.builder()
                .id(id)
                .member_id(member.getMember_id())
                .contents(contents)
                .boardPost(boardpost)
                .createDateTime(LocalDateTime.now())
                .member(member)
                .build();
    }

    // Comment 객체를 CommentDto 객체로 변환하는 메서드
    public static CommentDto fromEntity(Comment comment){
        return new CommentDto(
                comment.getId(),
                comment.getMember_id(),
                comment.getContents(),
                comment.getBoardPost().getBoardSequence(),
                comment.getCreateDateTime(),
                comment.getMember().getMemberSequence()
        );
    }
    // contents 필드만을 가진 생성자 (댓글 내용 업데이트를 위해 필요)
    public CommentDto(String contents) {
        this.contents = contents;
    }
}
