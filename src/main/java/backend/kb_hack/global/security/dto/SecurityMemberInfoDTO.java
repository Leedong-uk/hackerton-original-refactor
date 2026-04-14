package backend.kb_hack.global.security.dto;

import backend.kb_hack.domain.business.dto.BusinessDTO;
import backend.kb_hack.global.security.entity.MemberAuthVO;
import backend.kb_hack.global.security.entity.MemberVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SecurityMemberInfoDTO {
    private Long memberId;
    private String profileImageId;
    private String email;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private int helpCount;
    private String badge;
    private String minorNm;
    private BusinessDTO businessDTO;
    private List<MemberAuthVO> authMap;

    public static SecurityMemberInfoDTO convertToDTO(MemberVO vo) {
        return SecurityMemberInfoDTO.builder()
                .memberId(vo.getMemberId())
                .profileImageId(vo.getStorageKey())
                .email(vo.getEmail())
                .name(vo.getName())
                .createdDate(vo.getCreatedDate())
                .helpCount(vo.getHelpCount())
                .badge(vo.getBadge())
                .businessDTO(vo.getBusinessDTO())
                .authMap(vo.getAuthMap())
                .build();
    }
}
