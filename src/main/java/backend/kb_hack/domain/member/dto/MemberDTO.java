package backend.kb_hack.domain.member.dto;

import backend.kb_hack.domain.member.dto.reqeust.SigunUpRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long memberId;
    private String email;
    private String password;
    private String name;
    private String kakaoId;
    private String status;
    private Integer helpCount;
    private String badge;
    private String fcmToken;
    private Long profileImageId;    // FK → profile_image.profile_image_id

    public static MemberDTO convertToMemberDTO(SigunUpRequestDTO dto) {
        return MemberDTO.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .name(dto.getName())
                .kakaoId(dto.getKakaoId())
                .status("active")
                .helpCount(0)
                .badge("도움일꾼")
                .build();
    }
}
