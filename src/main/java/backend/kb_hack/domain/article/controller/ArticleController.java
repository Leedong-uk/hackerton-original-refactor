package backend.kb_hack.domain.article.controller;

import backend.kb_hack.domain.article.dto.response.ArticleListResponse;
import backend.kb_hack.domain.article.service.ArticleService;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/articles")
@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("")
    public SuccessResponse<List<ArticleListResponse>> getArticles() {
        List<ArticleListResponse> articles = articleService.getArticles();
        return SuccessResponse.makeResponse(SuccessStatusCode.ARTICLE_GET_SUCCESS, articles);
    }

}
