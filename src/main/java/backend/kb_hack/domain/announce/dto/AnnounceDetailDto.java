package backend.kb_hack.domain.announce.dto;

import backend.kb_hack.domain.document.dto.DocumentItemDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AnnounceDetailDto {
    private String title;
    private String author;
    private String excInsttNm;
    private String description;
    private String lcategory;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime pubDate;
    private String targetName;
    private int viewNum;
    private String printFilePath;
    private String printFileName;
    private String howToRegister;
    private String callCompany;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime reqstStartDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDateTime reqstEndDate;
    private String filePath;
    private String fileName;
    private List<DocumentItemDto> checklist;


    public static AnnounceDetailDto from(Announce announce) {
        return AnnounceDetailDto.builder()
                .title(announce.getTitle())
                .author(announce.getAuthor())
                .excInsttNm(announce.getExcInsttNm())
                .description(announce.getDescription())
                .lcategory(announce.getLcategory())
                .pubDate(announce.getPubDate())
                .reqstStartDate(announce.getReqstStartDate())
                .reqstEndDate(announce.getReqstEndDate())
                .targetName(announce.getTargetName())
                .viewNum(announce.getViewNum())
                .filePath(announce.getFilePath())
                .fileName(announce.getFileName())
                .howToRegister(announce.getHowToRegister())
                .callCompany(announce.getCallCompany())
                .printFilePath(announce.getPrintFilePath())
                .printFileName(announce.getPrintFileName())
                .build();
    }
}
