package egovframework.portal.sys.dynamic.mapper;

import java.util.List;

import egovframework.portal.sys.dynamic.vo.CategoryVO;
import egovframework.portal.sys.dynamic.vo.ColumnDefVO;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("dataMngCreatorMapper")
public interface DataMngCreatorMapper {

	public int getTotalCnt(DataMngCreatorVO searchVO);

	public List<DataMngCreatorVO> getList(DataMngCreatorVO searchVO);

	public DataMngCreatorVO getEntity(DataMngCreatorVO searchVO);

	public void insert(DataMngCreatorVO insertVO);

	public void update(DataMngCreatorVO updateVO);

	public void delete(DataMngCreatorVO deleteVO);

	public void createDynamicTable(DataMngCreatorVO insertVO);

	//MySql 버전은 테이블 생성시 적용
	//public void createDynamicSeq(DataMngCreatorVO insertVO);

	public void addDynamicColumns(DataMngCreatorVO updateVO);

	public void dropDynamicColumns(DataMngCreatorVO updateVO);

	public int getTotalCntUsingName(String tableName);

	/**
	 * urlName으로 tableName을 조회합니다.
	 *
	 * @Method Name : getUrlName
	 * @param urlName
	 * @author 권태성
	 * @return
	 */
	public String getTableName(String urlName);

	public void createTableComment(DataMngCreatorVO insertVO);

	//MySql 버전은 테이블 생성시 적용
	//public void createFieldComments(ColumnDefVO def);

	public void alterAddPrimaryKey(DataMngCreatorVO insertVO);

	/**
	 * 카테고리 테이블에 카테고리 등록
	 *
	 * @Method Name : insertCategory
	 * @param vo
	 */
	public void insertCategory(CategoryVO vo);

	/**
	 * 카테고리 정보 삭제
	 *
	 * @Method Name : deleteCategory
	 * @param vo
	 */
	public void deleteCategory(CategoryVO vo);

	/**
	 * 카테고리 목록 조회
	 *
	 * @Method Name : getCategoryList
	 * @param tableName
	 * @return
	 */
	public List<CategoryVO> getCategoryList(CategoryVO vo);

	public void updateHeader(DataMngCreatorVO tableDef);

}
