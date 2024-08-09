package CRUD.demo.domain;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Table(name = "member")
@Entity
@RequiredArgsConstructor
public class Member {

    @Id // @Generate... value를 넣어줘서 오류가 떳다. 이미 PK인데 넣어줄 필요가 없다.
    public int Sequence;
    public String MemberId;
    public int Password;
    public String role;
}
