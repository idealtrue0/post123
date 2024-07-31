package personal.post.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import personal.post.repository.MemberRepository;
import personal.post.repository.PostRepository;
import personal.post.service.PostService;

@Controller
public class PostController {
    private final PostService postService;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public PostController(PostService postService, MemberRepository memberRepository, PostRepository postRepository){
        this.postService = postService;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/writelist")
    public String list(){

        return "포스트/list";
    }


}
