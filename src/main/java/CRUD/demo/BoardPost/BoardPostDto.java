package CRUD.demo.BoardPost;

import CRUD.demo.Member.Member;
import CRUD.demo.file.FileDto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 복사 생성자
@ToString
public class BoardPostDto {

    private Long boardSequence; // 게시글 순서
    private String category; // 게시글 카테고리
    private String title; // 게시글 제목
    private String author; //게시글 글쓴이
    private String content; // 게시글 내용
    private int views; // 게시글 이미지

    private Boolean fileExists;
    private List<FileDto> boardFiles;
    private Long memberSequence;

    public BoardPost toEntity(Member member){
        return BoardPost.builder()
                .boardSequence(boardSequence)
                .title(title)
                .category(category)
                .title(title)
                .author(author)
                .content(content)
                .views(views)
                .build();
    }

    public static BoardPostDto toBoardPostDto(BoardPost boardPost){
        List<FileDto> fileDtos = boardPost.getBoardFiles().stream()
                .map(FileDto::toFileDto)
                .collect(Collectors.toList());
        Member member = boardPost.getMember();

        return new BoardPostDto(
                boardPost.getBoardSequence(),
                boardPost.getCategory(),
                boardPost.getTitle(),
                boardPost.getAuthor(),
                boardPost.getContent(),
                boardPost.getViews(),
                boardPost.getFileExists(),
                fileDtos,
                member.getMemberSequence()

        );
    }
}
