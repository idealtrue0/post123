package personal.post.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import personal.post.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //given
        Member member = new Member();
        member.setNickname("first");
        member.setPassword("f0i0r0s0t0");
        member.setUserId("0000");

        //when
        repository.save(member);

        //then
        Member result = repository.findByID(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByNickName(){
        //given
        Member member = new Member();
        member.setNickname("first");
        member.setPassword("f0i0r0s0t0");
        member.setUserId("0000");

        Member member1 = new Member();
        member1.setNickname("second");
        member1.setPassword("s1e1c1o1n1d");
        member1.setUserId("1111");

        repository.save(member);
        repository.save(member1);

        //when
        Member result = repository.findByNickName("first").get();

        //then
        assertThat(result).isEqualTo(member);

    }

    @Test
    public void findAll(){

        //given
        Member member = new Member();
        member.setNickname("first");
        member.setPassword("f0i0r0s0t0");
        member.setUserId("0000");

        Member member1 = new Member();
        member1.setNickname("second");
        member1.setPassword("s1e1c1o1n1d");
        member1.setUserId("1111");

        repository.save(member);
        repository.save(member1);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);

    }

}
