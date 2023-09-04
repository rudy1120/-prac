package egovframework.portal.sys.basic.participation.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.unit.bmc.participation.vo.ParticipantVO;

public interface SysParticipationService {

	int getTotalCnt(PartContVO searchVO);

	List<PartContVO> getList(PartContVO searchVO);

	String insert(PartContVO searchVO, MultipartHttpServletRequest request);

	String update(PartContVO searchVO, MultipartHttpServletRequest request);

	PartContVO getEntity(PartContVO searchVO);

	String delete(PartContVO searchVO);

	PartContVO getContent(int idx);

	int getParticipantCnt(ParticipantVO searchVO);

	List<ParticipantVO> getParticipantList(ParticipantVO searchVO);

}
