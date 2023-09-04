package egovframework.portal.unit.bmc.contract.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
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

import egovframework.portal.common.Constant;
import egovframework.portal.sys.basic.poll.ExcelFormat;
import egovframework.portal.unit.bmc.contract.service.ContractService;
import egovframework.portal.unit.bmc.contract.vo.ContractVO;
import egovframework.portal.unit.bmc.contract.vo.CtChangeVO;
import egovframework.portal.unit.bmc.contract.vo.CtInspectVO;
import egovframework.portal.unit.bmc.contract.vo.CtSubContractVO;
import egovframework.portal.unit.bmc.contract.vo.PaymentVO;
import egovframework.portal.util.StringUtil;
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
public class ContractController {
	
	private static final Logger LOGGER = LogManager.getLogger();
	
	@Autowired
	protected ContractService contractService;
	
	@RequestMapping("/bmc/contract/list.do")
	public String list(@ModelAttribute("contractVO") ContractVO contractVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId, @RequestParam(value="gb", defaultValue="0") String gb) throws Exception {
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
		int listCutRecord = StringUtil.isNumber("10") ? 10 : Constant.LIST_CUTRECORD;
		contractVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		contractVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
		
		if (contractVO.getCntrWay() == null) {
			if (gb.equals("1")) {
				contractVO.setCntrWay("2");
			} else {
				contractVO.setCntrWay("1"); // 일반계약현황 탭 이동
			}
			 
		}
		
		
		if (contractVO.getCntrGb() == null) {
			contractVO.setCntrGb("1");
		}
		
		int totalCnt = contractService.getTotalCnt(contractVO);
		List<ContractVO> list = contractService.getList(contractVO);
		
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("page", page);
		model.addAttribute("result", list);
	
		List<String> deptList = contractService.getDeptList();
		model.addAttribute("cntrWay", contractVO.getCntrWay());
		model.addAttribute("deptList", deptList);
		return "/bmc/unit/contract/list/";
	}
	@RequestMapping("/bmc/contract/list2.do")
	public String list2(@ModelAttribute("contractVO") ContractVO contractVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId, @RequestParam(value="gb", defaultValue="0") String gb) throws Exception {
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
		int listCutRecord = StringUtil.isNumber("10") ? 10 : Constant.LIST_CUTRECORD;
		contractVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		contractVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
		
		int totalCnt = contractService.getTotalCnt2(contractVO);
		List<ContractVO> list = contractService.getList2(contractVO);
		
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("page", page);
		model.addAttribute("result", list);
		
		return "/bmc/unit/contract/list2/";
	}
	
	// 2. 공고안내 > 대가지급 (뷰페이지 X)
	@RequestMapping("/bmc/contract/paymentList.do")
	public String paymentList(@ModelAttribute("paymentVO") PaymentVO paymentVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1); 
		int listCutRecord = StringUtil.isNumber("10") ? 10 : Constant.LIST_CUTRECORD;
		paymentVO.setFirstIndex(PaginationInfoUtil.calFirstIndex(page, listCutRecord));
		paymentVO.setLastIndex(PaginationInfoUtil.calLastIndex(page, listCutRecord));
		
		int totalCnt = contractService.getPmTotalCnt(paymentVO);
		List<PaymentVO> list = contractService.getPmList(paymentVO);
		
		model.addAttribute("paginationInfo", PaginationInfoUtil.calPaginationInfo(page, listCutRecord, Constant.LIST_CUTPAGE, totalCnt));
		model.addAttribute("listOrder", PaginationInfoUtil.calListOrder(totalCnt, page, listCutRecord));
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("page", page);
		model.addAttribute("result", list);
		
