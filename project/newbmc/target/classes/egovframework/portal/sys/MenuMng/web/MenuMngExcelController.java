package egovframework.portal.sys.MenuMng.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.yhdb.solution.secukeypad.pcweb.commonUtil.TUtil;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.portal.sys.MenuMng.service.MenuMngService;
import egovframework.portal.sys.MenuMng.vo.MenuVO;
import egovframework.portal.sys.content.service.MenuSysService;
import egovframework.rte.fdl.string.EgovStringUtil;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * @author TAE
 */
@Controller
public class MenuMngExcelController {

	@Autowired
	protected MenuMngService menuMngService;
	@Autowired
	protected MenuSysService menuService;

	private final Logger LOGGER = LogManager.getLogger();

	/**
	 * 메뉴 엑셀 등록 처리
	 *
	 * @Method Name : menuExcelUpload
	 * @param request
	 * @param response
	 * @param sess
	 * @throws JSONException
	 * @throws IOException
	 */
	@RequestMapping("/sys/totalAdminMng/menuMng/uploadProc.do")
	public void menuExcelUpload(HttpServletRequest request, HttpServletResponse response, HttpSession sess) throws JSONException, IOException {

		response.setContentType("text/plain; charset=utf-8");
		JSONObject jsonObject = new JSONObject();
		PrintWriter out = response.getWriter();
		String uploadDir = EgovProperties.getProperty("sys.excel.path");
		String siteCode = EgovStringUtil.null2string(request.getParameter("siteCode"), "");

		try {

			MultipartHttpServletRequest mpReq = (MultipartHttpServletRequest) request;
			String convertFileName = "";
			String ext = "";

			@SuppressWarnings("rawtypes")
			Iterator files = mpReq.getFileNames();
			int uploadCnt = 0;

			while (files.hasNext()) {
				String mFile = (String) files.next();
				MultipartFile multiFile = mpReq.getFile(mFile);
				String originalFileName = multiFile.getOriginalFilename();
				convertFileName = String.valueOf(System.currentTimeMillis());

				int index = originalFileName.lastIndexOf(".");
				ext = originalFileName.substring(index);
				if (ext.toLowerCase().endsWith(".xls")) {
					new File(uploadDir).mkdir();
					File uploadFile = new File(uploadDir + convertFileName + "." + ext);
					multiFile.transferTo(uploadFile);
				}
			}

			//엑셀 데이터 저장
			Workbook workbook = null;
			String valiMessage = "";
			boolean valError = false; //데이터 에러여부 flag

			try {

				workbook = Workbook.getWorkbook(new File(uploadDir + convertFileName + "." + ext));
				Sheet s = workbook.getSheet(0); //첫번째 시트 선택

				int col = s.getColumns(); //시트 내 열의 수
				int row = s.getRows(); //시트 내 행의 수

				sess.setAttribute("totalRows", row - 1);

				if (col < 11 || 11 > col) {
					valError = true;
					valiMessage = "엑셀업로드 처리중 오류가 발생하였습니다. (엑셀 양식이 맞지 않음)";
				}

				if ("".equals(siteCode)) {
					valError = true;
					valiMessage = "사이트 코드가 올바르지 않습니다.";
				}

				if (!valError) {

					int cellLen = 0;

					ArrayList<MenuVO> menuList = new ArrayList<MenuVO>();
					valiMessage = "엑셀 데이터 확인중에 오류가 발생하였습니다."; //데이터 에러 메시지
//					String patternRegex = "^[a-zA-Z0-9]*$"; //특수문자 확인용

					int depth1 = 1;
					int depth2 = 1;
					int depth3 = 1;
					int depth4 = 1;
					int depth5 = 1;
					String depthCode = "";

					//기존 메뉴 삭제
					menuMngService.deleteMenuTargetSite(siteCode);
					for (int i = 1; i < row; i++) {

						MenuVO menuVO = new MenuVO();
						Cell c[] = s.getRow(i);
						cellLen = c.length;

						for (int j = 0; j < col; j++) {

							if (j < cellLen && !"".equals(c[j].getContents())) {

								menuVO.setSiteCode(siteCode);
								menuVO.setIsTermset("N");
								/*
								 * if(j == 0){
								 * //번호
								 * }
								 */
								if (j == 1) {
									//메뉴 depth
									menuVO.setMenuLevel(Integer.parseInt(c[j].getContents()));

									//현재 메뉴 코드 조회
									if ("1".equals(c[j].getContents())) {
										depthCode = this.makeMenuCode(1, depthCode, depth1);
										menuVO.setmId(depthCode);
										menuVO.setMenuOrderCode(depthCode);
										depth1++;
										depth2 = 1;
										depth3 = 1;
										depth4 = 1;
										depth5 = 1;
									} else if ("2".equals(c[j].getContents())) {
										depthCode = this.makeMenuCode(2, depthCode, depth2);
										menuVO.setmId(depthCode);
										menuVO.setMenuOrderCode(depthCode);
										depth2++;
										depth3 = 1;
										depth4 = 1;
										depth5 = 1;
									} else if ("3".equals(c[j].getContents())) {
										depthCode = this.makeMenuCode(3, depthCode, depth3);
										menuVO.setmId(depthCode);
										menuVO.setMenuOrderCode(depthCode);
										depth3++;
										depth4 = 1;
										depth5 = 1;
									} else if ("4".equals(c[j].getContents())) {
										depthCode = this.makeMenuCode(4, depthCode, depth4);
										menuVO.setmId(depthCode);
										menuVO.setMenuOrderCode(depthCode);
										depth4++;
										depth5 = 1;
									} else if ("5".equals(c[j].getContents())) {
										depthCode = this.makeMenuCode(5, depthCode, depth5);
										menuVO.setmId(depthCode);
										menuVO.setMenuOrderCode(depthCode);
										depth5++;
									}
								}
								if (j == 2) {
									//이름
									menuVO.setMenuName(c[j].getContents().trim());
								}
								if (j == 3) {
									//타입
									menuVO.setMenuType(c[j].getContents());
								}
								if (j == 4) {
									//링크주소
									menuVO.setLinkUrl(c[j].getContents());
								}
								if (j == 5) {
									//링크타겟
									menuVO.setTarget(c[j].getContents());
								}
								if (j == 6) {
									//사용여부
									menuVO.setIsUse(c[j].getContents());
								}
								if (j == 7) {
									//프로그램 주소
									menuVO.setProgramUrl(c[j].getContents());
								}
								if (j == 8) {
									//컨텐츠 경로
									menuVO.setContentFilePath(c[j].getContents());
								}
								if (j == 9) {
									//컨텐츠 포함여부
									menuVO.setIsIncContent(c[j].getContents());
								}
								if (j == 10) {
									//게시판 IDX
									menuVO.setBbsMstIdx(Integer.parseInt(c[j].getContents()));
									menuVO.setBbsIdx(0);
								}

							}

						}

						menuList.add(menuVO);

					}

					ArrayList<Integer> allErrors = new ArrayList<Integer>();
					ArrayList<Integer> codeError = new ArrayList<Integer>();
					ArrayList<Integer> nameError = new ArrayList<Integer>();
					ArrayList<Integer> levelError = new ArrayList<Integer>();

					for (int i = 0; i < menuList.size(); i++) {

						if (menuList.get(i) != null) {
							/*
							 * int nextIdx = menuMngService.getMenuNextSeq();
							 * //메뉴코드 사용여부 확인
							 * //상위 메뉴코드 확인
							 */
							if ("".equals(TUtil.nullToBlank(menuList.get(i).getMenuName()))) {
								allErrors.add(i + 1);
								nameError.add(i + 1);
							} else if ("".equals(TUtil.nullToBlank(menuList.get(i).getmId()))) {
								allErrors.add(i + 1);
								codeError.add(i + 1);
							} else if (menuList.get(i).getMenuLevel() == 0 || menuList.get(i).getMenuLevel() < 1 || menuList.get(i).getMenuLevel() > 5) {
								allErrors.add(i + 1);
								levelError.add(i + 1);
							} else {
								menuMngService.insertMenu(menuList.get(i), request);
								uploadCnt++;
							}

							sess.setAttribute("nowRow", uploadCnt);

						}

					}

					//엑셀 업로드 후 파일 삭제
					File file = new File(uploadDir + convertFileName + "." + ext);
					file.delete();
					jsonObject.put("flag", "success");
					if (allErrors.size() > 0) {
						String resultMessage = "엑셀 업로드가 완료되었지만 일부 오류가 있는 데이터는 등록되지 않았습니다.\n";
						resultMessage = errorMessage(resultMessage, nameError, "menuName");
						resultMessage = errorMessage(resultMessage, codeError, "menuId");
						resultMessage = errorMessage(resultMessage, levelError, "menuLevel");
						//resultMessage += "\n사이트관리에서 배포대상을 추가해주셔야 컨텐츠 등록이 가능합니다.";
						jsonObject.put("message", resultMessage);
					} else {
						jsonObject.put("message", "엑셀 업로드가 완료 되었습니다.(" + uploadCnt + "건)");
					}
					out.println(jsonObject.toString());

				} else {

					File file = new File(uploadDir + convertFileName + "." + ext);
					file.delete();
					jsonObject.put("flag", "fail");
					jsonObject.put("message", valiMessage);
					out.println(jsonObject.toString());

				}

			} catch (BiffException e) {
				jsonObject.put("flag", "fail");
				jsonObject.put("message", "엑셀업로드 처리중 오류가 발생하였습니다. (code:2)");
				out.println(jsonObject.toString());
				LOGGER.error(">> failed to run menuExcelUpload/BiffException", e);
			}

		} catch (Exception e) {
			jsonObject.put("flag", "fail");
			jsonObject.put("message", "엑셀업로드 처리중 오류가 발생하였습니다. (code:1)");
			out.println(jsonObject.toString());
			LOGGER.error(">> failed to run menuExcelUpload/Exception", e);
		} finally {
			out.close();
		}

	}

