package me.sunggeun.springbootdeveloper.exercises.repository;

import me.sunggeun.springbootdeveloper.exercises.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
}
