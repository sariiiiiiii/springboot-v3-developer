package me.sunggeun.springbootdeveloper.dto.response;

import me.sunggeun.springbootdeveloper.domain.Article;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt
) {

    public ArticleResponse() {
        this(
                null,
                null,
                null,
                null
        );
    }

    public ArticleResponse(Article article) {
        this(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getCreatedAt()
        );
    }

}
