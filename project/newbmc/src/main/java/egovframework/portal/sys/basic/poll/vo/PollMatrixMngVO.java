package egovframework.portal.sys.basic.poll.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import egovframework.portal.common.vo.CommonVO;

/**
 * 매트릭스 응답 질문 정보 VO
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
public class PollMatrixMngVO extends CommonVO {

	private int idx = 0;
	/** 질문 IDX */
	private int questionIdx = 0;
	/** MATRIX 타입 질문 */
	private String matrixQuestion = "";
	/** 정렬 순서 */
	private int matrixOrder = 0;
	/** 삭제 여부 (defualt : N) */
	private String isDel = "";
	/** 등록 일자 */
	private Date createDate = null;
	/** 수정 일자 */
	private Date updateDate = null;
	/** 삭제 일자 */
	private Date deleteDate = null;

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

	public String getMatrixQuestion() {
		return matrixQuestion;
	}

	public void setMatrixQuestion(String matrixQuestion) {
		this.matrixQuestion = matrixQuestion;
	}

	public int getMatrixOrder() {
		return matrixOrder;
	}

	public void setMatrixOrder(int matrixOrder) {
		this.matrixOrder = matrixOrder;
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

	public List<PollResponseMngVO> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<PollResponseMngVO> responseList) {
		this.responseList = responseList;
	}

	@Override
	public String toString() {
		return "PollMatrixMngVO [idx=" + idx + ", questionIdx=" + questionIdx + ", matrixQuestion=" + matrixQuestion + ", matrixOrder=" + matrixOrder + ", isDel=" + isDel + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + "]";
	}

}