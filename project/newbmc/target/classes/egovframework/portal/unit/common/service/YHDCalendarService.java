package egovframework.portal.unit.common.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import egovframework.portal.unit.common.vo.DayVO;

/**
 * YHCalendar 일정 관리 SERVICE
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2016.08.22		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.08.22
 */
public interface YHDCalendarService {

	/** 해당월 일별 예약건수 조회 */
	public List<Map<String, Object>> getMonthlyDataList(DayVO searchVO) throws ParseException;

	/** 선택일 예약데이타 조회 */
	public List<Map<String, Object>> getDailyDataList(DayVO searchVO) throws ParseException;

	/** target table information fetch */
	public Map<String, String> getTableInfo(String keyUrl);

	/** recreate table */
	public void recreateCalendarTable(String tableName, String viewName);
}
