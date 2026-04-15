package backend.kb_hack.domain.article.mapper;

import backend.kb_hack.domain.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    List<Article> getArticles();
}
