package CRUD.demo.file;

import CRUD.demo.BoardPost.BoardPost;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Setter
public class BoardFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 파일 경로 - 필요 낫널, 길이 설정은 X
    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    private Long fileSize;

    // uuid(랜덤키) - 결국 저장이 되어야 하니까
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_sequence")
    private BoardPost boardPost;

    @Builder
    public BoardFile(Long id, String filePath, String fileName, String fileType, Long fileSize, BoardPost boardpost, String uuid) {
        this.id = id;
        this.filePath = filePath;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.uuid = uuid;
        this.boardPost = boardpost;
    }
}
