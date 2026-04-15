package backend.kb_hack.domain.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessPlus {
    private Long businessId;
    private Long businessCodeId;
    private String name;
    private String businessAddr;
    private String businessAddrDetail;
    private String businessNumber;
    private LocalDate businessOpenDate;
    private LocalDateTime createdDate;
    private Long memberId;

    // These fields are for mapping data from the JOIN query in the mapper
    private String businessCodeMajorName;
    private String businessCodeMiddleName;
    private String businessCodeMinorName;
}
