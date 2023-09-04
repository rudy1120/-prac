package egovframework.portal.sys.MenuMng.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 메뉴 담당자 인계요청 정보
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 6. 16.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 6. 16.
 */
public class MenuChargeReqVO {

	private int idx = 0;
	private String site_code = "";
	private String mid = "";
	private String req_charge_id = "";
	private String req_charge_fnm = "";
	private String req_charge_dep_code = "";
	private String req_charge_dep_nm = "";
	private String req_charge_tel = "";
	private Date req_dt = null;
	private String req_state;
	private String move_charge_id;
	private String move_charge_fnm;
	private String move_charge_dep_code;
	private String move_charge_dep_nm;
	private String move_charge_tel = "";
	private Date ack_dt = null;

	/** 일괄승인용 @author 김장섭 */
	private List<String> ackChecked = new ArrayList<>();

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getSite_code() {
		return site_code;
	}
	public void setSite_code(String site_code) {
		this.site_code = site_code;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getReq_charge_id() {
		return req_charge_id;
	}
	public void setReq_charge_id(String req_charge_id) {
		this.req_charge_id = req_charge_id;
	}
	public String getReq_charge_fnm() {
		return req_charge_fnm;
	}
	public void setReq_charge_fnm(String req_charge_fnm) {
		this.req_charge_fnm = req_charge_fnm;
	}
	public String getReq_charge_dep_code() {
		return req_charge_dep_code;
	}
	public void setReq_charge_dep_code(String req_charge_dep_code) {
		this.req_charge_dep_code = req_charge_dep_code;
	}
	public String getReq_charge_dep_nm() {
		return req_charge_dep_nm;
	}
	public void setReq_charge_dep_nm(String req_charge_dep_nm) {
		this.req_charge_dep_nm = req_charge_dep_nm;
	}
	public String getReq_charge_tel() {
		return req_charge_tel;
	}
	public void setReq_charge_tel(String req_charge_tel) {
		this.req_charge_tel = req_charge_tel;
	}
	public Date getReq_dt() {
		return req_dt;
	}
	public void setReq_dt(Date req_dt) {
		this.req_dt = req_dt;
	}
	public String getReq_state() {
		return req_state;
	}
	public void setReq_state(String req_state) {
		this.req_state = req_state;
	}
	public String getMove_charge_id() {
		return move_charge_id;
	}
	public void setMove_charge_id(String move_charge_id) {
		this.move_charge_id = move_charge_id;
	}
	public String getMove_charge_fnm() {
		return move_charge_fnm;
	}
	public void setMove_charge_fnm(String move_charge_fnm) {
		this.move_charge_fnm = move_charge_fnm;
	}
	public String getMove_charge_dep_code() {
		return move_charge_dep_code;
	}
	public void setMove_charge_dep_code(String move_charge_dep_code) {
		this.move_charge_dep_code = move_charge_dep_code;
	}
	public String getMove_charge_dep_nm() {
		return move_charge_dep_nm;
	}
	public void setMove_charge_dep_nm(String move_charge_dep_nm) {
		this.move_charge_dep_nm = move_charge_dep_nm;
	}
	public String getMove_charge_tel() {
		return move_charge_tel;
	}
	public void setMove_charge_tel(String move_charge_tel) {
		this.move_charge_tel = move_charge_tel;
	}
	public Date getAck_dt() {
		return ack_dt;
	}
	public void setAck_dt(Date ack_dt) {
		this.ack_dt = ack_dt;
	}
	public List<String> getAckChecked() {
		return ackChecked;
	}
	public void setAckChecked(List<String> ackChecked) {
		this.ackChecked = ackChecked;
	}



}