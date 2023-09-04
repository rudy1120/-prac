package egovframework.portal.sys.dynamic.service.impl;

import java.io.File;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.EgovProperties;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.common.Constant;
import egovframework.portal.sys.common.service.FileUploadHelperService;
import egovframework.portal.sys.dynamic.CategoryType;
import egovframework.portal.sys.dynamic.DataType;
import egovframework.portal.sys.dynamic.LinkType;
import egovframework.portal.sys.dynamic.mapper.DynamicDataMngMapper;
import egovframework.portal.sys.dynamic.service.DataMngCreatorService;
import egovframework.portal.sys.dynamic.service.DynamicDataMngService;
import egovframework.portal.sys.dynamic.vo.CategoryVO;
import egovframework.portal.sys.dynamic.vo.ColumnDefVO;
import egovframework.portal.sys.dynamic.vo.ColumnVO;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.portal.util.CollectionUtil;
import egovframework.portal.util.FileUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.excel.ExcelUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 동적 현황관리 SERVICE IMPL
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 05. 04.	J.Ryeon Lee			최초 생성 및 코딩
 * 2016. 11. 30.	J.Ryeon Lee			Clob 데이터 crlf >> br 태그 변환 처리 추가
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 05. 04.
 */
@Service
public class DynamicDataMngServiceImpl extends EgovAbstractServiceImpl implements DynamicDataMngService {

	@Resource(name = "dynamicDataMngMapper")
	protected DynamicDataMngMapper dynamicDataMngMapper;
	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;
	@Autowired
	private FileUploadHelperService fileUploadHelperService;
	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;
	@Autowired
	private DataMngCreatorService dataMngCreatorService;

	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public int getTotalCnt(Map<String, ? extends Object> searchVO) {
		Map<String, Object> paramVO = new LinkedHashMap<String, Object>();
		paramVO = CollectionUtil.copyMapToMap(searchVO);
		paramVO = setSearchCategoryAll(paramVO);
		return dynamicDataMngMapper.getTotalCnt(paramVO);
	}

	@Override
	public List<Map<String, String>> getList(Map<String, ? extends Object> searchVO, DataMngCreatorVO tableDef) throws Exception {
		Map<String, Object> paramVO = new LinkedHashMap<String, Object>();
		paramVO = CollectionUtil.copyMapToMap(searchVO);
		paramVO = setSearchCategoryAll(paramVO);
		List<Map<String, String>> rtn = dynamicDataMngMapper.getList(paramVO);
		for (Map<String, String> entity : rtn) {
			for (ColumnDefVO colDef : tableDef.getColumnDefList()) {
				if ("Y".equals(colDef.getNewLine())) {
					decode(entity, Boolean.FALSE, Boolean.FALSE);
				}
			}
		}
		return rtn;
	}

	@Override
	public List<Map<String, String>> getAllList(String tableName, String category, String category2, String userDownloadYn) {
		Map<String, Object> paramVO = new LinkedHashMap<String, Object>();
		paramVO.put("tableName", tableName);
		paramVO.put("CATEGORY", StringUtil.changeBlankToNull(category));
		paramVO.put("CATEGORY2", StringUtil.changeBlankToNull(category2));
		paramVO.put("userDownloadYn", userDownloadYn);
		paramVO = setSearchCategoryAll(paramVO);
		return dynamicDataMngMapper.getAllList(paramVO);
	}

	@Override
	public Map<String, String> getEntity(String tableName, String idx) throws Exception {
		return getEntity(tableName, idx, Boolean.FALSE);
	}

	@Override
	public Map<String, String> getEntity(String tableName, String idx, Boolean nl2Br) throws Exception {
		Map<String, String> rtn = null;
		if (StringUtil.isNotBlank(idx) && StringUtil.isNotBlank(tableName)) {
			DataMngCreatorVO searchVO = new DataMngCreatorVO();
			searchVO.setTableName(tableName);
			searchVO.setIdx(idx);

			rtn = dynamicDataMngMapper.getEntity(searchVO);
			if (rtn != null) {
				decode(rtn, nl2Br, Boolean.TRUE);
			}
		}

		return rtn;
	}

