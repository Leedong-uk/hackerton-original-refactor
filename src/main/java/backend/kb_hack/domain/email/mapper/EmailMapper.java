package backend.kb_hack.domain.email.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailMapper {
    Long findMemberIDByEmail(String email);
}
