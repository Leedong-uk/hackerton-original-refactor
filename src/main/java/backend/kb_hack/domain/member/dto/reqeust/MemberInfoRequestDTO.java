package backend.kb_hack.domain.member.dto.reqeust;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberInfoRequestDTO {
    private String image;
    @JsonIgnore
    private Long businessCodeId;
    private String businessNumber;
    private String memberName;
    private String businessNm;
    private LocalDateTime businessOpenDate;
    private String minorName;
    private String businessAddr;
    private String businessAddrDetail;

}
