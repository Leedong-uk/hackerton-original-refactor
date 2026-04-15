package backend.kb_hack.domain.article.service;

import backend.kb_hack.domain.article.dto.response.ArticleListResponse;
import backend.kb_hack.domain.article.entity.Article;
import backend.kb_hack.domain.article.mapper.ArticleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleMapper articleMapper;

    public List<ArticleListResponse> getArticles() {
        List<Article> articles = articleMapper.getArticles();
        return articles.stream()
                .map(ArticleListResponse::from)
                .toList();
    }

}
