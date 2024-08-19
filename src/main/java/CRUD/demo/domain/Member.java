package CRUD.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "member")
@Entity
@Getter @Setter
public class Member {

    @Id // @Generate... value를 넣어줘서 오류가 떳다. 이미 PK인데 넣어줄 필요가 없다. 그러나 DB에서 PK로 지정해주지 않았다면 Generatedvalue를 넣어주어야 한다.
    @Column(name = "sequence")
    private int Sequence;
    private String MemberId;
    private int Password;
    private String role;


}
