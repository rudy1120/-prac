package egovframework.portal.sys.basic.promotion.mapper;

import java.util.List;

import egovframework.portal.sys.basic.promotion.vo.PromotionVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface PromotionMngMapper {

	int getTotalCnt(PromotionVO searchVO);

	List<PromotionVO> getList(PromotionVO searchVO);

	PromotionVO getEntity(PromotionVO searchVO);

	void insert(PromotionVO insertVO);

	void update(PromotionVO updateVO);

	void delete(PromotionVO deleteVO);

	void deleteAll(String prmtType);

	List<PromotionVO> getDisplyableList(PromotionVO searchVO);

	int getDisplyableTotalCnt(PromotionVO searchVO);

	List<PromotionVO> getPrmtOrderList(PromotionVO searchVO);

	void changeOrder(PromotionVO updateVO);

	void updateOtherOrderOneDown(PromotionVO updateVO);

	void updateOtherOrderOneUp(PromotionVO updateVO);


}
