package backend.kb_hack.domain.sos.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SosImage {
    private Long sosImageId;
    private Long sosId;
    private String storageKey;
    private int sortOrder;
    private LocalDateTime createdDate;
}