	@Override
	public void insert(MultipartHttpServletRequest multiRequest, DataMngCreatorVO tableDef, Map<String, String> insertVO) throws Exception {
		String UPLOAD_DIR = EgovProperties.getProperty(Constant.DYNAMIC_FILE_SAVE_KEY);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tableName", tableDef.getTableName());
		params.put("useFile", tableDef.getUseFile());

		/* checkbox value 처리 */

		insertVO = setCheckbox(multiRequest, tableDef, insertVO);

		/* 개별 필드 첨부파일 처리 */

		for (ColumnDefVO col : tableDef.getColumnDefList()) {
			if ("file".equals(col.getType()) && StringUtil.isBlank(insertVO.get("_excel"))) {
				insertVO.put(col.getName(), uploadColAttachFile(multiRequest, UPLOAD_DIR + tableDef.getUrlName() + "/", col.getName(), insertVO));
				insertVO.remove("dmt_cn_" + col.getName());
			}
		}
		if (StringUtil.isNotBlank(insertVO.get("_excel"))) {
			insertVO.remove("_excel");
		}

		/* insert 작업을 위한 파라미터 정비 */

		List<ColumnVO> columns = new ArrayList<ColumnVO>();
		for (Entry<String, String> entry : insertVO.entrySet()) {
			if (isColumn(entry.getKey()) && StringUtil.isNotEmpty(entry.getValue()) && !entry.getKey().startsWith("file_")) {
				columns.add(new ColumnVO(isColumn(entry.getKey()) ? "col_" + entry.getKey() : entry.getKey(), entry.getValue()));
			}
		}

		if (StringUtil.isNotEmpty(insertVO.get("CATEGORY"))) {
			columns.add(new ColumnVO("CATEGORY", insertVO.get("CATEGORY")));
		}
		if (StringUtil.isNotEmpty(insertVO.get("CATEGORY2"))) {
			columns.add(new ColumnVO("CATEGORY2", insertVO.get("CATEGORY2")));
		}

		/* 첨부파일 반영 */

		if (multiRequest != null && tableDef.getUseFile().equals("Y")) {
			columns.add(new ColumnVO("attach_id", fileUtil.storeFileAndGetAttachIdByPath(multiRequest, UPLOAD_DIR + tableDef.getUrlName() + "/", "", insertVO.get("attach_id"), Boolean.TRUE)));
		}
		if (tableDef.getUseSecret().equals("Y") && StringUtil.isNotEmpty(insertVO.get("ISSECRET"))) {
			columns.add(new ColumnVO("is_secret", insertVO.get("ISSECRET")));
		}

		/* 엑셀 업로드시 첨부파일 처리 */

		if (insertVO.get("filePaths") != null || insertVO.get("fileNames") != null) {
			LOGGER.error(">>> filePaths : " + insertVO.get("filePaths").toString());
			LOGGER.error(">>> fileNames : " + insertVO.get("fileNames").toString());
			String[] filePaths = insertVO.get("filePaths").split(">");
			String[] fileNames = insertVO.get("fileNames").split(">");
			String attachId = fileInfoAdd(filePaths, fileNames);
			if (StringUtil.isNotBlank(attachId)) {
				columns.add(new ColumnVO("attach_id", attachId));
			}
		}

		/** 개별 첨부파일 처리 */

		for (Entry<String, String> entry : insertVO.entrySet()) {
			if (entry.getKey().startsWith("file_")) {
				if (StringUtil.isNotBlank(entry.getValue())) {
					String path = entry.getValue().substring(0, entry.getValue().lastIndexOf("/") + 1);
					String name = entry.getValue().substring(entry.getValue().lastIndexOf("/") + 1, entry.getValue().length());
					String[] filePaths = { path };
					String[] fileNames = { name };
					String attachId = fileInfoAdd(filePaths, fileNames);
					if (StringUtil.isNotBlank(attachId)) {
						columns.add(new ColumnVO(entry.getKey().replace("file_", "col_"), attachId));
					}
				}
			}
		}

		/* insert process */
		params.put("columns", columns);
		dynamicDataMngMapper.insert(params);
	}

