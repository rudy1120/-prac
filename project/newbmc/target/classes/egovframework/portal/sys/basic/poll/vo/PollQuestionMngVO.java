package egovframework.portal.sys.basic.poll.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import egovframework.portal.common.vo.CommonVO;

/**
 * 질문 정보 VO
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
public class PollQuestionMngVO extends CommonVO {

	private int idx = 0;
	/** 설문 IDX */
	private int pollIdx = 0;
	/** 문항 유형 (1:객관식 중복, 2:객관식 단답, 3:주관식 단답, 4:주관식 장문, 5:순위 선정형, 6:매트릭스 형) */
	private String type = "";
	/** 질문 */
	private String question = "";
	/** 질문 첨부파일 */
	private String questionFile = "";
	/** 필수 여부 (N:일반, Y:필수) */
	private String requiredYn = "Y";
	/** 삭제 여부 (defualt : N) */
	private String isDel = "";
	/** 등록 일자 */
	private Date createDate = null;
	/** 수정 일자 */
	private Date updateDate = null;
	/** 삭제 일자 */
	private Date deleteDate = null;
	/** 건너뛰기 설정 여부 */
	private String ableYn = "N";
	/** 보기 리스트 */
	private List<PollAnswerMngVO> answerList = new ArrayList<PollAnswerMngVO>();
	/** 건너뛰기 대상 리스트 */
	private List<PollAnswerAbleMngVO> ableList = new ArrayList<PollAnswerAbleMngVO>();
	/** 매트릭스 행 리스트 */
	private List<PollMatrixMngVO> matrixList = new ArrayList<PollMatrixMngVO>();
	/** 매트릭스 열 리스트 */
	private List<PollMatrixColMngVO> matrixColList = new ArrayList<PollMatrixColMngVO>();
	/** 파일 설명 글 */
	private String questionFileCn = "";
	/** 다중선택 제한 개수 */
	private String multipleLimit = "";
	/** 다중선택 개수 이상/이하 적용 여부 (NONE:안함, U:이상, L:이하) */
	private String isMore = "";
	/** 기존 첨부파일 ID */
	private String oldAtchFileId = "";
	/** 기존 파일 삭제 */
	private String atchFileDel = "";


	/************ 검색용 파라미터 ************/
	/** 중복 확인 */
	private Boolean dup = Boolean.FALSE;

	public PollQuestionMngVO() {
		super();
	}

	public PollQuestionMngVO(int pollIdx, String type) {
		super();
		this.pollIdx = pollIdx;
		this.type = type;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public int getPollIdx() {
		return pollIdx;
	}

	public void setPollIdx(int pollIdx) {
		this.pollIdx = pollIdx;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionFile() {
		return questionFile;
	}

	public void setQuestionFile(String questionFile) {
		this.questionFile = questionFile;
	}

	public String getRequiredYn() {
		return requiredYn;
	}

	public void setRequiredYn(String requiredYn) {
		this.requiredYn = requiredYn;
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

	public String getAbleYn() {
		return ableYn;
	}

	public void setAbleYn(String ableYn) {
		this.ableYn = ableYn;
	}

	public List<PollAnswerMngVO> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<PollAnswerMngVO> answerList) {
		this.answerList = answerList;
	}

	public List<PollAnswerAbleMngVO> getAbleList() {
		return ableList;
	}

	public void setAbleList(List<PollAnswerAbleMngVO> ableList) {
		this.ableList = ableList;
	}

	public String getQuestionFileCn() {
		return questionFileCn;
	}

	public void setQuestionFileCn(String questionFileCn) {
		this.questionFileCn = questionFileCn;
	}

	public String getMultipleLimit() {
		return multipleLimit;
	}

	public void setMultipleLimit(String multipleLimit) {
		this.multipleLimit = multipleLimit;
	}

	public String getIsMore() {
		return isMore;
	}

	public void setIsMore(String isMore) {
		this.isMore = isMore;
	}

	public List<PollMatrixMngVO> getMatrixList() {
		return matrixList;
	}

	public void setMatrixList(List<PollMatrixMngVO> matrixList) {
		this.matrixList = matrixList;
	}

	public List<PollMatrixColMngVO> getMatrixColList() {
		return matrixColList;
	}

	public void setMatrixColList(List<PollMatrixColMngVO> matrixColList) {
		this.matrixColList = matrixColList;
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

	public boolean isDup() {
		return dup;
	}

	public void setDup(boolean dup) {
		this.dup = dup;
	}

}