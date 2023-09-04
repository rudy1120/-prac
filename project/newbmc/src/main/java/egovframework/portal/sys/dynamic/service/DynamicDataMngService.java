package egovframework.portal.sys.dynamic.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;

/**
 * 동적 현황관리 SERVICE
 *
 * @author J.Ryeon Lee
 * @since 2016.05.04
 */
public interface DynamicDataMngService {

	int getTotalCnt(Map<String, ? extends Object> searchVO);

	List<Map<String, String>> getList(Map<String, ? extends Object> searchVO, DataMngCreatorVO tableDef) throws Exception;

	List<Map<String, String>> getAllList(String tableName, String category, String category2, String userDownloadYn);

	Map<String, String> getEntity(String tableName, String idx) throws Exception;

	Map<String, String> getEntity(String tableName, String idx, Boolean nl2Br) throws Exception;

	void insert(MultipartHttpServletRequest multiRequest, DataMngCreatorVO tableDef, Map<String, String> insertVO) throws Exception;

	void update(MultipartHttpServletRequest multiRequest, DataMngCreatorVO tableDef, Map<String, String> updateVO) throws Exception;

	void delete(DataMngCreatorVO tableDef, Map<String, String> entity, Map<String, String> deleteVO);

	public int uploadExcel(MultipartFile file, DataMngCreatorVO tableDef, String mode) throws Exception;

	void batchDelete(DataMngCreatorVO tableDef, List<String> idxs);

}
