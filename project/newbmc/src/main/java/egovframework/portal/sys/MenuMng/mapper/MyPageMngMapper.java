package egovframework.portal.sys.MenuMng.mapper;

import java.util.List;

import egovframework.portal.sys.MenuMng.vo.MenuChargeReqVO;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import egovframework.rte.psl.dataaccess.util.EgovMap;



@Mapper("myPageMngMapper")
public interface MyPageMngMapper {

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
	 * 마이페이지완료목록 조회
	 *
	 * @return
	 */
	public List<EgovMap> getMyMenuAckList(MenuVO searchVO);

	/**
	 * 메뉴정보 조회
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
	 * 담당자 인계 승인처리
	 *
	 * @param reqVO
	 */
	public void deleteReqMenuCharge(MenuChargeReqVO reqVO);





}