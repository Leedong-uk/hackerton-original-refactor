package backend.kb_hack.domain.document.dto;

import lombok.Data;

@Data
public class DocumentCheckItemDto {
    private Long documentId;
    private boolean checked;
}
