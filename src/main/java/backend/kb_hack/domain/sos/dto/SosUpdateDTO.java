package backend.kb_hack.domain.sos.dto;

import backend.kb_hack.domain.sos.entity.SosType;
import lombok.Data;

import java.util.List;

@Data
public class SosUpdateDTO {
    String title;
    SosType type;
    String content;
    String expiredAt;
    List<Long> deleteImageIds;
    List<String> newImages;

}
