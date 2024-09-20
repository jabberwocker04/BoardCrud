package CRUD.demo.security;

import CRUD.demo.Member.Member;
import CRUD.demo.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    // ** 사용자가 로그인을 시도할 때 Spring Security에 의해 자동으로 실행.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // ** Spring Security의 UserDetailsService 인터페이스를 구현,
        //    사용자 이름으로 사용자 정보를 로드하는 로직을 제공.
        // ** 인증 과정에서 사용자 이름을 통해 사용자 정보를 찾을 때 실행.
        Member member = memberRepository.findByEmail(username).orElseThrow(
//                () -> new Exception401("인증되지 않았습니다.")
                () -> new RuntimeException("인증 실패"));
        return new CustomUserDetails(member);
    }
}
