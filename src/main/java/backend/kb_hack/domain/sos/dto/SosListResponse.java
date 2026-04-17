package backend.kb_hack.domain.sos.dto;

import backend.kb_hack.domain.sos.entity.SosType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SosListResponse {
    private String businessName;    // business.business_nm
    private String businessAddr;
    private String businessAddrDetail;
    private Long sosId;
    private String sosTitle;        // sos.sos_title
    private SosType sosType;        // sos.sos_type
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private Long memberId;
    private String profileImage;
}
