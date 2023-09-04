package egovframework.portal.unit.bmc.participation.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.unit.bmc.participation.vo.ParticipantVO;

public interface ParticipationService {

	List<PartContVO> getList(PartContVO partContVO);

	int getTotalCnt(PartContVO partContVO);

	PartContVO getContent(PartContVO partContVO);

	void setViewCount(PartContVO partContVO);

	String insert(ParticipantVO participantVO, MultipartHttpServletRequest request);

	List<ParticipantVO> read(ParticipantVO participantVO);

	int getParticipantCnt(ParticipantVO participantVO);

	String delete(ParticipantVO participantVO);

	int getCheck(ParticipantVO participantVO);
}
