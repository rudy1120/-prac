package egovframework.portal.unit.bmc.sabo.service;

import java.util.List;
import egovframework.portal.unit.bmc.sabo.vo.SaboSubscribeVO;

public interface SaboSubscribeService {

	List<SaboSubscribeVO> getSaboInfo(SaboSubscribeVO saboSubscribeVO);
	List<SaboSubscribeVO> getUpSaboInfo(SaboSubscribeVO saboSubscribeVO);
	List<SaboSubscribeVO> getDelSaboInfo(SaboSubscribeVO saboSubscribeVO);

	void subSabo(SaboSubscribeVO saboSubscribeVO);

	void upSabo(SaboSubscribeVO saboSubscribeVO);

	void delSabo(SaboSubscribeVO saboSubscribeVO);

}