	/**
	 * 엑셀 업로드 상태 출력
	 *
	 * @Method Name : getUploadStatus
	 * @param rep
	 * @param sess
	 * @throws IOException
	 * @throws JSONException
	 */
	@ResponseBody
	@RequestMapping("/sys/totalAdminMng/menuMng/getUploadStatus.do")
	public byte[] getUploadStatus(HttpServletResponse rep, HttpSession sess) throws IOException, JSONException {
		rep.setContentType("text/plain; charset=utf-8");
		JSONObject jsonObject = new JSONObject();

		int totalRows = 0;
		int nowRow = 0;
		if (sess.getAttribute("totalRows") != null) {
			totalRows = (int) sess.getAttribute("totalRows");
		}
		if (sess.getAttribute("nowRow") != null) {
			nowRow = (int) sess.getAttribute("nowRow");
		}

		if (totalRows == 0 || nowRow == 0) {
			jsonObject.put("flag", "error");
			jsonObject.put("totalRows", "0");
			jsonObject.put("nowRow", "0");

			sess.removeAttribute("totalRows");
			sess.removeAttribute("nowRow");
		} else {
			jsonObject.put("flag", "success");
			jsonObject.put("totalRows", totalRows);
			jsonObject.put("nowRow", nowRow);

			if (totalRows == nowRow) {
				sess.removeAttribute("totalRows");
				sess.removeAttribute("nowRow");
			}
		}

		return jsonObject.toString().getBytes("UTF-8");
	}

