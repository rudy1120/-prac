package egovframework.portal.unit.bmc.sub.service;

import java.util.List;



import egovframework.portal.unit.bmc.sub.vo.SubscribeVO;

public interface SubscribeService {

	List<SubscribeVO> getWebInfo(SubscribeVO subscribeVO);
	List<SubscribeVO> getUpWebInfo(SubscribeVO subscribeVO);
	List<SubscribeVO> getDelWebInfo(SubscribeVO subscribeVO);

	void subWebzine(SubscribeVO subscribeVO);

	void upWebzine(SubscribeVO subscribeVO);

	void delWebzine(SubscribeVO subscribeVO);

}
