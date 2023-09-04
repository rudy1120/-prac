package egovframework.portal.unit.bmc.contract.vo;

public class CtChangeVO {
	
	/* 계약번호 */
	private String cntrNo;
	
	/* 변경차수 */
	private String chgNo;
	
	/* 변경일 */
	private String plchangeYrmd;
	
	/* 증감액 */
	private double plchangeAmt;
	
	private double contractAmt;
	
	/* 변경사유 */
	private String chgResn;
	
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

	public String getChgNo() {
		return chgNo;
	}

	public void setChgNo(String chgNo) {
		this.chgNo = chgNo;
	}

	public String getPlchangeYrmd() {
		return plchangeYrmd;
	}

	public void setPlchangeYrmd(String plchangeYrmd) {
		this.plchangeYrmd = plchangeYrmd;
	}

	public double getPlchangeAmt() {
		return plchangeAmt;
	}

	public void setPlchangeAmt(double plchangeAmt) {
		this.plchangeAmt = plchangeAmt;
	}

	public double getContractAmt() {
		return contractAmt;
	}

	public void setContractAmt(double contractAmt) {
		this.contractAmt = contractAmt;
	}

	public String getChgResn() {
		return chgResn;
	}

	public void setChgResn(String chgResn) {
		this.chgResn = chgResn;
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
		return "CtChangeVO [cntrNo=" + cntrNo + ", chgNo=" + chgNo + ", plchangeYrmd=" + plchangeYrmd + ", plchangeAmt="
				+ plchangeAmt + ", contractAmt=" + contractAmt + ", chgResn=" + chgResn + ", frstInsYrmd=" + frstInsYrmd
				+ ", frstInsEno=" + frstInsEno + "]";
	}
	
	
	
}
