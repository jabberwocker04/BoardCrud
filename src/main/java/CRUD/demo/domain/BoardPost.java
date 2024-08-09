package CRUD.demo.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Table(name = "board_post")
@Entity
@Getter @Setter
public class BoardPost {

    @Id
    @Column(name = "board_id")
    private int boardId;
    private String category;
    private String title;
    private String author;
    private int views;
    private int recommands;
    private String content;

}
