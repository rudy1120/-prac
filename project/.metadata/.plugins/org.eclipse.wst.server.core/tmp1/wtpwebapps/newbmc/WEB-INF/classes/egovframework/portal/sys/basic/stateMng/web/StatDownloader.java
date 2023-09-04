package egovframework.portal.sys.basic.stateMng.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import egovframework.portal.sys.MenuMng.mapper.MenuMngMapper;
import egovframework.portal.sys.MenuMng.mapper.SiteMngMapper;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.basic.stateMng.service.StateSysService;
import egovframework.portal.sys.basic.stateMng.vo.StateSysVO;
import egovframework.portal.util.StringUtil;

/**
 * 통계 정보 다운로더
 *
 * @author J.Ryeon Lee
 * @since 2016.05.18
 */
public class StatDownloader extends AbstractExcelView {

	@Autowired
	protected MenuMngService menuMngService;
	@Autowired
	protected StateSysService stateService;
	@Resource(name = "siteMngMapper")
	protected SiteMngMapper siteMngMapper;
	@Resource(name = "menuMngMapper")
	private MenuMngMapper menuMngMapper;

	private final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyyMMdd_HHmmss");

	private Boolean isDateRangeSelect(StateSysVO inputVO) {
		return inputVO.getsType() == 3 || ((inputVO.getKind() == 4 || inputVO.getKind() == 5) && StringUtil.isNotBlank(inputVO.getDateStart()));
	}

	private Boolean isDaySelect(StateSysVO inputVO) {
		return inputVO.getsType() == 2;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		StateSysVO inputVO = (StateSysVO) model.get("inputVo");

		String header = null;
		if (isDateRangeSelect(inputVO)) {
			header = inputVO.getDateStart() + "부터 " + inputVO.getDateEnd() + "간 ";
		} else if (isDaySelect(inputVO)) {
			header = inputVO.getYear() + "년 " + inputVO.getMonth() + "월 ";
		} else {
			header = StringUtil.isBlank(inputVO.getYear()) ? "전체 연도 " : inputVO.getYear() + "년 ";
		}

		String fileName = null;
		HSSFSheet excelSheet = null;

		StateSysVO searchVO = new StateSysVO();
		searchVO.setKind(inputVO.getKind());

		List<String> headerColumnList = defaultHeaderColumnList();
		Object results = model.get("results");

		switch (inputVO.getKind()) {
			case 0: // 사이트
				headerColumnList.clear();
				headerColumnList.add("일자");
				headerColumnList.add("이용자수");

				header += (StringUtil.isNotBlank(inputVO.getSiteCode()) ? siteMngMapper.getSiteBySiteCode(inputVO.getSiteCode()).getSiteName() : "전체 사이트") + " 방문자 통계";
				excelSheet = workbook.createSheet(header);
				excelSheet.createRow(0).createCell(0).setCellValue(header);
				setExcelHeader(workbook, excelSheet, header, headerColumnList);
				setVerticalExcelRows(workbook, excelSheet, (List<StateSysVO>) results, inputVO);
				break;
			case 1: // 메뉴
				headerColumnList.clear();
				headerColumnList.add("일자");
				headerColumnList.add("이용자수");

				Map<String, String> params = new HashMap<>();
				params.put("siteCode", inputVO.getSiteCode());
				params.put("mId", inputVO.getMenuId());

				header += siteMngMapper.getSiteBySiteCode(inputVO.getSiteCode()).getSiteName() + " " + //
					menuMngMapper.getMenuName(params) + //
					" 메뉴 방문자 통계";
				fileName = header + FORMATTER.format(new Date()) + ".xls";
				excelSheet = workbook.createSheet(header);
				excelSheet.createRow(excelSheet.getLastRowNum()).createCell(0).setCellValue(header);
				setExcelHeader(workbook, excelSheet, header, headerColumnList);
				setVerticalExcelRows(workbook, excelSheet, (List<StateSysVO>) results, inputVO);
				break;
			case 2: // 외부유입
				headerColumnList.clear();
				headerColumnList.add("외부유입 URL");
				headerColumnList.add("방문자수");
				header += "외부유입 방문자 통계";

				excelSheet = workbook.createSheet(header);
				excelSheet.createRow(0).createCell(0).setCellValue(header);
				setExcelHeader(workbook, excelSheet, header, headerColumnList);
				setVerticalExcelRows(workbook, excelSheet, (List<StateSysVO>) results, inputVO);
				break;
			case 3: // 브라우저
				headerColumnList.clear();
				headerColumnList.add("브라우저");
				headerColumnList.add("방문자수");

				header += "브라우저별 방문자 통계";
				excelSheet = workbook.createSheet(header);
				excelSheet.createRow(0).createCell(0).setCellValue(header);
				setExcelHeader(workbook, excelSheet, header, headerColumnList);
				setVerticalExcelRows(workbook, excelSheet, (List<StateSysVO>) results, inputVO);
				break;
			case 4: // 첨부파일
				headerColumnList.clear();
				headerColumnList.add("일자");
				headerColumnList.add("이용자수");

				header += "첨부파일 이용자 통계";
				excelSheet = workbook.createSheet(header);
				excelSheet.createRow(0).createCell(0).setCellValue(header);
				setExcelHeader(workbook, excelSheet, header, headerColumnList);
				setVerticalExcelRows2(workbook, excelSheet, (List<Map<String, Integer>>) results, inputVO);
				break;
			case 5: // 게시물
				headerColumnList.clear();
				headerColumnList.add("일자");
				headerColumnList.add("이용자수");

				header += "게시물 이용자 통계";
				excelSheet = workbook.createSheet(header);
				excelSheet.createRow(0).createCell(0).setCellValue(header);
				setExcelHeader(workbook, excelSheet, header, headerColumnList);
				setVerticalExcelRows2(workbook, excelSheet, (List<Map<String, Integer>>) results, inputVO);
				break;
			default:
				break;
		}

		fileName = header + FORMATTER.format(new Date()) + ".xls";
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");

//		HSSFSheet excelSheet = workbook.createSheet(header);
//		setExcelHeader(workbook, excelSheet, header, tableDef);
//		List<Map<String, String>> result = dynamicDataMngService.getAllList(tableDef.getTableName());
//		setExcelRows(workbook, excelSheet, result, tableDef);
	}

//	private List<StateSysVO> castToStateSysVOList(List<Map<String, Integer>> list) {
//		List<StateSysVO> rtn = new ArrayList<StateSysVO>();
//		StateSysVO element = null;
//		for (Map<String, Integer> el : list) {
//			element = new StateSysVO();
//			element.setStatsLabel(Integer.parseInt(String.valueOf(el.get("DMONTH"))) + "");
//			element.setStatsCount(Integer.parseInt(String.valueOf(el.get("CNT"))));
//			rtn.add(element);
//		}
//
//		return rtn;
//	}

