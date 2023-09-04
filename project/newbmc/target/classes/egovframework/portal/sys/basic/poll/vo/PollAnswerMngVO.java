package egovframework.portal.sys.basic.poll.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import egovframework.portal.common.vo.CommonVO;

/**
 * 일반 응답 항목 정보 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 7. 19.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 7. 19.
 */
public class PollAnswerMngVO extends CommonVO {

	private int idx = 0;
	/** 질문 IDX */
	private int questionIdx = 0;
	/** 응답 내용 */
	private String answer = "";
	/** 응답 파일 */
	private String answerFile = "";
	/** 응답 항목 순서 */
	private int answerOrder = 0;
	/** 기타 항목 여부 (N:불가능, Y:가능) */
	private String etcYn = "";
	/** 삭제 여부 (defualt : N) */
	private String isDel = "";
	/** 등록 일자 */
	private Date createDate = null;
	/** 수정 일자 */
	private Date updateDate = null;
	/** 삭제 일자 */
	private Date deleteDate = null;
	/** 응답 파일 설명글 */
	private String answerFileCn = "";
	/** 기존 첨부파일 ID */
	private String oldAtchFileId = "";
	/** 기존 파일 삭제 */
	private String atchFileDel = "";
	/** 응답자 수 */
	private int answerCnt = 0;
	/** 매트릭스 응답자 수 */
	private int matrixCnt = 0;
	/** 전체 응답자 수 */
	private int totalCnt = 0;

	private List<PollResponseMngVO> responseList = new ArrayList<PollResponseMngVO>();

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getQuestionIdx() {
		return questionIdx;
	}

	public void setQuestionIdx(int questionIdx) {
		this.questionIdx = questionIdx;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswerFile() {
		return answerFile;
	}

	public void setAnswerFile(String answerFile) {
		this.answerFile = answerFile;
	}

	public int getAnswerOrder() {
		return answerOrder;
	}

	public void setAnswerOrder(int answerOrder) {
		this.answerOrder = answerOrder;
	}

	public String getEtcYn() {
		return etcYn;
	}

	public void setEtcYn(String etcYn) {
		this.etcYn = etcYn;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
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

	public String getAnswerFileCn() {
		return answerFileCn;
	}

	public void setAnswerFileCn(String answerFileCn) {
		this.answerFileCn = answerFileCn;
	}

	public String getOldAtchFileId() {
		return oldAtchFileId;
	}

	public void setOldAtchFileId(String oldAtchFileId) {
		this.oldAtchFileId = oldAtchFileId;
	}

	public String getAtchFileDel() {
		return atchFileDel;
	}

	public void setAtchFileDel(String atchFileDel) {
		this.atchFileDel = atchFileDel;
	}

	public int getAnswerCnt() {
		return answerCnt;
	}

	public void setAnswerCnt(int answerCnt) {
		this.answerCnt = answerCnt;
	}

	public int getMatrixCnt() {
		return matrixCnt;
	}

	public void setMatrixCnt(int matrixCnt) {
		this.matrixCnt = matrixCnt;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public List<PollResponseMngVO> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<PollResponseMngVO> responseList) {
		this.responseList = responseList;
	}

}