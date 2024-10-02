package CRUD.demo.file;


import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface FileRepository extends JpaRepository<BoardFile, Long> {
    // 보드를 확인 해서 보드 반환
    List<BoardFile> findByBoardPost_BoardPostSequence(Long boardPostSequence);
}
