package backend.kb_hack.domain.festival.mapper;

import backend.kb_hack.domain.festival.Festival;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FestivalMapper {
    List<Festival> findAll();
    Festival findByFestivalId(@Param("festivalId") Long festivalId);
}
