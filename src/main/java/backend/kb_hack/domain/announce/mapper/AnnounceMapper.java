package backend.kb_hack.domain.announce.mapper;

import backend.kb_hack.domain.announce.dto.Announce;
import backend.kb_hack.domain.announce.dto.AnnounceRankingDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnnounceMapper {
    List<Announce> findAll();
    List<AnnounceRankingDTO> findTopAnnounces(@Param("limit") int limit);
    AnnounceRankingDTO findByAnnounceId(Long announceId);
    int increaseViewNum(@Param("announceId") Long announceId);
    Announce findById(@Param("announceId") Long announceId);

}
