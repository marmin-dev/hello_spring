package marmin.hellospring.service;

import marmin.hellospring.domain.Member;
import marmin.hellospring.repository.MemberRepository;
import marmin.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntergrationTest {

  @Autowired MemberService memberService;
  @Autowired
  MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("살려줘요");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("살려줘요");

        Member member2 = new Member();
        member2.setName("살려줘요");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }

}