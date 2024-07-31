package personal.post.controller;


import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import personal.post.domain.Member;
import personal.post.repository.MemberRepository;
import personal.post.service.MemberService;

import java.util.List;

@Controller
public class MembershipController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @Autowired
    public MembershipController(MemberService memberService, MemberRepository memberRepository){
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }


    //기본 홈페이지
    @GetMapping("/")
    public String home(){
        return "home";
    }

    //회원가입 페이지
    @GetMapping("/membership/new")
    public String newMemberPage(){
        return "회원가입/new";
    }

    //회원가입 페이지 후 초기화면
    @PostMapping("/membership/new")
    public String createMember(@ModelAttribute("form") signIn form, HttpServletRequest request ){

        if(memberService.isNicknameDuplicate(form.getNickname()) ) {//닉네임이 중복되는 경우
            request.setAttribute("msg", "중복되는 닉네임이 있습니다. 다른 닉네임을 사용해주세요."); //창
            request.setAttribute("url", "/membership/new");
            return "회원가입/alert";
        }
        else if(memberService.isUserIdDuplicate(form.getUserId())) { //아이디가 중복되는 경우
            request.setAttribute("msg", "중복되는 아이디가 있습니다. 다른 아이디를 사용해주세요."); //창
            request.setAttribute("url", "/membership/new");
            return "회원가입/alert";

        }
        else{
            Member member = new Member();
            member.setUserId(form.getUserId());
            member.setPassword(form.getPassword());
            member.setNickname(form.getNickname());

            memberService.join(member);

            return "redirect:/";
        }

    }

    //회원가입 목록 페이지
    @GetMapping("/members")
    public String members(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "회원리스트/membersList";
    }

    //로그인
    @GetMapping("/login")
    public String login(Model model){
        String loginActionUrl = "login";
        model.addAttribute("loginActionUrl", loginActionUrl);
        return "로그인/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpServletRequest request, Model model){
        if( !username.isEmpty() && !password.isEmpty() && //아이디 및 패스워드 빈칸이거나
                memberService.loginUser(username,password)) {
            model.addAttribute("nickname", memberRepository.findByUserId(username).get().getNickname());
            return "로그인/success";
        }
        else{
            request.setAttribute("msg", "아이디 및 패스워드를 확인해주세요."); //창
            request.setAttribute("url", "/login"); //갈 경로
            return "로그인/alert";
        }
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
