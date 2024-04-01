package me.sunggeun.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.sunggeun.springbootdeveloper.domain.Article;
import me.sunggeun.springbootdeveloper.dto.AddArticleRequest;
import me.sunggeun.springbootdeveloper.dto.ArticleResponse;
import me.sunggeun.springbootdeveloper.dto.UpdateArticleRequest;
import me.sunggeun.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(final AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<ArticleResponse> findAll() {
        List<Article> articles = blogRepository.findAll();
        return articles.stream()
                .map(ArticleResponse::new)
                .toList();
    }

    public ArticleResponse findById(final Long id) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        return new ArticleResponse(article);

    }

    public void delete(final Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public ArticleResponse update(final Long id, final UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.title(), request.content());

        return new ArticleResponse(article);
    }

}
