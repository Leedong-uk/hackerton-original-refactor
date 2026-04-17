package backend.kb_hack.domain.sos.controller;

import backend.kb_hack.domain.sos.dto.SosCreateRequest;
import backend.kb_hack.domain.sos.dto.SosDetailResponse;
import backend.kb_hack.domain.sos.dto.SosListResponse;
import backend.kb_hack.domain.sos.dto.SosUpdateDTO;
import backend.kb_hack.domain.sos.entity.SosType;
import backend.kb_hack.domain.sos.service.SosService;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import backend.kb_hack.global.security.dto.SecurityCustomUser;
import backend.kb_hack.global.security.entity.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sos")
@RequiredArgsConstructor
@Validated
public class SosController {

    private final SosService sosService;

    @PostMapping(value = "")
    public SuccessResponse<Void> createSos(@RequestBody SosCreateRequest sosCreateRequest) {
        Long memberId = getLoginMemberId();
        sosService.create(sosCreateRequest);
        return SuccessResponse.makeResponse(SuccessStatusCode.SOS_CREATE_SUCCESS);
    }


    @PutMapping(value = "/{sosId}")
    public SuccessResponse<Void> updateSos(@PathVariable Long sosId, @RequestBody SosUpdateDTO updateDTO) {
        sosService.update(sosId, updateDTO);
        return SuccessResponse.makeResponse(SuccessStatusCode.SOS_UPDATE_SUCCESS);
    }

    @DeleteMapping("/{sosId}")
    public SuccessResponse<Void> hardDeleteSos(@PathVariable Long sosId) {
        sosService.hardDelete(sosId);
        return SuccessResponse.makeResponse(SuccessStatusCode.SOS_DELETE_SUCCESS);
    }

    @GetMapping
    public ResponseEntity<List<SosListResponse>> getSosList() {
        return ResponseEntity.ok(sosService.getSosList());
    }


    @GetMapping("/{sosId}")
    public SuccessResponse<SosDetailResponse> getSosDetail(@PathVariable Long sosId) {
        return SuccessResponse.makeResponse(SuccessStatusCode.SOS_GET_SUCCESS,sosService.getSosDetail(sosId));
    }


    private Long getLoginMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityCustomUser securityUser = (SecurityCustomUser) authentication.getPrincipal();
        MemberVO vo = securityUser.getMemberVO();
        return vo.getMemberId();
    }


}
