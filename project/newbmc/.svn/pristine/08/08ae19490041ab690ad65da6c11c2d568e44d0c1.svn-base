package egovframework.portal.sys.dynamic.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import edu.emory.mathcs.backport.java.util.Collections;
import egovframework.portal.sys.dynamic.mapper.DataMngCreatorMapper;
import egovframework.portal.sys.dynamic.service.DataMngCreatorService;
import egovframework.portal.sys.dynamic.vo.CategoryDefVO;
import egovframework.portal.sys.dynamic.vo.CategoryVO;
import egovframework.portal.sys.dynamic.vo.ColumnDefVO;
import egovframework.portal.sys.dynamic.vo.DataMngCreatorVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import net.arnx.jsonic.JSON;

/**
 * 동적 현황관리 명세서/메뉴 생성 SERVICE IMPL
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2016.08.17		권태성				program url을 테이블명 대신 사용할 수 있도록 처리
 * 2016.08.25		J.Ryeon Lee			동적 코멘트 생성 쿼리 추가
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.05.03
 */
@Service
public class DataMngCreatorServiceImpl extends EgovAbstractServiceImpl implements DataMngCreatorService {

	private static final Logger LOGGER = LogManager.getLogger();

	@Resource(name = "dataMngCreatorMapper")
	protected DataMngCreatorMapper dataMngCreatorMapper;

	@Override
	public int getTotalCnt(DataMngCreatorVO searchVO) {
		return dataMngCreatorMapper.getTotalCnt(searchVO);
	}

	@Override
	public List<DataMngCreatorVO> getList(DataMngCreatorVO searchVO) {
		return dataMngCreatorMapper.getList(searchVO);
	}

	@Override
	public DataMngCreatorVO getEntity(DataMngCreatorVO searchVO) throws Exception {
		return StringUtil.isNotBlank(searchVO.getIdx()) ? getEntityBy(searchVO) : null;
	}

	@Override
	public DataMngCreatorVO getEntity(String tableName) throws Exception {
		DataMngCreatorVO searchVO = new DataMngCreatorVO();
		searchVO.setTableName(tableName);
		return StringUtil.isNotBlank(tableName) ? getEntityBy(searchVO) : null;
	}

	@SuppressWarnings({ "unchecked", "serial" })
	private DataMngCreatorVO getEntityBy(DataMngCreatorVO searchVO) throws Exception {
		DataMngCreatorVO rtn = dataMngCreatorMapper.getEntity(searchVO);
		if (rtn != null) {
			List<ColumnDefVO> result = (List<ColumnDefVO>) JSON.decode(
				rtn.getColumnDefs().trim(), // 20160825 DB 연결 변경 후 눈에 보이지 않는 문자열 때문에 파싱 에러가 났으므로 trim 처리 추가
				(new ArrayList<ColumnDefVO>() {
				}).getClass().getGenericSuperclass());
			List<CategoryDefVO> cateResult = (List<CategoryDefVO>) JSON.decode(
				rtn.getCategoryDefs().trim(),
				(new ArrayList<CategoryDefVO>() {
				}).getClass().getGenericSuperclass());
			Collections.sort(result, new ColumnDefOrderCompare());
			rtn.setColumnDefList(result);
			rtn.setCategoryDefList(cateResult);
			rtn.setCategory1List(this.getCategoryList(new CategoryVO(rtn.getTableName(), 1)));
			rtn.setCategory2List(this.getCategoryList(new CategoryVO(rtn.getTableName(), 2)));
		}

		return rtn;
	}

	@SuppressWarnings({ "unchecked", "serial" })
	@Override
	public void insert(DataMngCreatorVO insertVO) { // TODO 트랜잭션 처리 #update와 동일
		List<ColumnDefVO> colDefList = (StringUtil.isBlank(insertVO.getTableDefJson()) //
			? insertVO.getColumnDefList() // GUI를 통한 컬럼 입력
			: (List<ColumnDefVO>) (JSON.decode(insertVO.getTableDefJson(), (new ArrayList<ColumnDefVO>() {}).getClass().getGenericSuperclass()))); // JSON 데이터 직접 입력인 경우
		insertVO.setColumnDefList(colDefList);
		insertVO.setColumnDefList(uppercaseColumnName(insertVO.getColumnDefList()));
		insertVO.setColumnDefs(JSON.encode(insertVO.getColumnDefList(), Boolean.TRUE));
		insertVO.setCategoryDefs(JSON.encode(insertVO.getCategoryDefList(), Boolean.TRUE));
		insertVO.setUrlName(StringUtil.toCamelCase(insertVO.getTableName()));
		dataMngCreatorMapper.createDynamicTable(insertVO); // create dynamic table
		//MySql 버전은 테이블 생성시 적용
		//dataMngCreatorMapper.createDynamicSeq(insertVO); // create dynamic sequence
		dataMngCreatorMapper.insert(insertVO); // insert table def
		alterDynaicComments(insertVO); // create dynamic table comment (에러가 발생해도 무시함)
		alterAddPrimaryKey(insertVO); // add pk (create 생성문 때 붙이면 무시되는 버그가 있음, 에러가 발생해도 무시함)

		/** category 등록 **/
		this.insertCategory(insertVO);
	}

