package egovframework.portal.sys.basic.satisfaction.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.MenuMng.vo.MenusMngVO;
import egovframework.portal.sys.basic.satisfaction.vo.SatisfactionMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 만족도 관리
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014. 10. 21.	엄동건				최초 생성 및 코딩
 * </pre>
 *
 * @author 엄동건
 * @since 2014. 10. 21.
 */
@Mapper
public interface SatisfactionMngMapper {

	/** 만족도 중복 체크 */
	public int checkDuplSatisfaction(SatisfactionMngVO inputVo);

	/** 만족도 평점 목록 전체(Map) */
	public List<Map<String, String>> getSatisfactionStatListAsMap(SatisfactionMngVO searchVO);

	/** 만족도 등록 */
	public void insertSatisfaction(SatisfactionMngVO inputVo);

	/** 사이트별 메뉴 목록(평가정보가 있는 메뉴만 조회토록 함) */
	public List<MenusMngVO> comboMenusForSite(String siteCode);

	/** 만족도 평점 전체 건수 */
	public int getSatisfactionStatCnt(SatisfactionMngVO inputVO);

	/** 만족도 평점 목록 */
	public List<SatisfactionMngVO> getSatisfactionStatList(SatisfactionMngVO inputVO);

	/** 만족도 평가 내용 전체 건수 */
	public int getTotalCnt(SatisfactionMngVO searchVO);

	/** 만족도 평가 내용 목록 */
	public List<SatisfactionMngVO> getTotalList(SatisfactionMngVO searchVO);

	/** 만족도 평가 내용 전체 목록(Map) */
	public List<SatisfactionMngVO> getTotalListAsMap(SatisfactionMngVO searchVO);

}