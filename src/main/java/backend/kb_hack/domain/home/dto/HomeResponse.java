package backend.kb_hack.domain.home.dto;

import backend.kb_hack.domain.announce.dto.AnnounceRankingDTO;
import backend.kb_hack.domain.announce.dto.RecentViewAnnounceDTO;
import backend.kb_hack.domain.notice.dto.NoticeDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HomeResponse {
    List<AnnounceRankingDTO> announceRanking;
    List<RecentAnnounceDTO> recentAnnounce;
    List<RecentFestivalDTO> recentFestival;

    List<RecentArticleDTO> recentArticle;
    List<RecentViewAnnounceDTO> recentViewAnnounce;
    List<NoticeDTO> notice;
}
