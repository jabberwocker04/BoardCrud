package CRUD.demo.Comment;

import CRUD.demo.BoardPost.BoardPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard_sequence(Long board_sequence);
}
