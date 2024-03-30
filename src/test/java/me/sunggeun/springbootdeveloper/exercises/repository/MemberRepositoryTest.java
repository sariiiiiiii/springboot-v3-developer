package me.sunggeun.springbootdeveloper.exercises.repository;

import me.sunggeun.springbootdeveloper.exercises.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Sql("/insert-members.sql")
    @Test
    @Disabled
    void getAllMembers() {
        // when
        List<Member> members = memberRepository.findAll();
        Member member1 = memberRepository.findById(2L).get();
        Member member2 = memberRepository.findByName("C").get();

        //then
        Assertions.assertThat(members.size()).isEqualTo(3);
        Assertions.assertThat(member1.getName()).isEqualTo("B");
        Assertions.assertThat(member2.getId()).isEqualTo(3L);
    }

}