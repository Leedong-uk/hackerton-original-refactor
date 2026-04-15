package backend.kb_hack.domain.article.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private Long articleId;
    private String title;
    private String content;
    private String author;
    private String url;
    private LocalDateTime createdDate;
}
