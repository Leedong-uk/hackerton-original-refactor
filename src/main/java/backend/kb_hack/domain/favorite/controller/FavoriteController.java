package backend.kb_hack.domain.favorite.controller;

import backend.kb_hack.domain.favorite.dto.FavoriteResponseDto;
import backend.kb_hack.domain.favorite.service.FavoriteService;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{announceId}")
    public SuccessResponse<Void> addFavorite(@PathVariable Long announceId) {
        favoriteService.addFavorite(announceId);
        return SuccessResponse.makeResponse(SuccessStatusCode.SUCCESS_ADD_FAVORITE);
    }

    @DeleteMapping("/{announceId}")
    public SuccessResponse<Void> removeFavorite(@PathVariable Long announceId) {
        favoriteService.removeFavorite(announceId);
        return SuccessResponse.makeResponse(SuccessStatusCode.SUCCESS_DELETE_FAVORITE);
    }


    @GetMapping
    public SuccessResponse<List<FavoriteResponseDto>> getFavorites() {
        return SuccessResponse.makeResponse(SuccessStatusCode.SUCCESS_GET_FAVORITE,favoriteService.getFavorites());
    }
}
