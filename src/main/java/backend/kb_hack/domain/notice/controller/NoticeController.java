package backend.kb_hack.domain.notice.controller;

import backend.kb_hack.domain.notice.dto.NoticeDTO;
import backend.kb_hack.domain.notice.dto.NoticeDetailDTO;
import backend.kb_hack.domain.notice.service.NoticeService;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;


    @GetMapping("")
    public SuccessResponse<List<NoticeDTO>> getAllNotice() {
        return SuccessResponse.makeResponse(
                SuccessStatusCode.NOTICE_GET_SUCCESS,
                noticeService.getAllnotice()
        );
    }


    @GetMapping("/{notice_id}")
    public SuccessResponse<NoticeDetailDTO> getNoticeById(@PathVariable("notice_id") Long noticeId) {
        return SuccessResponse.makeResponse(
                SuccessStatusCode.NOTICE_GET_SUCCESS,
                noticeService.getNoticeById(noticeId)
        );
    }
}
