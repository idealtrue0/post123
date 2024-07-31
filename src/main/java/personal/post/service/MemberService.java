package personal.post.service;

import org.springframework.transaction.annotation.Transactional;
import personal.post.domain.Member;
import personal.post.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }


    /*
        회원가입
     */

    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member){
        memberRepository.findByUserId(member.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });
        memberRepository.findByNickname(member.getNickname())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 닉네임입니다.");
                });
    }

    /*
        로그인/로그아웃
     */

    public boolean loginUser(String userId, String password){
        Optional<Member> member = memberRepository.findByUserId(userId);

        if( member.get().getPassword().equals(password)){
            return true;
        }
        return false;
    }






    /*
        회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findByid(memberId);
    }

    /*
        아이디, 닉네임 중복 확인
     */

    public boolean isNicknameDuplicate(String nickname){
//        Optional<Member> existingMember = memberRepository.findByNickname(nickname);
//        return existingMember.isPresent();
        return memberRepository.findByNickname(nickname).isPresent();
    }

    public boolean isUserIdDuplicate(String userId){
//        Optional<Member> existingMember = memberRepository.findByNickname(userId);
//        return existingMember.isPresent();
        return memberRepository.findByUserId(userId).isPresent();
    }


}
