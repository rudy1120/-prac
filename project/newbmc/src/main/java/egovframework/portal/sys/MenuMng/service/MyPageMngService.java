package egovframework.portal.sys.MenuMng.service;

import egovframework.portal.sys.MenuMng.vo.MenuChargeReqVO;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.rte.psl.dataaccess.util.EgovMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 관리자 - 마이페이지관리 서비스 클래스
 *
 * @author 개발팀 김장섭
 * @since 2016-11-20
 * @version 1.0
 * @see
 */

public interface MyPageMngService {

	/**
	 * 마이페이지 수 조회
	 *
	 * @return
	 */
	public int getMyMenuCnt(MenuVO searchVO);

	/**
	 * 마이페이지목록 조회
	 *
	 * @return
	 */
	public List<EgovMap> getMyMenuList(MenuVO searchVO);


	/**
	 * 마이페이지 완료목록 조회
	 *
	 * @return
	 */
	public List<EgovMap> getMyMenuAckList(MenuVO searchVO);

	/**
	 * 마이페이지 조회
	 *
	 * @param searchVO
	 * @return
	 */
	public MenuChargeReqVO getMyMenuReq(MenuChargeReqVO searchVO);



	/**
	 * 담당자 인계 요청처리
	 *
	 * @param reqVO
	 */
	public void reqMenuChargeMove(MenuChargeReqVO reqVO);

	/**
	 * 담당자 인계 취소처리
	 *
	 * @param reqVO
	 */
	public void reqMenuChargeCancel(MenuChargeReqVO reqVO);


	/**
	 * 담당자 인계 승인처리
	 *
	 * @param reqVO
	 */
	public void reqMenuChargeAck(MenuChargeReqVO reqVO);



	/**
	 * 담당자 인계 삭제
	 *
	 * @param reqVO
	 */
	public void deleteReqMenuCharge(MenuChargeReqVO reqVO);





}