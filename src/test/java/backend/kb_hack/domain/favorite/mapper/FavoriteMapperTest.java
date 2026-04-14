package backend.kb_hack.domain.favorite.mapper;

import backend.kb_hack.domain.favorite.dto.FavoriteResponseDto;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Log4j2
class FavoriteMapperTest {
    @Autowired FavoriteMapper favoriteMapper;

    @Test
    @DisplayName("findFavoritesByMemberIdTest")
    void findFavoritesByMemberIdTest() throws Exception {
        List<FavoriteResponseDto> all = favoriteMapper.findFavoritesByMemberId(1L);
        all.forEach(a -> log.info("{}", a));
    }

}