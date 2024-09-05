package CRUD.demo.BoardPost;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostForm {

    private int BoardSequence; // sequence
    private String category;
    private String title;
    private String author;
    private int views;
    private int comment;
    private String content;

}
