package me.sunggeun.springbootdeveloper.dto.request;

import me.sunggeun.springbootdeveloper.domain.Article;

public record AddArticleRequest(
        String title,
        String content
) {

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }

}
