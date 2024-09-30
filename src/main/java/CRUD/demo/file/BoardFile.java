package CRUD.demo.file;

import CRUD.demo.BoardPost.BoardPost;
import CRUD.demo.Comment.CommentDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class BoardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardFileId;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    private Long fileSize;

    // uuid(랜덤키) -> 저장을 위함
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BoardPost_boardPostSequence")
    private BoardPost BoardPost;

    @Builder
    public BoardFile(Long boardFileId, String filePath, String fileName, String fileType, Long fileSize, String uuid, BoardPost boardPost) {
        this.boardFileId = boardFileId;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.uuid = uuid;
        this.BoardPost = boardPost;
    }

}
