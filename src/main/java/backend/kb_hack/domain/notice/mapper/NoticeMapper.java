package backend.kb_hack.domain.notice.mapper;

import backend.kb_hack.domain.notice.dto.NoticeDTO;
import backend.kb_hack.domain.notice.dto.NoticeDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {
    List<NoticeDTO> getAllNotice();
    NoticeDetailDTO getNoticeDetail(Long noticeId);
}
