package egovframework.portal.sys.basic.stateMng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.sys.basic.stateMng.mapper.StateSysMapper;
import egovframework.portal.sys.basic.stateMng.service.StateSysService;
import egovframework.portal.sys.basic.stateMng.vo.StateSearchMenuSysVO;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 관리자 - 통계서비스
 *
 * @author 개발팀 엄동건
 * @since 2015-01-22
 * @version 1.0
 */
@Service("stateSysService")
public class StateSysServiceImpl extends EgovAbstractServiceImpl implements StateSysService {

	@Resource(name = "stateSysMapper")
	private StateSysMapper stateSysMapper;

	// 로그 작성 - site
//	public void procLogSite(StateSysVO inputVo) {
//		// stateSysMapper.procLogSite(inputVo);
//
//		if (inputVo != null) {
//			// 중복조회
//			int cntDupl = stateSysMapper.procLogSite_step1(inputVo);
//			if (cntDupl == 0) {
//				// 로그등록
//				inputVo.setKeyTime(System.currentTimeMillis());
//				stateSysMapper.procLogSite_step2(inputVo);
//				// 토탈카운트 조회
//				int cntTotal = stateSysMapper.procLogSite_step3(inputVo);
//				inputVo.setCntTotal(cntTotal);
//				// 당일 토탈카운트 insert or update
//				stateSysMapper.procLogSite_step4(inputVo);
//			}
//		}
//	}

	// 로그 작성 - menu
//	public void procLogMenu(StateSysVO inputVo) {
//		// stateSysMapper.procLogMenu(inputVo);
//		// 중복조회
//		if (inputVo != null) {
//			int cntDupl = stateSysMapper.procLogMenu_step1(inputVo);
//
//			if (cntDupl == 0) {
//				// 로그등록
//				inputVo.setKeyTime(System.currentTimeMillis());
//				stateSysMapper.procLogMenu_step2(inputVo);
//				// 토탈카운트 조회
//				int cntTotal = stateSysMapper.procLogMenu_step3(inputVo);
//				inputVo.setCntTotal(cntTotal);
//				// 당일 토탈카운트 insert or update
//				stateSysMapper.procLogMenu_step4(inputVo);
//			}
//		}
//	}

	// 사이트별 메뉴목록 조회
	public List<StateSearchMenuSysVO> comboMenusForSite(String siteCode) {
		Map<String, String> params = new HashMap<>();
		params.put("siteCode", siteCode);
		return stateSysMapper.comboMenusForSite(params);
	}

	// 토탈카운트 전체
	public HashMap<String, Integer> getTotalCount(StateSysVO inputVo) {
		return stateSysMapper.getTotalCount(inputVo);
	}

	// 토탈카운트 사이트/메뉴
	public HashMap<String, Integer> getTotalCountSM(StateSysVO inputVo) {
		return stateSysMapper.getTotalCountSM(inputVo);
	}

	// 통계 년도 사이트/메뉴
	public List<StateSysVO> selectStatsYearSM(StateSysVO inputVo) {
		return stateSysMapper.selectStatsYearSM(inputVo);
	}

	// 통계 월 사이트/메뉴
	public List<StateSysVO> selectStatsMonthSM(StateSysVO inputVo) {
		return stateSysMapper.selectStatsMonthSM(inputVo);
	}

	// 통계 기간 사이트/메뉴
	public List<StateSysVO> selectStatsPeriodSM(StateSysVO inputVo) {
		return stateSysMapper.selectStatsPeriodSM(inputVo);
	}

	// 통계 유입사이트(년월기간 통합)
	public List<StateSysVO> selectStatsReferer(StateSysVO inputVo) {
		return stateSysMapper.selectStatsReferer(inputVo);
	}

	// 통계 브라우저(년월기간 통합)
	public List<StateSysVO> selectStatsAgent(StateSysVO inputVo) {
		return stateSysMapper.selectStatsAgent(inputVo);
	}

	// 통계 시각화 사이트맵 관련
	public List<StateSysVO> selectStatsVisualization(StateSysVO inputVo) {
		return stateSysMapper.selectStatsVisualization(inputVo);
	}

	/* 20160517 J.Ryeon Lee ADD */

	@Override
	public List<Map<String, Integer>> selectFileDownStatsYearSM(StateSysVO inputVO) {
		return stateSysMapper.selectFileDownStatsYearSM(inputVO);
	}

	@Override
	public List<Map<String, Integer>> selectFileDownStatsMonthSM(StateSysVO inputVO) {
		return stateSysMapper.selectFileDownStatsMonthSM(inputVO);
	}

	@Override
	public List<Map<String, Integer>> selectFileDownStatsDaySM(StateSysVO inputVO) {
		return stateSysMapper.selectFileDownStatsDaySM(inputVO);
	}

	@Override
	public List<Map<String, Integer>> selectFileDownStatsPeriodSM(StateSysVO inputVO) {
		return stateSysMapper.selectFileDownStatsPeriodSM(inputVO);
	}

