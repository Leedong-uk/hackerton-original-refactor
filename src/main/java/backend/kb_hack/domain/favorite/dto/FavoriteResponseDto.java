package backend.kb_hack.domain.favorite.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteResponseDto {
    private Long announceId;
    private String title;
    private LocalDateTime reqstEndDate;
    private int totalDocs;
    private int checkedDocs;
}
