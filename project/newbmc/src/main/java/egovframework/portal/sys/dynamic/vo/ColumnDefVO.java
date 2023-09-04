package egovframework.portal.sys.dynamic.vo;

import egovframework.portal.util.StringUtil;
import net.arnx.jsonic.JSONHint;

/**
 * 컬럼 정의 VO
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2016.08.25		J.Ryeon Lee			DB 테이블 코멘트 추가용 tableName 필드 추가
 * 2017.02.22		권태성				검색 옵션 추가
 * 2017.05.29		권태성				설명글 옵션 추가
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.05.03
 */
public class ColumnDefVO {

	/** 테이블명 */
	@JSONHint(ignore = true)
	private String tableName = "";
	/** old 컬럼명 */
	@JSONHint(ignore = true)
	private String oldName = "";
	/** 컬럼명 */
	private String name = "";
	/** 컬럼명 설명 */
	private String description = "";
	/** 데이터 타입 */
	private String type = "";
	/** 길이 */
	private String size = "";
	/** 공백가능여부(Y: 가능, N: 불가능) */
	private String nullable = "";
	/** 컬럼순서 */
	private String order = "";
	/** 목록 표출 여부 */
	private String display = "";
	/** 사용 여부(Y: 사용, N: 미사용) */
	private String isUse = "";
	/** 컬럼 width (클래스에 사용됨) */
	private String width = "";
	/** 컬럼 정렬 */
	private String align = "";
	/** 컬럼 셀렉트 종류 (N : 미사용, S : 셀렉트, R : 라디오, C : 체크박스) */
	private String selectType = "N";
	/** 컬럼 셀렉트 옵션 값 */
	private String optionValues = "";
	/** 검색 여부 옵션 값 **/
	private String isSearch = "Y";
	/** 링크 종류 (0 : 미사용, 1 : 상세페이지, 2 : 현재창, 3 : URL(새창) */
	private String linkType = "0";
	/** 목록에서 줄 바꿈여부 옵션 */
	private String newLine = "N";
	/** 설명글 */
	private String infoMsg = "";

	@JSONHint(ignore = true)
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@JSONHint(ignore = true)
	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		if ("file".equals(this.type)) {
			return "10";
		} else {
			return size;
		}
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	public String getOptionValues() {
		return optionValues;
	}

	public void setOptionValues(String optionValues) {
		this.optionValues = optionValues;
	}

	public String getIsSearch() {
		return (StringUtil.isBlank(this.isSearch) ? "Y" : isSearch);
	}

	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}

	public String getSelectType() {
		return selectType;
	}

	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getNewLine() {
		return newLine;
	}

	public void setNewLine(String newLine) {
		this.newLine = newLine;
	}

	public String getInfoMsg() {
		return infoMsg;
	}

	public void setInfoMsg(String infoMsg) {
		this.infoMsg = infoMsg;
	}

}