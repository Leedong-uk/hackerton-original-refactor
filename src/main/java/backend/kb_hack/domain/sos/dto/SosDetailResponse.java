package backend.kb_hack.domain.sos.dto;

import backend.kb_hack.domain.sos.entity.SosType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class SosDetailResponse {
    //member
    private String badge;

    //profileImage
    private String profileImage;

    //business
    private String businessName;
    private String businessAddr;
    private String businessAddrDetail;
    //businessCode
    private String minorName;

    //sos
    private Long sosId;
    private String sosTitle;
    private SosType sosType;
    private String sosContent;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private List<String> imageKeys;


}
