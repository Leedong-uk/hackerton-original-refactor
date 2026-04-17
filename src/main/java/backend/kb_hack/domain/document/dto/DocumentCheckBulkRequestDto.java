package backend.kb_hack.domain.document.dto;

import lombok.Data;

import java.util.List;

@Data
public class DocumentCheckBulkRequestDto {
    private List<DocumentCheckItemDto> items;
}
