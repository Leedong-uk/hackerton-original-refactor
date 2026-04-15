package backend.kb_hack.domain.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentItemDto {
    private Long documentId;
    private String title;
    private String description;
    private boolean checked;
}

