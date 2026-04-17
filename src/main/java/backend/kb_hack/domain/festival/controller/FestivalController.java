package backend.kb_hack.domain.festival.controller;

import backend.kb_hack.domain.festival.FestivalService;
import backend.kb_hack.domain.festival.dto.FestivalDetailDto;
import backend.kb_hack.domain.festival.dto.FestivalDto;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/festival")
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;


    @GetMapping
    public SuccessResponse<List<FestivalDto>> getAllFestival() {
        List<FestivalDto> festivalDtos = festivalService.getFestivalList();
        return SuccessResponse.makeResponse(SuccessStatusCode.FESTIVAL_GET_SUCCESS, festivalDtos);
    }


    @GetMapping("/{festivalId}")
    public SuccessResponse<FestivalDetailDto> getFestivalDetail(@PathVariable Long festivalId) {
        return SuccessResponse.makeResponse(
                SuccessStatusCode.FESTIVAL_GET_SUCCESS,
                festivalService.getFestivalDetail(festivalId)
        );
    }
}
