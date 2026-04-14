package backend.kb_hack.domain.business.dto;

import backend.kb_hack.domain.member.dto.reqeust.SigunUpRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusinessDTO {
    private Long businessId;
    @JsonIgnore
    private Long memberId;
    private Long businessCodeId; // businessCodeId -> businessClassId(원본)
    private String businessNumber; // businessNumber -> businessCode(원본)
    private String name;
    private String businessAddr;
    private String businessAddrDetail;
    private LocalDate businessOpenDate;


    public static BusinessDTO makeBusniessDTO(SigunUpRequestDTO dto){
        return BusinessDTO.builder()
                .name(dto.getBusinessNm())
                .businessAddr(dto.getBusinessAddr())
                .businessAddrDetail(dto.getBusinessAddrDetail())
                .businessNumber(dto.getBusinessNumber())
                .businessOpenDate(dto.getBusinessOpenDate())
                .build();
    }

}