package backend.kb_hack.domain.sos.service;

import backend.kb_hack.domain.business.mapper.BusinessMapper;
import backend.kb_hack.domain.sos.dto.*;
import backend.kb_hack.domain.sos.entity.Sos;
import backend.kb_hack.domain.sos.entity.SosImage;
import backend.kb_hack.domain.sos.entity.SosStatus;
import backend.kb_hack.domain.sos.mapper.SosImageMapper;
import backend.kb_hack.domain.sos.mapper.SosMapper;
import backend.kb_hack.global.security.dto.SecurityCustomUser;
import backend.kb_hack.global.security.entity.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SosService {

    private final SosMapper sosMapper;
    private final SosImageMapper sosImageMapper;
    private final StorageService storageService;
    private final BusinessMapper businessMapper;


    @Transactional
    public void create(SosCreateRequest req) {
        // member_id로 이미 SOS가 있는지 확인
        Long memberId = getLoginMemberId();
        Sos existing = sosMapper.findByMemberId(memberId);


        if (existing != null) {
            throw new IllegalArgumentException("이미 SOS가 존재합니다. (member_id=" + memberId + ")");
        }

        Sos sos = Sos.builder()
                .memberId(memberId)
                .title(req.getTitle())
                .type(req.getType())
                .content(req.getContent())
                .expiredAt(req.getExpiredAt())
                .status(SosStatus.ACTIVE)
                .build();
        sosMapper.insert(sos);

        List<String> keys = storageService.uploadAll(req.getImages(), sos.getSosId());
        for (int i = 0; i < keys.size(); i++) {
            SosImage image = SosImage.builder()
                    .sosId(sos.getSosId())
                    .storageKey(keys.get(i))
                    .sortOrder(i)          // 순서도 같이 저장
                    .build();
            sosImageMapper.insert(image);
        }
    }



    @Transactional
    public void update(Long sosId, SosUpdateDTO req) {
        // 1) SOS row 수정
        Sos sos = Sos.builder()
                .sosId(sosId)
                .title(req.getTitle())
                .type(req.getType())
                .content(req.getContent())
                .expiredAt(req.getExpiredAt() != null ? LocalDateTime.parse(req.getExpiredAt()) : null)
                .build();

        int updated = sosMapper.update(sos);
        if (updated == 0) {
            throw new IllegalArgumentException("해당 SOS가 존재하지 않거나 삭제된 상태입니다.");
        }

        // 2) 먼저 개수 검증
        List<SosImage> currentImages = sosImageMapper.findBySosId(sosId);
        int deleteCount = req.getDeleteImageIds() != null ? req.getDeleteImageIds().size() : 0;
        int newCount = req.getNewImages() != null ? req.getNewImages().size() : 0;
        int finalCount = currentImages.size() - deleteCount + newCount;

        if (finalCount > 3) {
            throw new IllegalArgumentException("이미지는 최대 3개까지 저장할 수 있습니다.");
        }

        // 3) 삭제할 이미지 처리
        if (req.getDeleteImageIds() != null) {
            for (Long imageId : req.getDeleteImageIds()) {
                sosImageMapper.deleteById(imageId);
            }
        }

        // 4) 새 이미지 추가
        if (req.getNewImages() != null && !req.getNewImages().isEmpty()) {
            List<String> keys = storageService.uploadAll(req.getNewImages(), sosId);
            for (int i = 0; i < keys.size(); i++) {
                SosImage image = SosImage.builder()
                        .sosId(sosId)
                        .storageKey(keys.get(i))
                        .sortOrder(i)
                        .build();
                sosImageMapper.insert(image);
            }
        }
    }



    @Transactional
    public void hardDelete(Long sosId) {
        sosImageMapper.deleteBySosId(sosId);
        int deleted = sosMapper.hardDelete(sosId);
        if (deleted == 0) {
            throw new IllegalArgumentException("존재하지 않는 SOS ID: " + sosId);
        }
    }


    @Transactional(readOnly = true)
    public List<SosListResponse> getSosList() {
        return sosMapper.findAll();
    }


    @Transactional(readOnly = true)
    public SosDetailResponse getSosDetail(Long sosId) {

        List<SosDetailRow> rows = sosMapper.findDetail(sosId);

        if (rows.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 SOS ID: " + sosId);
        }

        SosDetailRow first = rows.get(0);

        List<String> imageKeys = rows.stream()
                .map(SosDetailRow::getImageKey)  // storageKey → imageKey
                .filter(Objects::nonNull)
                .toList();

        String minorName = businessMapper.findMinorNameByBusinessId(first.getBusinessId());

        return SosDetailResponse.builder()
                .sosId(first.getSosId())
                .badge(first.getBadge())
                .profileImage(first.getProfileImage())
                .businessName(first.getBusinessName())
                .businessAddr(first.getBusinessAddr())
                .businessAddrDetail(first.getBusinessAddrDetail())
                .minorName(minorName)
                .sosTitle(first.getSosTitle())
                .sosType(first.getSosType())
                .sosContent(first.getSosContent())
                .expiresAt(first.getExpiredAt())  // expiresAt → expiredAt
                .createdAt(first.getCreatedAt())
                .imageKeys(imageKeys)
                .build();
    }



    // === 편의 메소드 === //

    private Long getLoginMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityCustomUser securityUser = (SecurityCustomUser) authentication.getPrincipal();
        MemberVO vo = securityUser.getMemberVO();
        return vo.getMemberId();
    }


}