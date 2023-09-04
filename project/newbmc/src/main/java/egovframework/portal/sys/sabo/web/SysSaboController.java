package egovframework.portal.sys.sabo.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.List;

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

import ch.qos.logback.core.net.SyslogOutputStream;
import egovframework.portal.sys.basic.poll.ExcelFormat;
import egovframework.portal.sys.sabo.service.SysSaboService;
import egovframework.portal.sys.sabo.vo.SysSaboVO;
import egovframework.portal.sys.smsService.vo.SmsServiceVO;
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



@Controller("SysSaboController")
public class SysSaboController {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	protected SysSaboService syssaboService;
	
	
	@RequestMapping("/sys/sabo/subList.do")
	public String list(@ModelAttribute("searchVO") SysSaboVO searchVO,
			@RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response)  throws Exception{

		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		searchVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		searchVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = syssaboService.getTotalCnt(searchVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		
		model.addAttribute("result", syssaboService.getList(searchVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("searchVO", searchVO);
		
		
		return "/sys/sabo/list/";
		
		
	}
	
	//엑셀 다운로드
		@SuppressWarnings("unused")
		@RequestMapping("/sys/sabo/ExcelDownloader.do")
		public void ExcelDownload(@ModelAttribute("searchVO") SysSaboVO searchVO
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
			WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "saboApply_" + currentTime + ".xls"));
			FileInputStream fileStream = null;
			byte[] bytestream = null;
			response.setHeader("Content-Disposition", "attachment; filename=saboApply_" + currentTime + ".xls");
			OutputStream outStream = response.getOutputStream();

			try {				
				WritableSheet sheet = workbook.createSheet("sheet1", 0);
				List<SysSaboVO> list = syssaboService.getExcelList(searchVO);
				setHeader(sheet, "사보 구독서비스 신청자 목록");
				setData(sheet, list);
				workbook.write();
				workbook.close();

				File file = new File(excelDir + "saboApply_" + currentTime + ".xls");
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
		public void setData(WritableSheet sheet, List<SysSaboVO> list) throws RowsExceededException, WriteException {
			int cellIdx = 1;
	/*		if (StringUtil.isBlank(poll.getHeader())) {
				cellIdx = 2;
			}*/
			

			sheet.addCell(new Label(0, cellIdx, "번호",setFormat(ExcelFormat.DATA_CENTRE)));
			sheet.addCell(new Label(1, cellIdx, "성함",setFormat(ExcelFormat.DATA_CENTRE)));
			sheet.addCell(new Label(2, cellIdx, "주소",setFormat(ExcelFormat.DATA_CENTRE)));
			sheet.addCell(new Label(3, cellIdx, "등록일자",setFormat(ExcelFormat.DATA_CENTRE)));

			cellIdx++;

			for (int i = 0; i < list.size(); i++) {
				
				SysSaboVO vo = list.get(i);
			// 행 생성
			String num = Integer.toString(i+1);
			sheet.addCell(new Label(0, cellIdx, num));
			sheet.addCell(new Label(1, cellIdx, vo.getName(),setFormat(ExcelFormat.DATA_CENTRE)));
			sheet.addCell(new Label(2, cellIdx, vo.getAddress()));
			sheet.addCell(new Label(3, cellIdx, vo.getRegdate()));
			cellIdx++;

			}
			sheet.setColumnView(1, 15);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 50);
			sheet.setColumnView(4, 50);

		}
		

		public void setHeader(WritableSheet sheet, String title) throws RowsExceededException, WriteException {
			/** 제목 설정 */
			sheet.mergeCells(0, 0, 3, 0);
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
