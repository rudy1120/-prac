package egovframework.portal.sys.bbs.vo;

public class BbsEbookInfoVO {

	/** 게시판 IDX */
	private String ptIdx = "";
	/** 게시글 IDX */
	private String bIdx = "";
	/** 첨부파일 ID */
	private String atchFileId = "";
	/** 파일관리 아이디 */
	private String fileSn = "";
	/** 이북 변환 코드 */
	private String ebookCode = "";
	/** 등록일 */
	private String createDate = "";
	/** 수정일 */
	private String updateDate = "";

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public String getbIdx() {
		return bIdx;
	}

	public void setbIdx(String bIdx) {
		this.bIdx = bIdx;
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

	public String getEbookCode() {
		return ebookCode;
	}

	public void setEbookCode(String ebookCode) {
		this.ebookCode = ebookCode;
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

}
