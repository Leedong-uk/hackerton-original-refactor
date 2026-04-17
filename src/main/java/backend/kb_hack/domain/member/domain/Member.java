package backend.kb_hack.domain.member.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Member {
    private Long memberId;



    private Long businessId;
    private Long profileImageId;
    private String memberEmail;
    private String password;
    private String memberName;
    private Date createdAt;
    private Integer helpCount;
    private String badge;
}

