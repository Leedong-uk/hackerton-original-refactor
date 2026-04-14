package backend.kb_hack.domain.announce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class announceDto {
    private Long announceId;
    private String title;
    private String excInsttNm; // 발행기관
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime endDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime pubDate;
    private boolean isFavorite;
    private String lcategory;
    
    public static announceDto from (Announce announce,boolean isFavorite){
        return announceDto.builder()
                .announceId(announce.getAnnounceId())
                .title(announce.getTitle())
                .excInsttNm(announce.getExcInsttNm())
                .startDate(announce.getReqstStartDate())
                .endDate(announce.getReqstEndDate())
                .pubDate(announce.getPubDate())
                .isFavorite(isFavorite)
                .lcategory(announce.getLcategory())
                .build();
    }
}
