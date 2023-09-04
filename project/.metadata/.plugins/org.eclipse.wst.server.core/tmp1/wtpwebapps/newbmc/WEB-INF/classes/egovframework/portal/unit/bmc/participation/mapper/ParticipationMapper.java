package egovframework.portal.unit.bmc.participation.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.participation.vo.PartContVO;
import egovframework.portal.unit.bmc.participation.vo.ParticipantVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ParticipationMapper {

	public List<PartContVO> getList(PartContVO partContVO);

	public int getTotalCnt(PartContVO partContVO);

	public PartContVO getContent(PartContVO partContVO);

	public void setViewCount(PartContVO partContVO);

	public void insert(ParticipantVO insertVO);

	public List<ParticipantVO> read(ParticipantVO participantVO);

	public int getParticipantCnt(ParticipantVO participantVO);

	public void delete(ParticipantVO deleteVO);

	public int getCheck(ParticipantVO participantVO);
}
