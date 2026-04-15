package backend.kb_hack.domain.announce.controller;

import backend.kb_hack.domain.announce.dto.AnnounceDetailDto;
import backend.kb_hack.domain.announce.dto.announceDto;
import backend.kb_hack.domain.announce.service.AnnounceService;
import backend.kb_hack.global.common.exception.enums.BadStatusCode;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.exception.type.BadRequestException;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/announce")
@RequiredArgsConstructor
public class AnnounceController {

    private final AnnounceService announceService;

    @GetMapping
    public SuccessResponse<List<announceDto>> getAllAnnounces() {
        List<announceDto> announces = announceService.getAnnounceList();
        return SuccessResponse.makeResponse(SuccessStatusCode.ANNOUNCE_GET_SUCCESS, announces);
    }

    @GetMapping("/{announceId}")
    public SuccessResponse<AnnounceDetailDto> getAnnounceDetail(@PathVariable("announceId") Long announceId) {
        AnnounceDetailDto dto = announceService.getAnnounceDetail(announceId);
        if (dto == null) {
            throw new BadRequestException(BadStatusCode.ANNOUNCE_NOT_FOUND);
        }
        return SuccessResponse.makeResponse(SuccessStatusCode.ANNOUNCE_GET_SUCCESS, dto);
    }

}
