package CRUD.demo.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardPostForm {

    private int boardId; // sequence
    private String category;
    private String title;
    private String author;
    private int views;
    private int recommands;
    private String content;

}
