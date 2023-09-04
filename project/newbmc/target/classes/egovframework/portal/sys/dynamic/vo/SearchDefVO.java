package egovframework.portal.sys.dynamic.vo;

/**
 * 검색 쿼리 생성용 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 11. 6.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 11. 6.
 */
public class SearchDefVO {

	/** column */
	private String column = "";
	/** operator */
	private String operator = "";
	/** value */
	private String value = "";
	/** prefix */
	private String prefix = "";
	/** suffix */
	private String suffix = "";

	public SearchDefVO() {
		super();
	}

	public SearchDefVO(String prefix, String column, String operator, String value, String suffix) {
		super();
		this.column = column;
		this.operator = operator;
		this.value = value;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public String toString() {
		return "SearchDefVO [column=" + column + ", operator=" + operator + ", value=" + value + ", prefix=" + prefix + ", suffix=" + suffix + "]";
	}

}