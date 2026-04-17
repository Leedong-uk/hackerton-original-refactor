package backend.kb_hack.domain.email.controller;

import backend.kb_hack.domain.email.dto.MailDTO;
import backend.kb_hack.domain.email.dto.MailVerificationDTO;
import backend.kb_hack.domain.email.service.EmailService;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    @PostMapping("")
    public SuccessResponse<Void> sendEmail(@RequestBody MailDTO mailDTO){
        emailService.sendVerificationCode(mailDTO.getEmail());
        return SuccessResponse.makeResponse(SuccessStatusCode.EMAIL_SEND_SUCCESS);
    }

    @PostMapping("/code")
    public SuccessResponse<Void> verificationEmail(@RequestBody MailVerificationDTO mailVerificationDTO){
        emailService.verifyCode(mailVerificationDTO.getEmail(),mailVerificationDTO.getVerificationCode());
        return SuccessResponse.makeResponse(SuccessStatusCode.EMAIL_VERIFY_CODE_SUCCESS);
    }
}