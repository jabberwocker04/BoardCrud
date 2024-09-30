package CRUD.demo.BoardPost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostForm {

    private Long boardPostSequence; // sequence
    private String category;
    private String title;
    private String author;
    private int comment;
    private String content;

}
