package egovframework.portal.sys.dynamic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.sys.dynamic.LinkType;
import egovframework.portal.sys.dynamic.service.DataMngCreatorService;
import egovframework.portal.sys.dynamic.service.DynamicDataMngService;
import egovframework.portal.sys.dynamic.vo.CategoryDefVO;
import egovframework.portal.sys.dynamic.vo.CategoryVO;
import egovframework.portal.sys.dynamic.vo.ColumnDefVO;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.portal.util.WriterUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.portal.util.excel.ExcelUtilConfig;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.arnx.jsonic.JSON;

/**
 * 동적 현황 데이터 다운로드 CONTORLLER
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2016. 09. 29.	권태성				최초 생성 및 코딩
 * 2017. 01. 05.	J.Ryeon Lee			웹 취약성 보완
 * 2017. 01. 11.	J.Ryeon Lee			클라이언트 데이터 다운로드 리퀘스트 매핑 추가
 * 2017. 01. 13.	J.Ryeon Lee			클라이언트 데이터 다운로드시 출력 컬럼 검증 로직 추가
 * 2017. 07. 14.	권태성				엑셀 다운로드 처리를 ExcelUtil을 통해서 처리하도록 변경
 * </pre>
 *
 * @author 권태성
 * @since 2016. 9. 29.
 */
@Controller
public class DynamicDataDownloadController {

	@Autowired
	private DataMngCreatorService dataMngCreaterService;
	@Autowired
	private DynamicDataMngService dynamicDataMngService;

	//private static final Logger LOGGER = LogManager.getLogger();

	@RequestMapping("/**/{urlName}/data/download.do")
	public void clientDynamicDataExcelDownload(@PathVariable String urlName, @RequestParam(required = false) String category, @RequestParam(required = false) String category2, @RequestParam(required = false, defaultValue = "N") String format, HttpServletRequest req, HttpServletResponse rep) throws Exception {
		String tableName = dataMngCreaterService.getTableName(urlName);
		DataMngCreatorVO tableDef = dataMngCreaterService.getEntity(tableName);
		if (tableDef != null && "Y".equals(tableDef.getFileDownYn())) {
			dynamicDataExcelDownload(urlName, category, category2, "Y", format, req, rep);
		} else {
			WriterUtil.flushJSAlert(rep, WriterUtil.createJsAlertContent("잘못된 접근입니다.", "window.close();"));
		}
	}

