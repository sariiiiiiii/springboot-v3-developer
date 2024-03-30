package me.sunggeun.springbootdeveloper.dto;

import me.sunggeun.springbootdeveloper.domain.Article;

public record ArticleResponse(
        String title,
        String content
) {

    public ArticleResponse(Article article) {
        this(
                article.getTitle(),
                article.getContent()
        );
    }

}
