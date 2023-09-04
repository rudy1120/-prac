package egovframework.portal.sys.MenuMng.service;

import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.promotion.PromotionType;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 사이트 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.10.07		엄동건				최초 생성 및 코딩
 * 2017.07.18		J.Ryeon Lee			홍보 정보 관련 메소드 추가
 * </pre>
 *
 * @author 엄동건
 * @since 2014.10.07
 */
public interface SiteMngService {

	public int getSiteListCnt(MenusMngVO searchVO);

	public List<MenusMngVO> getSiteList(MenusMngVO searchVO);

	public MenusMngVO getSite(MenusMngVO searchVO);

	public void insertSite(MenusMngVO insertVO);

	public void updateSite(MenusMngVO updateVO);

	public void deleteSite(MenusMngVO deleteVO);

	public List<MenusMngVO> getSiteCodeList(HttpServletRequest request);

	public int getSiteIdx(String siteCode);

	public List<MenusMngVO> getSiteCodeListWhere(MenusMngVO searchVO);

	public MenusMngVO getSiteBySiteCode(String siteCode);

	/** 사이트 목록(홍보 정보 사용 여부 관련) */
	public List<MenusMngVO> getListByPrmtType(PromotionType type);
}
