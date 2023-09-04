package egovframework.portal.sys.basic.poll.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

/**
 * 매트릭스 응답 열 정보 VO
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
public class PollMatrixColMngVO extends CommonVO {

	private int idx = 0;
	/** MATRIX IDX */
	private int questionIdx = 0;
	/** 열 이름 */
	private String matrix = "";
	/** 몇 번째 열인지 구분 값 */
	private int matrixDepth = 0;
	/** 삭제 여부 (defualt : N) */
	private String isDel = "";
	/** 등록 일자 */
	private Date createDate = null;
	/** 수정 일자 */
	private Date updateDate = null;
	/** 삭제 일자 */
	private Date deleteDate = null;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getMatrix() {
		return matrix;
	}

	public void setMatrix(String matrix) {
		this.matrix = matrix;
	}

	public int getMatrixDepth() {
		return matrixDepth;
	}

	public void setMatrixDepth(int matrixDepth) {
		this.matrixDepth = matrixDepth;
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

	public int getQuestionIdx() {
		return questionIdx;
	}

	public void setQuestionIdx(int questionIdx) {
		this.questionIdx = questionIdx;
	}

}