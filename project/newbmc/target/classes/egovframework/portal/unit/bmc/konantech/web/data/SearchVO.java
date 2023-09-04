package egovframework.portal.unit.bmc.konantech.web.data;

/** 
 * Dao 관련 Value Object
 * 
 * @author 장진후(Jinhoo.Jang)
 * @team 기술지원팀(Technical Support)
 * @since 2013.06.20
 * @version 1.0
 * @modify 2018.06.07
 * @modifier 안호빈(hobin.ahn)
 * @version 1.1 
 */
public class SearchVO {
	private String scenario;
	private String[] fields;
	private String query;
	private String logInfo;
	private String hilightTxt;
	private String orderBy;
	private boolean flag;
	
	//restful 추가 
	private String url;
	private String fields_rest;
	private String from;
	private String charset;	
	private String hilightFileds;
	private String defaultHilight;
	private String synDomainNo;
	
	
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}	
	public String[] getFields() {
		return fields;
	}
	public void setFields(String[] fields) {
		this.fields = fields;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}
	public String getHilightTxt() {
		return hilightTxt;
	}
	public void setHilightTxt(String hilightTxt) {
		this.hilightTxt = hilightTxt;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFields_rest() {
		return fields_rest;
	}
	public void setFields_rest(String fields_rest) {
		this.fields_rest = fields_rest;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getHilightFileds() {
		return hilightFileds;
	}
	public void setHilightFileds(String hilightFileds) {
		this.hilightFileds = hilightFileds;
	}
	public String getDefaultHilight() {
		return defaultHilight;
	}
	public void setDefaultHilight(String defaultHilight) {
		this.defaultHilight = defaultHilight;
	}
	public String getSynDomainNo() {
		return synDomainNo;
	}
	public void setSynDomainNo(String synDomainNo) {
		this.synDomainNo = synDomainNo;
	}
}
