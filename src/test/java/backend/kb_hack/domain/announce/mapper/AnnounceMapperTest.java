package backend.kb_hack.domain.announce.mapper;

import backend.kb_hack.domain.announce.dto.Announce;
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
class AnnounceMapperTest {
    @Autowired
    AnnounceMapper announceMapper;

    @Test
    @DisplayName("findAllTest")
    void findAllTest() throws Exception {
        List<Announce> all = announceMapper.findAll();
        all.forEach(a -> log.info("{}", a));
    }


}