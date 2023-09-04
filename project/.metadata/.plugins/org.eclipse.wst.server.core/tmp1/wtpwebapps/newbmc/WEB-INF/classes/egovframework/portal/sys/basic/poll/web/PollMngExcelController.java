package egovframework.portal.sys.basic.poll.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.basic.poll.ExcelFormat;
import egovframework.portal.sys.basic.poll.PollQuestionType;
import egovframework.portal.sys.basic.poll.mapper.PollMngServiceMapper;
import egovframework.portal.sys.basic.poll.mapper.QuestionMngServiceMapper;
import egovframework.portal.sys.basic.poll.vo.PollAnswerMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixColMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollQuestionMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponseMngVO;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.Tuple;
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

/**
 * 설문조사 엑셀 컨트롤러
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 10. 31.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 10. 31.
 */
@Controller
public class PollMngExcelController {

	private static final Logger LOGGER = LogManager.getLogger();

	@Resource
	PollMngServiceMapper pollMngServiceMapper;
	@Resource
	QuestionMngServiceMapper questionMngServiceMapper;

	@RequestMapping("/sys/pollMng/result/excel.do")
	public void excelDownload(@RequestParam("idx") int idx, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String excelDir = "/home/webdata/egov_uploadFile/excel/";
		File dir = new File(excelDir);
		if (!dir.exists()) {
			dir.setExecutable(false, true);
			dir.setReadable(true);
			dir.setWritable(false, true);
			dir.mkdirs();
		}

		String currentTime = String.valueOf(System.currentTimeMillis());
		WritableWorkbook workbook = Workbook.createWorkbook(new File(excelDir + "default_" + currentTime + ".xls"));
		FileInputStream fileStream = null;
		byte[] bytestream = null;
		response.setHeader("Content-Disposition", "attachment; filename=POLL_" + currentTime + ".xls");
		OutputStream outStream = response.getOutputStream();

		try {

			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			PollMngVO poll = pollMngServiceMapper.getPollView(new PollMngVO(idx));
			poll.setQuestionList(questionMngServiceMapper.getQuestionList(poll));
			for (PollQuestionMngVO question : poll.getQuestionList()) {
				question.setDup(Boolean.TRUE);
				question.setAnswerList(questionMngServiceMapper.getAnswerList(question));
				question.setAbleList(questionMngServiceMapper.getAbleList(question));
				question.setMatrixList(questionMngServiceMapper.getMatrixRowList(question));
				question.setMatrixColList(questionMngServiceMapper.getMatrixColList(question));
				for (PollAnswerMngVO answer : question.getAnswerList()) {
					answer.setResponseList(questionMngServiceMapper.getResponseList(new Tuple<Integer, String>(answer.getIdx(), "answer")));
				}
				for (PollMatrixMngVO matrix : question.getMatrixList()) {
					matrix.setResponseList(questionMngServiceMapper.getResponseList(new Tuple<Integer, String>(matrix.getIdx(), "matrix")));
				}
			}

			setHeader(sheet, poll.getTitle(), poll.getUserCnt(), poll.getHeader());
			setData(sheet, poll);
			workbook.write();
			workbook.close();

			File file = new File(excelDir + "default_" + currentTime + ".xls");
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
	public void setData(WritableSheet sheet, PollMngVO poll) throws RowsExceededException, WriteException {
		int cellIdx = 3;
		if (StringUtil.isBlank(poll.getHeader())) {
			cellIdx = 2;
		}
		for (int i = 0; i < poll.getQuestionList().size(); i++) {
			PollQuestionMngVO question = poll.getQuestionList().get(i);
			/** 질문 출력 */
			sheet.mergeCells(1, cellIdx, 9, cellIdx);
			sheet.setRowView(cellIdx, 700);
			sheet.addCell(new Label(0, cellIdx, "질문" + (i + 1), setFormat(ExcelFormat.QUESTION_CENTRE)));
			sheet.addCell(new Label(1, cellIdx, question.getQuestion(), setFormat(ExcelFormat.QUESTION_LEFT)));
			cellIdx++;

			if (PollQuestionType.SINGLE.getCode().equals(question.getType()) || PollQuestionType.MULTI.getCode().equals(question.getType())) {
				/** 보기 항목 */
				sheet.mergeCells(1, cellIdx, 7, cellIdx);
				sheet.addCell(new Label(0, cellIdx, "번호", setFormat(ExcelFormat.TITLE)));
				sheet.addCell(new Label(1, cellIdx, "응답 문항", setFormat(ExcelFormat.TITLE)));
				sheet.addCell(new Label(8, cellIdx, "응답자", setFormat(ExcelFormat.TITLE)));
				sheet.addCell(new Label(9, cellIdx, "응답율", setFormat(ExcelFormat.TITLE)));
				cellIdx++;
			}

			if (PollQuestionType.SINGLE.getCode().equals(question.getType()) || PollQuestionType.MULTI.getCode().equals(question.getType())) {
				Boolean etc = Boolean.FALSE;
				int etcIdx = 0;
				int etcNum = 0;
				for (int j = 0; j < question.getAnswerList().size(); j++) {
					double percent = 0.0;
					PollAnswerMngVO answer = question.getAnswerList().get(j);
					if ("기타".equals(answer.getAnswer())) {
						etc = Boolean.TRUE;
						etcIdx = answer.getIdx();
						etcNum = (j + 1);
					}
					if (answer.getAnswerCnt() != 0) {
						percent = ((double) answer.getAnswerCnt() / (double) answer.getTotalCnt()) * 100;
					}
					if (percent <= 0.0) {
						percent = 0.0;
					}
					sheet.mergeCells(1, cellIdx, 7, cellIdx);
					sheet.addCell(new Label(0, cellIdx, String.valueOf((j + 1)), setFormat(ExcelFormat.DATA_CENTRE)));
					sheet.addCell(new Label(1, cellIdx, answer.getAnswer(), setFormat(ExcelFormat.DATA_CENTRE)));
					sheet.addCell(new Label(8, cellIdx, answer.getAnswerCnt() + "명", setFormat(ExcelFormat.DATA_CENTRE)));
					sheet.addCell(new Label(9, cellIdx, String.valueOf(percent).substring(0, String.valueOf(percent).indexOf(".") + 2) + "%", setFormat(ExcelFormat.DATA_CENTRE)));
					cellIdx++;
				}
				/** 기타 항목 응답내용 */
				if (etc) {
					List<PollResponseMngVO> responseList = questionMngServiceMapper.getResponseList(new Tuple<Integer, String>(etcIdx, question.getType()));
					if (responseList != null && responseList.size() > 0) {
						sheet.mergeCells(1, cellIdx, 9, cellIdx);
						sheet.addCell(new Label(0, cellIdx, "번호", setFormat(ExcelFormat.TITLE)));
						sheet.addCell(new Label(1, cellIdx, "기타 응답 내용", setFormat(ExcelFormat.TITLE)));
						cellIdx++;
						for (int j = 0; j < responseList.size(); j++) {
							PollResponseMngVO response = responseList.get(j);
							sheet.mergeCells(1, cellIdx, 9, cellIdx);
							sheet.addCell(new Label(0, cellIdx, etcNum + "-" + (j + 1), setFormat(ExcelFormat.DATA_CENTRE)));
							sheet.addCell(new Label(1, cellIdx, response.getValue(), setFormat(ExcelFormat.DATA_CENTRE)));
							cellIdx++;
						}
					}
				}
			} else if (PollQuestionType.SUBJECTIVE.getCode().equals(question.getType()) || PollQuestionType.SHORT_SUBJECTIVE.getCode().equals(question.getType())) {
				List<PollResponseMngVO> responseList = questionMngServiceMapper.getResponseList(new Tuple<Integer, String>(question.getIdx(), question.getType()));
				sheet.mergeCells(1, cellIdx, 9, cellIdx);
				sheet.addCell(new Label(0, cellIdx, "번호", setFormat(ExcelFormat.TITLE)));
				sheet.addCell(new Label(1, cellIdx, "응답 내용", setFormat(ExcelFormat.TITLE)));
				cellIdx++;
				if (responseList != null && responseList.size() > 0) {
					for (int j = 0; j < responseList.size(); j++) {
						PollResponseMngVO response = responseList.get(j);
						sheet.mergeCells(1, cellIdx, 9, cellIdx);
						sheet.addCell(new Label(0, cellIdx, String.valueOf(j + 1), setFormat(ExcelFormat.DATA_CENTRE)));
						sheet.addCell(new Label(1, cellIdx, response.getValue(), setFormat(ExcelFormat.DATA_CENTRE)));
						cellIdx++;
					}
				} else {
					sheet.mergeCells(0, cellIdx, 9, cellIdx);
					sheet.addCell(new Label(0, cellIdx, "응답 내용이 없습니다.", setFormat(ExcelFormat.DATA_CENTRE)));
				}
			} else if (PollQuestionType.RANKING.getCode().equals(question.getType())) {
				for (int j = 0; j < question.getAnswerList().size(); j++) {
					PollAnswerMngVO answer = question.getAnswerList().get(j);
					sheet.mergeCells(1, cellIdx, 9, cellIdx);
					sheet.addCell(new Label(0, cellIdx, "보기" + (j + 1), setFormat(ExcelFormat.TITLE)));
					sheet.addCell(new Label(1, cellIdx, answer.getAnswer(), setFormat(ExcelFormat.TITLE)));
					cellIdx++;
					sheet.addCell(new Label(7, cellIdx, "순위", setFormat(ExcelFormat.DATA_CENTRE)));
					sheet.addCell(new Label(8, cellIdx, "응답자", setFormat(ExcelFormat.DATA_CENTRE)));
					sheet.addCell(new Label(9, cellIdx, "응답율", setFormat(ExcelFormat.DATA_CENTRE)));
					cellIdx++;
					for (int k = 0; k < question.getAnswerList().size(); k++) {
						int answerCnt = 0;
						double percent = 0.0;
						for (PollResponseMngVO rep : answer.getResponseList()) {
							if (rep.getValue().equals(String.valueOf(k + 1))) {
								answerCnt++;
							}
						}
						percent = ((double) answerCnt / (double) answer.getResponseList().size()) * 100;
						sheet.addCell(new Label(7, cellIdx, (k + 1) + "순위", setFormat(ExcelFormat.DATA_CENTRE)));
						sheet.addCell(new Label(8, cellIdx, answerCnt + "명", setFormat(ExcelFormat.DATA_CENTRE)));
						sheet.addCell(new Label(9, cellIdx, (percent > 0.0 ? String.valueOf(percent).substring(0, String.valueOf(percent).indexOf(".") + 2) : 0.0) + "%", setFormat(ExcelFormat.DATA_CENTRE)));
						cellIdx++;
					}
				}
			} else if (PollQuestionType.MATRIX.getCode().equals(question.getType())) {
				sheet.mergeCells(0, cellIdx, 3, cellIdx);
				sheet.addCell(new Label(0, cellIdx, "질문", setFormat(ExcelFormat.TITLE)));
				sheet.mergeCells(4, cellIdx, 7, cellIdx);
				sheet.addCell(new Label(4, cellIdx, "응답내용", setFormat(ExcelFormat.TITLE)));
				sheet.addCell(new Label(8, cellIdx, "참여자수", setFormat(ExcelFormat.TITLE)));
				sheet.addCell(new Label(9, cellIdx, "참여비율", setFormat(ExcelFormat.TITLE)));
				cellIdx++;
				for (PollMatrixMngVO matrixRow : question.getMatrixList()) {
					sheet.mergeCells(0, cellIdx, 3, cellIdx + question.getMatrixColList().size() - 1);
					sheet.addCell(new Label(0, cellIdx, matrixRow.getMatrixQuestion(), setFormat(ExcelFormat.DATA_CENTRE)));
					for (int j = 0; j < question.getMatrixColList().size(); j++) {
						PollMatrixColMngVO matrixCol = question.getMatrixColList().get(j);
						int colIdx = cellIdx + j;
						sheet.mergeCells(4, colIdx, 7, colIdx);
						sheet.addCell(new Label(4, colIdx, matrixCol.getMatrix(), setFormat(ExcelFormat.DATA_CENTRE)));
						int answerCnt = 0;
						double percent = 0.0;
						for (PollResponseMngVO rep : matrixRow.getResponseList()) {
							if (rep.getMatrixAnswerIdx() == matrixCol.getIdx()) {
								answerCnt++;
							}
						}
						percent = ((double) answerCnt / (double) matrixRow.getResponseList().size()) * 100;
						sheet.addCell(new Label(8, colIdx, answerCnt + "명", setFormat(ExcelFormat.DATA_CENTRE)));
						sheet.addCell(new Label(9, colIdx, (percent > 0.0 ? String.valueOf(percent).substring(0, String.valueOf(percent).indexOf(".") + 2) : 0.0) + "%", setFormat(ExcelFormat.DATA_CENTRE)));
					}
					cellIdx = cellIdx + question.getMatrixColList().size();
				}
			}
		}
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
	public void setHeader(WritableSheet sheet, String title, int userCnt, String header) throws RowsExceededException, WriteException {
		/** 제목 설정 */
		sheet.mergeCells(0, 0, 9, 0);
		sheet.setRowView(0, 500);
		sheet.addCell(new Label(0, 0, title, setFormat(ExcelFormat.HEADER)));
		/** 제목 다음 라인 참여자 수 */
		sheet.mergeCells(0, 1, 7, 1);
		sheet.addCell(new Label(0, 1, "", setFormat(ExcelFormat.DATA_CENTRE)));
		sheet.addCell(new Label(8, 1, "참여자수", setFormat(ExcelFormat.TITLE)));
		sheet.addCell(new Label(9, 1, userCnt + "명", setFormat(ExcelFormat.DATA_CENTRE)));
		if (StringUtil.isNotBlank(header)) {
			/** 설문조사 헤더 문구 출력 */
			sheet.mergeCells(0, 2, 9, 2);
			sheet.setRowView(2, 1500);
			sheet.addCell(new Label(0, 2, header, setFormat(ExcelFormat.DATA_LEFT)));
		}
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

}