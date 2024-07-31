package personal.post;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import personal.post.repository.MemberRepository;
import personal.post.repository.PostRepository;
import personal.post.service.MemberService;
import personal.post.service.PostService;

@Configuration
public class SpringBeanConfig {

//    private final DataSource dataSource;
//    private final EntityManager em;
//    public SpringBeanConfig(DataSource dataSource, EntityManager em){
//        this.dataSource = dataSource;
//        this.em = em;
//    }

//    @Bean
//    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository();
//        return new JpaMemberRepository(em);
//
//    }

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;



    public SpringBeanConfig(MemberRepository memberRepository, PostRepository postRepository){
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    @Bean
    public PostService postService(){
        return new PostService(postRepository, memberRepository);
    }




}
