package egovframework.portal.sys.basic.satisfaction.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.satisfaction.vo.SatisfactionMngVO;

/**
 * 만족도 관리 service
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014. 10. 21.	엄동건				최초 생성 및 코딩
 * 2017. 02. 22.	J.Ryeon Lee			만족도 내용을 평가 내용과 평점으로 제공하도록 기능 분기
 * </pre>
 *
 * @author 엄동건
 * @since 2014. 10. 21.
 */
public interface SatisfactionMngService {

	/** 만족도 중복 체크 */
	public int checkDuplSatisfaction(SatisfactionMngVO inputVo);

	/** 만족도 등록 */
	public void insertSatisfaction(SatisfactionMngVO inputVo);

	/** 사이트별 메뉴 목록 FETCH (평가 정보가 있는 메뉴만 조회) */
	public List<MenusMngVO> comboMenusForSite(String siteCode);

	/** 만족도 평균점 전체 건수 */
	public int getSatisfactionStatCnt(SatisfactionMngVO inputVO);

	/** 만족도 평균점 목록 */
	public List<SatisfactionMngVO> getSatisfactionStatList(SatisfactionMngVO inputVO);

	/** 만족도 평균점 목록(Map) */
	public List<Map<String, String>> getSatisfactionStatListAsMap(SatisfactionMngVO searchVO);

	/** 만족도 전체 건수 */
	public int getTotalCnt(SatisfactionMngVO searchVO);

	/** 만족도 목록 */
	public List<SatisfactionMngVO> getTotalList(SatisfactionMngVO searchVO);

	/** 만족도 목록(Map) */
	public List<Map<String, String>> getTotalListAsMap(SatisfactionMngVO searchVO);

}