package backend.kb_hack.global.security.mapper;


import backend.kb_hack.global.security.entity.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityMemberMapper {
    MemberVO getMemberByMemberEmail(String memberEmail);
    String getMinorNmByBusinessId(Long businessId);

}
