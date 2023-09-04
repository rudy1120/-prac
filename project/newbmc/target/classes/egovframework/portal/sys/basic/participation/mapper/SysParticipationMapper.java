package egovframework.portal.sys.basic.participation.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.unit.bmc.participation.vo.ParticipantVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SysParticipationMapper {

	int getTotalCnt(PartContVO searchVO);

	List<PartContVO> getList(PartContVO searchVO);

	void insert(PartContVO insertVO);

	void update(PartContVO updateVO);

	PartContVO getEntity(PartContVO searchVO);

	void delete(PartContVO deleteVO);

	PartContVO getContent(int idx);

	int getParticipantCnt(ParticipantVO searchVO);

	List<ParticipantVO> getParticipantList(ParticipantVO searchVO);
}
