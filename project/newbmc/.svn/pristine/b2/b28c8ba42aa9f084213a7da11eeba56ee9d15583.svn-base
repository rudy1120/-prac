package egovframework.portal.sys.smsService.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.basic.poll.ExcelFormat;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.sms.service.SmsService;
import egovframework.portal.sys.smsService.service.SmsServiceService;
import egovframework.portal.sys.smsService.vo.SmsServiceVO;
import egovframework.portal.unit.bmc.apply.service.ApplySmsService;
import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.paging.PaginationInfoUtil;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

@Controller
public class SmsServiceController {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	protected SmsServiceService smsService;
	
	/** 문자발송 서비스 */
	@Autowired
	protected SmsService smsServiceDo;
	
	@Autowired
	protected ApplySmsService applySmsService;

	//분양알림 신청 목록 조회
	@RequestMapping("/sys/smsApply/list.do")
	public String list(@ModelAttribute("searchVO") SmsServiceVO searchVO
			, @RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		//공급용도 데이터
		List<ApplySmsVO> resultList = applySmsService.getPurposerList();
		model.addAttribute("resultList", resultList);
		
		
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = smsService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		model.addAttribute("result", smsService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/smsService/list/";
	}
	
	//분양알림 신청 목록 조회
	@RequestMapping("/sys/smsApply/list2.do")
	public String list2(@ModelAttribute("searchVO") SmsServiceVO searchVO
			, @RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		//주택유형 데이터
		List<ApplySmsVO> resultList = applySmsService.getHousingTypeList();
		model.addAttribute("resultList", resultList);
		
		
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = smsService.getTotalCnt2(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		model.addAttribute("result", smsService.getList2(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/smsService/list2/";
	}
	//문자발송이력 
	@RequestMapping("/sys/smsApply/list3.do")
	public String list3(@ModelAttribute("searchVO") SmsServiceVO searchVO
			, @RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		List<ApplySmsVO> resultList = applySmsService.getHousingTypeList();
		model.addAttribute("resultList", resultList);
		// PAGINATION SETTING 
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = smsService.getTotalCnt3(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		model.addAttribute("result", smsService.getList3(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		return "/sys/smsService/list3/";
	}
	
	//문자 발송 서비스
	@RequestMapping("/sys/smsApply/smsWrite.do")
	public String smsWrite(@ModelAttribute("searchVO") SmsServiceVO searchVO, @RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//공급용도 데이터
		List<ApplySmsVO> usesList = applySmsService.getPurposerList();
		model.addAttribute("usesList", usesList);
		
		//주택유형 데이터
		List<ApplySmsVO> housingList = applySmsService.getHousingTypeList();
		model.addAttribute("housingList", housingList);
		
		return "/sys/smsService/write/";
	}
	
	//문자 발송 실행
	@RequestMapping("/sys/smsApply/sendSms.do")
	public String smsSend(@ModelAttribute("searchVO") BbsMngVO searchVO, ModelMap model
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
		smsServiceDo.insertSmsQueue(searchVO);
		
		WriterUtil.flushJsAlertAndRedirect(response, "문자 발송이 완료됐습니다.", "document.location.href='/sys/smsApply/smsWrite.do?mId=0116040000';");
		return null;
	}
	
	//엑셀 다운로드
	@SuppressWarnings("unused")
	@RequestMapping("/sys/smsApply/ExcelDownloader.do")
	public void ExcelDownload(@ModelAttribute("searchVO") SmsServiceVO searchVO
			, HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=utf-8");

		String excelDir = "/home/webdata/egov_uploadFile/excel/";
		File dir = new File(excelDir);
		if (!dir.exists()) {
			dir.setExecutable(false, true);
			dir.setReadable(true);
			dir.setWritable(false, true);
			dir.mkdirs();
		}

		String currentTime = String.valueOf(System.currentTimeMillis());
		WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "smsApply_" + currentTime + ".xls"));
		FileInputStream fileStream = null;
		byte[] bytestream = null;
		response.setHeader("Content-Disposition", "attachment; filename=smsApply_" + currentTime + ".xls");
		OutputStream outStream = response.getOutputStream();

		try {

			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			List<SmsServiceVO> list = smsService.getExcelList(searchVO);

			setHeader(sheet, "분양정보 문자서비스등록 목록");
			setData(sheet, list);
			workbook.write();
			workbook.close();

			File file = new File(excelDir + "smsApply_" + currentTime + ".xls");
			bytestream = new byte[(int) file.length()];
			fileStream = new FileInputStream(file);
			int i = 0, j = 0;
			while ((i = fileStream.read()) != -1) {
				bytestream[j] = (byte) i;
				j++;
			}

			try {
				boolean success = file.delete();
				if (!success) {
					LOGGER.info("not success");
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error(">> IllegalArgumentException", e);
			}
			outStream.write(bytestream);

		} catch (WriteException e) {
			e.printStackTrace();
		} finally {
			if (fileStream != null) {
				fileStream.close();
			}
			if (outStream != null) {
				outStream.close();
			}
		}
	}

	/**
	 * 엑셀 데이터 영역 세팅
	 *
	 * @param sheet 엑셀 시트
	 * @param poll 설문조사 데이터
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void setData(WritableSheet sheet, List<SmsServiceVO> list) throws RowsExceededException, WriteException {
		int cellIdx = 1;
/*		if (StringUtil.isBlank(poll.getHeader())) {
			cellIdx = 2;
		}*/
		

		sheet.addCell(new Label(0, cellIdx, "번호",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(1, cellIdx, "성함",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, "연락처",setFormat(ExcelFormat.DATA_CENTRE)));
		//sheet.addCell(new Label(3, cellIdx, "사업지구",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(3, cellIdx, "공급용도",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(4, cellIdx, "서비스 이용기간",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(5, cellIdx, "신청일시",setFormat(ExcelFormat.DATA_CENTRE)));
		cellIdx++;

		for (int i = 0; i < list.size(); i++) {
			
			SmsServiceVO vo = list.get(i);
		// 행 생성
		String num = Integer.toString(i+1);
		sheet.addCell(new Label(0, cellIdx, num));
		sheet.addCell(new Label(1, cellIdx, vo.getName(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, vo.getPhone()));
		//sheet.addCell(new Label(3, cellIdx, vo.getAreaname()));
		sheet.addCell(new Label(3, cellIdx, vo.getUsesname()));
		sheet.addCell(new Label(4, cellIdx, vo.getTermcode()));
		sheet.addCell(new Label(5, cellIdx, vo.getRegdate()));
		cellIdx++;

		}
		sheet.setColumnView(1, 15);
		sheet.setColumnView(2, 30);
		sheet.setColumnView(3, 100);
		sheet.setColumnView(4, 20);
		sheet.setColumnView(5, 50);

	}
	@SuppressWarnings("unused")
	@RequestMapping("/sys/smsApply/ExcelDownloader2.do")
	public void ExcelDownload2(@ModelAttribute("searchVO") SmsServiceVO searchVO
			, HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html;charset=utf-8");

		String excelDir = "/home/webdata/egov_uploadFile/excel/";
		File dir = new File(excelDir);
		if (!dir.exists()) {
			dir.setExecutable(false, true);
			dir.setReadable(true);
			dir.setWritable(false, true);
			dir.mkdirs();
		}

		String currentTime = String.valueOf(System.currentTimeMillis());
		WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "smsApply_" + currentTime + ".xls"));
		FileInputStream fileStream = null;
		byte[] bytestream = null;
		response.setHeader("Content-Disposition", "attachment; filename=smsApply_" + currentTime + ".xls");
		OutputStream outStream = response.getOutputStream();

		try {

			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			List<SmsServiceVO> list = smsService.getExcelList2(searchVO);

			setHeader2(sheet, "모집공고 문자서비스등록 목록");
			setData2(sheet, list);
			workbook.write();
			workbook.close();

			File file = new File(excelDir + "smsApply_" + currentTime + ".xls");
			bytestream = new byte[(int) file.length()];
			fileStream = new FileInputStream(file);
			int i = 0, j = 0;
			while ((i = fileStream.read()) != -1) {
				bytestream[j] = (byte) i;
				j++;
			}

			try {
				boolean success = file.delete();
				if (!success) {
					LOGGER.info("not success");
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error(">> IllegalArgumentException", e);
			}
			outStream.write(bytestream);

		} catch (WriteException e) {
			e.printStackTrace();
		} finally {
			if (fileStream != null) {
				fileStream.close();
			}
			if (outStream != null) {
				outStream.close();
			}
		}
	}
	/**
	 * 엑셀 데이터 영역 세팅
	 *
	 * @param sheet 엑셀 시트
	 * @param poll 설문조사 데이터
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void setData2(WritableSheet sheet, List<SmsServiceVO> list) throws RowsExceededException, WriteException {
		int cellIdx = 1;
/*		if (StringUtil.isBlank(poll.getHeader())) {
			cellIdx = 2;
		}*/
		

		sheet.addCell(new Label(0, cellIdx, "번호",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(1, cellIdx, "성함",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, "연락처",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(3, cellIdx, "주택유형",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(4, cellIdx, "서비스 이용기간",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(5, cellIdx, "신청일시",setFormat(ExcelFormat.DATA_CENTRE)));
		cellIdx++;

		for (int i = 0; i < list.size(); i++) {
			
			SmsServiceVO vo = list.get(i);
		// 행 생성
		String num = Integer.toString(i+1);
		sheet.addCell(new Label(0, cellIdx, num));
		sheet.addCell(new Label(1, cellIdx, vo.getName(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, vo.getPhone()));
		sheet.addCell(new Label(3, cellIdx, vo.getCodename()));
		sheet.addCell(new Label(4, cellIdx, vo.getTermcode()));
		sheet.addCell(new Label(5, cellIdx, vo.getRegdate()));
		cellIdx++;

		}
		sheet.setColumnView(1, 15);
		sheet.setColumnView(2, 30);
		sheet.setColumnView(3, 50);
		sheet.setColumnView(4, 20);
		sheet.setColumnView(5, 50);

	}
	/**
	 * 엑셀 헤더 설정
	 *
	 * @param sheet
	 * @param title
	 * @param userCnt
	 * @param header
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	public void setHeader(WritableSheet sheet, String title) throws RowsExceededException, WriteException {
		/** 제목 설정 */
		sheet.mergeCells(0, 0, 5, 0);
		sheet.setRowView(0, 1000);
		sheet.addCell(new Label(0, 0, title, setFormat(ExcelFormat.HEADER)));
	}
	
	public void setHeader2(WritableSheet sheet, String title) throws RowsExceededException, WriteException {
		/** 제목 설정 */
		sheet.mergeCells(0, 0, 4, 0);
		sheet.setRowView(0, 1000);
		sheet.addCell(new Label(0, 0, title, setFormat(ExcelFormat.HEADER)));
	}

	/**
	 * 지정된 셀 스타일을 return 해줍니다.
	 *
	 * @param fmt 셀 종류
	 * @return
	 * @throws WriteException
	 */
	public WritableCellFormat setFormat(ExcelFormat fmt) throws WriteException {
		WritableCellFormat format = new WritableCellFormat();
		format.setVerticalAlignment(VerticalAlignment.CENTRE); //셀 세로 정렬
		format.setBorder(Border.ALL, BorderLineStyle.THIN); //테두리와 테두리 스타일 설정
		format.setWrap(Boolean.TRUE);
		if (ExcelFormat.HEADER.equals(fmt)) {
			format = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT));
			format.setAlignment(Alignment.CENTRE);
			format.setVerticalAlignment(VerticalAlignment.CENTRE); //셀 세로 정렬
			format.setBorder(Border.ALL, BorderLineStyle.THIN); //테두리와 테두리 스타일 설정
		} else if (ExcelFormat.TITLE.equals(fmt)) {
			format.setAlignment(Alignment.CENTRE);
			format.setBackground(Colour.IVORY);
		} else if (ExcelFormat.QUESTION_CENTRE.equals(fmt)) {
			format.setAlignment(Alignment.CENTRE);
			format.setBackground(Colour.LIGHT_TURQUOISE);
//			format.setWrap(Boolean.TRUE);
		} else if (ExcelFormat.QUESTION_LEFT.equals(fmt)) {
			format.setAlignment(Alignment.LEFT);
			format.setBackground(Colour.LIGHT_TURQUOISE);
//			format.setWrap(Boolean.TRUE);
		} else if (ExcelFormat.DATA_CENTRE.equals(fmt)) {
			format.setAlignment(Alignment.CENTRE);
//			format.setWrap(Boolean.TRUE);
		} else if (ExcelFormat.DATA_LEFT.equals(fmt)) {
			format.setAlignment(Alignment.LEFT);
//			format.setWrap(Boolean.TRUE);
		}
		return format;
	}

	
	/** 엑셀파일 생성 **/

}
