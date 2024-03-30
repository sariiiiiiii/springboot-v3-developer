package me.sunggeun.springbootdeveloper.dto;

public record UpdateArticleRequest(
        String title,
        String content
) {
}
