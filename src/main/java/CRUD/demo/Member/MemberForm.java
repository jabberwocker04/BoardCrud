package CRUD.demo.Member;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class MemberForm {

    private Long memberSequence;

    private String member_id;

    private String Password;

    private String Email;

//    private String role;

    private List<String> roles;

}
