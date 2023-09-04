package egovframework.portal.unit.bmc.moveRes.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

public class MoveReserveVO extends CommonVO {
	
	private int cust_id; //고객리스트 고객id
	private String name;
	private String tel;
	private String addr1;
	private String addr2;
	private String use_yn;
	private String gbn1; //N차 계약자
	private String gbn2; //행복주택 구분
	
	private String ckname;
	private String cktel;
	private String ckaddr1;
	private String ckaddr2;
	
	private int idx; //날짜 예약 idx
	private String move_date;
	private String move_time;
	private String regdate;
	private String uptdate;
	private String updt_yn;
	
	private Date datepick;
	private String dateSel;
	private String timeSel;	
	
	/* 참가자 인증키 */
	private String dupinfo;
	private String sidx;
	
	private String cnt;
	
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getGbn1() {
		return gbn1;
	}
	public void setGbn1(String gbn1) {
		this.gbn1 = gbn1;
	}
	public String getGbn2() {
		return gbn2;
	}
	public void setGbn2(String gbn2) {
		this.gbn2 = gbn2;
	}
	public String getCkname() {
		return ckname;
	}
	public void setCkname(String ckname) {
		this.ckname = ckname;
	}
	public String getCktel() {
		return cktel;
	}
	public void setCktel(String cktel) {
		this.cktel = cktel;
	}
	public String getCkaddr1() {
		return ckaddr1;
	}
	public void setCkaddr1(String ckaddr1) {
		this.ckaddr1 = ckaddr1;
	}
	public String getCkaddr2() {
		return ckaddr2;
	}
	public void setCkaddr2(String ckaddr2) {
		this.ckaddr2 = ckaddr2;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMove_date() {
		return move_date;
	}
	public void setMove_date(String move_date) {
		this.move_date = move_date;
	}
	public String getMove_time() {
		return move_time;
	}
	public void setMove_time(String move_time) {
		this.move_time = move_time;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getUptdate() {
		return uptdate;
	}
	public void setUptdate(String uptdate) {
		this.uptdate = uptdate;
	}
	public String getUpdt_yn() {
		return updt_yn;
	}
	public void setUpdt_yn(String updt_yn) {
		this.updt_yn = updt_yn;
	}
	public Date getDatepick() {
		return datepick;
	}
	public void setDatepick(Date datepick) {
		this.datepick = datepick;
	}
	public String getDateSel() {
		return dateSel;
	}
	public void setDateSel(String dateSel) {
		this.dateSel = dateSel;
	}
	public String getTimeSel() {
		return timeSel;
	}
	public void setTimeSel(String timeSel) {
		this.timeSel = timeSel;
	}
	public String getDupinfo() {
		return dupinfo;
	}
	public void setDupinfo(String dupinfo) {
		this.dupinfo = dupinfo;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getCnt() {
		return cnt;
	}
	public void setCnt(String cnt) {
		this.cnt = cnt;
	}


}
