package egovframework.portal.sys.basic.stateMng.mapper;

import java.util.List;

import egovframework.portal.sys.basic.stateMng.vo.StateExceptIPVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("stateExceptIPMngMapper")
public interface StateExceptIPMngMapper {

	public int getStateExceptIPListCnt(StateExceptIPVO searchVO);

	public List<StateExceptIPVO> getStateExceptIPList(StateExceptIPVO searchVO);

	public List<StateExceptIPVO> getStateExceptIPChkList(StateExceptIPVO searchVO);

	public StateExceptIPVO getStateExceptIP(StateExceptIPVO searchVO);

	public int getStateExceptIPDupChk(StateExceptIPVO searchVO);

	public void insertStateExceptIP(StateExceptIPVO insertVO);

	public void updateStateExceptIP(StateExceptIPVO updateVO);

	public void deleteStateExceptIP(StateExceptIPVO deleteVO);

}
