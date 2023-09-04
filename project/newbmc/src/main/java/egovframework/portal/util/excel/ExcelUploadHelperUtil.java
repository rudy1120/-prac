package egovframework.portal.util.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import egovframework.portal.util.StringUtil;
import jxl.Cell;
import jxl.Sheet;

/**
 * 엑셀 업로드 지원 util
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 4. 25.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 4. 25.
 */
public enum ExcelUploadHelperUtil {

	INSTANCE;

	private static final Logger LOGGER = LogManager.getLogger();

	public static List<Map<String, String>> dataList(Sheet sheet) {
		return dataList(sheet, Boolean.TRUE);
	}

	/** @param ignore 첫번째 셀의 value가 blank인 경우 row에 추가하지 않음 */
	public static List<Map<String, String>> dataList(Sheet sheet, Boolean ignore) {
		List<Map<String, String>> recordList = new ArrayList<>();

		try {
			int row = sheet.getRows();
			List<String> columnInfo = extractColumnInfo(sheet.getRow(1)); // 0th row는 해당 파일 타이틀 등을 위해 사용

			Map<String, String> record = null;
			Cell[] cells = null;
			for (int i = 2; i < row; i++) {
				record = new HashMap<>();
				cells = sheet.getRow(i);

				for (int j = 0; j < columnInfo.size(); j++) {
					record.put("e" + j, ExcelUtil.getContents(cells, j));
				}

				if (!(ignore && StringUtil.isBlank(record.get("e0")))) {
					recordList.add(record);
				}
			}

		} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
			LOGGER.error(">> failed to extract data from a excel", e);
		}

		return recordList;
	}

	private static List<String> extractColumnInfo(Cell[] cells) {
		List<String> columnNames = new ArrayList<>();
		for (Cell cell : cells) {
			if (StringUtil.isNotBlank(cell.getContents())) {
				columnNames.add(cell.getContents());
			}
		}

		return columnNames;
	}

}