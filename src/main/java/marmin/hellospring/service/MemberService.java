package marmin.hellospring.service;

import marmin.hellospring.domain.Member;
import marmin.hellospring.repository.MemberRepository;
import marmin.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 확인
        memberRepository.findByName(member.getName())
        .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }
//전체아이디 불러오기
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }
    //아이디 하나 찾기
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
