package egovframework.portal.sys.basic.poll.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.basic.poll.vo.PollAnswerAbleMngVO;
import egovframework.portal.sys.basic.poll.vo.PollAnswerMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixColMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollQuestionMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponseMngVO;
import egovframework.portal.util.Tuple;

/**
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 9. 5.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 9. 5.
 */
public interface QuestionMngService {

	/**
	 * 질문 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertQuestion(MultipartHttpServletRequest request, PollMngVO vo) throws Exception;

	/**
	 * 질문 카운트 조회
	 *
	 * @param vo
	 * @return
	 */
	public int getQuestionCnt(PollMngVO vo);

	/**
	 * 기존에 등록되어 있는 질문 정보 조회/세팅
	 *
	 * @param vo
	 * @return
	 */
	public PollMngVO getQuestionInfo(PollMngVO vo);

	/**
	 * 기존에 등록되어 있는 질문, 문항 정보 조회/세팅 (응답자 정보 포함)
	 *
	 * @param vo
	 * @return
	 */
	public PollMngVO getQuestionInfoWithResult(PollMngVO vo);

	/**
	 * 응답 가능 리스트 조회
	 *
	 * @param pollIdx
	 * @return
	 */
	public List<PollAnswerAbleMngVO> getAnswerAbleList(int pollIdx);

	/**
	 * 질문 목록 조회
	 *
	 * @param pollIdx
	 * @return
	 */
	public List<PollQuestionMngVO> getQuestionList(int pollIdx);

	/**
	 * 질문 등록
	 *
	 * @param question
	 * @return
	 */
	public boolean setInsertQuestion(PollQuestionMngVO question);

	/**
	 * 질문 응답 문항 목록 조회
	 *
	 * @param question
	 * @return
	 */
	public List<PollAnswerMngVO> getAnswerList(PollQuestionMngVO question);

	/**
	 * 질문 응답 문항 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertQuestionAnswer(PollAnswerMngVO vo);

	/**
	 * 응답 가능 조건 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollAnswerAbleMngVO> getAbleList(PollQuestionMngVO vo);

	/**
	 * 응답 가능 설정 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertQuestionAble(PollAnswerAbleMngVO vo);

	/**
	 * 매트릭스 행 리스트 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollMatrixMngVO> getMatrixRowList(PollQuestionMngVO vo);

	/**
	 * 매트릭스 행 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertMatrixRow(PollMatrixMngVO vo);

	/**
	 * 매트릭스 열 리스트 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollMatrixColMngVO> getMatrixColList(PollQuestionMngVO vo);

	/**
	 * 매트릭스 열 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertMatrixCol(PollMatrixColMngVO vo);

	/**
	 * 응답 결과 리스트 조회
	 *
	 * @param tuple
	 * @return
	 */
	public List<PollResponseMngVO> getResponseList(Tuple<Integer, String> tuple);

	/**
	 * 질문 수정
	 *
	 * @param vo
	 * @return
	 */
	public boolean setUpdateQuestion(MultipartHttpServletRequest request, PollMngVO vo) throws Exception;

}