package egovframework.portal.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import egovframework.portal.util.StringUtil;
import jxl.Cell;
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
 * 엑셀 생성을 위해 사용하는 유틸입니다.
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 3. 21.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 3. 21.
 */
public enum ExcelUtil {

	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * 엑셀 생성 메소드
	 *
	 * @author 권태성
	 * @param rep
	 * @param excelData
	 * @throws Exception
	 */
	public void setExcel(HttpServletResponse rep, ExcelUtilConfig excelData) throws Exception {

		if (excelData == null || !excelData.essentialCheck()) {
			LOGGER.error(">> ExcelUtil Msg : default value check error");
			throw new Exception();
		}

		//String uploadDir = "/home/webdata/egov_uploadFile/excel/" + System.currentTimeMillis() + "/";
		String uploadDir = "/home/webdata/egov_uploadFile/excel/";
		File dir = new File(uploadDir);
		if (!dir.exists()) {
			dir.setExecutable(false, true);
			dir.setReadable(true);
			dir.setWritable(false, true);
			dir.mkdirs();
		}

		WritableWorkbook workbook = Workbook.createWorkbook(new File(uploadDir + "default.xls"));

		byte[] bytestream = null;
		FileInputStream fileStream = null;

		rep.setHeader("Content-Disposition", "attachment; filename=" + excelData.getFileName() + ".xls");
		OutputStream outStream = rep.getOutputStream();

		try {

			WritableSheet sheet = workbook.createSheet("sheet1", 0);
			WritableCellFormat headerFormat = new WritableCellFormat(new WritableFont(WritableFont.ARIAL, 14, WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK, ScriptStyle.NORMAL_SCRIPT));
			headerFormat.setAlignment(Alignment.CENTRE);
			headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE); //셀 세로 정렬
			headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN); //테두리와 테두리 스타일 설정

			WritableCellFormat titleFormat = new WritableCellFormat();
			titleFormat.setAlignment(Alignment.CENTRE);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); //셀 세로 정렬
			titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN); //테두리와 테두리 스타일 설정
			titleFormat.setBackground(excelData.getTitleColor());

			WritableCellFormat inFormat = new WritableCellFormat();
			inFormat.setAlignment(Alignment.CENTRE);
			inFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			inFormat.setBorder(Border.ALL, BorderLineStyle.THIN);

			String header = excelData.getHeaderTitle();
			int col = 0;
			//헤더세팅
			col = setHeader(excelData, sheet, titleFormat, col);
			if (excelData.isUseNum()) {
				sheet.mergeCells(0, 0, col, 0); //타이틀 셀 병합
			} else {
				sheet.mergeCells(0, 0, col - 1, 0); //타이틀 셀 병합
			}
			Label titleL = new Label(0, 0, header, headerFormat);
			sheet.addCell(titleL);

			//데이터 세팅
			if (excelData.getDataYn()) {
				setData(excelData, inFormat, sheet);
			}

			workbook.write();
			workbook.close();

			File file = new File(uploadDir + "default.xls");
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
			LOGGER.error(">> failed DynamicDataDownloadController - dynamicDataExcelDownload", e);
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
	 * 헤더정보 세팅 메소드
	 *
	 * @author 권태성
	 * @param excelData
	 * @param sheet
	 * @param titleFormat
	 * @param col
	 * @return
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	private int setHeader(ExcelUtilConfig excelData, WritableSheet sheet, WritableCellFormat titleFormat, int col) throws RowsExceededException, WriteException {
		if (excelData.isUseNum()) {
			sheet.setColumnView(col, 10); //번호
			sheet.addCell(new Label(col, 1, "번호", titleFormat));
		}
		for (int i = 0; i < excelData.getHeaderNameList().size(); i++) {
			if (excelData.isUseNum()) {
				sheet.addCell(new Label(++col, 1, excelData.getHeaderNameList().get(i), titleFormat));
			} else {
				sheet.addCell(new Label(col++, 1, excelData.getHeaderNameList().get(i), titleFormat));
			}
			sheet.setColumnView(col, excelData.getColSizeList().get(i));
		}
		return col;
	}

	/**
	 * 데이터 세팅 메소드
	 *
	 * @author 권태성
	 * @param excelData
	 * @param inFormat
	 * @param sheet
	 * @throws Exception
	 */
	private void setData(ExcelUtilConfig excelData, WritableCellFormat inFormat, WritableSheet sheet) throws Exception {
		for (int i = 0; i < excelData.getDataList().size(); i++) {
			int k = 0;
			Map<String, String> element = excelData.getDataList().get(i);
			if (excelData.isUseNum()) {
				sheet.addCell(new Label(0, i + 2, String.valueOf(i + 1), inFormat)); //번호 설정
			}
			for (String key : excelData.getDataKeyList()) {
				int col = 0;
				if (!excelData.isUseNum()) {
					col = (k++);
				} else {
					col = (k++) + 1;
				}
				if ((Object) element.get(key) instanceof java.sql.Clob) {
					sheet.addCell(new Label(col, i + 2, StringUtil.clobToString((Clob) (Object) element.get(key)), inFormat));
					
				} else if ((Object) element.get(key) instanceof java.math.BigDecimal) {
					BigDecimal decimal = (BigDecimal) (Object) element.get(key);
					sheet.addCell(new Label(col, i + 2, decimal.toString(), inFormat));
					
				} else if ((Object) element.get(key) instanceof java.sql.Date) {
					Date date = (Date) (Object) element.get(key);
					sheet.addCell(new Label(col, i + 2, date.toString(), inFormat));
					
				} else {
					sheet.addCell(new Label(col, i + 2, String.valueOf(element.get(key)), inFormat));
					
				}
			}
		}
	}

	/**
	 * 셀 내의 컨텐츠를 가져오는 메소드
	 *
	 * @author J.Ryeon Lee
	 * @param cells
	 * @param i
	 * @return
	 */
	public static String getContents(Cell[] cells, int i) {
		return i < cells.length && cells[i] != null && StringUtil.isNotBlank(cells[i].getContents()) ? cells[i].getContents().trim() : "";
	}

}