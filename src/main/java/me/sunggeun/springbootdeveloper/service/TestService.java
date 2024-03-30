package me.sunggeun.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.sunggeun.springbootdeveloper.entity.Member;
import me.sunggeun.springbootdeveloper.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

}
