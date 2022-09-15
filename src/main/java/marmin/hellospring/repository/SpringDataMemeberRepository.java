package marmin.hellospring.repository;

import marmin.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataMemeberRepository extends JpaRepository<Member,Long> ,MemberRepository {
    //JPQL select m from Member m where m.name = ? 과 같이 변환해서 반환
    @Override
    Optional<Member> findByName(String name);
}
