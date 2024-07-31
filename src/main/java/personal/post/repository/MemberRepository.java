package personal.post.repository;


import personal.post.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findByid(Long id);
    Optional<Member> findByUserId(String userid);
    Optional<Member> findByNickname(String nickname);
    Optional<Member> findByPassword(String password);
    List<Member> findAll();

}
