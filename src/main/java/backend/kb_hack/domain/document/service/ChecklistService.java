package backend.kb_hack.domain.document.service;

import backend.kb_hack.domain.document.dto.*;
import backend.kb_hack.domain.document.mapper.DocumentMapper;
import backend.kb_hack.global.security.dto.SecurityCustomUser;
import backend.kb_hack.global.security.entity.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChecklistService {

    private final DocumentMapper documentMapper;

    /** 공고별 체크리스트 */
    @Transactional(readOnly = true)
    public ChecklistResponseDto getChecklistByAnnounce(Long announceId) {
        Long memberId = getLoginMemberId();
        List<DocumentResponseDto> docs = documentMapper.findDocumentsByMemberAndAnnounce(memberId, announceId);

        if (docs.isEmpty()) return null;

        DocumentResponseDto first = docs.get(0);

        List<DocumentItemDto> checklist = docs.stream()
                .map(d -> DocumentItemDto.builder()
                        .documentId(d.getDocumentId())
                        .title(d.getTitle())
                        .description(d.getDescription())
                        .checked(d.isChecked())
                        .build())
                .toList();

        return ChecklistResponseDto.builder()
                .announceId(first.getAnnounceId())
                .announceTitle(first.getAnnounceTitle())
                .totalDocs(first.getTotalDocs())
                .checkedDocs(first.getCheckedDocs())
                .reqstStartDate(first.getReqstStartDate())
                .reqstEndDate(first.getReqstEndDate())
                .pubDate(first.getPubDate())
                .checklist(checklist)
                .build();
    }

    /** 회원 전체 즐겨찾기 체크리스트 */
    @Transactional(readOnly = true)
    public List<DocumentResponseDto> getChecklist() {
        Long memberId = getLoginMemberId();
        return documentMapper.findDocumentsByMemberId(memberId);
    }

    /** 단건 체크/해제 */
    @Transactional
    public void updateCheckStatus(Long documentId, DocumentCheckRequestDto dto) {
        Long memberId = getLoginMemberId();
        upsert(documentId, memberId, dto.isChecked());
    }

    /** 공고별 배치 체크/해제 */
    @Transactional
    public void updateCheckStatusBulk(Long announceId, List<DocumentCheckItemDto> items) {
        Long memberId = getLoginMemberId();
        if (items == null) return;
        for (DocumentCheckItemDto item : items) {
            if (item == null || item.getDocumentId() == null) continue;
            upsert(item.getDocumentId(), memberId, item.isChecked());
        }
    }

    /** INSERT or UPDATE */
    private void upsert(Long documentId, Long memberId, boolean checked) {
        boolean exists = documentMapper.existsDocumentCheck(documentId, memberId);
        if (exists) {
            documentMapper.updateDocumentCheck(documentId, memberId, checked);
        } else {
            documentMapper.insertDocumentCheck(documentId, memberId, checked);
        }
    }

    /** 🔑 현재 로그인한 memberId 가져오기 */
    private Long getLoginMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityCustomUser securityUser = (SecurityCustomUser) authentication.getPrincipal();
        MemberVO vo = securityUser.getMemberVO();
        return vo.getMemberId();
    }
}