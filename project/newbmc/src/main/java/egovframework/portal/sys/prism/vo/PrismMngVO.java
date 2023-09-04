package egovframework.portal.sys.prism.vo;

import java.io.Serializable;
import egovframework.portal.common.vo.CommonVO;
/**
 * 관리자 - 정책연구보고서 게시판 VO 클래스
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2019.10.22		박선민				최초 생성
 * </pre>
 * 
 * @author 박선민
 * @since 2019.10.22
 */
public class PrismMngVO extends CommonVO implements Serializable{
	
	private static final long serialVersionUID = 4691964437017835157L;
	
	/*기본키*/
	private int idx;
	
	/*과제명*/
	private String projectNm;
	
	/*기관명*/
	private String orgnNm;
	
	/*담당부서*/
	private String depNm;
	
	/*전화번호*/
	private String phoneNb;
	
	/*연구기간 시작일*/
	private String rSdate;
	
	/*연구기간 종료일*/
	private String rEdate;

	/*연구분야*/
	private String rField;

	/*개요*/
	private String summary;
	
	/*수행기관*/
	private String executeNm;
	
	/*수행연구원*/
	private String researcher;
	
	/*계약일자*/
	private String contDt;
	
	/*계약방식*/
	private String contMethod;	

	/*계약금액*/
	private String contPrice;
	
	/*연구제목*/
	private String title;
	
	/*영문제목*/
	private String eTitle;
	
	/*부제목*/
	private String subtitle;
	
	/*결과보고서*/
	private String resReport;
	
	/*주제어*/
	private String topic;
	
	/*제작일자*/
	private String prdtDt;

	/*공헌자*/
	private String contributor;
	
	/*발행년도*/
	private String issYear;
	
	/*제출일*/
	private String subDt;
	
	/*목차*/
	private String contents;

	/*평가결과서*/
	private String evalResult;

	/*활용결과 보고서*/
	private String applReport;	
	
	/*등록일자*/
	private String createDate;	
	
	/*등록일자*/
	private String updateDate;	
	
	/*조회수*/
	private int cnt;
	
	/*삭제여부*/
	private char isDel;
	
	/*작성자 부서명*/
	private String bWrite;
	
	/*작성자 ID*/
	private String writeId;
	
	/*작성자 IP*/
	private String hostIp;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getProjectNm() {
		return projectNm;
	}

	public void setProjectNm(String projectNm) {
		this.projectNm = projectNm;
	}

	public String getOrgnNm() {
		return orgnNm;
	}

	public void setOrgnNm(String orgnNm) {
		this.orgnNm = orgnNm;
	}

	public String getDepNm() {
		return depNm;
	}

	public void setDepNm(String depNm) {
		this.depNm = depNm;
	}

	public String getPhoneNb() {
		return phoneNb;
	}

	public void setPhoneNb(String phoneNb) {
		this.phoneNb = phoneNb;
	}

	public String getrSdate() {
		return rSdate;
	}

	public void setrSdate(String rSdate) {
		this.rSdate = rSdate;
	}

	public String getrEdate() {
		return rEdate;
	}

	public void setrEdate(String rEdate) {
		this.rEdate = rEdate;
	}

	public String getrField() {
		return rField;
	}

	public void setrField(String rField) {
		this.rField = rField;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getExecuteNm() {
		return executeNm;
	}

	public void setExecuteNm(String executeNm) {
		this.executeNm = executeNm;
	}

	public String getResearcher() {
		return researcher;
	}

	public void setResearcher(String researcher) {
		this.researcher = researcher;
	}

	public String getContDt() {
		return contDt;
	}

	public void setContDt(String contDt) {
		this.contDt = contDt;
	}

	public String getContMethod() {
		return contMethod;
	}

	public void setContMethod(String contMethod) {
		this.contMethod = contMethod;
	}

	public String getContPrice() {
		return contPrice;
	}

	public void setContPrice(String contPrice) {
		this.contPrice = contPrice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String geteTitle() {
		return eTitle;
	}

	public void seteTitle(String eTitle) {
		this.eTitle = eTitle;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getResReport() {
		return resReport;
	}

	public void setResReport(String resReport) {
		this.resReport = resReport;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getPrdtDt() {
		return prdtDt;
	}

	public void setPrdtDt(String prdtDt) {
		this.prdtDt = prdtDt;
	}

	public String getContributor() {
		return contributor;
	}

	public void setContributor(String contributor) {
		this.contributor = contributor;
	}

	public String getIssYear() {
		return issYear;
	}

	public void setIssYear(String issYear) {
		this.issYear = issYear;
	}

	public String getSubDt() {
		return subDt;
	}

	public void setSubDt(String subDt) {
		this.subDt = subDt;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getEvalResult() {
		return evalResult;
	}

	public void setEvalResult(String evalResult) {
		this.evalResult = evalResult;
	}

	public String getApplReport() {
		return applReport;
	}

	public void setApplReport(String applReport) {
		this.applReport = applReport;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public char getIsDel() {
		return isDel;
	}

	public void setIsDel(char isDel) {
		this.isDel = isDel;
	}

	public String getbWrite() {
		return bWrite;
	}

	public void setbWrite(String string) {
		this.bWrite = string;
	}

	public String getWriteId() {
		return writeId;
	}

	public void setWriteId(String string) {
		this.writeId = string;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String string) {
		this.hostIp = string;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
