package me.sunggeun.springbootdeveloper.dto.request;

public record UpdateArticleRequest(
        String title,
        String content
) {
}
