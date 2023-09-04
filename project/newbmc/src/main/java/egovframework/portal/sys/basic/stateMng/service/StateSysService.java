package egovframework.portal.sys.basic.stateMng.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.portal.sys.basic.stateMng.vo.StateSearchMenuSysVO;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;

/**
 * 접속자 통계관리 service
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2015.01.22		엄동건				최초 생성 및 코딩
 * 2016.05.17		J.Ryeon Lee			첨부파일 다운로드 건수 통계 기능 추가
 * </pre>
 *
 * @author 개발팀 엄동건
 * @since 2015.01.22
 * @version 1.0
 */
public interface StateSysService {

	// 로그 작성 - site
//	public void procLogSite(StateSysVO inputVo);

	// 로그 작성 - menu
//	public void procLogMenu(StateSysVO inputVo);

	// 사이트별 메뉴목록 조회
	public List<StateSearchMenuSysVO> comboMenusForSite(String siteCode);

	// 토탈카운트 전체
	public HashMap<String, Integer> getTotalCount(StateSysVO inputVo);

	// 토탈카운트 사이트/메뉴
	public HashMap<String, Integer> getTotalCountSM(StateSysVO inputVo);

	// 통계 년도 사이트/메뉴
	public List<StateSysVO> selectStatsYearSM(StateSysVO inputVo);

	// 통계 월 사이트/메뉴
	public List<StateSysVO> selectStatsMonthSM(StateSysVO inputVo);

	// 통계 기간 사이트/메뉴
	public List<StateSysVO> selectStatsPeriodSM(StateSysVO inputVo);

	// 통계 유입사이트(년월기간 통합)
	public List<StateSysVO> selectStatsReferer(StateSysVO inputVo);

	// 통계 브라우저(년월기간 통합)
	public List<StateSysVO> selectStatsAgent(StateSysVO inputVo);

	// 통계 시각화 사이트맵 관련
	public List<StateSysVO> selectStatsVisualization(StateSysVO inputVo);

	/* 20160517 J.Ryeon LEE ADD */

	public List<Map<String, Integer>> selectFileDownStatsYearSM(StateSysVO inputVO);

	public List<Map<String, Integer>> selectFileDownStatsMonthSM(StateSysVO inputVO);

	public List<Map<String, Integer>> selectFileDownStatsDaySM(StateSysVO inputVO);

	public List<Map<String, Integer>> selectFileDownStatsPeriodSM(StateSysVO inputVO);

	public Map<String, Integer> getTotalFileDownCount(StateSysVO inputVO);

	public List<Map<String, Integer>> selectBbsViewStatsPeriodSM(StateSysVO inputVO);

	public List<Map<String, Integer>> selectBbsViewStatsDaySM(StateSysVO inputVO);

	public List<Map<String, Integer>> selectBbsViewStatsMonthSM(StateSysVO inputVO);

	public List<Map<String, Integer>> selectBbsViewStatsYearSM(StateSysVO inputVO);

	public Map<String, Integer> getTotalBbsViewCount(StateSysVO inputVO);

	public String searchTableByParams(int kind, int sType, StateSysVO inputVO, String year);

	public String searchQueryByParams(int kind, int sType, StateSysVO inputVO);

	// 많이 찾는 사이트
	public List<StateSysVO> selectPopularSiteYearSM(StateSysVO inputVo);

	public List<StateSysVO> selectPopularSiteMonthSM(StateSysVO inputVo);

	public List<StateSysVO> selectPopularSitePeriodSM(StateSysVO inputVo);

	// 많이 찾는 메뉴
	public List<StateSysVO> selectPopularMenuYearSM(StateSysVO inputVo);

	public List<StateSysVO> selectPopularMenuMonthSM(StateSysVO inputVo);

	public List<StateSysVO> selectPopularMenuPeriodSM(StateSysVO inputVo);

}
