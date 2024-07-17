package personal.post.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personal.post.domain.Member;
import personal.post.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setUserId("abc");
        member.setNickname("first");
        member.setPassword("0000");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberRepository.findByID(saveId).get();
        assertEquals(member.getUserId(), findMember.getUserId());
        assertEquals(member.getNickname(), findMember.getNickname());


    }


}
