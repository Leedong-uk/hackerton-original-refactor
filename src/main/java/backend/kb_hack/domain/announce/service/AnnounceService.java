package backend.kb_hack.domain.announce.service;

import backend.kb_hack.domain.announce.dto.Announce;
import backend.kb_hack.domain.announce.dto.AnnounceDetailDto;
import backend.kb_hack.domain.announce.dto.announceDto;
import backend.kb_hack.domain.announce.mapper.AnnounceMapper;
import backend.kb_hack.domain.document.dto.DocumentItemDto;
import backend.kb_hack.domain.document.dto.DocumentResponseDto;
import backend.kb_hack.domain.document.mapper.DocumentMapper;
import backend.kb_hack.domain.favorite.dto.FavoriteResponseDto;
import backend.kb_hack.domain.favorite.service.FavoriteService;
import backend.kb_hack.global.security.dto.SecurityCustomUser;
import backend.kb_hack.global.security.entity.MemberVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnounceService {
    private final AnnounceMapper announceMapper;
    private final FavoriteService favoriteService;
    private final RecentAnnounceService recentAnnounceService;
    private final DocumentMapper documentMapper;

    public List<announceDto> getAnnounceList(){
        List<Announce> announces= announceMapper.findAll();

        List<FavoriteResponseDto> favorites =favoriteService.getFavorites();

        List<Long> favoriteIds = favorites.stream()
                .map(FavoriteResponseDto::getAnnounceId)
                .collect(Collectors.toList());

        return announces.stream()
                .map(announce -> announceDto.from(announce,favoriteIds.contains(announce.getAnnounceId())))
                .collect(Collectors.toList());
    }

    public AnnounceDetailDto getAnnounceDetail(@Param("announceId") Long announceId) {
        Announce announce = announceMapper.findById(announceId);
        recentAnnounceService.addRecentAnnounce(String.valueOf(announceId),announce.getTitle());

        if(announce==null){
            return null;
        }

        // 로그인한 사용자 ID
        Long memberId = getLoginMemberId();

        // 제출서류 조회
        List<DocumentResponseDto> docs =
                documentMapper.findDocumentsByMemberAndAnnounce(memberId, announceId);

        List<DocumentItemDto> checklist = docs.stream()
                .map(d -> DocumentItemDto.builder()
                        .documentId(d.getDocumentId())
                        .title(d.getTitle())
                        .description(d.getDescription())
                        .checked(d.isChecked())
                        .build())
                .toList();

        // DTO 변환 + 체크리스트 세팅
        AnnounceDetailDto dto = AnnounceDetailDto.from(announce);
        dto.setChecklist(checklist);

        return dto;
    }

    private Long getLoginMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityCustomUser securityUser = (SecurityCustomUser) authentication.getPrincipal();
        MemberVO vo = securityUser.getMemberVO();
        return vo.getMemberId();
    }
}
