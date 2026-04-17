package backend.kb_hack.domain.sos.mapper;

import backend.kb_hack.domain.sos.dto.SosDetailRow;
import backend.kb_hack.domain.sos.dto.SosListResponse;
import backend.kb_hack.domain.sos.entity.Sos;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SosMapper {
    int insert(Sos sos);
    Sos findByMemberId(Long memberId);
    int hardDelete(Long sosId);
    List<SosListResponse> findAll();
    List<SosDetailRow> findDetail(Long sosId);
    int update(Sos sos);



}
