package egovframework.portal.sys.dynamic.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.dynamic.LinkType;
import egovframework.portal.validation.ByteSize;

/**
 * 동적 현황관리 명세서/메뉴 등록 VO
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2016.08.25		J.Ryeon Lee			검증 아노테이션 추가
 * 2016.12.16		권태성				다중 카테고리를 위해 수정
 * 2017.01.11		J.Ryeon Lee			사용자 파일 다운로드 토글 기능 추가
 * 2017.01.17		권태성				정렬 옵션 추가
 * 2017.01.18		권태성				번호 출력 옵션 추가
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.05.03
 */
public class DataMngCreatorVO extends CommonVO {

	/** PK */
	private String idx = "";
	/** 현황관리명 */
	@NotBlank
	@ByteSize(max = 300)
	private String tableDesc = "";
	/** 테이블명 */
	@NotBlank
	@ByteSize(max = 20)
	@RegExp("^[a-zA-Z_]+$")
	private String tableName = "";
	/** 컬럼정의서(FORM, 비영속화) */
	private List<ColumnDefVO> columnDefList = new ArrayList<ColumnDefVO>();
	/** 컬럼정의서(영속화 대상) */
	private String columnDefs = "";
	/** 첨부파일 사용 여부(Y: 사용, N: 미사용) */
	@RegExp("^[Y|N]$")
	private String useFile = "";
	/** 맵 사용 여부(Y: 사용, N: 미사용) */
	@RegExp("^[Y|N]$")
	private String useMap = "";
	/** 링크 타입 @see {@link LinkType} */
	@RegExp("^[0|1|2|3]$")
	private String linkType = "";
	/** 링크 표출 컬럼명 */
	@ByteSize(max = 20)
	@RegExp("^[a-zA-Z_]+$")
	private String linkColName = "";
	/** 카테고리 항목 이름 */
	@ByteSize(max = 1000)
	private String category = "";
	/** 카테고리 폭 */
	@ByteSize(max = 3)
	@RegExp("^[0-9]+$")
	private String categoryWidth = "";
	/** 담당과(이름) */
	@ByteSize(max = 100)
	private String deptName = "";
	/** 담당과(코드) */
	@ByteSize(max = 10)
	private String deptCode = "";
	/** 담당과(전화번호 앞자리) */
	@RegExp("^[0-9]{0,4}$")
	private String deptTel1 = "";
	/** 담당과(전화번호 가운데자리) */
	@RegExp("^[0-9]{0,4}$")
	private String deptTel2 = "";
	/** 담당과(전화번호 끝자리) */
	@RegExp("^[0-9]{0,4}$")
	private String deptTel3 = "";
	/** header content */
	private String headerContent = "";
	/**  */
	private String isDel = "";
	/**  */
	private Date createDate = null;
	/**  */
	private Date updateDate = null;
	/**  */
	private Date deleteDate = null;
	/** 이미지만 첨부가능 (DB 상에는 1, 0으로 들어감) **/
	@RegExp("^[Y|N]$")
	private String imageOnly = "";
	/** URL에 사용할 이름 **/
	private String urlName = "";
	/** 커스텀 상세 뷰 패스 **/
	@ByteSize(max = 1000)
	private String viewPath = "";
	/** 커스텀 리스트 뷰 패스 **/
	@ByteSize(max = 1000)
	private String listPath = "";
	/** 목록에 보여줄 데이터 갯수 **/
	private int listCnt = 20;
	/** 관리자 커스텀 리스트 뷰 패스 **/
	@ByteSize(max = 1000)
	private String sysListPath = "";
	/** 비공개 데이터 사용여부 **/
	@ByteSize(max = 1)
	private String useSecret = "";
	/** 카테고리1 설정용 **/
	private List<CategoryVO> category1List = new ArrayList<CategoryVO>();
	/** 카테고리2 설정용 **/
	private List<CategoryVO> category2List = new ArrayList<CategoryVO>();
	/** 파일 다운로드 링크 제공 유무 */
	private String fileDownYn = "";
	/** 정렬옵션 **/
	@RegExp("^[DESC|ASC]$")
	private String orderType = "";
	/** 번호 출력여부 **/
	@RegExp("^[Y|N]$")
	private String numYn = "";
	/** 정렬 컬럼명 */
	private String orderColName = "";
	/** 검색폼 표시 여부 */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String searchYn = "";
	/**  */
	private String headerModifyYn = "";
	/** 카테고리 설정 */
	private String categoryDefs = "";
	/** 카테고리 설정 배열 */
	private List<CategoryDefVO> categoryDefList = new ArrayList<CategoryDefVO>();
	/** 테이블 생성 정보 */
	private String tableDefJson = "";

	public DataMngCreatorVO() {
		// default
	}

	public DataMngCreatorVO(String tableName) {
		super();
		setTableName(tableName);
	}

