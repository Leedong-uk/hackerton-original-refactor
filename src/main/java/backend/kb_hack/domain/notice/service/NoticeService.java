package backend.kb_hack.domain.notice.service;

import backend.kb_hack.domain.notice.dto.NoticeDTO;
import backend.kb_hack.domain.notice.dto.NoticeDetailDTO;
import backend.kb_hack.domain.notice.mapper.NoticeMapper;
import backend.kb_hack.global.common.exception.enums.BadStatusCode;
import backend.kb_hack.global.common.exception.type.ServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeMapper noticeMapper;

    public List<NoticeDTO> getAllnotice() {
        List<NoticeDTO> notices = noticeMapper.getAllNotice();
        if (notices.isEmpty()) {
            throw new ServerErrorException(BadStatusCode.FAIL_TO_PROCESSING_NOTICE_EXCEPTION);
        }
        return notices;
    }

    public NoticeDetailDTO getNoticeById(Long noticeId) {
        NoticeDetailDTO detail = noticeMapper.getNoticeDetail(noticeId);
        return detail;
    }
}
