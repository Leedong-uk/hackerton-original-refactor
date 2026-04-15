package backend.kb_hack.domain.article.dto.response;

import backend.kb_hack.domain.article.entity.Article;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ArticleListResponse {
    private Long articleId;
    private String title;
    private String content;
    private String url;
    private LocalDateTime createdDate;

    public static ArticleListResponse from(Article article) {
        return new ArticleListResponse(
                article.getArticleId(),
                article.getTitle(),
                article.getContent(),
                article.getUrl(),
                LocalDateTime.from(article.getCreatedDate())
        );
    }

}