	@Override
	public Map<String, Integer> getTotalFileDownCount(StateSysVO inputVO) {
		return stateSysMapper.getTotalFileDownCount(inputVO);
	}

	@Override
	public List<Map<String, Integer>> selectBbsViewStatsPeriodSM(StateSysVO inputVO) {
		return stateSysMapper.selectBbsViewStatsPeriodSM(inputVO);
	}

	@Override
	public List<Map<String, Integer>> selectBbsViewStatsDaySM(StateSysVO inputVO) {
		return stateSysMapper.selectBbsViewStatsDaySM(inputVO);
	}

	@Override
	public List<Map<String, Integer>> selectBbsViewStatsMonthSM(StateSysVO inputVO) {
		return stateSysMapper.selectBbsViewStatsMonthSM(inputVO);
	}

	@Override
	public List<Map<String, Integer>> selectBbsViewStatsYearSM(StateSysVO inputVO) {
		return stateSysMapper.selectBbsViewStatsYearSM(inputVO);
	}

	@Override
	public Map<String, Integer> getTotalBbsViewCount(StateSysVO inputVO) {
		return stateSysMapper.getTotalBbsViewCount(inputVO);
	}

	@Override
	public String searchQueryByParams(int kind, int sType, StateSysVO inputVO) {
		String searchQuery = null;
		switch (kind) {
			case 1:
				searchQuery = " log_sitecode='" + inputVO.getSiteCode() + "'";
				searchQuery += " and log_mId='" + (StringUtil.isNotBlank(inputVO.getMenuId()) ? inputVO.getMenuId() : "0100000000") + "'";
				break;
			case 2:
			case 3:
				/* 관련 프로시저 자동 생성 처리하려니 오류가 발생하길래, 기생성해둔 한도 내에서 작동하도록 구현 2015~2019 */
				switch (sType) {
					case 1:
						searchQuery = " date_format(log_date, '%Y') ='" + inputVO.getYear() + "' ";
						break;
					case 2:
						searchQuery = " date_format(log_date, '%Y%m') ='" + inputVO.getYear() + inputVO.getMonth() + "' ";
						break;
					case 3:
						searchQuery = " log_date between date_format('" + inputVO.getDateStart() + "','%Y-%m-%d') and date_format('" + inputVO.getDateEnd() + "','%Y-%m-%d') ";
						break;
					default:
						break;
				}
				break;
			default: // case 0
				searchQuery = " 1 = 1";
				if (StringUtil.isNotBlank(inputVO.getSiteCode())) {
					searchQuery += " AND log_sitecode='" + inputVO.getSiteCode() + "'";
				}
				break;
		}

		return searchQuery;
	}

	@Override
	public String searchTableByParams(int kind, int sType, StateSysVO inputVO, String year) {
		String table = null;

		switch (kind) {
			case 1:
				table = "state_today_menu";
				break;
			case 2:
			case 3:

				int yearMin = 2015;
				int yearMax = 2022;
				int curMin = Integer.parseInt(sType == 3 ? inputVO.getDateStart().substring(0, 4) : year);
				int curMax = Integer.parseInt(sType == 3 ? inputVO.getDateEnd().substring(0, 4) : year);

				if (curMin < yearMin) {
					curMin = yearMin;
				}
				if (curMax > yearMax) {
					curMax = yearMax;
				}

				table = "";
				if (curMin == curMax) {
					table += " state_log_site_" + curMin + " ";
				} else {
					table += "(";
					boolean _bool = false;
					for (; curMin <= curMax; curMin++) {
						if (_bool) {
							table += " union all ";
						}
						table += " select referer, log_date,useragent from state_log_site_" + curMin;
						_bool = true;
					}
					table += ")";
				}

				break;
			default: // case 0, 2
				table = "state_today_site";
				break;
		}

		return table;
	}


	// 많이 찾는 사이트
	public List<StateSysVO> selectPopularSiteYearSM(StateSysVO inputVo) {
		return stateSysMapper.selectPopularSiteYearSM(inputVo);
	}

	public List<StateSysVO> selectPopularSiteMonthSM(StateSysVO inputVo) {
		return stateSysMapper.selectPopularSiteMonthSM(inputVo);
	}

	public List<StateSysVO> selectPopularSitePeriodSM(StateSysVO inputVo) {
		return stateSysMapper.selectPopularSitePeriodSM(inputVo);
	}

	// 많이 찾는 메뉴
	public List<StateSysVO> selectPopularMenuYearSM(StateSysVO inputVo) {
		return stateSysMapper.selectPopularMenuYearSM(inputVo);
	}

	public List<StateSysVO> selectPopularMenuMonthSM(StateSysVO inputVo) {
		return stateSysMapper.selectPopularMenuMonthSM(inputVo);
	}

	public List<StateSysVO> selectPopularMenuPeriodSM(StateSysVO inputVo) {
		return stateSysMapper.selectPopularMenuPeriodSM(inputVo);
	}

}
