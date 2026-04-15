package backend.kb_hack.domain.document.mapper;

import backend.kb_hack.domain.document.dto.DocumentResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DocumentMapper {
    List<DocumentResponseDto> findDocumentsByMemberAndAnnounce( @Param("memberId") Long memberId, @Param("announceId") Long announceId);

}
