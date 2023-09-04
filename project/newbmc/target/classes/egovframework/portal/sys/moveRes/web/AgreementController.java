package egovframework.portal.sys.moveRes.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.basic.poll.ExcelFormat;
import egovframework.portal.sys.moveRes.service.AgreementService;
import egovframework.portal.sys.moveRes.service.ReserveService;
import egovframework.portal.sys.moveRes.vo.AgreementVO;
import egovframework.portal.sys.moveRes.vo.ReserveVO;
import egovframework.portal.util.BeanUtil;
import egovframework.portal.util.StringUtil;
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

@Controller("AgreementController")
public class AgreementController {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	protected AgreementService agreementService;
	
	//시청앞 행복주택 동의서
	@RequestMapping("/sys/moveRes/cityhallAgree.do")
	public String list(@ModelAttribute("agreementVO") AgreementVO agreementVO,
			@RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response)  throws Exception{

		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		agreementVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		agreementVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = agreementService.getCityhallTCnt(agreementVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		
		model.addAttribute("result", agreementService.getCityhallList(agreementVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("reserveVO", agreementVO);
		
		return "/sys/moveRes/agreeList1/";
	}
	
	//아미4 행복주택 동의서
	@RequestMapping("/sys/moveRes/amiAgree.do")
	public String write(@ModelAttribute("agreementVO") AgreementVO agreementVO, @RequestParam String mId,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		agreementVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		agreementVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = agreementService.getAmiTCnt(agreementVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		
		model.addAttribute("result", agreementService.getAmiList(agreementVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("reserveVO", agreementVO);
		
		return "/sys/moveRes/agreeList2/";
	}
	
	//시청앞 행복주택 엑셀 다운로드
	@SuppressWarnings("unused")
	@RequestMapping("/sys/moveRes/agree1/ExcelDownloader.do")
	public void ExcelDownload1(@ModelAttribute("agreementVO") AgreementVO agreementVO
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
		WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "cityhallAgreement_" + currentTime + ".xls"));
		FileInputStream fileStream = null;
		byte[] bytestream = null;
		response.setHeader("Content-Disposition", "attachment; filename=cityhallAgreement_" + currentTime + ".xls");
		OutputStream outStream = response.getOutputStream();

		try {				
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			List<AgreementVO> list = agreementService.getExcelList1(agreementVO);
			
			setHeader(sheet, "시청앞 행복주택 개인정보 제3자 제공 동의서 목록");
			setData(sheet, list);
			workbook.write();
			workbook.close();

			File file = new File(excelDir + "cityhallAgreement_" + currentTime + ".xls");
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
	
	//아미4 행복주택 엑셀 다운로드
	@SuppressWarnings("unused")
	@RequestMapping("/sys/moveRes/agree2/ExcelDownloader.do")
	public void ExcelDownload2(@ModelAttribute("agreementVO") AgreementVO agreementVO
			, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "ami4Agreement_" + currentTime + ".xls"));
		FileInputStream fileStream = null;
		byte[] bytestream = null;
		response.setHeader("Content-Disposition", "attachment; filename=ami4Agreement_" + currentTime + ".xls");
		OutputStream outStream = response.getOutputStream();

		try {				
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			List<AgreementVO> list = agreementService.getExcelList2(agreementVO);
			
			setHeader(sheet, "아미4 행복주택 개인정보 제3자 제공 동의서 목록");
			setData(sheet, list);
			workbook.write();
			workbook.close();

			File file = new File(excelDir + "ami4Agreement_" + currentTime + ".xls");
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
		
	public void setData(WritableSheet sheet, List<AgreementVO> list) throws RowsExceededException, WriteException {
		int cellIdx = 1;
/*		if (StringUtil.isBlank(poll.getHeader())) {
			cellIdx = 2;
		}*/
		sheet.addCell(new Label(0, cellIdx, "번호",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(1, cellIdx, "성함",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, "연락처",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(3, cellIdx, "동 · 호수",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(4, cellIdx, "필수1",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(5, cellIdx, "필수2",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(6, cellIdx, "필수3",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(7, cellIdx, "이해여부",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(8, cellIdx, "제출일시",setFormat(ExcelFormat.DATA_CENTRE)));

		cellIdx++;

		for (int i = 0; i < list.size(); i++) {
			
			AgreementVO vo = list.get(i);
		// 행 생성
		String num = Integer.toString(i+1);
		sheet.addCell(new Label(0, cellIdx, num));
		sheet.addCell(new Label(1, cellIdx, vo.getUsername(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, vo.getUsertel(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(3, cellIdx, vo.getAddr1()+"동"+vo.getAddr2()+"호",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(4, cellIdx, vo.getChk1(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(5, cellIdx, vo.getChk2(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(6, cellIdx, vo.getChk3(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(7, cellIdx, vo.getUnderstand(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(8, cellIdx, vo.getRegdate(),setFormat(ExcelFormat.DATA_CENTRE)));
		cellIdx++;

		}
		sheet.setColumnView(1, 15);
		sheet.setColumnView(2, 25);
		sheet.setColumnView(3, 25);
		sheet.setColumnView(4, 25);
		sheet.setColumnView(5, 25);
		sheet.setColumnView(6, 25);
		sheet.setColumnView(7, 25);
		sheet.setColumnView(8, 25);

	}
	

	public void setHeader(WritableSheet sheet, String title) throws RowsExceededException, WriteException {
		/** 제목 설정 */
		sheet.mergeCells(0, 0, 8, 0);
		sheet.setRowView(0, 1000);
		sheet.addCell(new Label(0, 0, title, setFormat(ExcelFormat.HEADER)));
	}
	
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
//				format.setWrap(Boolean.TRUE);
		} else if (ExcelFormat.QUESTION_LEFT.equals(fmt)) {
			format.setAlignment(Alignment.LEFT);
			format.setBackground(Colour.LIGHT_TURQUOISE);
//				format.setWrap(Boolean.TRUE);
		} else if (ExcelFormat.DATA_CENTRE.equals(fmt)) {
			format.setAlignment(Alignment.CENTRE);
//				format.setWrap(Boolean.TRUE);
		} else if (ExcelFormat.DATA_LEFT.equals(fmt)) {
			format.setAlignment(Alignment.LEFT);
//				format.setWrap(Boolean.TRUE);
		}
		return format;
	}
	
	
}
