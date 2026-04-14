package backend.kb_hack.domain.announce.service;

import backend.kb_hack.domain.announce.dto.Announce;
import backend.kb_hack.domain.announce.dto.announceDto;
import backend.kb_hack.domain.announce.mapper.AnnounceMapper;
import backend.kb_hack.domain.favorite.dto.FavoriteResponseDto;
import backend.kb_hack.domain.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnounceService {
    private final AnnounceMapper announceMapper;
    private final FavoriteService favoriteService;

    public List<announceDto> getAnnounceList(){
        List<Announce> announces= announceMapper.findAll();

        List<FavoriteResponseDto> favorites =favoriteService.getFavorites();

        List<Long> favoriteIds = favorites.stream()
                .map(FavoriteResponseDto::getAnnounceId)
                .collect(Collectors.toList());

        return announces.stream()
                .map(announce -> announceDto.from(announce,favoriteIds.contains(announce.getAnnounceId())))
                .collect(Collectors.toList());
    }
}
