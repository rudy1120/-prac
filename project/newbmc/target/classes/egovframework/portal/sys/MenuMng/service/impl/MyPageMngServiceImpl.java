package egovframework.portal.sys.MenuMng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.MenuMng.mapper.MenuChargeMngMapper;
import egovframework.portal.sys.MenuMng.mapper.MenuMngMapper;
import egovframework.portal.sys.MenuMng.mapper.MyPageMngMapper;
import egovframework.portal.sys.MenuMng.service.MyPageMngService;
import egovframework.portal.sys.MenuMng.vo.MenuChargeReqVO;
import egovframework.portal.sys.MenuMng.vo.MenuChargeVO;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.MenuMng.vo.MenusChargeHistoryMngVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.psl.dataaccess.util.EgovMap;

/**
 * 관리자 - 마이페이지관리 서비스 클래스
 *
 * <pre>
 * &lt;&lt;개정이력(Modification Information)&gt;&gt;
 * 2016.11.20 김장섭
 * 최초 생성
 *
 * </pre>
 *
 * @author 개발팀 김장섭
 * @since 2016.11.20
 * @version 1.0
 */
@Service("myPageMngService")
public class MyPageMngServiceImpl extends EgovAbstractServiceImpl implements MyPageMngService  {

	@Resource(name = "myPageMngMapper")
	private MyPageMngMapper myPageMngMapper;

	@Resource(name = "menuChargeMngMapper")
	private MenuChargeMngMapper menuChargeMngMapper;

	@Resource(name = "menuMngMapper")
	private MenuMngMapper menuMngMapper;



	/**
	 * 마이페이지 수 조회
	 *
	 * @return
	 */
	public int getMyMenuCnt(MenuVO searchVO) {
		int result = myPageMngMapper.getMyMenuCnt(searchVO);
		return result;
	}

	/**
	 * 마이페이지목록 조회
	 *
	 * @return
	 */
	public List<EgovMap> getMyMenuList(MenuVO searchVO) {

		return myPageMngMapper.getMyMenuList(searchVO);
	}

	/**
	 * 마이페이지완료목록 조회
	 *
	 * @return
	 */
	public List<EgovMap> getMyMenuAckList(MenuVO searchVO) {

		return myPageMngMapper.getMyMenuAckList(searchVO);
	}

	/**
	 * 마이페이지 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public MenuChargeReqVO getMyMenuReq(MenuChargeReqVO searchVO) {
		return myPageMngMapper.getMyMenuReq(searchVO);
	}


	/**
	 * 담당자 인계 요청처리
	 *
	 * @param reqVO
	 */
	public void reqMenuChargeMove(MenuChargeReqVO reqVO) {
		myPageMngMapper.reqMenuChargeMove(reqVO);
	}

	/**
	 * 담당자 인계 취소처리
	 *
	 * @param reqVO
	 */
	public void reqMenuChargeCancel(MenuChargeReqVO reqVO) {
		myPageMngMapper.reqMenuChargeCancel(reqVO);
	}

	/**
	 * 담당자 인계 승인처리
	 *
	 * @param reqVO
	 */
	public void reqMenuChargeAck(MenuChargeReqVO reqVO) {
		myPageMngMapper.reqMenuChargeAck(reqVO);

		//담당자 메뉴정보 업데이트
		MenuChargeVO deleteVO = new MenuChargeVO(reqVO.getSite_code(), reqVO.getMid(), reqVO.getReq_charge_id(), reqVO.getReq_charge_fnm(), reqVO.getReq_charge_dep_code(), reqVO.getReq_charge_dep_nm(), reqVO.getReq_charge_tel());
		MenuChargeVO updateVO = new MenuChargeVO(reqVO.getSite_code(), reqVO.getMid(), reqVO.getMove_charge_id(), reqVO.getMove_charge_fnm(), reqVO.getMove_charge_dep_code(), reqVO.getMove_charge_dep_nm(), reqVO.getMove_charge_tel());

		menuChargeMngMapper.setDeleteOldById(deleteVO);
		menuChargeMngMapper.setInsertCharge(updateVO);

		MenusChargeHistoryMngVO mChargeHistoryVO = new MenusChargeHistoryMngVO();

		mChargeHistoryVO.setSiteCode(reqVO.getSite_code());
		mChargeHistoryVO.setMid(reqVO.getMid());
		mChargeHistoryVO.setChargeId(reqVO.getMove_charge_id());
		mChargeHistoryVO.setChargeFnm(reqVO.getMove_charge_fnm());
		mChargeHistoryVO.setChargeDepCode(reqVO.getMove_charge_dep_code());
		mChargeHistoryVO.setChargeDepNm(reqVO.getMove_charge_dep_nm());

		menuMngMapper.insertMenuChargeHistory(mChargeHistoryVO);
	}



	/**
	 * 담당자 인계 삭제
	 *
	 * @param reqVO
	 */
	public void deleteReqMenuCharge(MenuChargeReqVO reqVO) {

		myPageMngMapper.deleteReqMenuCharge(reqVO);
	}


}
