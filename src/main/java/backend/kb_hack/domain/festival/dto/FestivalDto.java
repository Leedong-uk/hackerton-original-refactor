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
public class FestivalDto {
    private Long festivalId;
    private String title;
    private String address;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private String firstImage;

    public static FestivalDto from(Festival festival) {
        return  FestivalDto.builder()
                .festivalId(festival.getFestivalId())
                .title(festival.getTitle())
                .address(festival.getAddress()+" "+festival.getDetailAddress())
                .eventStartDate(festival.getEventStartDate())
                .eventEndDate(festival.getEventEndDate())
                .firstImage(festival.getFirstImage())
                .build();
    }
}
