package personal.post;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import personal.post.repository.MemberRepository;
import personal.post.repository.MemoryMemberRepository;
import personal.post.service.MemberService;

@Configuration
public class SpringBeanConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
