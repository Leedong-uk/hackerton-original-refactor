package backend.kb_hack.domain.home.service;

import backend.kb_hack.domain.announce.dto.AnnounceRankingDTO;
import backend.kb_hack.domain.announce.dto.RecentViewAnnounceDTO;
import backend.kb_hack.domain.announce.service.AnnounceRankingService;
import backend.kb_hack.domain.announce.service.RecentAnnounceService;
import backend.kb_hack.domain.home.dto.HomeResponse;
import backend.kb_hack.domain.home.dto.RecentAnnounceDTO;
import backend.kb_hack.domain.home.dto.RecentArticleDTO;
import backend.kb_hack.domain.home.dto.RecentFestivalDTO;
import backend.kb_hack.domain.home.mapper.HomeMapper;
import backend.kb_hack.domain.notice.dto.NoticeDTO;
import backend.kb_hack.global.common.exception.enums.BadStatusCode;
import backend.kb_hack.global.common.exception.type.ServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeMapper homeMapper;
    private final RecentAnnounceService recentAnnounceService;
    private final AnnounceRankingService announceRankingService;

    @Transactional
    public HomeResponse getHomeData(){
        List<RecentAnnounceDTO> recentAnnounce;
        List<RecentFestivalDTO> recentFestival;
        List<RecentArticleDTO> recentArticle;
        List<AnnounceRankingDTO> announceRanking;
        List<NoticeDTO> notice;

        try {
            recentAnnounce = homeMapper.getRecentAnnounce();
        } catch (Exception e) {
            throw new ServerErrorException(BadStatusCode.FAIL_TO_GET_RECENT_ANNOUNCE_EXCEPTION);
        }

        try{
            announceRanking = announceRankingService.getTopN(3);
        }catch (Exception e) {
            throw new ServerErrorException(BadStatusCode.FAIL_TO_GET_RECENT_ANNOUNCE_EXCEPTION);
        }

        try {
            recentFestival = homeMapper.getRecentFestival();
        } catch (Exception e) {
            throw new ServerErrorException(BadStatusCode.FAIL_TO_GET_RECENT_FESTIVAL_EXCEPTION);
        }

        try {
            recentArticle = homeMapper.getRecentArticle();
        } catch (Exception e) {
            throw new ServerErrorException(BadStatusCode.FAIL_TO_GET_RECENT_ARTICLE_EXCEPTION);
        }

        try {
            notice = homeMapper.getNotice();
        } catch (Exception e) {
            throw new ServerErrorException(BadStatusCode.FAIL_TO_GET_RECENT_NOTICE_EXCEPTION);
        }

        List<RecentViewAnnounceDTO> recentAnnounceList = recentAnnounceService.getRecentAnnounceList();
        return new HomeResponse(announceRanking,recentAnnounce,recentFestival,recentArticle,recentAnnounceList,notice);
    }
}
