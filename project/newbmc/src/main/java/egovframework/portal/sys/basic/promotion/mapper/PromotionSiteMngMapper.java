package egovframework.portal.sys.basic.promotion.mapper;

import egovframework.portal.sys.basic.promotion.vo.PromotionSiteVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface PromotionSiteMngMapper {

	void insert(PromotionSiteVO insertVO);

	void deleteAll(String prmtIdx);

}
