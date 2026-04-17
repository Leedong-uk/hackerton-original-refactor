package backend.kb_hack.domain.document.mapper;

import backend.kb_hack.domain.document.dto.DocumentResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DocumentMapper {
    boolean existsDocumentCheck(@Param("documentId") Long documentId, @Param("memberId") Long memberId);
    void insertDocumentCheck(@Param("documentId") Long documentId, @Param("memberId") Long memberId, @Param("checked") boolean checked);
    void updateDocumentCheck(@Param("documentId") Long documentId, @Param("memberId") Long memberId, @Param("checked") boolean checked);
    List<DocumentResponseDto> findDocumentsByMemberAndAnnounce(@Param("announceId") Long announceId, @Param("memberId") Long memberId);
    List<DocumentResponseDto> findDocumentsByMemberId(@Param("memberId") Long memberId);
}