	@Override
	public void update(MultipartHttpServletRequest multiRequest, DataMngCreatorVO tableDef, Map<String, String> updateVO) throws Exception {
		String UPLOAD_DIR = EgovProperties.getProperty(Constant.DYNAMIC_FILE_SAVE_KEY);
		Map<String, Object> params = new HashMap<>();
		List<ColumnVO> columns = new ArrayList<ColumnVO>();

		/* checkbox value 처리 */

		updateVO = setCheckbox(multiRequest, tableDef, updateVO);

		/* 개별 필드 첨부파일 처리 */

		for (ColumnDefVO col : tableDef.getColumnDefList()) {
			if ("file".equals(col.getType())) {
				updateVO.put(col.getName(), uploadColAttachFile(multiRequest, UPLOAD_DIR + tableDef.getUrlName() + "/", col.getName(), updateVO));
				updateVO.remove("dmt_cn_" + col.getName());
			}
		}

		/* update 작업을 위한 파라미터 정비 */

		for (Entry<String, String> entry : updateVO.entrySet()) {
			if (isColumn(entry.getKey())) { // ignore
				for (ColumnDefVO col : tableDef.getColumnDefList()) {
					if (entry.getKey().equalsIgnoreCase(col.getName())) {
						columns.add(new ColumnVO("col_" + entry.getKey(), entry.getValue(), col.getType()));
					}
				}
			}
		}

		/* 첨부파일 반영 */

		if (tableDef.getUseFile().equals("Y")) {
			columns.add(
				new ColumnVO("attach_id",
					fileUtil.storeFileAndGetAttachIdByPath(multiRequest, UPLOAD_DIR + tableDef.getUrlName() + "/", "", updateVO.get("attach_id"), Boolean.TRUE), //
					DataType.VARCHAR2.name().toLowerCase()) //
			);
		}
		if (tableDef.getUseSecret().equals("Y") && StringUtil.isNotEmpty(updateVO.get("ISSECRET"))) {
			columns.add(new ColumnVO("is_secret", updateVO.get("ISSECRET")));
		}

		if (StringUtil.isNotEmpty(updateVO.get("CATEGORY"))) {
			columns.add(new ColumnVO("CATEGORY", updateVO.get("CATEGORY"), DataType.VARCHAR2.name().toLowerCase()));
		}
		if (StringUtil.isNotEmpty(updateVO.get("CATEGORY2"))) {
			columns.add(new ColumnVO("CATEGORY2", updateVO.get("CATEGORY2"), DataType.VARCHAR2.name().toLowerCase()));
		}

		/* update process */

		params.put("tableName", tableDef.getTableName());
		params.put("columns", columns);
		params.put("updateVO", updateVO);
		dynamicDataMngMapper.update(params);
	}

