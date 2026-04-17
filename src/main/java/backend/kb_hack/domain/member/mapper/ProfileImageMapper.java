package backend.kb_hack.domain.member.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProfileImageMapper {
    int insertProfileImage(Long memberId , String profileImage);
}
