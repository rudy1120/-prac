package egovframework.portal.sys.dynamic.vo;

import java.util.Date;

/**
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2016. 12. 16		권태성				다중 카테고리를 위해 수정
 * </pre>
 *
 * @author 권태성
 * @since 2016. 11. 11.
 */
public class CategoryVO {

	private int idx;
	/** 테이블 이름 */
	private String tableName;
	/** 카테고리 키 */
	private String categoryKey;
	/** 카테고리 값 */
	private String categoryValue;
	/** 사용여부 */
	private String useYn;
	/** 등록일자 */
	private Date createDate;
	/** 수정일자 */
	private Date updateDate;
	/** 삭제일자 */
	private Date deleteDate;
	/** 카테고리 정렬 */
	private int categoryOrder;
	/** 카테고리 목록 순번 */
	private int categoryIdx;

	public CategoryVO() {
		super();
	}

	public CategoryVO(String tableName, int categoryIdx) {
		super();
		this.tableName = tableName;
		this.categoryIdx = categoryIdx;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getCategoryKey() {
		return categoryKey;
	}

	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
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

	public int getCategoryOrder() {
		return categoryOrder;
	}

	public void setCategoryOrder(int categoryOrder) {
		this.categoryOrder = categoryOrder;
	}

	public int getCategoryIdx() {
		return categoryIdx;
	}

	public void setCategoryIdx(int categoryIdx) {
		this.categoryIdx = categoryIdx;
	}

}