package egovframework.portal.sys.basic.event.mapper;

import java.util.List;

import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.basic.event.vo.EventParticipantVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SysEventMapper {

	int getTotalCnt(EventContentVO searchVO);

	List<EventContentVO> getList(EventContentVO searchVO);

	EventContentVO getEntity(EventContentVO searchVO);

	void insert(EventContentVO insertVO);

	void delete(EventContentVO deleteVO);

	void update(EventContentVO updateVO);

	EventContentVO getContent(EventContentVO eventContentVO);

	List<EventParticipantVO> getParList(EventParticipantVO eventParticipantVO);

	void loc(EventContentVO locVO);

	void locEnd(EventContentVO searchVO);
}
