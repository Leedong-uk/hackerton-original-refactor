package backend.kb_hack.domain.business.mapper;

import backend.kb_hack.domain.business.BusinessPlus;
import backend.kb_hack.domain.business.dto.BusinessDTO;
import backend.kb_hack.domain.member.dto.reqeust.MemberInfoRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface BusinessMapper {
    int insertBusiness(BusinessDTO businessVO);
    long findBusinessCodeIdByMinorname(String minorName);

    int updateBusiness(@Param("dto") MemberInfoRequestDTO dto,
                       @Param("businessId") Long businessId);

    String findMinorNameByBusinessId(Long businessId);

    Optional<BusinessPlus> findBusinessAndClassInfoByMemberId(@Param("memberId") Long memberId);

}