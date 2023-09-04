package egovframework.portal.unit.bmc.sub.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.sub.vo.SubscribeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SubscribeMapper {

	List<SubscribeVO> getWebInfo(SubscribeVO subscribeVO);
	List<SubscribeVO> getUpWebInfo(SubscribeVO subscribeVO);
	List<SubscribeVO> getDelWebInfo(SubscribeVO subscribeVO);

	void subWebzine(SubscribeVO subscribeVO);

	void upWebzine(SubscribeVO subscribeVO);

	void delWebzine(SubscribeVO subscribeVO);

}
