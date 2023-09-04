package egovframework.portal.sys.basic.poll.mapper;

import java.util.List;

import egovframework.portal.sys.basic.poll.vo.PollAnswerAbleMngVO;
import egovframework.portal.sys.basic.poll.vo.PollAnswerMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixColMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollQuestionMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponseMngVO;
import egovframework.portal.util.Tuple;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("questionMngServiceMapper")
public interface QuestionMngServiceMapper {

	/**
	 * 질문 등록
	 *
	 * @param vo
	 */
	public int setInsertQuestion(PollQuestionMngVO vo);

	/**
	 * 응답 항목 등록
	 *
	 * @param vo
	 */
	public void setInsertQuestionAnswer(PollAnswerMngVO vo);

	/**
	 * 응답 가능 설정 등록
	 *
	 * @param vo
	 */
	public void setInsertQuestionAble(PollAnswerAbleMngVO vo);

	/**
	 * 응답 가능 설정 삭제
	 *
	 * @param vo
	 */
	public void setDeleteQuestionAble(PollAnswerAbleMngVO vo);

	/**
	 * 매트릭스 행 등록
	 *
	 * @param vo
	 */
	public void setInsertMatrixRow(PollMatrixMngVO vo);

	/**
	 * 매트릭스 열 등록
	 *
	 * @param vo
	 */
	public void setInsertMatrixCol(PollMatrixColMngVO vo);

	/**
	 * 질문 카운트 조회
	 *
	 * @param vo
	 * @return
	 */
	public int getQuestionCnt(PollMngVO vo);

	/**
	 * 질문 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollQuestionMngVO> getQuestionList(PollMngVO vo);

	/**
	 * 응답 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollAnswerMngVO> getAnswerList(PollQuestionMngVO vo);

	/**
	 * 건너뛰기 대상 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollAnswerAbleMngVO> getAbleList(PollQuestionMngVO vo);

	/**
	 * 매트릭스 행 리스트 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollMatrixMngVO> getMatrixRowList(PollQuestionMngVO vo);

	/**
	 * 매트릭스 열 리스트 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollMatrixColMngVO> getMatrixColList(PollQuestionMngVO vo);

	/**
	 * 응답 가능 리스트 조회
	 *
	 * @param pollIdx
	 * @return
	 */
	public List<PollAnswerAbleMngVO> getAnswerAbleList(int pollIdx);

	/**
	 * 응답 결과 리스트 조회
	 *
	 * @param paramMap
	 * @return
	 */
	public List<PollResponseMngVO> getResponseList(Tuple<Integer, String> tuple);

	/**
	 * 기존 질문 삭제
	 *
	 * @param vo
	 */
	public void setDeleteQuestionAll(int idx);

}