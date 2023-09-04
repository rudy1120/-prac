package egovframework.portal.unit.common.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.unit.common.vo.DayVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface YHDCalendarMapper {

	/** 해당월 일별 예약건수 조회 */
	public List<Map<String, Object>> getMonthlyDataList(DayVO searchVO);

	/** 예약데이타 조회 */
	public List<Map<String, Object>> getDailyDataList(DayVO searchVO);

	public Map<String, String> getTableInfo(String keyUrl);

	public void dropTable(Map<String, String> params);

	public void copyTable(Map<String, String> params);

	public void getMonthlyDataListCall(DayVO searchVO);


}
