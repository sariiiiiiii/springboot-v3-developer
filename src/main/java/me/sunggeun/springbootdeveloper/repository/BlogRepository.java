package me.sunggeun.springbootdeveloper.repository;

import me.sunggeun.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}