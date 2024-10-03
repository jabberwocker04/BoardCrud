package CRUD.demo.boardPost;

import CRUD.demo.file.FileDto;
import CRUD.demo.member.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 복사 생성자
@ToString
public class BoardPostForm {

    private Long boardPostSequence; // sequence

    private String category;

    private String title;

    private String author;

    private String content; // 게시글 내용

    private LocalDateTime updatedTime;

    private Boolean fileExists;

    private List<FileDto> boardFiles;

    private Long memberSequence;

//    public BoardPost toEntity(Member member){
//        return BoardPost.builder()
//                .category(category)
//                .title(title)
//                .content(content)
//                .memberId(author)
//                .updatedTime(updatedTime)
//                .fileExists(fileExists)
//                .build();
//    }

    public static BoardPostForm toBoardPostForm(BoardPost boardPost){
        List<FileDto> fileDto = boardPost.getBoardFiles().stream()
                .map(FileDto::toFileDto)
                .collect(Collectors.toList());

        Member member= boardPost.getMember();
        return new BoardPostForm(
                boardPost.getBoardPostSequence(),
                boardPost.getCategory(),
                boardPost.getTitle(),
                boardPost.getContent(),
                boardPost.getAuthor(),
                LocalDateTime.now(),
                boardPost.getFileExists(),
                fileDto,
                boardPost.getBoardPostSequence()

        );

    }
}