	@Override
	public void delete(DataMngCreatorVO tableDef, Map<String, String> entity, Map<String, String> deleteVO) {
		deleteVO.put("tableName", tableDef.getTableName());
		dynamicDataMngMapper.delete(deleteVO);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int uploadExcel(MultipartFile file, DataMngCreatorVO tableDef, String mode) throws Exception {
		String UPLOAD_DIR = EgovProperties.getProperty(Constant.DYNAMIC_FILE_SAVE_KEY);
		String uploadedFileName = fileUploadHelperService.uploadFile(UPLOAD_DIR, file);
		tableDef.setCategory1List(dataMngCreatorService.getCategoryList(new CategoryVO(tableDef.getTableName(), 1)));
		tableDef.setCategory2List(dataMngCreatorService.getCategoryList(new CategoryVO(tableDef.getTableName(), 2)));

		try {
			Workbook workbook = Workbook.getWorkbook(new File(UPLOAD_DIR + uploadedFileName));
			Sheet sheet = workbook.getSheet(0);
			int row = sheet.getRows();

			List<Map<String, String>> recordList = new ArrayList<Map<String, String>>();
			Map<String, String> record = null;
			Cell[] cells = null;
			for (int i = 2; i < row; i++) {
				record = new HashMap<String, String>();
				cells = sheet.getRow(i);
				int j = 1;
				for (ColumnDefVO def : tableDef.getColumnDefList()) { // 정의된 컬럼값 세팅
					String contents = ExcelUtil.getContents(cells, j++);
					if ("file".equals(def.getType()) && contents.indexOf("/home/webdata") != -1) {
						record.put("file_" + def.getName(), contents);
					} else {
						record.put(def.getName(), contents);
					}
				}

				if (tableDef.getCategory1List() != null && tableDef.getCategory1List().size() > 0) { // 카테고리 사용시
					record.put("CATEGORY", ExcelUtil.getContents(cells, j++));
				}

				if (tableDef.getCategory2List() != null && tableDef.getCategory2List().size() > 0) { // 카테고리 사용시
					record.put("CATEGORY2", ExcelUtil.getContents(cells, j++));
				}

				if (tableDef.getUseMap().equals("Y")) { // 맵 사용시
					record.put("LAT", ExcelUtil.getContents(cells, j++));
					record.put("LNG", ExcelUtil.getContents(cells, j++));
				}

				if (tableDef.getLinkType().equals(LinkType.HOMEPAGE.getCode())) { // 홈페이지 사용시
					record.put("HP_URL", ExcelUtil.getContents(cells, j++));
				}

				if ("Y".equals(tableDef.getUseFile())) {
					record.put("filePaths", ExcelUtil.getContents(cells, j++));//첨부파일 경로
					record.put("fileNames", ExcelUtil.getContents(cells, j++));//첨부파일 이름
				}

				recordList.add(record);
			}

			if ("copy".equals(mode)) {
				dynamicDataMngMapper.perfectlyDeleteAll(tableDef.getTableName()); // 기존 데이터 삭제
			}

			for (Map<String, String> insertVO : recordList) {
				insertVO.put("_excel", "Y");
				insert(null, tableDef, insertVO);
			}

			return recordList.size();
		} catch (StringIndexOutOfBoundsException | NullPointerException | ArrayIndexOutOfBoundsException | DataAccessException e) {
			e.printStackTrace();
			throw e;
		} finally {
			FileUtil.removeFullFile(UPLOAD_DIR, uploadedFileName, "/"); // 처리를 위해 등록한 임시 엑셀 파일을 삭제
		}
	}

	private boolean isNotColumn(String key) {
		return StringUtil.isNotBlank(key) && StringUtil.contains(key, "IDX,tableName,searchType,searchTxt,page,mId,file_cn,attach_id,filePaths,fileNames,ISSECRET,CATEGORY,CATEGORY2,category,category2".split(","));
	}

	private boolean isColumn(String key) {
		return !isNotColumn(key);
	}

	/**
	 * 엑셀 업로드시 파일정보 추가
	 *
	 * @Method Name : fileInfoAdd
	 * @param filePaths
	 * @param fileNames
	 * @param columns
	 * @param values
	 * @throws Exception
	 */
	private String fileInfoAdd(String[] filePaths, String[] fileNames) throws Exception {

		if (filePaths.length == fileNames.length) {
			List<FileVO> files = new ArrayList<>();
			String attachId = fileUtil.getNextIdString();
			for (int i = 0; i < filePaths.length; i++) {
				String filePath = filePaths[i].trim();
				String fileName = fileNames[i].trim();
				if (!"".equals(filePath) && !"".equals(fileName)) {
					File dest = new File(filePath + fileName);

					if (dest.exists()) {
						FileVO file = new FileVO();
						file.setFileExtsn(fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()));
						file.setFileSn(String.valueOf(i));
						file.setFileStreCours(filePath);
						file.setOrignlFileNm(fileName);
						file.setFileMg("" + dest.length());
						file.setStreFileNm(fileName);
						file.setAtchFileId(attachId);
						files.add(file);
					} else {
						LOGGER.error(">> file not found error / filePath : " + filePath + " / fileName : " + fileName);
						throw new Exception();
					}
				}
			}
			return fileMngService.insertFileInfs(files);

		} else {
			LOGGER.error(">> not matching filePath & fileName");
			throw new Exception();
		}

	}

