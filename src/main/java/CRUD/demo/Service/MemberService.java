package CRUD.demo.Service;

import CRUD.demo.Repository.MemberRepository;
import CRUD.demo.Repository.Member_Repository;
import CRUD.demo.domain.Member;
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
    public int join(Member member){

        member_Repository.save(member);
        return member.getSequence();
    }

    //맴버 전체 조회(select)
    @Transactional
    public List<Member> findMembers(){
        return member_Repository.findAll();
    }

    //맴버 Update를 위한 맴버 단 건 조회(select)
    @Transactional(readOnly = true)
    public Member findOne(Long sequence){
        return member_Repository.findOne(sequence);
    }

//    //맴버 전체 조회(sequence), select
//    @Transactional(readOnly = true)
//    public List<Member> findMembers(Long sequence){return member_Repository.findBySequence(sequence);}

    //맴버 수정(update)
    @Transactional
    public void updateMember(Long sequence, String memberId, int password, String role) {
        Member findMember = member_Repository.findOne(sequence);
        findMember.setMemberId(memberId);
        findMember.setPassword(password);
        findMember.setRole(role);
    }

    //맴버 삭제(delete)
    @Transactional
    public void deleteMember(Long sequence){
        //맴버 엔티티 조회
        Member member = member_Repository.findOne(sequence);
        //맴버 삭제

        member_Repository.delete(sequence);
        //
    }

    @Transactional
    public void delete(int sequence){
        memberRepository.deleteBySequence(sequence);
        System.out.println(sequence+"is Deleted!");
    }


}
