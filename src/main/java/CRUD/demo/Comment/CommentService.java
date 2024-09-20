package CRUD.demo.Comment;

import CRUD.demo.BoardPost.BoardPost;
import CRUD.demo.BoardPost.BoardPostRepository;
import CRUD.demo.Member.Member;
import CRUD.demo.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardPostRepository boardPostRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Comment save(CommentDto commentDto) {
        // commentDto boardId를 이용하여 해당 게시글을 찾음
        Optional<BoardPost> optionalBoardPost =
                boardPostRepository.findById(commentDto.getBoardSequence());
        Optional<Member> optionalUser =
                memberRepository.findById(commentDto.getMemberSequence());

        // 게시글이 존재하면, 댓글 엔티티를 생성하고 저장, 반환
        if(optionalBoardPost.isPresent() && optionalUser.isPresent()){
            BoardPost foundBoard  = optionalBoardPost.get();
            Member foundUser = optionalUser.get();
            Comment comment = commentDto.toEntity(foundBoard, foundUser);
            return commentRepository.save(comment);
        } else {
            return null;
        }
    }
    // 특정 게시글에 대한 댓글 목록을 조회
    public List<CommentDto> getCommentsByBoardId(Long boardSequence) {
        List<Comment> commentList = commentRepository.findByBoard_sequence(boardSequence);
        //조회된 댓글 목록을 CommentDto로 변환하고 리스트로 반환
        return commentList.stream()
                .map(CommentDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 특정 댓글 삭제
    @Transactional
    public Comment deleteComment(Long commentId, Long memberSequence) {
        Comment deletedComment = commentRepository.findById(commentId).orElse(null);
        if (deletedComment == null || !deletedComment.getMember().getMemberSequence().equals(memberSequence)) {
            throw new IllegalArgumentException();
        }
        commentRepository.deleteById(commentId);
        return deletedComment;
    }

    // 특정 댓글 업데이트
    @Transactional
    public Comment updateComment(Long commentId, String contents, Long memberId) {
        // commentId를 이용하여 업데이트 할 댓글을 조회
        Optional<Comment> optionalComment =
                commentRepository.findById(commentId);
        Optional<Member> optionalUser =
                memberRepository.findById(memberId);
        // 조회된 댓글이 존재하면, 주어진 contents를 이용하여 댓글을 업데이트
        if(optionalComment.isPresent() && optionalUser.isPresent()){
            Comment comment = optionalComment.get();
            Member member = optionalUser.get();
            if(member.getMember_id().equals(commentId)){
                CommentDto commentDto = new CommentDto(contents);
                comment.updateComment(commentDto);
                return comment; // 업데이트 된 댓글 반환
            }
        }
        return null;
    }

}
