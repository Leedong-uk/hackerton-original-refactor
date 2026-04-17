package backend.kb_hack.domain.member.service;

import backend.kb_hack.domain.business.service.BusinessService;
import backend.kb_hack.domain.member.dto.AuthDTO;
import backend.kb_hack.domain.member.dto.MemberDTO;
import backend.kb_hack.domain.member.dto.reqeust.SigunUpRequestDTO;
import backend.kb_hack.domain.member.mapper.MemberMapper;
import backend.kb_hack.domain.member.mapper.ProfileImageMapper;
import backend.kb_hack.global.common.exception.enums.BadStatusCode;
import backend.kb_hack.global.common.exception.type.BadRequestException;
import backend.kb_hack.global.common.exception.type.ServerErrorException;
import backend.kb_hack.global.security.dto.SecurityCustomUser;
import backend.kb_hack.global.security.entity.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberService {
    private final MemberMapper memberMapper ;
    private final BusinessService businessService;
    private final ProfileImageMapper profileImageMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberDTO signUpInsertMemberInfo(SigunUpRequestDTO sigunUpRequestDTO) {

        if (sigunUpRequestDTO == null) {
            throw new BadRequestException(BadStatusCode.EMPTY_SIGNUP_INFO_EXCEPTION);
        }
        try {
            MemberDTO dto = MemberDTO.convertToMemberDTO(sigunUpRequestDTO);

            try {
                if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
                    String randomPassword = UUID.randomUUID().toString().substring(0, 4);
                    dto.setPassword(randomPassword);
                }
                dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            } catch (Exception e) {
                throw new ServerErrorException(BadStatusCode.PASSWORD_ENCODING_FAIL_EXCEPTION);
            }


            int i = memberMapper.insertMember(dto);
            if (i == 0) {
                throw new BadRequestException(BadStatusCode.FAIL_TO_REGISTER_MEMBER_EXCEPTION);
            }

            AuthDTO authDTO = new AuthDTO();
            authDTO.setMemberId(dto.getMemberId());
            authDTO.setAuth("USER");
            int authResult = memberMapper.insertAuth(authDTO);
            if(authResult == 0 ){
                throw new ServerErrorException(BadStatusCode.FAIL_TO_REGISTER_MEMBER_AUTH_EXCEPTION);
            }

            return dto;

        } catch (DataAccessException e) {
            log.error("DB 에러 원인: {}", e.getMessage(), e);  // 추가!
            throw new ServerErrorException(BadStatusCode.DATABASE_PROCESSING_EXCEPTION);
        } catch (Exception e) {
            log.error("알 수 없는 에러: {}", e.getMessage(), e);  // 추가!
            throw new ServerErrorException(BadStatusCode.INTERNAL_SERVER_EXCEPTION);
        }

    }

    @Transactional
    public void signup (SigunUpRequestDTO sigunUpRequestDTO){
        MemberDTO dto = signUpInsertMemberInfo(sigunUpRequestDTO);
        profileImageMapper.insertProfileImage(dto.getMemberId(), sigunUpRequestDTO.getProfileImageKey()); // memberId 추가!
        businessService.sigunUpInsertBusinessInfo(sigunUpRequestDTO, dto.getMemberId());

    }



    public void updatePassword(String memberEmail, String newPassword){
        if (newPassword == null || newPassword.isBlank() || memberEmail == null || memberEmail.isBlank()) {
            throw new BadRequestException(BadStatusCode.INVALID_PARAMETER_EXCEPTION);
        }
        String encode = bCryptPasswordEncoder.encode(newPassword);
        int i = memberMapper.updatePasswordByMemberEmail(memberEmail, encode);

        if (i == 0) {
            throw new ServerErrorException(BadStatusCode.DATABASE_PROCESSING_EXCEPTION);
        }
    }

    private static MemberVO getMemberVO() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityCustomUser securityUser = (SecurityCustomUser) authentication.getPrincipal();
        MemberVO vo = securityUser.getMemberVO();
        return vo;
    }

    /** 🔑 현재 로그인한 memberId 가져오기 */
    private Long getLoginMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityCustomUser securityUser = (SecurityCustomUser) authentication.getPrincipal();
        MemberVO vo = securityUser.getMemberVO();
        return vo.getMemberId();
    }


}
