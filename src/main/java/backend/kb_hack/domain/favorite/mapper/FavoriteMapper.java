package backend.kb_hack.domain.favorite.mapper;

import backend.kb_hack.domain.favorite.dto.FavoriteResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    List<FavoriteResponseDto> findFavoritesByMemberId(@Param("memberId") Long memberId);

}