	/**
	 * 정비대상메뉴 엑셀 다운로드
	 *
	 * @Method Name : menuCheckListDown
	 * @param searchVO
	 * @param request
	 * @param response
	 * @param sess
	 */
	@RequestMapping("/sys/totalAdminMng/menuMng/menuCheckListDown.do")
	public void menuCheckListDown(@ModelAttribute("searchVO") MenuVO searchVO, HttpServletRequest request, HttpServletResponse response, HttpSession sess) throws Exception {

		WritableWorkbook workbook = null;
		FileInputStream fileStream = null;
		OutputStream outStream = null;
		try {
			String uploadDir = EgovProperties.getProperty("sys.excel.path");
			//기본 파일 생성
			workbook = Workbook.createWorkbook(new File(uploadDir + "default.xls"));
			//sheet 생성
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			WritableCellFormat titleFormat = new WritableCellFormat();
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); //셀 세로 정렬
			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN); //테두리와 테두리 스타일 설정
			titleFormat.setBackground(Colour.IVORY);

			WritableCellFormat inFormat = new WritableCellFormat();
			inFormat.setAlignment(Alignment.CENTRE);
			inFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			inFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			//셀별 크기 설정
			sheet.setColumnView(0, 10); //번호
			sheet.setColumnView(1, 30); //메뉴명
			sheet.setColumnView(2, 30); //메뉴ID
			sheet.setColumnView(3, 20); //메뉴레벨
			sheet.setColumnView(4, 20); //메뉴타입
			sheet.setColumnView(5, 20); //담당자
			sheet.setColumnView(6, 20); //요점검내용

			//타이틀 이름
			Label l1 = new Label(0, 0, "번호", titleFormat);
			Label l2 = new Label(1, 0, "메뉴명", titleFormat);
			Label l3 = new Label(2, 0, "메뉴ID", titleFormat);
			Label l4 = new Label(3, 0, "메뉴레벨", titleFormat);
			Label l5 = new Label(4, 0, "메뉴타입", titleFormat);
			Label l6 = new Label(5, 0, "담당자", titleFormat);
			Label l7 = new Label(6, 0, "요점검내용", titleFormat);

			//타이틀 이름을 sheet에 쓰기
			sheet.addCell(l1);
			sheet.addCell(l2);
			sheet.addCell(l3);
			sheet.addCell(l4);
			sheet.addCell(l5);
			sheet.addCell(l6);
			sheet.addCell(l7);

