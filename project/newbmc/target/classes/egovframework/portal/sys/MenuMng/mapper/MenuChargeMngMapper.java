package egovframework.portal.sys.MenuMng.mapper;

import java.util.List;

import egovframework.portal.sys.MenuMng.vo.MenuChargeVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 메뉴 담당자 관리 Mapper
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 6. 16.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 6. 16.
 */
@Mapper("menuChargeMngMapper")
public interface MenuChargeMngMapper {

	/**
	 * 담당자 등록
	 *
	 * @param vo
	 */
	public void setInsertCharge(MenuChargeVO vo);

	/**
	 * 담당자 삭제
	 *
	 * @param vo
	 */
	public void setDeleteOld(MenuChargeVO vo);


	/**
	 * 담당자 아이디별 삭제
	 *
	 * @param vo
	 */
	public void setDeleteOldById(MenuChargeVO vo);

	/**
	 * 담당자 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<MenuChargeVO> getMenuCharge(MenuChargeVO vo);

}