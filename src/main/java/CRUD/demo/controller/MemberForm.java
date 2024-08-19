package CRUD.demo.controller;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;


@Getter @Setter
public class MemberForm {

    private int Sequence;
    private String MemberId;
    private int Password;

}
