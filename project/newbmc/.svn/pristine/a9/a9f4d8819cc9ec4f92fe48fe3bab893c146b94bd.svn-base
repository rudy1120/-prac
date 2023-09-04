package egovframework.portal.sys.basic.stateMng.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import egovframework.portal.sys.basic.stateMng.vo.StateSearchMenuSysVO;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("stateSysMapper")
public interface StateSysMapper {

	/** @return 세션별 메뉴 로그 건수 */
//	public int procLogMenu_step1(StateSysVO inputVo);

	/** 메뉴 접근 로그 기록 */
//	public void procLogMenu_step2(StateSysVO inputVo);

	/** @return 해당 메뉴 로그 기록 최고 건수 */ 
//	public int procLogMenu_step3(StateSysVO inputVo);

	/** 금일 메뉴 접근 기록 */
//	public void procLogMenu_step4(StateSysVO inputVo);

	// 로그 작성 - site
//	public int procLogSite_step1(StateSysVO inputVo);

	// 로그 작성 - site
//	public void procLogSite_step2(StateSysVO inputVo);

	// 로그 작성 - site
//	public int procLogSite_step3(StateSysVO inputVo);

	// 로그 작성 - site
//	public void procLogSite_step4(StateSysVO inputVo);

	// 사이트별 메뉴목록 조회
	public List<StateSearchMenuSysVO> comboMenusForSite(Map<String, String> params);

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

	/* 20160517 J.Ryeon Lee ADD */

	/** @return 파일 다운로드 통계(연도별) */
	public List<Map<String, Integer>> selectFileDownStatsYearSM(StateSysVO inputVO);

	/** @return 파일 다운로드 통계(월별) */
	public List<Map<String, Integer>> selectFileDownStatsMonthSM(StateSysVO inputVO);

	/** @return 파일 다운로드 통계(일별) */
	public List<Map<String, Integer>> selectFileDownStatsDaySM(StateSysVO inputVO);

	/** @return 기간내 파일 다운로드 건수 총합  */
	public List<Map<String, Integer>> selectFileDownStatsPeriodSM(StateSysVO inputVO);

	/** @return 파일 다운로드 통계 요약(최소 연도/최대 연도/전체 다운로드 건수/금년 전체 다운로드 건수/금월 전체 다운로드 건수/금일 전체 다운로드 건수) */
	public Map<String, Integer> getTotalFileDownCount(StateSysVO inputVO);

	/** @return 기간내 게시글 열람 건수 총합 */
	public List<Map<String, Integer>> selectBbsViewStatsPeriodSM(StateSysVO inputVO);

	/** @return 게시글 열람 통계(일별) */
	public List<Map<String, Integer>> selectBbsViewStatsDaySM(StateSysVO inputVO);

	/** @return 게시글 열람 통계(월별) */
	public List<Map<String, Integer>> selectBbsViewStatsMonthSM(StateSysVO inputVO);

	/** @return 게시글 열람 통계(연도별) */
	public List<Map<String, Integer>> selectBbsViewStatsYearSM(StateSysVO inputVO);

	/** @return 게시글 열람 통계 요약(최소 연도/최대 연도/전체 열람 건수/금년 전체 열람 건수/금월 전체 열람 건수/금일 전체 열람 건수) */
	public Map<String, Integer> getTotalBbsViewCount(StateSysVO inputVO);


	// 많이 찾는 사이트
	public List<StateSysVO> selectPopularSiteYearSM(StateSysVO inputVo);

	public List<StateSysVO> selectPopularSiteMonthSM(StateSysVO inputVo);

	public List<StateSysVO> selectPopularSitePeriodSM(StateSysVO inputVo);

	// 많이 찾는 메뉴
	public List<StateSysVO> selectPopularMenuYearSM(StateSysVO inputVo);

	public List<StateSysVO> selectPopularMenuMonthSM(StateSysVO inputVo);

	public List<StateSysVO> selectPopularMenuPeriodSM(StateSysVO inputVo);

}