			List<MenuVO> menuCheckList = menuMngService.getMenuCheckList(searchVO);
			for (int i = 0; i < menuCheckList.size(); i++) {
				//번호 입력
				Label num = new Label(0, i + 1, String.valueOf(i + 1), inFormat);
				sheet.addCell(num);
				//메뉴명
				Label menuName = new Label(1, i + 1, menuCheckList.get(i).getMenuName(), inFormat);
				sheet.addCell(menuName);
				//메뉴ID
				Label mId = new Label(2, i + 1, menuCheckList.get(i).getmId(), inFormat);
				sheet.addCell(mId);
				//메뉴레벨
				Label menuLevel = new Label(3, i + 1, String.valueOf(menuCheckList.get(i).getMenuLevel()), inFormat);
				sheet.addCell(menuLevel);
				//메뉴타입
				Label menuType = null;
				if ("C".equals(menuCheckList.get(i).getMenuType())) {
					menuType = new Label(4, i + 1, "CMS 컨텐츠", inFormat);
				} else if ("F".equals(menuCheckList.get(i).getMenuType())) {
					menuType = new Label(4, i + 1, "파일직접선택", inFormat);
				} else if ("B".equals(menuCheckList.get(i).getMenuType())) {
					menuType = new Label(4, i + 1, "게시판", inFormat);
				} else if ("P".equals(menuCheckList.get(i).getMenuType())) {
					menuType = new Label(4, i + 1, "프로그램", inFormat);
				} else if ("L".equals(menuCheckList.get(i).getMenuType())) {
					menuType = new Label(4, i + 1, "링크", inFormat);
				}
				sheet.addCell(menuType);
				//담당자
				Label chargeDepNm = null;
				if (menuCheckList.get(i).getChargeId() != null && "".equals(menuCheckList.get(i).getChargeId())) {
					if (menuCheckList.get(i).getChargeDepNm() != null && "".equals(menuCheckList.get(i).getChargeId())) {
						chargeDepNm = new Label(5, i + 1, menuCheckList.get(i).getChargeDepNm() + menuCheckList.get(i).getChargeFnm(), inFormat);
					} else {
						chargeDepNm = new Label(5, i + 1, menuCheckList.get(i).getChargeFnm(), inFormat);
					}
				} else {
					chargeDepNm = new Label(5, i + 1, "-", inFormat);
				}
				sheet.addCell(chargeDepNm);
				//요점검내용
				Label reason = null;
				if (menuCheckList.get(i).getChargeId() != null && "".equals(menuCheckList.get(i).getChargeId())) {
					reason = new Label(6, i + 1, "담당자 정보 불일치(부서이동으로 인한)", inFormat);
				} else {
					reason = new Label(6, i + 1, "담당자 미지정", inFormat);
				}
				sheet.addCell(reason);

			}
			workbook.write();

			File file = new File(uploadDir + "default.xls");

			byte[] bytestream = new byte[(int) file.length()];
			fileStream = new FileInputStream(file);
			int i = 0, j = 0;
			while ((i = fileStream.read()) != -1) {
				bytestream[j] = (byte) i;
				j++;
			}

