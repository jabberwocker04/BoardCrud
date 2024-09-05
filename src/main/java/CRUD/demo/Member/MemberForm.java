package CRUD.demo.Member;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberForm {

    private int memberSequence;
    private String member_id;
    private int Password;
    private String role;

}
