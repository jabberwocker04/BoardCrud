package CRUD.demo.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final Member_Repository member_Repository; // Class Repository <
    private final MemberRepository memberRepository; // Interface Repository
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(Member member){

        member_Repository.save(member);
        return member.getMemberSequence();
    }

    //맴버 전체 조회(select)
    @Transactional
    public List<Member> findMembers(){
        return member_Repository.findAll();
    }

    //맴버 Update를 위한 맴버 단 건 조회(select)
    @Transactional(readOnly = true)
    public Member findOne(Long memberSequence){
        return member_Repository.findOne(memberSequence);
    }

    //맴버 전체 조회(sequence), select
    @Transactional(readOnly = true)
    public List<Member> findMembers(Long memberSequence){return member_Repository.findByMemberSequence(memberSequence);}

    //맴버 수정(update)
    @Transactional
    public void updateMember(Long memberSequence, String member_id, String password, List<String> role) {
        Member findMember = member_Repository.findOne(memberSequence);
        findMember.setMember_id(member_id);
        findMember.setPassword(password);
        findMember.setRoles(role);
    }

    //맴버 삭제(delete)
    @Transactional
    public void deleteMember(Long memberSequence){
        //맴버 엔티티 조회
        Member member = member_Repository.findOne(memberSequence);
        //맴버 삭제

        member_Repository.delete(memberSequence);
        //
    }

    @Transactional
    public void delete(Long memberSequence){
        memberRepository.deleteByMemberSequence(memberSequence);
        System.out.println(memberSequence + "is Deleted!");
    }

}