	private List<String> defaultHeaderColumnList() {
		List<String> rtn = new ArrayList<String>();
		rtn.add("1월");
		rtn.add("2월");
		rtn.add("3월");
		rtn.add("4월");
		rtn.add("5월");
		rtn.add("6월");
		rtn.add("7월");
		rtn.add("8월");
		rtn.add("9월");
		rtn.add("10월");
		rtn.add("11월");
		rtn.add("12월");

		return rtn;
	}

	public void createHeaderColorCell(HSSFWorkbook workbook, HSSFRow excelHeader, HSSFSheet excelSheet, int cellIndex, String cellValue, int cWidth, int cHeight) {
		HSSFCellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(HSSFColor.LIGHT_TURQUOISE.index);
		headerStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		HSSFCell cell = excelHeader.createCell(cellIndex);
		cell.setCellValue(cellValue);
		cell.setCellStyle(headerStyle);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 개행 문자 적용

		excelSheet.setColumnWidth(cellIndex, cWidth * 30);
	}

	public void setExcelHeader(HSSFWorkbook workbook, HSSFSheet excelSheet, String header, List<String> columnNames, String... subHeaders) {
		HSSFRow excelHeader = excelSheet.createRow(excelSheet.getLastRowNum() + 1);

		int subHeaderLen = 0;
		if (subHeaders != null) {
			subHeaderLen = subHeaders.length;
			for (int i = 0; i < subHeaderLen; i++) {
				createHeaderColorCell(workbook, excelHeader, excelSheet, i, subHeaders[i], 200, 30);
			}
		}

		for (int i = 0; i < columnNames.size(); i++) {
			createHeaderColorCell(workbook, excelHeader, excelSheet, subHeaderLen + i, columnNames.get(i), 200, 30);
		}
	}

