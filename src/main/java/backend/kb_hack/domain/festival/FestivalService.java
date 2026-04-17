package backend.kb_hack.domain.festival;

import backend.kb_hack.domain.festival.dto.FestivalDetailDto;
import backend.kb_hack.domain.festival.dto.FestivalDto;
import backend.kb_hack.domain.festival.mapper.FestivalMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalMapper festivalMapper;

    public List<FestivalDto> getFestivalList(){
        List<Festival> festivals = festivalMapper.findAll();
        return festivals.stream()
                .map(festival -> FestivalDto.from(festival))
                .collect(Collectors.toList());
    }

    public FestivalDetailDto getFestivalDetail(@Param("festivalId") Long festivalId){
        Festival festival =festivalMapper.findByFestivalId(festivalId);
        return FestivalDetailDto.from(festival);
    }

}
