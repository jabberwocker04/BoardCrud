package CRUD.demo.Service;

import CRUD.demo.Repository.MemberRepository;
import CRUD.demo.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
//
//    @Transactional
//    public List<Member> findMembers(){
//        return memberRepository.findAll();
//    }



}
