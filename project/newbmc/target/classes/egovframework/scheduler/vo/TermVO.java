package egovframework.scheduler.vo;

import egovframework.portal.common.vo.CommonVO;

public class TermVO extends CommonVO {

	private int idx;
	private String regdate;
	private int cnt_s;
	private int cnt_h;
	
	private int total_s;
	private int total_h;
	
	private int sale;
	private int housing;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getCnt_s() {
		return cnt_s;
	}
	public void setCnt_s(int cnt_s) {
		this.cnt_s = cnt_s;
	}
	public int getCnt_h() {
		return cnt_h;
	}
	public void setCnt_h(int cnt_h) {
		this.cnt_h = cnt_h;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}	
	public int getTotal_s() {
		return total_s;
	}
	public void setTotal_s(int total_s) {
		this.total_s = total_s;
	}
	public int getTotal_h() {
		return total_h;
	}
	public void setTotal_h(int total_h) {
		this.total_h = total_h;
	}	
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public int getHousing() {
		return housing;
	}
	public void setHousing(int housing) {
		this.housing = housing;
	}
	
	@Override
	public String toString() {
		return "TermVO [idx=" + idx + ", cnt_s=" + cnt_s + ", cnt_h=" + cnt_h + ", regdate=" + regdate
				+ ", total_s=" + total_s + ",total_h=" + total_h + ", sale=" + sale + ", housing"
				+ housing + "]";
	}
	
}
