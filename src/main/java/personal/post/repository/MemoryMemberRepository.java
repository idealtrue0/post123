package personal.post.repository;

import org.springframework.stereotype.Repository;
import personal.post.domain.Member;

import java.util.*;


//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findByID(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByUserId(String userid) {
        return store.values().stream()
                .filter(member -> member.getUserId().equals(userid))
                .findAny();
    }


    @Override
    public Optional<Member> findByNickName(String nickname) {
        return store.values().stream()
                .filter(member -> member.getNickname().equals(nickname))
                .findAny();
    }

    @Override
    public Optional<Member> findByPassword(String password) {
        return store.values().stream()
                .filter(member -> member.getPassword().equals(password))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
