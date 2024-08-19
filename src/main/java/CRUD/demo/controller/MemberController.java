package CRUD.demo.controller;

import CRUD.demo.Service.MemberService;
import CRUD.demo.domain.Member;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping(value = "/member/createMemberForm") // 회원가입 컨트롤러
    public String createForm() { //(Model model) {
//        model.addAttribute("memberForm", new MemberForm());
//        System.out.println("Member Controller Create form 실행");
        return "member/createMemberForm";

    }

//    @PostMapping("member/new")
//    public String create(@Valid MemberForm memberform, BindingResult result) { // valid를 사용할 경우 스프링에서 validation이 여러 기능을 한다. | BindingResult라는 기능이 있다.
//        // 이것을 사용하면 오류가 담겨서 코드가 실행된다(오류만 뜨는 것이 아니라)
//        // MemberForm을 entity대신 넣는 이유는, 서로 다른걸로 알아야 맞다. 도메인과 화면이 원하는 validation이 다를 수 있다.
//        // 때문에 Entity 하나로 두 개(get, post)를 모두 사용하기보다 따로 사용하는 것이 좋다.
//        // 그래서 Get으로 가져올 때는 entity 혹은 dto를 가져오는 것이고, post할 때는 Form을 만들어서 하게된다.
//        if (result.hasErrors()) {
//            return "member/CreateMemberForm"; // 에러가 발생하면 BindingResult가 그 폼으로 다시 에러를 보내준다. 그러면 스프링이 그 폼에서 에러를 쓸 수 있도록 도와준다.
//        } // 그리고 Thymeleaf의 코드를 참조해서 사용게 된다.(createMemberForm의 field.haserrors 부분 참고.
//
//        Member member = new Member();
//        member.setMemberId(memberform.getMemberId());
//        member.setPassword(memberform.getPassword());
//
//        memberService.join(member);
//        return "redirect:/"; // 이렇게 하면 첫번째 페이지로 넘어간다.
//    }
    @GetMapping("members")
    public String list(Model model){
//        List<Member> members = memberService.findMembers();
//        model.addAttribute("members", members);
        return "member/MemberList";
    }

}
