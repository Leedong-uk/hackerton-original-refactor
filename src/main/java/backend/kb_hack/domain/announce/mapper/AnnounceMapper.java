package backend.kb_hack.domain.announce.mapper;

import backend.kb_hack.domain.announce.dto.Announce;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnounceMapper {
    List<Announce> findAll();
}
