package backend.kb_hack.domain.festival;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Festival {
    private Long festivalId;
    private String address;
    private String detailAddress;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private String firstImage;
    private String tel;
    private String title;
    private String festivalTitle;   // 추가!
    private String overview;
    private Long contentId;         // String → Long
    private String telName;
    private double mapx;            // mapX → mapx
    private double mapy;            // mapY → mapy
    private LocalDateTime createdDate;
}
