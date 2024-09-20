package CRUD.demo.file;

import CRUD.demo.BoardPost.BoardPost;
import lombok.*;

@AllArgsConstructor
@ToString
@Setter
@Getter
@NoArgsConstructor
public class FileDto {

    private String filePath;

    private String fileName;

    private String fileType;

    private Long fileSize;

    private Long boardSequence;

    // uuid(랜덤 키)
    private String uuid;

    public BoardFile toEntity(BoardPost boardPost){
        return BoardFile.builder()
                .filePath(filePath)
                .fileName(fileName)
                .fileType(fileType)
                .fileSize(fileSize)
                .uuid(uuid)
                .boardpost(boardPost)
                .build();
    }

    public static FileDto toFileDto(BoardFile boardFile){
        return new FileDto(
                boardFile.getFilePath(),
                boardFile.getFileName(),
                boardFile.getFileType(),
                boardFile.getFileSize(),
                boardFile.getBoardPost().getBoardSequence(),
                boardFile.getUuid()
        );
    }

}
