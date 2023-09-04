package egovframework.portal.sys.dynamic.service;

import java.util.List;

import egovframework.portal.sys.dynamic.vo.CategoryVO;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;

/**
 * 동적 현황관리 생성 SERVICE
 *
 * @author J.Ryeon Lee
 * @since 2016.05.03
 */
public interface DataMngCreatorService {

	int getTotalCnt(DataMngCreatorVO searchVO);

	List<DataMngCreatorVO> getList(DataMngCreatorVO searchVO);

	DataMngCreatorVO getEntity(DataMngCreatorVO searchVO) throws Exception;

	DataMngCreatorVO getEntity(String tableName) throws Exception;

	void insert(DataMngCreatorVO insertVO);

	void update(DataMngCreatorVO originVO, DataMngCreatorVO updateVO) throws Exception;

	void delete(DataMngCreatorVO deleteVO);

	int getTotalCntUsingName(String tableName);

	/**
	 * urlName으로 tableName을 조회합니다.
	 * @Method Name : getUrlName
	 * @param urlName
	 * @return
	 */
	public String getTableName(String urlName);

	/**
	 * 카테고리 목록 조회
	 * @Method Name : getCategoryList
	 * @param tableName
	 * @return
	 */
	public List<CategoryVO> getCategoryList(CategoryVO vo);

	/** 헤더 가이드 변경 */
	void updateHeader(DataMngCreatorVO tableDef);

}
