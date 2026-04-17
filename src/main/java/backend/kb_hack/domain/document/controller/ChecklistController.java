package backend.kb_hack.domain.document.controller;

import backend.kb_hack.domain.document.dto.ChecklistResponseDto;
import backend.kb_hack.domain.document.dto.DocumentCheckBulkRequestDto;
import backend.kb_hack.domain.document.dto.DocumentCheckItemDto;
import backend.kb_hack.domain.document.service.ChecklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checklist")
@RequiredArgsConstructor
public class ChecklistController {

    private final ChecklistService checklistService;


    // /** ✅ 전체 체크리스트 조회 */

    @GetMapping("/{announceId}")
    public ResponseEntity<ChecklistResponseDto> getChecklistByAnnounce(@PathVariable Long announceId) {
        return ResponseEntity.ok(checklistService.getChecklistByAnnounce(announceId));
    }


    /** ✅ 공고별 배치 체크/해제 */
    @PostMapping("/{announceId}")
    public ResponseEntity<String> updateCheckStatusBulk(
            @PathVariable Long announceId,
            @RequestBody List<DocumentCheckItemDto> items) {
        checklistService.updateCheckStatusBulk(announceId, items);
        return ResponseEntity.ok("체크 상태가 저장되었습니다. (announceId=" + announceId + ")");
    }

}
