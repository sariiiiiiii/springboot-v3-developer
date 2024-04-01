package me.sunggeun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sunggeun.springbootdeveloper.domain.Article;
import me.sunggeun.springbootdeveloper.dto.AddArticleRequest;
import me.sunggeun.springbootdeveloper.dto.ArticleResponse;
import me.sunggeun.springbootdeveloper.dto.UpdateArticleRequest;
import me.sunggeun.springbootdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody final AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articleResponses = blogService.findAll();
        return ResponseEntity.ok()
                .body(articleResponses);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable("id") final Long id) {
        ArticleResponse articleResponse = blogService.findById(id);
        return ResponseEntity.ok()
                .body(articleResponse);
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") final Long id) {
        blogService.delete(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(@PathVariable("id") final Long id, @RequestBody final UpdateArticleRequest request) {
        ArticleResponse articleResponse = blogService.update(id, request);
        return ResponseEntity.ok().body(articleResponse);
    }

}
