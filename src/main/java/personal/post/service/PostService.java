package personal.post.service;


import org.springframework.transaction.annotation.Transactional;
import personal.post.repository.MemberRepository;
import personal.post.repository.PostRepository;

@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public PostService(PostRepository postRepository, MemberRepository memberRepository){
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
    }




}