	private void setVerticalExcelRows(HSSFWorkbook workbook, HSSFSheet excelSheet, List<StateSysVO> result, StateSysVO inputVO) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);

		String format = null;
		if (inputVO.getsType() == 3) {
			//
		} else if (inputVO.getsType() == 2) {
			format = inputVO.getYear() + "년 " + inputVO.getMonth() + "월 %s일";
		} else {
			format = inputVO.getYear() + "년 " + "%s월";
		}

		if (inputVO.getKind() == 2 || inputVO.getKind() == 3) {
			format = null;
		}

		HSSFRow excelRow = null;

		// 통계값
		int i = 0;
		for (StateSysVO element : result) {
			excelRow = excelSheet.createRow(excelSheet.getLastRowNum() + 1);

			createCellAndSetValue(excelRow, i++, StringUtil.isNotBlank(format) ? String.format(format, element.getStatsLabel()) : element.getStatsLabel());
			createCellAndSetValue(excelRow, i++, element.getStatsCount());
			i = 0;
		}
	}

	private void setVerticalExcelRows2(HSSFWorkbook workbook, HSSFSheet excelSheet, List<Map<String, Integer>> result, StateSysVO inputVO) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);

		String format = null;
		String key = null;
		if (isDateRangeSelect(inputVO)) {
			format = inputVO.getDateStart() + " ~ " + inputVO.getDateEnd();
		} else if (isDaySelect(inputVO)) {
			key = "DDAY";
			format = inputVO.getYear() + "년 " + inputVO.getMonth() + "월 %s일";
		} else if (StringUtil.isBlank(inputVO.getYear())) {
			key = "DYEAR";
			format = "%s년";
		} else {
			key = "DMONTH";
			format = inputVO.getYear() + "년 " + "%s월";
		}

		HSSFRow excelRow = null;

		// 통계값
		int i = 0;
		for (Map<String, Integer> element : result) {
			excelRow = excelSheet.createRow(excelSheet.getLastRowNum() + 1);

			createCellAndSetValue(excelRow, i++, StringUtil.isNotEmpty(key) ? String.format(format, element.get(key)) : format);
			createCellAndSetValue(excelRow, i++, String.valueOf(element.get("CNT")));
			i = 0;
		}
	}

	public void setHorizontalExcelRows(HSSFWorkbook workbook, HSSFSheet excelSheet, List<Map<String, Integer>> result, StateSysVO inputVO) throws Exception {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);

		HSSFRow excelRow = excelSheet.createRow(excelSheet.getLastRowNum() + 1);
		int i = 0;
		for (Map<String, Integer> element : result) {
			createCellAndSetValue(excelRow, i, String.valueOf(element.get("DMONTH")));
			createCellAndSetValue(excelRow, i++, String.valueOf(element.get("CNT")));
		}

//		// 서브헤더 세팅
//		if (StringUtil.isNotBlank(inputVO.getDateStart()) || StringUtil.isNotBlank(inputVO.getDateEnd())) {
//			switch (inputVO.getKind()) {
//				case 0: break;
//				case 1: break;
//				case 2: break;
//				case 3: break;
//				case 4: break;
//				case 5: break;
//				default: break;
//			}
//		} else if (StringUtil.isNotBlank(inputVO.getYear()) && StringUtil.isNotBlank(inputVO.getMonth())) {
//
//		} else {
//
//		}
//
//
//		if (subHeader != null) {
//			subHeaderLen = subHeader.length;
//			for (int i = 0; i < subHeaderLen; i++) {
//				createCellAndSetValue(excelRow, i, subHeader[i]);
//			}
//		}

//		// 통계값
//		int value = -1;
//		for (int i = 0; i < headerColumnList.size(); i++) {
//			value = 0;
//			for (StateSysVO element : result) {
//				if (headerColumnList.get(i).replaceAll("[^0-9]", "").equals(element.getStatsLabel())) {
//					value = element.getStatsCount();
//					break;
//				}
//			}
//			createCellAndSetValue(excelRow, i + 0, value);
//		}
	}

	private void createCellAndSetValue(HSSFRow excelRow, int i, String value) {
		HSSFCell cell = excelRow.createCell(i);
		cell.setCellValue(value);
		cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	}

	private void createCellAndSetValue(HSSFRow excelRow, int i, int value) {
		HSSFCell cell = excelRow.createCell(i);
		cell.setCellValue(value);
		cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	}

}
