package CRUD.demo.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
//public interface CommentRepository extends JpaRepository<Comment, Long> {
//
////    @Query("SELECT b FROM Comment b WHERE b.boardPost.boardPostSequence = :sequence")
////    List<Comment> findByBoardPostSequence(Long boardPostSequence);
//
//    List<Comment> findByBoardPost_BoardPostSequence(Long BoardPostSequence);
//}