	public DataMngCreatorVO(String idx, String tableName) {
		setIdx(idx);
		setTableName(tableName);
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<ColumnDefVO> getColumnDefList() {
		return columnDefList;
	}

	public void setColumnDefList(List<ColumnDefVO> columnDefList) {
		this.columnDefList = columnDefList;
	}

	public String getColumnDefs() {
		return columnDefs;
	}

	public void setColumnDefs(String columnDefs) {
		this.columnDefs = columnDefs;
	}

	public String getUseFile() {
		return useFile;
	}

	public void setUseFile(String useFile) {
		this.useFile = useFile;
	}

	public String getUseMap() {
		return useMap;
	}

	public void setUseMap(String useMap) {
		this.useMap = useMap;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getLinkColName() {
		return linkColName;
	}

	public void setLinkColName(String linkColName) {
		this.linkColName = linkColName;
	}

	public String getCategoryWidth() {
		return categoryWidth;
	}

	public void setCategoryWidth(String categoryWidth) {
		this.categoryWidth = categoryWidth;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptTel1() {
		return deptTel1;
	}

	public void setDeptTel1(String deptTel1) {
		this.deptTel1 = deptTel1;
	}

	public String getDeptTel2() {
		return deptTel2;
	}

	public void setDeptTel2(String deptTel2) {
		this.deptTel2 = deptTel2;
	}

	public String getDeptTel3() {
		return deptTel3;
	}

	public void setDeptTel3(String deptTel3) {
		this.deptTel3 = deptTel3;
	}

	public String getHeaderContent() {
		return headerContent;
	}

	public void setHeaderContent(String headerContent) {
		this.headerContent = headerContent;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getImageOnly() {
		return imageOnly;
	}

	public void setImageOnly(String imageOnly) {
		this.imageOnly = imageOnly;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getViewPath() {
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getListPath() {
		return listPath;
	}

	public void setListPath(String listPath) {
		this.listPath = listPath;
	}

	public int getListCnt() {
		return listCnt;
	}

	public void setListCnt(int listCnt) {
		if (listCnt == 0) {
			this.listCnt = 20;
		} else {
			this.listCnt = listCnt;
		}
	}

	public String getSysListPath() {
		return sysListPath;
	}

	public void setSysListPath(String sysListPath) {
		this.sysListPath = sysListPath;
	}

	public List<CategoryVO> getCategory1List() {
		return category1List;
	}

	public void setCategory1List(List<CategoryVO> category1List) {
		this.category1List = category1List;
	}

	public List<CategoryVO> getCategory2List() {
		return category2List;
	}

	public void setCategory2List(List<CategoryVO> category2List) {
		this.category2List = category2List;
	}

	public String getUseSecret() {
		return useSecret;
	}

	public void setUseSecret(String useSecret) {
		this.useSecret = useSecret;
	}

	public String getFileDownYn() {
		return fileDownYn;
	}

	public void setFileDownYn(String fileDownYn) {
		this.fileDownYn = fileDownYn;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getNumYn() {
		return numYn;
	}

	public void setNumYn(String numYn) {
		this.numYn = numYn;
	}

	public String getOrderColName() {
		return orderColName;
	}

	public void setOrderColName(String orderColName) {
		this.orderColName = orderColName;
	}

	public String getSearchYn() {
		return searchYn;
	}

	public void setSearchYn(String searchYn) {
		this.searchYn = searchYn;
	}

	public String getHeaderModifyYn() {
		return headerModifyYn;
	}

	public void setHeaderModifyYn(String headerModifyYn) {
		this.headerModifyYn = headerModifyYn;
	}

	public String getCategoryDefs() {
		return categoryDefs;
	}

	public void setCategoryDefs(String categoryDefs) {
		this.categoryDefs = categoryDefs;
	}

	public List<CategoryDefVO> getCategoryDefList() {
		return categoryDefList;
	}

	public void setCategoryDefList(List<CategoryDefVO> categoryDefList) {
		this.categoryDefList = categoryDefList;
	}

	public String getTableDefJson() {
		return tableDefJson;
	}

	public void setTableDefJson(String tableDefJson) {
		this.tableDefJson = tableDefJson;
	}

	@Override
	public String toString() {
		return "DataMngCreatorVO [idx=" + idx + ", tableDesc=" + tableDesc + ", tableName=" + tableName + ", columnDefList=" + columnDefList + ", columnDefs=" + columnDefs + ", useFile=" + useFile + ", useMap=" + useMap + ", linkType=" + linkType + ", linkColName=" + linkColName + ", category=" + category + ", categoryWidth=" + categoryWidth + ", deptName=" + deptName + ", deptCode=" + deptCode + ", deptTel1=" + deptTel1 + ", deptTel2=" + deptTel2 + ", deptTel3=" + deptTel3 + ", headerContent=" + headerContent + ", isDel=" + isDel + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + ", imageOnly=" + imageOnly + ", urlName=" + urlName + ", viewPath=" + viewPath + ", listPath=" + listPath + ", listCnt=" + listCnt + ", sysListPath=" + sysListPath + ", useSecret=" + useSecret + ", category1List=" + category1List + ", category2List=" + category2List + ", fileDownYn=" + fileDownYn + ", orderType=" + orderType + ", numYn=" + numYn + ", orderColName=" + orderColName + ", searchYn=" + searchYn + ", headerModifyYn=" + headerModifyYn + ", categoryDefs=" + categoryDefs + ", categoryDefList=" + categoryDefList + ", tableDefJson=" + tableDefJson + "]";
	}

}