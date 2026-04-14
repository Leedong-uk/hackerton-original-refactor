package backend.kb_hack.domain.announce.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Announce {
    private Long announceId;
    private String title;
    private String author;
    private String excInsttNm;
    private String description;
    private String lcategory;
    private LocalDateTime pubDate;
    private String targetName;
    private int viewNum;
    private String printFilePath;
    private String printFileName;
    private String howToRegister;
    private String callCompany;
    private LocalDateTime reqstStartDate;
    private LocalDateTime reqstEndDate;
    private String filePath;
    private String fileName;

}
