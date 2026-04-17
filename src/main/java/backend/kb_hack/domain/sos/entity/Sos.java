package backend.kb_hack.domain.sos.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sos {
    private Long sosId;
    private Long memberId;
    private String title;
    private SosType type;
    private String content;
    private LocalDateTime expiredAt;
    private SosStatus status;
    private LocalDateTime createdDate;   // DB default
}
