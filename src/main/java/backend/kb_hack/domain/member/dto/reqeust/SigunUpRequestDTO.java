package backend.kb_hack.domain.member.dto.reqeust;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SigunUpRequestDTO {
    private String email;
    private String password;
    private String name;
    private String kakaoId;
    private String profileImageKey;
    private String businessNumber;
    private String businessNm; // -> 이게 원래 table 상 name 인데
    private LocalDate businessOpenDate;
    private String businessAddr;
    private String businessAddrDetail;
    private String minorName;
}
