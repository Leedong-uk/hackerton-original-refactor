package backend.kb_hack.global.security.entity;

import backend.kb_hack.domain.business.dto.BusinessDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberVO {
    private Long memberId;
    private String storageKey;
    private String email;
    private String password;
    private String name;
    private LocalDateTime createdDate;
    private int helpCount;
    private String badge;
    private String minorNm;
    private BusinessDTO businessDTO;
    private List<MemberAuthVO> authMap;
}