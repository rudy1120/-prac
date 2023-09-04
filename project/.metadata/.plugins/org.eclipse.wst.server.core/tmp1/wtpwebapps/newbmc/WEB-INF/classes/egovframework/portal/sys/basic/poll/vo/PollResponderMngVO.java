package egovframework.portal.sys.basic.poll.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

/**
 * 응답자 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 7. 20.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 7. 20.
 */
public class PollResponderMngVO extends CommonVO {

	private int idx = 0;
	/** 이름 */
	private String name = "";
	/** 생년월일 (yyyymmdd) */
	private String birth = "";
	/** 성별 */
	private String gender = "";
	/** 전화 번호 */
	private String tel = "";
	/** 개인 식별 코드 */
	private String privatecode = "";
	/** IP 주소 */
	private String ip = "";
	/** 삭제 여부 (defualt : N) */
	private String isDel = "";
	/** 수집 동의일자 */
	private Date agreeDate = null;
	/** 등록 일자 */
	private Date createDate = null;
	/** 수정 일자 */
	private Date updateDate = null;
	/** 삭제 일자 */
	private Date deleteDate = null;
	/** 응답한 설문 IDX */
	private int pollIdx = 0;
	/** 추첨 여부 (1:참여완료, 2:당첨, 3:당첨(취소), 4:대기) */
	private String lottery = "1";
	/** 추첨 취소 일자 */
	private Date lotCancelDate = null;

	/* ##### SEARCH KEYWORD ##### */

	/** 추첨상태 */
	private String searchState = "";
	/** 성별 */
	private String searchGender = "";

	public PollResponderMngVO() {
		// default
	}

	public PollResponderMngVO(String name, String birth, String gender, String tel, String privatecode, String ip, int pollIdx, String lottery) {
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.tel = tel;
		this.privatecode = privatecode;
		this.ip = ip;
		this.pollIdx = pollIdx;
		this.lottery = lottery;
	}

	public PollResponderMngVO(String ip, int pollIdx, String lottery) {
		this.ip = ip;
		this.pollIdx = pollIdx;
		this.lottery = lottery;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPrivatecode() {
		return privatecode;
	}

	public void setPrivatecode(String privatecode) {
		this.privatecode = privatecode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Date getAgreeDate() {
		return agreeDate;
	}

	public void setAgreeDate(Date agreeDate) {
		this.agreeDate = agreeDate;
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

	public int getPollIdx() {
		return pollIdx;
	}

	public void setPollIdx(int pollIdx) {
		this.pollIdx = pollIdx;
	}

	public String getLottery() {
		return lottery;
	}

	public void setLottery(String lottery) {
		this.lottery = lottery;
	}

	public Date getLotCancelDate() {
		return lotCancelDate;
	}

	public void setLotCancelDate(Date lotCancelDate) {
		this.lotCancelDate = lotCancelDate;
	}

	public String getSearchState() {
		return searchState;
	}

	public void setSearchState(String searchState) {
		this.searchState = searchState;
	}

	public String getSearchGender() {
		return searchGender;
	}

	public void setSearchGender(String searchGender) {
		this.searchGender = searchGender;
	}

	@Override
	public String toString() {
		return "PollResponderMngVO [idx=" + idx + ", name=" + name + ", birth=" + birth + ", gender=" + gender + ", tel=" + tel + ", privatecode=" + privatecode + ", ip=" + ip + ", isDel=" + isDel + ", agreeDate=" + agreeDate + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + ", pollIdx=" + pollIdx + "]";
	}

}