	/**
	 * 카테고리 검색 split 처리
	 *
	 * @param searchVO
	 * @param type
	 * @return
	 */
	public Map<String, Object> setSearchCategory(Map<String, Object> searchVO, CategoryType categoryType) {
		if (searchVO.get("CATEGORY" + categoryType.getCode()) != null) {
			if (searchVO.get("CATEGORY" + categoryType.getCode()).toString().indexOf(",") != -1) {
				searchVO.put("CATEGORY" + categoryType.getCode(), searchVO.get("CATEGORY" + categoryType.getCode()).toString().split(","));
			} else {
				if (!"".equals(searchVO.get("CATEGORY" + categoryType.getCode()).toString())) {
					String[] arry = { searchVO.get("CATEGORY" + categoryType.getCode()).toString() };
					searchVO.put("CATEGORY" + categoryType.getCode(), arry);
				}
			}
		}
		return searchVO;
	}

	/**
	 * 카테고리 1, 2 모두 조회
	 *
	 * @param paramVO
	 * @return
	 */
	public Map<String, Object> setSearchCategoryAll(Map<String, Object> paramVO) {
		paramVO = setSearchCategory(paramVO, CategoryType.CATEGORY1);
		paramVO = setSearchCategory(paramVO, CategoryType.CATEGORY2);
		return paramVO;
	}

	/**
	 * 체크박스 입력 값 배열을 하나의 필드에 저장하기 위한 메소드
	 *
	 * @param multiRequest
	 * @param tableDef
	 * @param updateVO
	 * @return
	 */
	private Map<String, String> setCheckbox(MultipartHttpServletRequest multiRequest, DataMngCreatorVO tableDef, Map<String, String> updateVO) {
		for (ColumnDefVO column : tableDef.getColumnDefList()) {
			if ("C".equals(column.getSelectType())) {
				String[] values = multiRequest.getParameterValues(column.getName());
				if (values != null) {
					StringBuilder builder = new StringBuilder();
					for (String value : values) {
						builder.append(value + ",");
					}
					updateVO.put(column.getName(), builder.toString().substring(0, builder.toString().length() - 1));
				}
			}
		}
		return updateVO;
	}

	private String uploadColAttachFile(MultipartHttpServletRequest multiRequest, String uploadPath, String target, Map<String, String> paramVO) throws Exception {
		if (multiRequest != null) {
			List<FileVO> _result = null;
			final Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
			files.put(target, multiRequest.getFileMap().get("dmt_" + target));
			String _atchFileId = "";
			if (!files.isEmpty() && files.get(target) != null) {
				_result = fileUtil.parseFileInf(files, "DMT_", 0, "", uploadPath, false, multiRequest);
				if (_result != null && _result.size() > 0) {
					_result.get(0).setFileCn(paramVO.get("dmt_cn_" + target));
					_atchFileId = fileMngService.insertFileInfs(_result); // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
				}
				return _atchFileId;
			} else {
				if(!paramVO.get(target).isEmpty()) {
					FileVO fvo = new FileVO();
					fvo.setAtchFileId(paramVO.get(target));
					fvo.setFileSn("0");
					fvo.setFileCn(paramVO.get("file_cn"));
					fileMngService.updateFileCn(fvo);
				}
				return paramVO.get(target);
			}
		}
		return paramVO.get(target);
	}

	private Map<String, String> decode(Map<String, String> entity, Boolean nl2Br, Boolean isView) throws Exception {
		if (isView) {
			entity.remove("IS_DEL");
//			entity.remove("CREATE_DATE");
			entity.remove("UPDATE_DATE");
			entity.remove("DELETE_DATE");
		}

		for (Entry<String, String> entry : entity.entrySet()) {
			if ((Object) entry.getValue() instanceof java.sql.Clob) {
				if (nl2Br) {
					entity.put(entry.getKey(), StringUtil.nl2br(StringUtil.clobToString((Clob) (Object) entry.getValue()), 2));
				} else {
					entity.put(entry.getKey(), StringUtil.clobToString((Clob) (Object) entry.getValue()));
				}
			}
		}

		return entity;
	}

	@Override
	public void batchDelete(DataMngCreatorVO tableDef, List<String> idxs) {
		Map<String, Object> params = new HashMap<>();
		params.put("tableName", tableDef.getTableName());
		params.put("idxs", idxs);
		dynamicDataMngMapper.batchDelete(params);
	}

}