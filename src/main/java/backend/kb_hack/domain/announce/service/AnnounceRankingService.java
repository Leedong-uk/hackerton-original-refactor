package backend.kb_hack.domain.announce.service;

import backend.kb_hack.domain.announce.dto.AnnounceRankingDTO;
import backend.kb_hack.domain.announce.mapper.AnnounceMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnnounceRankingService {

    private final AnnounceMapper announceMapper;
    private final StringRedisTemplate redisTemplate;
    private static final String RANKING_KEY = "announce:ranking";

    @PostConstruct
    public void preloadRanking() {
        redisTemplate.delete(RANKING_KEY);
        List<AnnounceRankingDTO> all = announceMapper.findTopAnnounces(1000);
        for (AnnounceRankingDTO a : all) {
            redisTemplate.opsForZSet().add(RANKING_KEY, a.getAnnounceId().toString(), a.getViewNum() );
        }
    }

    public List<AnnounceRankingDTO> getTopN(int n) {
        Set<String> topIds = redisTemplate.opsForZSet().reverseRange(RANKING_KEY, 0, n - 1);
        if (topIds == null || topIds.isEmpty()) {
            List<AnnounceRankingDTO> topFromDb = announceMapper.findTopAnnounces(n);

            for (AnnounceRankingDTO a : topFromDb) {
                redisTemplate.opsForZSet().add(RANKING_KEY, a.getAnnounceId().toString(), a.getViewNum());
            }
            return topFromDb;
        }

        List<AnnounceRankingDTO> results = new ArrayList<>();
        for (String idStr : topIds) {
            AnnounceRankingDTO a = announceMapper.findByAnnounceId(Long.valueOf(idStr));
            if (a != null) results.add(a);
        }
        return results;
    }

    public void increaseView(Long announceId) {
        announceMapper.increaseViewNum(announceId);
    }
}
