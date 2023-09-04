package egovframework.portal.unit.bmc.event.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.basic.event.vo.EventParticipantVO;

public interface EventService {

	int getTotalCnt(EventContentVO eventContentVO);

	List<EventContentVO> getList(EventContentVO eventContentVO);

	EventContentVO getContent(EventContentVO eventContentVO);

	String insert(EventParticipantVO eventParticipantVO, MultipartHttpServletRequest request);

	List<EventParticipantVO> getResult(EventParticipantVO eventParticipantVO);

	int getCheck(EventParticipantVO eventParticipantVO);
}
