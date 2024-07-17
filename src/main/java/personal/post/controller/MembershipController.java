package personal.post.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import personal.post.domain.Member;
import personal.post.service.MemberService;

import java.util.List;

@Controller
public class MembershipController {

    private final MemberService memberService;

    @Autowired
    public MembershipController(MemberService memberService){
        this.memberService = memberService;
    }



    @GetMapping("/") //기본 홈페이지
    public String home(){
        return "home";
    }

    @GetMapping("/membership/new") //회원가입 페이지
    public String newMemberPage(){
        return "회원가입/new";
    }

    @PostMapping("/membership/new") //회원가입 페이지 후 초기화면
    public String createMember(signIn form){
        Member member = new Member();
        member.setUserId(form.getUserId());
        member.setPassword(form.getPassword());
        member.setNickname(form.getNickname());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members") //회원가입 목록 페이지
    public String members(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "회원리스트/membersList";
    }











    /* 컨트롤러 연습 */



    //aaa사이트 main.html로 꾸미기, html에서 abc라는 값을 dd라는 변수로 사용
    @GetMapping("/aaa")
    public String main(Model model){
        model.addAttribute("dd", "abc");
        return "main";
    }

    /*  @responsebody를 통해 변수넣기
     >> @requestparam : abc변수넣기
     >> localhost:8080/string?hellobbb
     >> abc변수는 bbb라는 값이 들어감
     */
    @GetMapping("string")
    @ResponseBody
    public String mainString(@RequestParam("abc") String defg){
        return "hello" + defg;
    }


}
