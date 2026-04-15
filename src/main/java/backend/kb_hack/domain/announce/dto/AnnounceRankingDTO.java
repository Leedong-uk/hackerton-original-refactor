package backend.kb_hack.domain.announce.dto;

import lombok.Data;

@Data
public class AnnounceRankingDTO {
    private Long announceId;
    private int viewNum;
    private String title;
}
