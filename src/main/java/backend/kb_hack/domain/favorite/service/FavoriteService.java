package backend.kb_hack.domain.favorite.service;

import backend.kb_hack.domain.favorite.dto.FavoriteResponseDto;
import backend.kb_hack.domain.favorite.mapper.FavoriteMapper;
import backend.kb_hack.global.security.dto.SecurityCustomUser;
import backend.kb_hack.global.security.entity.MemberVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteMapper favoriteMapper;

    @Transactional(readOnly = true)
    public List<FavoriteResponseDto> getFavorites() {
        Long memberId = getLoginMemberId();
        return favoriteMapper.findFavoritesByMemberId(memberId);
    }

    private Long getLoginMemberId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityCustomUser securityUser = (SecurityCustomUser) authentication.getPrincipal();
        MemberVO vo = securityUser.getMemberVO();
        return vo.getMemberId();
    }
}