	/**
	 * 현황 엑셀 다운로드
	 *
	 * @Method Name : dynamicDataExcelDownload
	 * @param urlName
	 * @param req
	 * @param rep
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping("/sys/dataMng/{urlName}/dynamicDataDownloader.do")
	public void dynamicDataExcelDownload(@PathVariable String urlName, @RequestParam(required = false) String category,
		@RequestParam(required = false) String category2, @RequestParam(required = false, defaultValue = "N") String userDownloadYn,
		@RequestParam(required = false, defaultValue = "N") String format, HttpServletRequest req, HttpServletResponse rep) throws Exception {

		Boolean isAdminDownload = userDownloadYn.equals("N");
		String tableName = dataMngCreaterService.getTableName(urlName);
		DataMngCreatorVO tableDef = dataMngCreaterService.getEntity(tableName);
		tableDef.setCategory1List(dataMngCreaterService.getCategoryList(new CategoryVO(tableName, 1)));
		tableDef.setCategory2List(dataMngCreaterService.getCategoryList(new CategoryVO(tableName, 2)));
		if (!isAdminDownload) {
			//유저 다운로드의 경우 전화번호 컬럼 제거
			for (int i = 0; i < tableDef.getColumnDefList().size(); i++) {
				ColumnDefVO def = tableDef.getColumnDefList().get(i);
				if ("전화번호".equals(def.getDescription())) {
					tableDef.getColumnDefList().remove(i);
				}
			}
		}
		if (tableDef != null) {
			List<Map<String, String>> result = dynamicDataMngService.getAllList(tableDef.getTableName(), category, category2, userDownloadYn);
			setHeader(tableDef, true);
			ExcelUtil excel = ExcelUtil.INSTANCE;
			ExcelUtilConfig excelConfig = new ExcelUtilConfig(urlName, tableDef.getTableDesc() + " 현황", result, Boolean.TRUE, (!"Y".equals(format)));
			excelConfig.setHeaderNameValues(setHeader(tableDef, ("Y".equals(format))));
			excelConfig.setDataKeyListValues(setDataKey(tableDef, ("Y".equals(format))));
			excelConfig.setAllColSize(30);
			excel.setExcel(rep, excelConfig);
		} else {
			WriterUtil.flushJSAlert(rep, "잘못된 요청입니다.");
		}
	}

	/**
	 * 해당 현황의 데이터 목록을 가져올때 사용 할 Key 생성
	 *
	 * @param tableDef
	 * @param flushAllCol
	 * @return
	 */
	public String setDataKey(DataMngCreatorVO tableDef, boolean flushAllCol) {
		String ret = "";
		for (ColumnDefVO def : tableDef.getColumnDefList()) { // 정의된 컬럼값 출력
			if (flushAllCol || def.getDisplay().equals("Y")) { // 출력할 수 있는 컬럼 검증
				ret = retAdd(ret, "COL_" + def.getName());
			}
		}
		if (tableDef.getCategory1List() != null && tableDef.getCategory1List().size() > 0) { // 카테고리 사용시
			ret = retAdd(ret, "CATEGORY");
		}
		if (tableDef.getCategory2List() != null && tableDef.getCategory2List().size() > 0) { // 카테고리 사용시
			ret = retAdd(ret, "CATEGORY2");
		}
		if (flushAllCol && tableDef.getUseMap().equals("Y")) { // 맵 사용시
			ret = retAdd(ret, "COL_LAT");
			ret = retAdd(ret, "COL_LNG");
		}
		if (tableDef.getLinkType().equals(LinkType.HOMEPAGE.getCode())) { // 홈페이지 링크 사용시
			ret = retAdd(ret, "COL_HP_URL");
		}
		return ret;
	}

	/**
	 * 해당 현황의 데이터 타이틀을 생성하는 메소드
	 *
	 * @param tableDef
	 * @param flushAllCol
	 * @return
	 * @throws RowsExceededException
	 * @throws WriteException
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	public String setHeader(DataMngCreatorVO tableDef, boolean flushAllCol) throws RowsExceededException, WriteException {
		String ret = "";
		for (ColumnDefVO def : tableDef.getColumnDefList()) { // 정의된 컬럼값 출력
			if (flushAllCol || def.getDisplay().equals("Y")) { // 출력할 수 있는 컬럼 검증
				ret = retAdd(ret, def.getDescription());
			}
		}
		List<CategoryDefVO> categoryNames = (List<CategoryDefVO>) JSON.decode(tableDef.getCategoryDefs(), (new ArrayList<CategoryDefVO>() {
		}).getClass().getGenericSuperclass());
		if (tableDef.getCategory1List() != null && tableDef.getCategory1List().size() > 0) { // 카테고리 사용시
			ret = retAdd(ret, categoryNames.get(0).getName());
		}
		if (tableDef.getCategory2List() != null && tableDef.getCategory2List().size() > 0) { // 카테고리 사용시
			ret = retAdd(ret, categoryNames.get(1).getName());
		}
		if (flushAllCol && tableDef.getUseMap().equals("Y")) { // 맵 사용시
			ret = retAdd(ret, "위도");
			ret = retAdd(ret, "경도");
		}
		if (tableDef.getLinkType().equals(LinkType.HOMEPAGE.getCode())) { // 홈페이지 링크 사용시
			ret = retAdd(ret, "홈페이지");
		}
		return ret;
	}

	public String retAdd(String ret, String target) {
		return (ret.length() > 0 ? ret + "," + target : ret + target);
	}

}