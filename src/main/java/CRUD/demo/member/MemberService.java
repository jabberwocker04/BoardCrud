package CRUD.demo.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final Member_Repository member_Repository; // Class Repository
    private final MemberRepository memberRepository; // Interface Repository

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

//    //맴버 전체 조회(sequence), select
//    @Transactional(readOnly = true)
//    public List<Member> findMembers(Long sequence){return member_Repository.findBySequence(sequence);}

    //맴버 수정(update)
    @Transactional
    public void updateMember(Long memberSequence, String member_id, String password, String email) {
        Member findMember = member_Repository.findOne(memberSequence);

        findMember.setMemberId(member_id);
        findMember.setPassword(password);
        findMember.setEmail(email);
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
