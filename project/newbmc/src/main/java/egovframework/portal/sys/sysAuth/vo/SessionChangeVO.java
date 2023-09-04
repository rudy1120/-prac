package egovframework.portal.sys.sysAuth.vo;

public class SessionChangeVO {
	
	private int no;
	private int period;
	private String createDate;
	private String id;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "SessionChangeVO [no=" + no + ", period=" + period + ", createDate=" + createDate + ", id=" + id + "]";
	}
}
