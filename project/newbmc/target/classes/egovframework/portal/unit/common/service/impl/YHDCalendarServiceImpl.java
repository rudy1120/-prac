package egovframework.portal.unit.common.service.impl;

import java.sql.Clob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.common.mapper.YHDCalendarMapper;
import egovframework.portal.unit.common.service.YHDCalendarService;
import egovframework.portal.unit.common.vo.DayVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.TUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service
public class YHDCalendarServiceImpl extends EgovAbstractServiceImpl implements YHDCalendarService {

	@Autowired
	private YHDCalendarMapper calendarMapper;

	private final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<Map<String, Object>> getMonthlyDataList(DayVO searchVO) throws ParseException {
		searchVO.setEnd(calEnd(searchVO.getStart()));
		//mysql 임시 칼렌더 테이블 생성
		calendarMapper.getMonthlyDataListCall(searchVO);
		return calendarMapper.getMonthlyDataList(searchVO);
	}

	@Override
	public List<Map<String, Object>> getDailyDataList(DayVO searchVO) throws ParseException {
		searchVO.setEnd(searchVO.getEnd());

//		Map<String, String> tableInfo = searchVO.getTableInfo();
		List<Map<String, Object>> rtn = calendarMapper.getDailyDataList(searchVO);

		return rtn;
	}

	private String calEnd(String start) throws ParseException {
		Calendar end = Calendar.getInstance();
		end.setTime(FORMATTER.parse(start));
		end.set(Calendar.DAY_OF_MONTH, end.get(Calendar.DAY_OF_MONTH) + 60); // 60일 범위로 고정
		return FORMATTER.format(end.getTime());
	}

	@Override
	public Map<String, String> getTableInfo(String keyUrl) {
		Map<String, String> rtn = StringUtil.isNotBlank(keyUrl) && !TUtil.isXSS(keyUrl) //
			? calendarMapper.getTableInfo(keyUrl) : null;

		if (rtn != null) {

			if (StringUtil.isBlank(rtn.get("colEndColName"))) {
				rtn.put("colEndColName", rtn.get("colStartColName"));
			}

			try {
				rtn.put("colHeaderGuide", StringUtil.clobToString((Clob) (Object) rtn.get("colHeaderGuide")));
			} catch (Exception e) {
				rtn.put("colHeaderGuide", null);
			}
		}

		if (rtn != null && StringUtil.isBlank(rtn.get("colEndColName"))) {
			rtn.put("colEndColName", rtn.get("colStartColName"));
		}

		return rtn;
	}

	@Override
	public void recreateCalendarTable(String tableName, String viewName) {
		Map<String, String> params = new HashMap<>();
		params.put("tableName", tableName);
		calendarMapper.dropTable(params);
		params.put("viewName", viewName);
		calendarMapper.copyTable(params);
	}

}