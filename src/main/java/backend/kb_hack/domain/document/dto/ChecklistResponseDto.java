package backend.kb_hack.domain.document.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChecklistResponseDto {
    private Long announceId;
    private String announceTitle;
    private int totalDocs;
    private int checkedDocs;
    private LocalDateTime reqstStartDate;
    private LocalDateTime reqstEndDate;
    private LocalDateTime pubDate;
    private List<DocumentItemDto> checklist;
}
