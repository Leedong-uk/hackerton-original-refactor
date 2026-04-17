package backend.kb_hack.domain.home.controller;

import backend.kb_hack.domain.home.dto.HomeResponse;
import backend.kb_hack.domain.home.service.HomeService;
import backend.kb_hack.global.common.exception.enums.SuccessStatusCode;
import backend.kb_hack.global.common.response.success.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("")
    public SuccessResponse<HomeResponse> homeResponse() {
        return SuccessResponse.makeResponse(
                SuccessStatusCode.HOME_DATA_LOAD_SUCCESS,
                homeService.getHomeData()
        );
    }
}