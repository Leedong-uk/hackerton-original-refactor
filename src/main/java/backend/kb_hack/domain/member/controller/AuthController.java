package backend.kb_hack.domain.member.controller;

import backend.kb_hack.domain.email.dto.MailDTO;
import backend.kb_hack.domain.email.service.EmailService;
import backend.kb_hack.domain.member.dto.reqeust.MemberInfoRequestDTO;
import backend.kb_hack.domain.member.dto.reqeust.SigunUpRequestDTO;
import backend.kb_hack.domain.member.service.MemberService;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
//    private final MemberInfoService memberInfoService;
    private final EmailService emailService;


    @PostMapping("/member-info")
    public SuccessResponse<Void> signUpMember(@RequestBody SigunUpRequestDTO requestDTO) {
        memberService.signup(requestDTO);
        return SuccessResponse.makeResponse(SuccessStatusCode.SIGNUP_SUCCESS);
    }



//    @PatchMapping("/password")
//    public SuccessResponse<Void> changeNewPassword(@RequestBody LoginUpdateNewPassword loginUpdateNewPassword){
//        memberService.updatePassword(loginUpdateNewPassword.getMemberEmail(),loginUpdateNewPassword.getPassword());
//        return SuccessResponse.makeResponse(SuccessStatusCode.CHANGE_NEW_PASSWORD_SUCCESS);
//    }
//
//    @PatchMapping("/member-info")
//    public SuccessResponse<Void> updateUserInfo(@RequestBody MemberInfoRequestDTO memberInfoRequestDTO) {
//        memberInfoService.updateMemberInfo(memberInfoRequestDTO);
//        return SuccessResponse.makeResponse(SuccessStatusCode.CHANGE_MEMBER_INFO_SUCCESS);
//    }

    @PostMapping("/email")
    public SuccessResponse<Void>sendEmail(@RequestBody MailDTO mailDTO){
        emailService.sendVerificationCodeMemberInfo(mailDTO.getEmail());
        return SuccessResponse.makeResponse(SuccessStatusCode.EMAIL_SEND_SUCCESS);
    }
}
