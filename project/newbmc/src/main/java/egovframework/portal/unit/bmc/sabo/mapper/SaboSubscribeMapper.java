package egovframework.portal.unit.bmc.sabo.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.sabo.vo.SaboSubscribeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SaboSubscribeMapper {

	List<SaboSubscribeVO> getSaboInfo(SaboSubscribeVO saboSubscribeVO);
	List<SaboSubscribeVO> getUpSaboInfo(SaboSubscribeVO saboSubscribeVO);
	List<SaboSubscribeVO> getDelSaboInfo(SaboSubscribeVO saboSubscribeVO);

	void subSabo(SaboSubscribeVO saboSubscribeVO);

	void upSabo(SaboSubscribeVO saboSubscribeVO);

	void delSabo(SaboSubscribeVO saboSubscribeVO);

}