	@Override
	public void update(DataMngCreatorVO originVO, DataMngCreatorVO updateVO) throws Exception { // TODO DDL 트랜잭션은 oracle 11g 이상부터 지원이라 현재로서는 롤백할 방법이 없음

		/* 테이블 정의 변경 */

		updateVO.setColumnDefList(uppercaseColumnName(updateVO.getColumnDefList()));

		List<ColumnDefVO> newColumnList = new ArrayList<ColumnDefVO>(); // 신규 컬럼 목록
		List<ColumnDefVO> mergeColumnList = new ArrayList<ColumnDefVO>(); // 취합 컬럼 목록
		for (ColumnDefVO newDef : updateVO.getColumnDefList()) {
			if (StringUtil.isBlank(newDef.getOldName())) { // 기존 컬럼값 제거 (disabled 값) / 신규 컬럼 추출
				newColumnList.add(newDef);
			} else {
				for (ColumnDefVO oldDef : originVO.getColumnDefList()) { // 취합 컬럼값 추출
					if (oldDef.getName().equals(newDef.getOldName())) {
						BeanUtils.copyProperties(newDef, oldDef, new String[] { "name", "type", "size" }); // except disabled value (name, type, size, nullable)
						mergeColumnList.add(oldDef);
					}
				}
			}
		}

		List<ColumnDefVO> delColumnList = new ArrayList<ColumnDefVO>(); // 삭제 컬럼 목록
		delColumnList.addAll(originVO.getColumnDefList());
		delColumnList.removeAll(mergeColumnList);

		/* 동적 테이블 구조 변경(ALTER) */

		if (newColumnList.size() > 0) { // 신규 컬럼이 있는 경우에만
			updateVO.setColumnDefList(newColumnList);
			dataMngCreatorMapper.addDynamicColumns(updateVO);
		}

		if (delColumnList.size() > 0) { // 삭제 컬럼이 있는 경우에만
			updateVO.setColumnDefList(delColumnList);
			dataMngCreatorMapper.dropDynamicColumns(updateVO);
		}

		/* 테이블 정보 반영 */

		mergeColumnList.addAll(newColumnList);
		updateVO.setColumnDefList(mergeColumnList); // 취합한 컬럼 정보를 반영 (기존 + 신규 추가 컬럼)
		updateVO.setColumnDefs(JSON.encode(updateVO.getColumnDefList(), Boolean.TRUE));
		updateVO.setCategoryDefs(JSON.encode(updateVO.getCategoryDefList(), Boolean.TRUE));
		dataMngCreatorMapper.update(updateVO);
		alterDynaicComments(updateVO); // 코멘트 재반영

		/** category 등록 **/
		this.insertCategory(updateVO);
	}

	@Override
	public void delete(DataMngCreatorVO deleteVO) {
		dataMngCreatorMapper.delete(deleteVO);
	}

	private List<ColumnDefVO> uppercaseColumnName(List<ColumnDefVO> columnDefList) {
		List<ColumnDefVO> rtn = new ArrayList<ColumnDefVO>();
		for (ColumnDefVO def : columnDefList) {
			def.setName(def.getName().toUpperCase());
			rtn.add(def);
		}

		return rtn;
	}

	@Override
	public int getTotalCntUsingName(String tableName) {
		return dataMngCreatorMapper.getTotalCntUsingName(tableName);
	}

	/**
	 * urlName으로 tableName을 조회합니다.
	 *
	 * @Method Name : getUrlName
	 * @param urlName
	 * @return
	 */
	public String getTableName(String urlName) {
		return dataMngCreatorMapper.getTableName(urlName);
	}

	/**
	 * 카테고리 테이블에 카테고리 등록
	 *
	 * @Method Name : insertCategory
	 * @param param
	 */
	public void insertCategory(DataMngCreatorVO param) {
		if (param.getCategory1List() != null) {
			this.deleteCategory(new CategoryVO(param.getTableName(), 1)); //카테고리는 수정할때마다 기존 카테고리 제거하고 신규등록
			for (CategoryVO vo : param.getCategory1List()) {
				vo.setTableName(param.getTableName());
				vo.setCategoryIdx(1);
				dataMngCreatorMapper.insertCategory(vo);
			}
		}
		if (param.getCategory2List() != null) {
			this.deleteCategory(new CategoryVO(param.getTableName(), 2)); //카테고리는 수정할때마다 기존 카테고리 제거하고 신규등록
			for (CategoryVO vo : param.getCategory2List()) {
				vo.setTableName(param.getTableName());
				vo.setCategoryIdx(2);
				dataMngCreatorMapper.insertCategory(vo);
			}
		}
	}

	/**
	 * 카테고리 정보 삭제
	 *
	 * @Method Name : deleteCategory
	 * @param tableName
	 */
	public void deleteCategory(CategoryVO vo) {
		dataMngCreatorMapper.deleteCategory(vo);
	}

	/**
	 * 카테고리 목록 조회
	 *
	 * @Method Name : getCategoryList
	 * @param tableName
	 * @return
	 */
	public List<CategoryVO> getCategoryList(CategoryVO vo) {
		return dataMngCreatorMapper.getCategoryList(vo);
	}

	@Override
	public void updateHeader(DataMngCreatorVO tableDef) {
		dataMngCreatorMapper.updateHeader(tableDef);
	}

	private void alterDynaicComments(DataMngCreatorVO insertVO) {
		try {
			dataMngCreatorMapper.createTableComment(insertVO);
			//MySql 버전은 테이블 생성시 적용
			/*
			for (ColumnDefVO def : insertVO.getColumnDefList()) {
				def.setTableName(insertVO.getTableName());
				dataMngCreatorMapper.createFieldComments(def);
			}*/
		} catch (Exception e) {
			LOGGER.error("[관리자] " + System.currentTimeMillis() + " 동적 현황 테이블 코멘트 추가 실패");
		}
	}

	private void alterAddPrimaryKey(DataMngCreatorVO insertVO) {
		try {
			dataMngCreatorMapper.alterAddPrimaryKey(insertVO);
		} catch (Exception e) {
			LOGGER.fatal("[관리자] " + System.currentTimeMillis() + " 동적 현황 테이블 PK 생성 실패");
		}
	}

}