			try {
				boolean success = file.delete();
				if (!success) {
					LOGGER.error(">> not success");
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error(e.getMessage(), e);
			}
			response.setHeader("Content-Disposition", "attachment; filename=MENU_REPAIR_LIST.xls");
			outStream = response.getOutputStream();
			outStream.write(bytestream);
		} catch (WriteException e) {
			LOGGER.error(">> failed to run menuCheckListDown/WriteException", e);
		} finally {
			if (workbook != null) {
				workbook.close();
			}
			if (fileStream != null) {
				fileStream.close();
			}
			if (outStream != null) {
				outStream.close();
			}
		}

	}

	/**
	 * 메뉴 엑셀 다운로드
	 *
	 * @Method Name : downloadExcel
	 * @param siteCode
	 * @param request
	 * @param response
	 * @param sess
	 */
	@RequestMapping("/sys/totalAdminMng/menuMng/downloadExcel.do")
	public void downloadExcel(@RequestParam("siteCode") String siteCode, HttpServletRequest request, HttpServletResponse response, HttpSession sess) throws Exception {

		//메뉴 데이터 조회
		Map<String, Object> menuMap = menuMngService.getMenuListMap(siteCode);
		@SuppressWarnings("unchecked")
		List<MenuVO> depth1Menu = (List<MenuVO>) menuMap.get("siteMenuList");

		OutputStream outStream = null;
		WritableWorkbook workbook = null;
		FileInputStream fileStream = null;
		String uploadDir = EgovProperties.getProperty("sys.excel.path");

		try {
			outStream = response.getOutputStream();

			//기본 파일 생성
			workbook = Workbook.createWorkbook(new File(uploadDir + siteCode.toUpperCase() + "_MENU.xls"));
			//sheet 생성
			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			WritableCellFormat titleFormat = new WritableCellFormat();

			titleFormat.setAlignment(Alignment.CENTRE); //셀 가로 정열
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); //셀 세로 정렬
			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN); //테두리와 테두리 스타일 설정
			titleFormat.setBackground(Colour.IVORY);

			WritableCellFormat inFormat = new WritableCellFormat();
			inFormat.setAlignment(Alignment.CENTRE);
			inFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			inFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			//셀별 크기 설정
			sheet.setColumnView(0, 10); //번호
			sheet.setColumnView(1, 30); //메뉴이름
			sheet.setColumnView(2, 10); //메뉴 depth
			sheet.setColumnView(3, 20); //메뉴코드
			sheet.setColumnView(4, 10); //메뉴타입
			sheet.setColumnView(5, 20); //링크주소
			sheet.setColumnView(6, 10); //링크타겟
			sheet.setColumnView(7, 40); //프로그램주소
			sheet.setColumnView(8, 40); //컨텐츠경로
			sheet.setColumnView(9, 10); //게시판IDX
			sheet.setColumnView(10, 15); //컨텐츠포함여부
			sheet.setColumnView(11, 15); //메뉴사용여부
			sheet.setColumnView(12, 15); //관리담당자
			sheet.setColumnView(13, 15); //관리부서
			sheet.setColumnView(14, 15); //담당자 전화번호

			//타이틀 이름
			Label l1 = new Label(0, 0, "번호", titleFormat);
			Label l2 = new Label(1, 0, "메뉴이름", titleFormat);
			Label l3 = new Label(2, 0, "메뉴 depth", titleFormat);
			Label l4 = new Label(3, 0, "메뉴코드", titleFormat);
			Label l5 = new Label(4, 0, "메뉴타입", titleFormat);
			Label l6 = new Label(5, 0, "링크URL", titleFormat);
			Label l7 = new Label(6, 0, "링크타겟", titleFormat);
			Label l8 = new Label(7, 0, "프로그램URL", titleFormat);
			Label l9 = new Label(8, 0, "컨텐츠경로", titleFormat);
			Label l10 = new Label(9, 0, "게시판IDX", titleFormat);
			Label l11 = new Label(10, 0, "컨텐츠포함여부", titleFormat);
			Label l12 = new Label(11, 0, "메뉴사용여부", titleFormat);
			Label l13 = new Label(12, 0, "관리담당자", titleFormat);
			Label l14 = new Label(13, 0, "관리부서", titleFormat);
			Label l15 = new Label(14, 0, "담당자 전화번호", titleFormat);

			//타이틀 이름을 sheet에 쓰기
			sheet.addCell(l1);
			sheet.addCell(l2);
			sheet.addCell(l3);
			sheet.addCell(l4);
			sheet.addCell(l5);
			sheet.addCell(l6);
			sheet.addCell(l7);
			sheet.addCell(l8);
			sheet.addCell(l9);
			sheet.addCell(l10);
			sheet.addCell(l11);
			sheet.addCell(l12);
			sheet.addCell(l13);
			sheet.addCell(l14);
			sheet.addCell(l15);

			int rowCnt = 1;
			//데이터 입력
			for (int i = 0; i < depth1Menu.size(); i++) {

				//depth1 데이터 추가
				//번호 입력
				Label num = new Label(0, rowCnt, String.valueOf(rowCnt), inFormat);
				sheet.addCell(num);
				//메뉴이름
				Label menuName = new Label(1, rowCnt, depth1Menu.get(i).getMenuName(), inFormat);
				sheet.addCell(menuName);
				//메뉴depth
				Label menuDepth = new Label(2, rowCnt, String.valueOf(depth1Menu.get(i).getMenuLevel()), inFormat);
				sheet.addCell(menuDepth);
				//메뉴코드
				Label menuCode = new Label(3, rowCnt, depth1Menu.get(i).getmId(), inFormat);
				sheet.addCell(menuCode);
				//메뉴타입
				Label menuType = new Label(4, rowCnt, depth1Menu.get(i).getMenuType(), inFormat);
				sheet.addCell(menuType);
				//링크URL
				Label linkUrl = new Label(5, rowCnt, depth1Menu.get(i).getLinkUrl(), inFormat);
				sheet.addCell(linkUrl);
				//링크타겟
				Label linkTarget = new Label(6, rowCnt, depth1Menu.get(i).getTarget(), inFormat);
				sheet.addCell(linkTarget);
				//프로그램URL
				Label programUrl = new Label(7, rowCnt, depth1Menu.get(i).getProgramUrl(), inFormat);
				sheet.addCell(programUrl);
				//컨텐츠경로
				Label contentsPath = new Label(8, rowCnt, depth1Menu.get(i).getContentFilePath(), inFormat);
				sheet.addCell(contentsPath);
				//게시판IDX
				Label boardIdx = new Label(9, rowCnt, String.valueOf(depth1Menu.get(i).getBbsMstIdx()), inFormat);
				sheet.addCell(boardIdx);
				//컨텐츠포함여부
				Label isContents = new Label(10, rowCnt, depth1Menu.get(i).getIsIncContent(), inFormat);
				sheet.addCell(isContents);
				//메뉴사용여부
				Label isUse = new Label(11, rowCnt, depth1Menu.get(i).getIsUse(), inFormat);
				sheet.addCell(isUse);
				//관리담당자
				Label chargeFnm = new Label(12, rowCnt, depth1Menu.get(i).getChargeFnm(), inFormat);
				sheet.addCell(chargeFnm);
				//관리부서
				Label chargeDepNm = new Label(13, rowCnt, depth1Menu.get(i).getChargeDepNm(), inFormat);
				sheet.addCell(chargeDepNm);
				//담당자 전화번호
				Label chargeTel = new Label(14, rowCnt, depth1Menu.get(i).getChargeTel(), inFormat);
				sheet.addCell(chargeTel);
				rowCnt++;

				//depth2 데이터 추가
				if (depth1Menu.get(i).getDepth2MenuList() != null && depth1Menu.get(i).getDepth2MenuList().size() != 0) {
					List<MenuVO> depth2MenuList = depth1Menu.get(i).getDepth2MenuList();
					for (int j = 0; j < depth2MenuList.size(); j++) {
						num = new Label(0, rowCnt, String.valueOf(rowCnt), inFormat);
						sheet.addCell(num); //번호 입력
						menuName = new Label(1, rowCnt, depth2MenuList.get(j).getMenuName(), inFormat);
						sheet.addCell(menuName); //메뉴이름
						menuDepth = new Label(2, rowCnt, String.valueOf(depth2MenuList.get(j).getMenuLevel()), inFormat);
						sheet.addCell(menuDepth); //메뉴depth
						menuCode = new Label(3, rowCnt, depth2MenuList.get(j).getmId(), inFormat);
						sheet.addCell(menuCode); //메뉴코드
						menuType = new Label(4, rowCnt, depth2MenuList.get(j).getMenuType(), inFormat);
						sheet.addCell(menuType); //메뉴타입
						linkUrl = new Label(5, rowCnt, depth2MenuList.get(j).getLinkUrl(), inFormat);
						sheet.addCell(linkUrl); //링크URL
						linkTarget = new Label(6, rowCnt, depth2MenuList.get(j).getTarget(), inFormat);
						sheet.addCell(linkTarget); //링크타겟
						programUrl = new Label(7, rowCnt, depth2MenuList.get(j).getProgramUrl(), inFormat);
						sheet.addCell(programUrl); //프로그램URL
						contentsPath = new Label(8, rowCnt, depth2MenuList.get(j).getContentFilePath(), inFormat);
						sheet.addCell(contentsPath); //컨텐츠경로
						boardIdx = new Label(9, rowCnt, String.valueOf(depth2MenuList.get(j).getBbsMstIdx()), inFormat);
						sheet.addCell(boardIdx); //게시판IDX
						isContents = new Label(10, rowCnt, depth2MenuList.get(j).getIsIncContent(), inFormat);
						sheet.addCell(isContents); //컨텐츠포함여부
						isUse = new Label(11, rowCnt, depth2MenuList.get(j).getIsUse(), inFormat);
						sheet.addCell(isUse); //메뉴사용여부
						chargeFnm = new Label(12, rowCnt, depth2MenuList.get(j).getChargeFnm(), inFormat);
						sheet.addCell(chargeFnm); //관리담당자
						chargeDepNm = new Label(13, rowCnt, depth2MenuList.get(j).getChargeDepNm(), inFormat);
						sheet.addCell(chargeDepNm); //관리부서
						chargeTel = new Label(14, rowCnt, depth2MenuList.get(j).getChargeTel(), inFormat);
						sheet.addCell(chargeTel); //담당자 전화번호
						rowCnt++;

						if (depth2MenuList.get(j).getDepth3MenuList() != null && depth2MenuList.get(j).getDepth3MenuList().size() != 0) {
							List<MenuVO> depth3MenuList = depth2MenuList.get(j).getDepth3MenuList();
							for (int k = 0; k < depth3MenuList.size(); k++) {
								num = new Label(0, rowCnt, String.valueOf(rowCnt), inFormat);
								sheet.addCell(num); //번호 입력
								menuName = new Label(1, rowCnt, depth3MenuList.get(k).getMenuName(), inFormat);
								sheet.addCell(menuName); //메뉴이름
								menuDepth = new Label(2, rowCnt, String.valueOf(depth3MenuList.get(k).getMenuLevel()), inFormat);
								sheet.addCell(menuDepth); //메뉴depth
								menuCode = new Label(3, rowCnt, depth3MenuList.get(k).getmId(), inFormat);
								sheet.addCell(menuCode); //메뉴코드
								menuType = new Label(4, rowCnt, depth3MenuList.get(k).getMenuType(), inFormat);
								sheet.addCell(menuType); //메뉴타입
								linkUrl = new Label(5, rowCnt, depth3MenuList.get(k).getLinkUrl(), inFormat);
								sheet.addCell(linkUrl); //링크URL
								linkTarget = new Label(6, rowCnt, depth3MenuList.get(k).getTarget(), inFormat);
								sheet.addCell(linkTarget); //링크타겟
								programUrl = new Label(7, rowCnt, depth3MenuList.get(k).getProgramUrl(), inFormat);
								sheet.addCell(programUrl); //프로그램URL
								contentsPath = new Label(8, rowCnt, depth3MenuList.get(k).getContentFilePath(), inFormat);
								sheet.addCell(contentsPath); //컨텐츠경로
								boardIdx = new Label(9, rowCnt, String.valueOf(depth3MenuList.get(k).getBbsMstIdx()), inFormat);
								sheet.addCell(boardIdx); //게시판IDX
								isContents = new Label(10, rowCnt, depth3MenuList.get(k).getIsIncContent(), inFormat);
								sheet.addCell(isContents); //컨텐츠포함여부
								isUse = new Label(11, rowCnt, depth3MenuList.get(k).getIsUse(), inFormat);
								sheet.addCell(isUse); //메뉴사용여부
								chargeFnm = new Label(12, rowCnt, depth3MenuList.get(k).getChargeFnm(), inFormat);
								sheet.addCell(chargeFnm); //관리담당자
								chargeDepNm = new Label(13, rowCnt, depth3MenuList.get(k).getChargeDepNm(), inFormat);
								sheet.addCell(chargeDepNm); //관리부서
								chargeTel = new Label(14, rowCnt, depth3MenuList.get(k).getChargeTel(), inFormat);
								sheet.addCell(chargeTel); //담당자 전화번호
								rowCnt++;

								if (depth3MenuList.get(k).getDepth4MenuList() != null && depth3MenuList.get(k).getDepth4MenuList().size() != 0) {
									List<MenuVO> depth4MenuList = depth3MenuList.get(k).getDepth4MenuList();
									for (int l = 0; l < depth4MenuList.size(); l++) {
										num = new Label(0, rowCnt, String.valueOf(rowCnt), inFormat);
										sheet.addCell(num); //번호 입력
										menuName = new Label(1, rowCnt, depth4MenuList.get(l).getMenuName(), inFormat);
										sheet.addCell(menuName); //메뉴이름
										menuDepth = new Label(2, rowCnt, String.valueOf(depth4MenuList.get(l).getMenuLevel()), inFormat);
										sheet.addCell(menuDepth); //메뉴depth
										menuCode = new Label(3, rowCnt, depth4MenuList.get(l).getmId(), inFormat);
										sheet.addCell(menuCode); //메뉴코드
										menuType = new Label(4, rowCnt, depth4MenuList.get(l).getMenuType(), inFormat);
										sheet.addCell(menuType); //메뉴타입
										linkUrl = new Label(5, rowCnt, depth4MenuList.get(l).getLinkUrl(), inFormat);
										sheet.addCell(linkUrl); //링크URL
										linkTarget = new Label(6, rowCnt, depth4MenuList.get(l).getTarget(), inFormat);
										sheet.addCell(linkTarget); //링크타겟
										programUrl = new Label(7, rowCnt, depth4MenuList.get(l).getProgramUrl(), inFormat);
										sheet.addCell(programUrl); //프로그램URL
										contentsPath = new Label(8, rowCnt, depth4MenuList.get(l).getContentFilePath(), inFormat);
										sheet.addCell(contentsPath); //컨텐츠경로
										boardIdx = new Label(9, rowCnt, String.valueOf(depth4MenuList.get(l).getBbsMstIdx()), inFormat);
										sheet.addCell(boardIdx); //게시판IDX
										isContents = new Label(10, rowCnt, depth4MenuList.get(l).getIsIncContent(), inFormat);
										sheet.addCell(isContents); //컨텐츠포함여부
										isUse = new Label(11, rowCnt, depth4MenuList.get(l).getIsUse(), inFormat);
										sheet.addCell(isUse); //메뉴사용여부
										chargeFnm = new Label(12, rowCnt, depth4MenuList.get(l).getChargeFnm(), inFormat);
										sheet.addCell(chargeFnm); //관리담당자
										chargeDepNm = new Label(13, rowCnt, depth4MenuList.get(l).getChargeDepNm(), inFormat);
										sheet.addCell(chargeDepNm); //관리부서
										chargeTel = new Label(14, rowCnt, depth4MenuList.get(l).getChargeTel(), inFormat);
										sheet.addCell(chargeTel); //담당자 전화번호
										rowCnt++;

										if (depth4MenuList.get(l).getDepth5MenuList() != null && depth4MenuList.get(l).getDepth5MenuList().size() != 0) {
											List<MenuVO> depth5MenuList = depth4MenuList.get(l).getDepth5MenuList();
											for (int m = 0; m < depth5MenuList.size(); m++) {
												num = new Label(0, rowCnt, String.valueOf(rowCnt), inFormat);
												sheet.addCell(num); //번호 입력
												menuName = new Label(1, rowCnt, depth5MenuList.get(m).getMenuName(), inFormat);
												sheet.addCell(menuName); //메뉴이름
												menuDepth = new Label(2, rowCnt, String.valueOf(depth5MenuList.get(m).getMenuLevel()), inFormat);
												sheet.addCell(menuDepth); //메뉴depth
												menuCode = new Label(3, rowCnt, depth5MenuList.get(m).getmId(), inFormat);
												sheet.addCell(menuCode); //메뉴코드
												menuType = new Label(4, rowCnt, depth5MenuList.get(m).getMenuType(), inFormat);
												sheet.addCell(menuType); //메뉴타입
												linkUrl = new Label(5, rowCnt, depth5MenuList.get(m).getLinkUrl(), inFormat);
												sheet.addCell(linkUrl); //링크URL
												linkTarget = new Label(6, rowCnt, depth5MenuList.get(m).getTarget(), inFormat);
												sheet.addCell(linkTarget); //링크타겟
												programUrl = new Label(7, rowCnt, depth5MenuList.get(m).getProgramUrl(), inFormat);
												sheet.addCell(programUrl); //프로그램URL
												contentsPath = new Label(8, rowCnt, depth5MenuList.get(m).getContentFilePath(), inFormat);
												sheet.addCell(contentsPath); //컨텐츠경로
												boardIdx = new Label(9, rowCnt, String.valueOf(depth5MenuList.get(m).getBbsMstIdx()), inFormat);
												sheet.addCell(boardIdx); //게시판IDX
												isContents = new Label(10, rowCnt, depth5MenuList.get(m).getIsIncContent(), inFormat);
												sheet.addCell(isContents); //컨텐츠포함여부
												isUse = new Label(11, rowCnt, depth5MenuList.get(m).getIsUse(), inFormat);
												sheet.addCell(isUse); //메뉴사용여부
												chargeFnm = new Label(12, rowCnt, depth5MenuList.get(m).getChargeFnm(), inFormat);
												sheet.addCell(chargeFnm); //관리담당자
												chargeDepNm = new Label(13, rowCnt, depth5MenuList.get(m).getChargeDepNm(), inFormat);
												sheet.addCell(chargeDepNm); //관리부서
												chargeTel = new Label(14, rowCnt, depth5MenuList.get(m).getChargeTel(), inFormat);
												sheet.addCell(chargeTel); //담당자 전화번호
												rowCnt++;
											}
										}
									}
								}
							}
						}
					}
				}
			}

			workbook.write();

			File file = new File(uploadDir + siteCode.toUpperCase() + "_MENU.xls");

			byte[] bytestream = new byte[(int) file.length()];
			fileStream = new FileInputStream(file);
			int i = 0, j = 0;
			while ((i = fileStream.read()) != -1) {
				bytestream[j] = (byte) i;
				j++;
			}

			try {
				boolean success = file.delete();
				if (!success) {
					LOGGER.error(">> not success");
				}
			} catch (IllegalArgumentException e) {
				LOGGER.error(">> failed to run downloadExcel/ IllegalArgumentException", e);
			}
			response.setHeader("Content-Disposition", "attachment; filename=" + siteCode.toUpperCase() + "_MENU.xls");
			outStream.write(bytestream);

		} catch (IOException e) {
			LOGGER.error(">> failed to run downloadExcel/IOException", e);
		} catch (WriteException e1) {
			LOGGER.error(">> failed to run downloadExcel/ WriteException", e1);
		} finally {
			if (fileStream != null) {
				fileStream.close();
			}
			if (outStream != null) {
				outStream.close();
			}
			if (workbook != null) {
				workbook.close();
			}
		}

	}

	/**
	 * 에러 내용 메시지 세팅 메소드
	 *
	 * @Method Name : errorMessage
	 * @param resultMessage
	 * @param errorList
	 * @param type
	 * @return
	 */
	public String errorMessage(String resultMessage, ArrayList<Integer> errorList, String type) {
		if (errorList.size() > 0) {
			String num = "";
			for (int i = 0; i < errorList.size(); i++) {
				if (i == 0) {
					if (type.equals("menuName")) {
						num += "메뉴 이름 에러 : " + errorList.get(i);
					} else if (type.equals("menuId")) {
						num += "메뉴 코드 에러 : " + errorList.get(i);
					} else {
						num += "메뉴 depth 에러 : " + errorList.get(i);
					}
				} else if (i == errorList.size() - 1) {
					num += "," + errorList.get(i) + "\n";
				} else {
					num += "," + errorList.get(i);
				}
			}
			return resultMessage += num;
		} else {
			return resultMessage;
		}
	}

	public String makeMenuCode(int depth, String nowCode, int nowDepth) {
		if (depth == 1) {
			if (nowDepth < 10) {
				nowCode = "0" + nowDepth + "00000000";
			} else {
				nowCode = nowDepth + "00000000";
			}
		} else if (depth == 2) {
			if (nowDepth < 10) {
				nowCode = nowCode.substring(0, 2) + "0" + nowDepth + "000000";
			} else {
				nowCode = nowCode.substring(0, 2) + nowDepth + "000000";
			}
		} else if (depth == 3) {
			if (nowDepth < 10) {
				nowCode = nowCode.substring(0, 4) + "0" + nowDepth + "0000";
			} else {
				nowCode = nowCode.substring(0, 4) + nowDepth + "0000";
			}
		} else if (depth == 4) {
			if (nowDepth < 10) {
				nowCode = nowCode.substring(0, 6) + "0" + nowDepth + "00";
			} else {
				nowCode = nowCode.substring(0, 6) + nowDepth + "00";
			}
		} else if (depth == 5) {
			if (nowDepth < 10) {
				nowCode = nowCode.substring(0, 8) + "0" + nowDepth;
			} else {
				nowCode = nowCode.substring(0, 8) + nowDepth;
			}
		}

		return nowCode;
	}
}