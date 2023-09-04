package egovframework.portal.unit.bmc.event.mapper;

import java.util.List;

import egovframework.portal.sys.basic.event.vo.EventContentVO;
import egovframework.portal.sys.basic.event.vo.EventParticipantVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface EventMapper {

	int getTotalCnt(EventContentVO eventContentVO);

	List<EventContentVO> getList(EventContentVO eventContentVO);

	EventContentVO getContent(EventContentVO eventContentVO);

	void insert(EventParticipantVO insertVO);

	List<EventParticipantVO> getResult(EventParticipantVO eventParticipantVO);

	int getCheck(EventParticipantVO eventParticipantVO);

}
