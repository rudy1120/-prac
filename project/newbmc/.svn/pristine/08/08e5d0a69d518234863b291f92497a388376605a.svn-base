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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.basic.poll.ExcelFormat;
import egovframework.portal.sys.moveRes.service.ReserveService;
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

@Controller("ReserveController")
public class ReserveController {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	protected ReserveService reserveService;
	
	// 이사날짜 행복주택 리스트
	@RequestMapping("/sys/moveRes/resList.do")
	public String list(@ModelAttribute("reserveVO") ReserveVO reserveVO,
			@RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response)  throws Exception{

		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		reserveVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		reserveVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		
		int totalCnt = reserveService.getTCnt(reserveVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		
		model.addAttribute("result", reserveService.getList(reserveVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("reserveVO", reserveVO);
		
		return "/sys/moveRes/list/";
	}
	
	// 이사날짜 행복주택 세부 리스트
	@RequestMapping("/sys/moveRes/detailList.do")
	public String detaillist(@ModelAttribute("reserveVO") ReserveVO reserveVO,
			@RequestParam String mId, ModelMap model
			, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		// PAGINATION SETTING 
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		reserveVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page));
		reserveVO.setLastIndex(PaginationInfoUtil.calLastIndex(page));
		if(reserveVO.getGbn2() == null) {
			reserveVO.setGbn2("A");
		}
		int totalCnt = reserveService.getTotalCnt(reserveVO);
		ReserveVO title = reserveService.getTitle(reserveVO);
		model.addAttribute("page", page);
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page));
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, totalCnt));
		
		//VIEW PARAMETER SETTING 
		
		model.addAttribute("result", reserveService.getDetailList(reserveVO));
		model.addAttribute("resultCnt", totalCnt);
		model.addAttribute("reserveVO", reserveVO);
		model.addAttribute("gbn2", reserveVO.getGbn2());
		model.addAttribute("title", title.getName());
		
		return "/sys/moveRes/detailList/";
	}
	
	@RequestMapping("/sys/moveRes/write.do")
	public String write(@ModelAttribute("reserveVO") ReserveVO reserveVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		reserveVO.setGbn2(request.getParameter("gbn2"));
		ReserveVO title = reserveService.getTitle(reserveVO);
		
		model.addAttribute("gbn2", request.getParameter("gbn2"));
		model.addAttribute("title", title.getName());
		
		return "/sys/moveRes/write/";
	}
	
	// 2. 고객경영참여 관리자 수정 및 등록 화면
	@RequestMapping("/sys/moveRes/modify.do")
	public String modify(@ModelAttribute("reserveVO") ReserveVO reserveVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cid = request.getParameter("cust_id");
		String gbn2 = request.getParameter("gbn2");
		
		reserveVO.setCust_id(Integer.parseInt(cid));
		reserveVO.setGbn2(gbn2);
		
		ReserveVO title = reserveService.getTitle(reserveVO);
		ReserveVO entity = reserveService.getEntity(reserveVO);
		if (entity != null) {
			BeanUtil.copy(entity, reserveVO);
		}
		model.addAttribute("reserveVO", reserveVO);
		model.addAttribute("title", title.getName());
		
		return "/sys/moveRes/update/";
	}
	
	@RequestMapping("/sys/moveRes/reserve.do")
	public String resProc(@ModelAttribute("reserveVO") ReserveVO reserveVO, @RequestParam String mId, ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cid = request.getParameter("cust_id");
		String gbn2 = request.getParameter("gbn2");
		reserveVO.setCust_id(Integer.parseInt(cid));
		reserveVO.setGbn2(gbn2);
		
		ReserveVO chkRes = reserveService.chkReserve(reserveVO);
		ReserveVO entity = reserveService.getEntity(reserveVO);
		
		if(chkRes != null) { //예약 내역 있으면
			WriterUtil.flushJsAlertAndHistoryBack(response, "이미 이사일자를 예약했습니다.");
			return null;
		} else{
			if (entity != null) {
				BeanUtil.copy(entity, reserveVO);
			}
			model.addAttribute("reserveVO", reserveVO);
			
			return "/sys/moveRes/reserve/";
		}
			
	}

	// 2-2. 일광행복주택 관리자 대리 예약
	@RequestMapping("/sys/moveRes/resProc.do")
	public String resProc2(@ModelAttribute("reserveVO") ReserveVO reserveVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cid = request.getParameter("cust_id");
		String m_date = request.getParameter("dateSel");
		String m_time = request.getParameter("timeSel");
		String gbn2 = request.getParameter("gbn2");
		
		reserveVO.setCust_id(Integer.parseInt(cid));
		reserveVO.setMove_date(m_date);
		reserveVO.setMove_time(m_time);
		reserveVO.setAddr1(request.getParameter("addr1"));
		reserveVO.setTimeSel(request.getParameter("timeSel"));
		reserveVO.setDateSel(request.getParameter("dateSel"));
		
		int chkResCnt = reserveService.chkRes(reserveVO);
		
		if(chkResCnt > 0) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "이미 예약이 완료된 시간입니다.");
			return null;
		} else {

			reserveService.resInsert(reserveVO);
			
			return "redirect:/sys/moveRes/detailList.do?mId=" + mId + "&gbn2=" + gbn2;
		}
	}

	// 2-3. 일광행복주택 예약자 수정
	@RequestMapping("/sys/moveRes/modifyProc.do")
	public String modifyProc(@ModelAttribute("reserveVO") ReserveVO reserveVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cid = request.getParameter("cust_id");
		String gbn2 = request.getParameter("gbn2");
		
		reserveVO.setCust_id(Integer.parseInt(cid));
		reserveVO.setAddr1(request.getParameter("addr1"));
		reserveVO.setTimeSel(request.getParameter("timeSel"));
		reserveVO.setDateSel(request.getParameter("dateSel"));
		
		int chkResCnt = reserveService.chkRes(reserveVO);
		
		if(chkResCnt > 0) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "이미 예약이 완료된 시간입니다.");
			return null;
		} else {
		
			reserveService.resUp(reserveVO);
			
			return "redirect:/sys/moveRes/detailList.do?mId=" + mId + "&gbn2=" + gbn2;
		}
	} 
	
	// 2-2. 일광행복주택 예약자 등록
	@RequestMapping("/sys/moveRes/writeProc.do")
	public String writeProc(@ModelAttribute("reserveVO") ReserveVO reserveVO, @RequestParam String mId, ModelMap model, MultipartHttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String gbn2 = request.getParameter("gbn2");
		
		reserveVO.setAddr1(addr1);
		reserveVO.setAddr2(addr2);
		reserveVO.setGbn2(gbn2);
		
		ReserveVO chkCust = reserveService.chkCust(reserveVO);
		if(chkCust != null) {
			WriterUtil.flushJsAlertAndHistoryBack(response, "동일한 동,호수의 계약자가 존재합니다.");
			return null;
		} else{
			reserveService.regiInsert(reserveVO);
			return "redirect:/sys/moveRes/detailList.do?mId=" + mId + "&gbn2=" + gbn2;
		}
		
	}

	// 예약 삭제
	@ResponseBody
	@RequestMapping("/sys/moveRes/delResProc.do")
	public String delResProc(@ModelAttribute("reserveVO") ReserveVO reserveVO, @RequestParam String mId, HttpServletRequest request) throws Exception {
		
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String delIdx = reserveService.deleteRes(reserveVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}
	
	// 예약자 삭제
	@ResponseBody
	@RequestMapping("/sys/moveRes/delCustProc.do")
	public String delCustProc(@ModelAttribute("reserveVO") ReserveVO reserveVO, @RequestParam String mId, HttpServletRequest request) throws Exception {
		
		JSONObject rtn = new JSONObject().put("success", Boolean.FALSE);
		String delIdx = reserveService.deleteCust(reserveVO);
		rtn.put("success", StringUtil.isNotBlank(delIdx));

		return rtn.toString();
	}
	
	/************ 시간 선택 확인 ************/
	@RequestMapping("/sys/moveRes/chkTime.do")
	public void chkTime(@ModelAttribute("reserveVO") ReserveVO reserveVO, 
			HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		List<ReserveVO> resultList = reserveService.chkTime(reserveVO);
		
		ArrayList<JSONObject> arr = new ArrayList<JSONObject>();
		for (int i = 0; i < resultList.size(); i++) {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(resultList.get(i));
			arr.add(new JSONObject(json));
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(arr);
		
	}
	
	//엑셀 다운로드
	@SuppressWarnings("unused")
	@RequestMapping("/sys/moveRes/ExcelDownloader.do")
	public void ExcelDownload(@ModelAttribute("reserveVO") ReserveVO reserveVO,  Model model
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
		WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "happyHouseRes_" + currentTime + ".xls"));
		FileInputStream fileStream = null;
		byte[] bytestream = null;
		response.setHeader("Content-Disposition", "attachment; filename=happyHouseRes_" + currentTime + ".xls");
		OutputStream outStream = response.getOutputStream();

		try {				
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			List<ReserveVO> list = reserveService.getExcelList(reserveVO);
			
			reserveVO.setGbn2(request.getParameter("gbn2"));
			ReserveVO title = reserveService.getTitle(reserveVO);
			
			setHeader(sheet, title.getName() + " 이사날짜 예약자 · 미예약자 목록");
			setData(sheet, list);
			workbook.write();
			workbook.close();

			File file = new File(excelDir + "happyHouseRes_" + currentTime + ".xls");
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
	public void setData(WritableSheet sheet, List<ReserveVO> list) throws RowsExceededException, WriteException {
		int cellIdx = 1;
/*		if (StringUtil.isBlank(poll.getHeader())) {
			cellIdx = 2;
		}*/
		sheet.addCell(new Label(0, cellIdx, "번호",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(1, cellIdx, "성함",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, "연락처",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(3, cellIdx, "동 · 호수",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(4, cellIdx, "이사일자",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(5, cellIdx, "이사시간",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(6, cellIdx, "등록일시",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(7, cellIdx, "수정일시",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(8, cellIdx, "기계약여부",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(9, cellIdx, "수정가능여부",setFormat(ExcelFormat.DATA_CENTRE)));

		cellIdx++;

		for (int i = 0; i < list.size(); i++) {
			
			ReserveVO vo = list.get(i);
		// 행 생성
		String num = Integer.toString(i+1);
		sheet.addCell(new Label(0, cellIdx, num));
		sheet.addCell(new Label(1, cellIdx, vo.getName(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, vo.getTel(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(3, cellIdx, vo.getAddr1()+"동"+vo.getAddr2()+"호",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(4, cellIdx, vo.getMove_date(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(5, cellIdx, vo.getM_time(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(6, cellIdx, vo.getRegdate(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(7, cellIdx, vo.getUptdate(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(8, cellIdx, vo.getGbn1(),setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(9, cellIdx, vo.getUpdt_yn(),setFormat(ExcelFormat.DATA_CENTRE)));
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
		sheet.setColumnView(9, 15);

	}
	

	public void setHeader(WritableSheet sheet, String title) throws RowsExceededException, WriteException {
		/** 제목 설정 */
		sheet.mergeCells(0, 0, 9, 0);
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
