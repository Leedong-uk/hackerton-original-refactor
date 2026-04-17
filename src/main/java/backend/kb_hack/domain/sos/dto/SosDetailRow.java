package backend.kb_hack.domain.sos.dto;

import backend.kb_hack.domain.sos.entity.SosType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SosDetailRow {
    private String badge;
    private String profileImage;       // 추가!
    private Long sosId;
    private String sosTitle;           // title → sosTitle
    private SosType sosType;
    private String sosContent;         // content → sosContent
    private LocalDateTime expiredAt;
    private LocalDateTime createdAt;
    private Long sosImageId;
    private String imageKey;           // storageKey → imageKey
    private Long businessId;
    private String businessName;
    private String businessAddr;
    private String businessAddrDetail;
}