package egovframework.com.yhdb.cooltube.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

/**
 * Cooltube 썸네일 파일 VO
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * -------------	----------------	---------------------------
 * 2016-09-29		박동환				최초 생성 및 코딩
 * 
 * </pre>
 *
 * @author 개발팀 박동환
 * @since 2016-09-29
 * @version 1.0
 */
public class MovFileVO extends CommonVO {

	/** 동영상 파일 인덱스 */
	private int movFileIdx = 0;
	/** 공통 파일 아이디 */
	private String atchFileId = "";
	/** 파일 순서 */
	private String fileSn = "";
	/** 원 파일명 */
	private String beforEncodingFileNm = "";
	/** 인코딩 단계 */
	private String encodingStep = "";
	/** 실패 횟수 */
	private int failCount = 0;
	/** 인코딩 단계 */
	private Date regDt = new Date();
	/** 실제 파일 경로 */
	private String fileStreCours = "";
	/** 저장 파일 이름 */
	private String streFileNm = "";
	
	
	public int getMovFileIdx() {
		return movFileIdx;
	}
	public void setMovFileIdx(int movFileIdx) {
		this.movFileIdx = movFileIdx;
	}
	public String getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}
	public String getFileSn() {
		return fileSn;
	}
	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}
	public String getBeforEncodingFileNm() {
		return beforEncodingFileNm;
	}
	public void setBeforEncodingFileNm(String beforEncodingFileNm) {
		this.beforEncodingFileNm = beforEncodingFileNm;
	}
	public String getEncodingStep() {
		return encodingStep;
	}
	public void setEncodingStep(String encodingStep) {
		this.encodingStep = encodingStep;
	}
	public int getFailCount() {
		return failCount;
	}
	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	
	

}
