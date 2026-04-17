//package backend.kb_hack.domain.member.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final MemberService memberService;
//    private final MemberInfoService memberInfoService;
//    private final EmailService emailService;
//
//
//    @PostMapping("/member-info")
//    public SuccessResponse<Void> signUpMember(@RequestBody SigunUpRequestDTO requestDTO) {
//        memberService.signup(requestDTO);
//        return SuccessResponse.makeResponse(SuccessStatusCode.SIGNUP_SUCCESS);
//    }
//
//    @DeleteMapping("/member-info")
//    public SuccessResponse<Void> signOutMember(){
//        memberService.delete();
//        return SuccessResponse.makeResponse(SuccessStatusCode.SIGNOUT_SUCCESS);
//    }
//
//
//    @PatchMapping("/password")
//    public SuccessResponse<Void> changeNewPassword(@RequestBody LoginUpdateNewPassword loginUpdateNewPassword){
//        memberService.updatePassword(loginUpdateNewPassword.getMemberEmail(),loginUpdateNewPassword.getPassword());
//        return SuccessResponse.makeResponse(SuccessStatusCode.CHANGE_NEW_PASSWORD_SUCCESS);
//    }
//
//    @PatchMapping("/member-info")
//    public SuccessResponse<Void> updateUserInfo(
//            @io.swagger.v3.oas.annotations.parameters.RequestBody(
//                    description = "수정할 회원/사업체 정보",
//                    required = true,
//                    content = @Content(schema = @Schema(implementation = MemberInfoRequestDTO.class))
//            )
//            @RequestBody MemberInfoRequestDTO memberInfoRequestDTO
//    ) {
//        memberInfoService.updateMemberInfo(memberInfoRequestDTO);
//        return SuccessResponse.makeResponse(SuccessStatusCode.CHANGE_MEMBER_INFO_SUCCESS);
//    }
//
//    @PostMapping("/email")
//    public SuccessResponse<Void>sendEmail(@RequestBody MailDTO mailDTO){
//        emailService.sendVerificationCodeMemberInfo(mailDTO.getEmail());
//        return SuccessResponse.makeResponse(SuccessStatusCode.EMAIL_SEND_SUCCESS);
//    }
//}
