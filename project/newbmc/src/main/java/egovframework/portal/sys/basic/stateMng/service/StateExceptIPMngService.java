package egovframework.portal.sys.basic.stateMng.service;

import java.util.List;

import egovframework.portal.sys.basic.stateMng.vo.StateExceptIPVO;

/**
 * 관리자 통계 제외IP 관리를 위한 서비스 클래스
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2018.10.11		김장섭				최초 생성 및 코딩
 *
 * </pre>
 *
 * @author 김장섭
 * @since 2018.10.11
 */
public interface StateExceptIPMngService {

	public int getStateExceptIPListCnt(StateExceptIPVO searchVO);

	public List<StateExceptIPVO> getStateExceptIPList(StateExceptIPVO searchVO);

	public List<StateExceptIPVO> getStateExceptIPChkList(StateExceptIPVO searchVO);

	public StateExceptIPVO getStateExceptIP(StateExceptIPVO searchVO);

	public int getStateExceptIPDupChk(StateExceptIPVO searchVO);

	public void insertStateExceptIP(StateExceptIPVO insertVO);

	public void updateStateExceptIP(StateExceptIPVO updateVO);

	public void deleteStateExceptIP(StateExceptIPVO deleteVO);
}