		return "/bmc/unit/contract/paymentList/";
	}
	
	// 3. 계약현황 및 수의계약현황 상세보기
	@RequestMapping("/bmc/contract/view.do")
	public String view(@ModelAttribute("contractVO") ContractVO contractVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		
		ContractVO element = contractService.getContent(contractVO);
		List<CtChangeVO> changeList = contractService.getChangeInfo(contractVO); // 계약변경
		List<CtInspectVO> inspectList = contractService.getInspectInfo(contractVO); // 검사현황 및 기성금지급일 
		List<CtSubContractVO> subcontractList = contractService.getSubcontractInfo(contractVO); // 하도급현황
		model.addAttribute("contractVO", contractVO);
		model.addAttribute("element", element);
		model.addAttribute("changeList", changeList);
		model.addAttribute("inspectList", inspectList);
		model.addAttribute("subcontractList", subcontractList);
		return "/bmc/unit/contract/view/";
	}
	@RequestMapping("/bmc/contract/view2.do")
	public String view2(@ModelAttribute("contractVO") ContractVO contractVO, HttpServletRequest request, HttpServletResponse response, ModelMap model, @RequestParam String mId) throws Exception {
		

		List<ContractVO> List = contractService.getList2(contractVO); // 하도급현황
		model.addAttribute("contractVO", contractVO);
		model.addAttribute("list", List);
		return "/bmc/unit/contract/view2/";
	}
	
	
	// 4. 엑셀 다운로드
	@SuppressWarnings("unused")
	@RequestMapping("/bmc/contract/ExcelDownloader.do")
	public void ExcelDownload(@ModelAttribute("contractVO") ContractVO contractVO
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
		WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "CONTRACT_" + currentTime + ".xls"));
		FileInputStream fileStream = null;
		byte[] bytestream = null;
		response.setHeader("Content-Disposition", "attachment; filename=CONTRACT_" + currentTime + ".xls");
		OutputStream outStream = response.getOutputStream();

		try {

			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			contractVO.setLastIndex(contractService.getTotalCnt(contractVO));
			List<ContractVO> list = contractService.getList(contractVO);

			setHeader(sheet, "계약현황 목록");
			setData(sheet, list);
			workbook.write();
			workbook.close();

			File file = new File(excelDir + "CONTRACT_" + currentTime + ".xls");
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
	
	public void setData(WritableSheet sheet, List<ContractVO> list) throws RowsExceededException, WriteException {
		int cellIdx = 1;
		
		sheet.addCell(new Label(0, cellIdx, "번호",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(1, cellIdx, "담당부서",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, "계약명",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(3, cellIdx, "계약일자",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(4, cellIdx, "계약금액(원)",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(5, cellIdx, "업체명",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(6, cellIdx, "계약방법",setFormat(ExcelFormat.DATA_CENTRE)));
		cellIdx++;

		for (int i = 0; i < list.size(); i++) {
			
			ContractVO vo = list.get(i);
		// 행 생성
		String num = Integer.toString(i+1);
		sheet.addCell(new Label(0, cellIdx, num));
		sheet.addCell(new Label(1, cellIdx, vo.getDept()));
		sheet.addCell(new Label(2, cellIdx, vo.getCntrNm()));
		sheet.addCell(new Label(3, cellIdx, vo.getCntrYrmd()));
		sheet.addCell(new Label(4, cellIdx, String.valueOf(vo.getFinalcntrAmt())));
		sheet.addCell(new Label(5, cellIdx, vo.getCompNm()));
		sheet.addCell(new Label(6, cellIdx, vo.getCntrWay()));
		cellIdx++;

		}
		sheet.setColumnView(1, 50);
		sheet.setColumnView(2, 100);
		sheet.setColumnView(3, 30);
		sheet.setColumnView(4, 30);
		sheet.setColumnView(5, 50);
		sheet.setColumnView(6, 50);

	}
	
	@SuppressWarnings("unused")
	@RequestMapping("/bmc/contract/ExcelDownloader2.do")
	public void ExcelDownload2(@ModelAttribute("paymentVO") PaymentVO paymentVO
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
		WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "PAYMENT_" + currentTime + ".xls"));
		FileInputStream fileStream = null;
		byte[] bytestream = null;
		response.setHeader("Content-Disposition", "attachment; filename=PAYMENT_" + currentTime + ".xls");
		OutputStream outStream = response.getOutputStream();

		try {

			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			paymentVO.setLastIndex(contractService.getPmTotalCnt(paymentVO));
			List<PaymentVO> list = contractService.getPmList(paymentVO);

			setHeader(sheet, "대가지급 목록");
			setData2(sheet, list);
			workbook.write();
			workbook.close();

			File file = new File(excelDir + "PAYMENT_" + currentTime + ".xls");
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
	
	public void setData2(WritableSheet sheet, List<PaymentVO> list) throws RowsExceededException, WriteException {
		int cellIdx = 1;
		
		sheet.addCell(new Label(0, cellIdx, "번호",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(1, cellIdx, "지급일",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(2, cellIdx, "지급종류",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(3, cellIdx, "계약명",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(4, cellIdx, "업체명",setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(5, cellIdx, "지급액(원)",setFormat(ExcelFormat.DATA_CENTRE)));
		cellIdx++;

		for (int i = 0; i < list.size(); i++) {
			
			PaymentVO vo = list.get(i);
		// 행 생성
		String num = Integer.toString(i+1);
		sheet.addCell(new Label(0, cellIdx, num));
		sheet.addCell(new Label(1, cellIdx, vo.getPayYrmd()));
		sheet.addCell(new Label(2, cellIdx, vo.getPayGb()));
		sheet.addCell(new Label(3, cellIdx, vo.getCntrNm()));
		sheet.addCell(new Label(4, cellIdx, vo.getCompNm()));
		sheet.addCell(new Label(5, cellIdx, String.valueOf(vo.getPayAmt())));
		cellIdx++;

		}
		sheet.setColumnView(1, 50);
		sheet.setColumnView(2, 30);
		sheet.setColumnView(3, 100);
		sheet.setColumnView(4, 50);
		sheet.setColumnView(5, 50);
	}
	
	
	public void setHeader(WritableSheet sheet, String title) throws RowsExceededException, WriteException {
		/** 제목 설정 */
		sheet.mergeCells(0, 0, 5, 0);
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
}
