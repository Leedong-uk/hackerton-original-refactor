package backend.kb_hack.domain.festival.dto;

import backend.kb_hack.domain.festival.Festival;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FestivalDetailDto {
    private String title;
    private String address;
    private double mapX;
    private double mapY;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate eventStartDate;
    @JsonFormat(pattern = "yyyyMMdd")
    private LocalDate eventEndDate;
    private String firstImage;
    private String tel;
    private String overview;
    private String telName;

    public static FestivalDetailDto from(Festival festival) {
        return  FestivalDetailDto.builder()
                .title(festival.getTitle())
                .address(festival.getAddress()+" "+festival.getDetailAddress())
                .mapX(festival.getMapx())
                .mapY(festival.getMapy())
                .eventStartDate(festival.getEventStartDate())
                .eventEndDate(festival.getEventEndDate())
                .firstImage(festival.getFirstImage())
                .tel(festival.getTel())
                .overview(festival.getOverview())
                .telName(festival.getTelName())
                .build();
    }

}