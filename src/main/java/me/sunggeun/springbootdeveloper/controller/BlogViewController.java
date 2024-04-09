package me.sunggeun.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.sunggeun.springbootdeveloper.dto.response.ArticleResponse;
import me.sunggeun.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleResponse> articleResponses = blogService.findAll();
        model.addAttribute("articles", articleResponses);
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        ArticleResponse articleResponse = blogService.findById(id);
        model.addAttribute("article", articleResponse);
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(name = "id", required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("article", new ArticleResponse());
        } else {
            ArticleResponse articleResponse = blogService.findById(id);
            model.addAttribute("article", articleResponse);
        }
        return "newArticle";
    }

}
