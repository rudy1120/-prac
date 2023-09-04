package egovframework.portal.sys.dynamic.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("dynamicDataMngMapper")
public interface DynamicDataMngMapper {

	public int getTotalCnt(Map<String, Object> searchVO);

	public List<Map<String, String>> getList(Map<String, Object> searchVO);

	public List<Map<String, String>> getAllList(Map<String, Object> params);

	public Map<String, String> getEntity(DataMngCreatorVO searchVO);

	public void insert(Map<String, Object> params);

	public void update(Map<String, Object> params);

	public void delete(Map<String, String> deleteVO);

	public void perfectlyDeleteAll(String tableName);

	public void batchDelete(Map<String, Object> params);

}
