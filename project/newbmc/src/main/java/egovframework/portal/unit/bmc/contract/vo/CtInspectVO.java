package egovframework.portal.unit.bmc.contract.vo;

public class CtInspectVO {
	
	/* 계약번호 */
	private String cntrNo;
	
	/* 검사연번 */
	private String inspectNo;
	
	/* 검사일 */
	private String inspectYrmd;
	
	/* 검사금액 */
	private double inspectAmt;
	
	/* 검사원및입회원 */
	private String inspectStff;
	
	/* 기성금지급일 */
	private String readymadeYrmd;
	
	/* 최초입력일 */
	private String frstInsYrmd;
	
	/* 최초입력자 */
	private String frstInsEno;

	public String getCntrNo() {
		return cntrNo;
	}

	public void setCntrNo(String cntrNo) {
		this.cntrNo = cntrNo;
	}

	public String getInspectNo() {
		return inspectNo;
	}

	public void setInspectNo(String inspectNo) {
		this.inspectNo = inspectNo;
	}

	public String getInspectYrmd() {
		return inspectYrmd;
	}

	public void setInspectYrmd(String inspectYrmd) {
		this.inspectYrmd = inspectYrmd;
	}

	public double getInspectAmt() {
		return inspectAmt;
	}

	public void setInspectAmt(double inspectAmt) {
		this.inspectAmt = inspectAmt;
	}

	public String getInspectStff() {
		return inspectStff;
	}

	public void setInspectStff(String inspectStff) {
		this.inspectStff = inspectStff;
	}

	public String getReadymadeYrmd() {
		return readymadeYrmd;
	}

	public void setReadymadeYrmd(String readymadeYrmd) {
		this.readymadeYrmd = readymadeYrmd;
	}

	public String getFrstInsYrmd() {
		return frstInsYrmd;
	}

	public void setFrstInsYrmd(String frstInsYrmd) {
		this.frstInsYrmd = frstInsYrmd;
	}

	public String getFrstInsEno() {
		return frstInsEno;
	}

	public void setFrstInsEno(String frstInsEno) {
		this.frstInsEno = frstInsEno;
	}

	@Override
	public String toString() {
		return "CtInspectVO [cntrNo=" + cntrNo + ", inspectNo=" + inspectNo + ", inspectYrmd=" + inspectYrmd
				+ ", inspectAmt=" + inspectAmt + ", inspectStff=" + inspectStff + ", readymadeYrmd=" + readymadeYrmd
				+ ", frstInsYrmd=" + frstInsYrmd + ", frstInsEno=" + frstInsEno + "]";
	}
}
