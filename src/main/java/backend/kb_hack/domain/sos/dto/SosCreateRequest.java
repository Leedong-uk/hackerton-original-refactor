package backend.kb_hack.domain.sos.dto;

import backend.kb_hack.domain.sos.entity.SosType;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SosCreateRequest {

    private String title; // nullable
    private SosType type;
    private String content;
    private LocalDateTime expiredAt;
    private List<String> images;
}
