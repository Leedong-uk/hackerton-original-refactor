package backend.kb_hack.domain.home.mapper;

import backend.kb_hack.domain.home.dto.RecentAnnounceDTO;
import backend.kb_hack.domain.home.dto.RecentArticleDTO;
import backend.kb_hack.domain.home.dto.RecentFestivalDTO;
import backend.kb_hack.domain.notice.dto.NoticeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomeMapper {
    List<RecentAnnounceDTO> getRecentAnnounce();
    List<RecentFestivalDTO> getRecentFestival();
    List<RecentArticleDTO> getRecentArticle();
    List<NoticeDTO> getNotice();
}
