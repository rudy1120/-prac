package egovframework.portal.unit.common.vo;

import java.util.Map;

import egovframework.portal.common.vo.CommonVO;

public class DayVO extends CommonVO {

	private String start = "";
	private String end = "";
	private Map<String, String> tableInfo = null;
	private String category = "";
	private String category2 = "";

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public Map<String, String> getTableInfo() {
		return tableInfo;
	}

	public void setTableInfo(Map<String, String> tableInfo) {
		this.tableInfo = tableInfo;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory2() {
		return category2;
	}

	public void setCategory2(String category2) {
		this.category2 = category2;
	}

}
