package me.sunggeun.springbootdeveloper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sunggeun.springbootdeveloper.domain.Article;
import me.sunggeun.springbootdeveloper.dto.request.AddArticleRequest;
import me.sunggeun.springbootdeveloper.dto.request.UpdateArticleRequest;
import me.sunggeun.springbootdeveloper.repository.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach
    void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .build();
        blogRepository.deleteAll();
    }

    @Test
    @DisplayName("addArticle: 블로그 글 추가에 성공한다.")
    void addArticle() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest request = new AddArticleRequest(title, content);

        final String requestBody = objectMapper.writeValueAsString(request);

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody)
        );

        //then
        result.andExpect(MockMvcResultMatchers.status().isCreated());

        List<Article> articles = blogRepository.findAll();

        Assertions.assertThat(articles.size()).isEqualTo(1);
        Assertions.assertThat(articles.get(0).getTitle()).isEqualTo("title");
        Assertions.assertThat(articles.get(0).getContent()).isEqualTo("content");
    }

    @Test
    @DisplayName("findAllArticles: 블로그 글 목록 조회에 성공한다.")
    void findAllArticles() throws Exception {
        // given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build()
        );

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON_VALUE));

        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value(savedArticle.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value(savedArticle.getContent()));
    }

    @Test
    @DisplayName("findArticle: 블로그 글 조회에 성공한다.")
    void findArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(url, savedArticle.getId()));

        //then
        resultActions
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(savedArticle.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(savedArticle.getContent()));
    }

    @Test
    @DisplayName("deleteArticle: 블로그 글 삭제에 성공한다.")
    void deleteArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "title";
        final String content = "content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        mockMvc.perform(MockMvcRequestBuilders.delete(url, savedArticle.getId()));

        //then
        List<Article> articles = blogRepository.findAll();
        Assertions.assertThat(articles).isEmpty();
    }

    @Test
    @DisplayName("updateArticle: 블로그 글 수정에 성공한다.")
    void updateArticle() throws Exception {
        // given
        final String url = "/api/articles/{id}";
        final String title = "orgTitle";
        final String content = "orgContent";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content).build());

        final String newTitle = "newTitle";
        final String newContent = "newContent";

        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(newTitle, newContent);

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(updateArticleRequest))
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Article findArticle = blogRepository.findById(savedArticle.getId()).get();
        Assertions.assertThat(findArticle.getTitle()).isEqualTo(updateArticleRequest.title());
        Assertions.assertThat(findArticle.getContent()).isEqualTo(updateArticleRequest.content());
    }

}