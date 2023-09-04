package egovframework.portal.sys.basic.poll.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import egovframework.portal.common.vo.CommonVO;

/**
 * 응답 결과 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017.07.20		권태성				최초 생성 및 코딩
 * 2017.08.25		J.Ryeon Lee			응답자/응답 결과 JOIN 처리
 * </pre>
 *
 * @author 권태성
 * @since 2017. 7. 20.
 */
public class PollResponseMngVO extends CommonVO {

	private int idx = 0;
	/** 설문 IDX */
	private int pollIdx = 0;
	/** 질문 IDX */
	private int questionIdx = 0;
	/** 응답 IDX */
	private int answerIdx = 0;
	/** MATRIX 질문 IDX */
	private int matrixIdx = 0;
	/** MATRIX 응답 IDX */
	private int matrixAnswerIdx = 0;
	/** 주관식 응답 메시지 (객관식은 선택한 IDX를 따라감) */
	private String value = "";
	/** 응답자 IDX */
	private int userIdx = 0;
	/** 삭제 여부 (defualt : N) */
	private String isDel = "";
	/** 등록 일자 */
	private Date createDate = null;
	/** 수정 일자 */
	private Date updateDate = null;
	/** 삭제 일자 */
	private Date deleteDate = null;

	/** matrix형 응답을 받기 위한 list */
	private List<PollResponseMngVO> matList = new ArrayList<PollResponseMngVO>();
	/** 객관식 복수 선택 */
	private List<Integer> answerIdxList = new ArrayList<Integer>();
	/** 순위 선정형 응답을 받기 위한 list */
	private List<PollResponseMngVO> orderList = new ArrayList<PollResponseMngVO>();

	/* ##### PROCESS ##### */

	/** 응답자 개인정보 상세 */
	private PollResponderMngVO responder = null;

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

	public int getQuestionIdx() {
		return questionIdx;
	}

	public void setQuestionIdx(int questionIdx) {
		this.questionIdx = questionIdx;
	}

	public int getAnswerIdx() {
		return answerIdx;
	}

	public void setAnswerIdx(int answerIdx) {
		this.answerIdx = answerIdx;
	}

	public int getMatrixIdx() {
		return matrixIdx;
	}

	public void setMatrixIdx(int matrixIdx) {
		this.matrixIdx = matrixIdx;
	}

	public int getMatrixAnswerIdx() {
		return matrixAnswerIdx;
	}

	public void setMatrixAnswerIdx(int matrixAnswerIdx) {
		this.matrixAnswerIdx = matrixAnswerIdx;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getUserIdx() {
		return userIdx;
	}

	public void setUserIdx(int userIdx) {
		this.userIdx = userIdx;
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

	public PollResponderMngVO getResponder() {
		return responder;
	}

	public void setResponder(PollResponderMngVO responder) {
		this.responder = responder;
	}

	public List<PollResponseMngVO> getMatList() {
		return matList;
	}

	public void setMatList(List<PollResponseMngVO> matList) {
		this.matList = matList;
	}

	public List<Integer> getAnswerIdxList() {
		return answerIdxList;
	}

	public void setAnswerIdxList(List<Integer> answerIdxList) {
		this.answerIdxList = answerIdxList;
	}

	public List<PollResponseMngVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<PollResponseMngVO> orderList) {
		this.orderList = orderList;
	}

	@Override
	public String toString() {
		return "PollResponseMngVO [idx=" + idx + ", pollIdx=" + pollIdx + ", questionIdx=" + questionIdx + ", answerIdx=" + answerIdx + ", matrixIdx=" + matrixIdx + ", matrixAnswerIdx=" + matrixAnswerIdx + ", value=" + value + ", userIdx=" + userIdx + ", isDel=" + isDel + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + "]";
	}

}