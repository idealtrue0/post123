package personal.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import personal.post.domain.Member;

import java.util.Optional;

public interface SpringJpaRepository extends JpaRepository<Member, Long>, MemberRepository {

    Optional<Member> findByUserId(String userId);
    Optional<Member> findByPassword(String password);
    Optional<Member> findByNickname(String nickname);

}
