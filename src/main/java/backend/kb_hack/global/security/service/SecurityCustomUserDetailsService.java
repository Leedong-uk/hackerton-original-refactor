package backend.kb_hack.global.security.service;

import backend.kb_hack.global.common.exception.enums.BadStatusCode;
import backend.kb_hack.global.common.exception.type.NotFoundException;
import backend.kb_hack.global.security.dto.SecurityCustomUser;
import backend.kb_hack.global.security.entity.MemberVO;
import backend.kb_hack.global.security.mapper.SecurityMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityCustomUserDetailsService implements UserDetailsService {
    private final SecurityMemberMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        MemberVO vo = mapper.getMemberByMemberEmail(username);
        System.out.println("vo = " + vo);
        if(vo == null){
            throw new NotFoundException(BadStatusCode.USER_NOT_FOUND_EXCEPTION);
        }
        return new SecurityCustomUser(vo);
    }
}
