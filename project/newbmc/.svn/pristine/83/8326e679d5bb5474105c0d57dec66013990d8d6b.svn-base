package egovframework.portal.sys.basic.event.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.basic.event.vo.EventParticipantVO;

public interface SysEventService {

	int getTotalCnt(EventContentVO searchVO);

	List<EventContentVO> getList(EventContentVO searchVO);

	EventContentVO getEntity(EventContentVO searchVO);

	String insert(EventContentVO searchVO, MultipartHttpServletRequest request);

	String update(EventContentVO searchVO, MultipartHttpServletRequest request);

	String delete(EventContentVO searchVO);

	EventContentVO getContent(EventContentVO eventContentVO);

	List<EventParticipantVO> getParList(EventParticipantVO eventParticipantVO);

	String loc(EventContentVO searchVO);

	String loc2(EventContentVO searchVO);

}
