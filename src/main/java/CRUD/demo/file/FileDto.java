package CRUD.demo.file;

import CRUD.demo.boardPost.BoardPost;
import lombok.*;

@AllArgsConstructor // toFileDto 때문에 설정
@ToString
@Setter
@Getter
@NoArgsConstructor
public class FileDto {

    private String filePath;

    private String fileName;

    private String fileType;

    private Long fileSize;

    // 게시판의 키(pk)를 받아 와야 하니까 Dto에만 보드 아이디 존재
    private Long boardPostSequence;

    // uuid(랜덤키)
    private String uuid;

    public BoardFile toEntity(BoardPost boardPost){
        return BoardFile.builder()
                .filePath(filePath)
                .fileName(fileName)
                .fileType(fileType)
                .fileSize(fileSize)
                .uuid(uuid)
                .boardPost(boardPost)
                .build();
    }
    public static FileDto toFileDto(BoardFile boardFile){
        return new FileDto(
                boardFile.getFilePath(),
                boardFile.getFileName(),
                boardFile.getFileType(),
                boardFile.getFileSize(),
                boardFile.getBoardPost().getBoardPostSequence(),
                boardFile.getUuid()
        );
    }


}
