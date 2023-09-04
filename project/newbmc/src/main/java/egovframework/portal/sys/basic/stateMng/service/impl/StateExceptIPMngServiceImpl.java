package egovframework.portal.sys.basic.stateMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.basic.stateMng.mapper.StateExceptIPMngMapper;
import egovframework.portal.sys.basic.stateMng.service.StateExceptIPMngService;
import egovframework.portal.sys.basic.stateMng.vo.StateExceptIPVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

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
 *
 * </pre>
 */
@Service("stateExceptIPMngService")
public class StateExceptIPMngServiceImpl extends EgovAbstractServiceImpl implements
	StateExceptIPMngService {

	@Resource(name = "stateExceptIPMngMapper")
	private StateExceptIPMngMapper stateExceptIPMngMapper;

	public int getStateExceptIPListCnt(StateExceptIPVO searchVO) {
		return stateExceptIPMngMapper.getStateExceptIPListCnt(searchVO);
	}

	public List<StateExceptIPVO> getStateExceptIPList(StateExceptIPVO searchVO) {
		return stateExceptIPMngMapper.getStateExceptIPList(searchVO);
	}

	public List<StateExceptIPVO> getStateExceptIPChkList(StateExceptIPVO searchVO) {
		return stateExceptIPMngMapper.getStateExceptIPChkList(searchVO);
	}

	public StateExceptIPVO getStateExceptIP(StateExceptIPVO searchVO) {
		return stateExceptIPMngMapper.getStateExceptIP(searchVO);
	}

	public int getStateExceptIPDupChk(StateExceptIPVO searchVO) {
		return stateExceptIPMngMapper.getStateExceptIPDupChk(searchVO);
	}

	public void insertStateExceptIP(StateExceptIPVO insertVO) {
		stateExceptIPMngMapper.insertStateExceptIP(insertVO);
	}

	public void updateStateExceptIP(StateExceptIPVO updateVO) {
		stateExceptIPMngMapper.updateStateExceptIP(updateVO);
	}

	public void deleteStateExceptIP(StateExceptIPVO deleteVO) {
		stateExceptIPMngMapper.deleteStateExceptIP(deleteVO);
	}
}
