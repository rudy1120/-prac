package egovframework.portal.sys.basic.poll.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.EgovFileMngUtil;
import egovframework.com.cmm.service.FileVO;
import egovframework.portal.common.TransDefinition;
import egovframework.portal.sys.basic.poll.mapper.QuestionMngServiceMapper;
import egovframework.portal.sys.basic.poll.service.QuestionMngService;
import egovframework.portal.sys.basic.poll.vo.PollAnswerAbleMngVO;
import egovframework.portal.sys.basic.poll.vo.PollAnswerMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixColMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollQuestionMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponseMngVO;
import egovframework.portal.util.Tuple;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

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
@Service("QuestionMngService")
public class QuestionMngServiceImpl extends EgovAbstractServiceImpl implements QuestionMngService {

	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	QuestionMngServiceMapper questionMngServiceMapper;

	@Resource(name = "txManager")
	private PlatformTransactionManager transactionManager;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	public boolean setInsertQuestion(MultipartHttpServletRequest request, PollMngVO vo) throws Exception {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());

		try {

			Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
			for (int i = 0; i < vo.getQuestionList().size(); i++) {
				files.clear();
				PollQuestionMngVO question = vo.getQuestionList().get(i);
				question.setPollIdx(vo.getIdx());
				/** 질문 이미지 처리 **/
				String questionAtchfile = "questionList[" + i + "].questionAtchfile";
				if (!request.getFileMap().isEmpty() && request.getFileMap() != null && request.getFileMap().containsKey(questionAtchfile) && request.getFileMap().get(questionAtchfile).getSize() != 0) {
					files.put("questionFile", request.getFileMap().get("questionList[" + i + "].questionAtchfile"));
					question.setQuestionFile(getAttachId(files, "questionFile", question.getQuestionFileCn(), request));
				}

				/** 필수 항목 여부 **/
				if ("on".equals(question.getRequiredYn())) {
					question.setRequiredYn("Y");
				} else {
					question.setRequiredYn("N");
				}
				questionMngServiceMapper.setInsertQuestion(question);
				/** 응답 보기 항목 등록 **/
				for (int j = 0; j < question.getAnswerList().size(); j++) {
					files.clear();
					PollAnswerMngVO answer = question.getAnswerList().get(j);
					String answerAtchfile = "questionList[" + i + "].answerList[" + j + "].answerAtchfile";
					if (!request.getFileMap().isEmpty() && request.getFileMap() != null && request.getFileMap().containsKey(answerAtchfile) && request.getFileMap().get(answerAtchfile).getSize() != 0) {
						files.put("answerAtchfile", request.getFileMap().get("questionList[" + i + "].answerList[" + j + "].answerAtchfile"));
						answer.setAnswerFile(getAttachId(files, "answerAtchfile", answer.getAnswerFileCn(), request));
					}
					/** 보기 이미지 처리 **/
					if ("기타".equals(answer.getAnswer())) {
						answer.setEtcYn("Y");
					} else {
						answer.setEtcYn("N");
					}
					answer.setQuestionIdx(question.getIdx());
					questionMngServiceMapper.setInsertQuestionAnswer(answer);
				}
				if ("Y".equals(question.getAbleYn())) {
					/** 문항 건너뛰기 등록 **/
					for (PollAnswerAbleMngVO able : question.getAbleList()) {
						able.setPollIdx(vo.getIdx());
						able.setQuestionIdx(question.getIdx());
						questionMngServiceMapper.setInsertQuestionAble(able);
					}
				}
				/** Matrix 등록 **/
				if ("6".equals(question.getType())) {
					for (int j = 0; j < question.getMatrixList().size(); j++) {
						PollMatrixMngVO matrix = question.getMatrixList().get(j);
						matrix.setQuestionIdx(question.getIdx());
						matrix.setMatrixOrder(j);
						questionMngServiceMapper.setInsertMatrixRow(matrix);
					}
					for (int j = 0; j < question.getMatrixColList().size(); j++) {
						PollMatrixColMngVO matrixCol = question.getMatrixColList().get(j);
						matrixCol.setQuestionIdx(question.getIdx());
						matrixCol.setMatrixDepth(j);
						questionMngServiceMapper.setInsertMatrixCol(matrixCol);
					}
				}
			}
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	public boolean setUpdateQuestion(MultipartHttpServletRequest request, PollMngVO vo) throws Exception {

		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());

		try {

			/** 기존에 등록되어 있던 질문 삭제 **/
			questionMngServiceMapper.setDeleteQuestionAll(vo.getIdx());
			/** 기존 문항 제거 **/
			questionMngServiceMapper.setDeleteQuestionAble(new PollAnswerAbleMngVO(vo.getIdx()));
			Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
			for (int i = 0; i < vo.getQuestionList().size(); i++) {
				files.clear();
				PollQuestionMngVO question = vo.getQuestionList().get(i);
				question.setPollIdx(vo.getIdx());
				/** 질문 이미지 처리 **/
				String questionAtchfile = "questionList[" + i + "].questionAtchfile";
				if (!request.getFileMap().isEmpty() && request.getFileMap() != null && request.getFileMap().containsKey(questionAtchfile) && request.getFileMap().get(questionAtchfile).getSize() != 0) {
					files.put("questionFile", request.getFileMap().get("questionList[" + i + "].questionAtchfile"));
					question.setQuestionFile(getAttachId(files, "questionFile", question.getQuestionFileCn(), request));
				} else {
					if ("Y".equals(question.getAtchFileDel())) {
						question.setQuestionFile(""); //기존 첨부파일 삭제
					} else {
						question.setQuestionFile(question.getOldAtchFileId()); //기존 첨부파일 유지
						fileMngService.updateFileCn(new FileVO(question.getQuestionFile(), "0", question.getQuestionFileCn()));
					}
				}

				/** 필수 항목 여부 **/
				if ("on".equals(question.getRequiredYn())) {
					question.setRequiredYn("Y");
				} else {
					question.setRequiredYn("N");
				}
				questionMngServiceMapper.setInsertQuestion(question);
				/** 응답 보기 항목 등록 **/
				for (int j = 0; j < question.getAnswerList().size(); j++) {
					files.clear();
					PollAnswerMngVO answer = question.getAnswerList().get(j);
					String answerAtchfile = "questionList[" + i + "].answerList[" + j + "].answerAtchfile";
					if (!request.getFileMap().isEmpty() && request.getFileMap() != null && request.getFileMap().containsKey(answerAtchfile) && request.getFileMap().get(answerAtchfile).getSize() != 0) {
						files.put("answerAtchfile", request.getFileMap().get("questionList[" + i + "].answerList[" + j + "].answerAtchfile"));
						answer.setAnswerFile(getAttachId(files, "answerAtchfile", answer.getAnswerFileCn(), request));
					} else {
						if ("Y".equals(answer.getAtchFileDel())) {
							answer.setAnswerFile("");
						} else {
							answer.setAnswerFile(answer.getOldAtchFileId());
							fileMngService.updateFileCn(new FileVO(answer.getAnswerFile(), "0", answer.getAnswerFileCn()));
						}
					}
					/** 보기 이미지 처리 **/
					if ("기타".equals(answer.getAnswer())) {
						answer.setEtcYn("Y");
					} else {
						answer.setEtcYn("N");
					}
					answer.setQuestionIdx(question.getIdx());
					questionMngServiceMapper.setInsertQuestionAnswer(answer);
				}
				if ("Y".equals(question.getAbleYn())) {
					/** 문항 건너뛰기 등록 **/
					for (PollAnswerAbleMngVO able : question.getAbleList()) {
						able.setPollIdx(vo.getIdx());
						able.setQuestionIdx(question.getIdx());
						questionMngServiceMapper.setInsertQuestionAble(able);
					}
				}
				/** Matrix 등록 **/
				if ("6".equals(question.getType())) {
					for (int j = 0; j < question.getMatrixList().size(); j++) {
						PollMatrixMngVO matrix = question.getMatrixList().get(j);
						matrix.setQuestionIdx(question.getIdx());
						matrix.setMatrixOrder(j);
						questionMngServiceMapper.setInsertMatrixRow(matrix);
					}
					for (int j = 0; j < question.getMatrixColList().size(); j++) {
						PollMatrixColMngVO matrixCol = question.getMatrixColList().get(j);
						matrixCol.setQuestionIdx(question.getIdx());
						matrixCol.setMatrixDepth(j);
						questionMngServiceMapper.setInsertMatrixCol(matrixCol);
					}
				}
			}
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	public String getAttachId(Map<String, MultipartFile> files, String name, String fileCn, MultipartHttpServletRequest request) throws Exception {

		if (!files.isEmpty() && files.get(name) != null) {
			List<FileVO> _result = fileUtil.parseFileInf(files, "QUESTION_", 0, "", "/home/webdata/egov_uploadFile/POLL/QUESTION/", false, request);
			if (_result != null) {
				_result.get(0).setFileCn(fileCn);
				return fileMngService.insertFileInfs(_result); // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
			} else {
				return "";
			}
		} else {
			return "";
		}

	}

	/**
	 * 질문 카운트 조회
	 *
	 * @param vo
	 * @return
	 */
	public int getQuestionCnt(PollMngVO vo) {
		return questionMngServiceMapper.getQuestionCnt(vo);
	}

	/**
	 * 기존에 등록되어 있는 질문, 문항 정보 조회/세팅
	 *
	 * @param vo
	 * @return
	 */
	public PollMngVO getQuestionInfo(PollMngVO vo) {
		vo.setQuestionList(getQuestionList(vo, Boolean.FALSE));
		return vo;
	}

	/**
	 * 기존에 등록되어 있는 질문, 문항 정보 조회/세팅 (응답자 정보 포함)
	 *
	 * @param vo
	 * @return
	 */
	public PollMngVO getQuestionInfoWithResult(PollMngVO vo) {
		vo.setQuestionList(getQuestionList(vo, Boolean.TRUE));
		return vo;
	}

	/**
	 * 질문 목록 조회
	 *
	 * @param vo
	 * @param result
	 * @return
	 */
	public List<PollQuestionMngVO> getQuestionList(PollMngVO vo, Boolean result) {
		List<PollQuestionMngVO> questionList = questionMngServiceMapper.getQuestionList(vo);
		for (PollQuestionMngVO question : questionList) {
			question.setDup(result);
			question.setAnswerList(questionMngServiceMapper.getAnswerList(question));
			question.setAbleList(questionMngServiceMapper.getAbleList(question));
			question.setMatrixList(questionMngServiceMapper.getMatrixRowList(question));
			question.setMatrixColList(questionMngServiceMapper.getMatrixColList(question));
			if (result) {
				for (PollAnswerMngVO answer : question.getAnswerList()) {
					answer.setResponseList(questionMngServiceMapper.getResponseList(new Tuple<Integer, String>(answer.getIdx(), "answer")));
				}
				for (PollMatrixMngVO matrix : question.getMatrixList()) {
					matrix.setResponseList(questionMngServiceMapper.getResponseList(new Tuple<Integer, String>(matrix.getIdx(), "matrix")));
				}
			}
		}
		return questionList;
	}

	/**
	 * 응답 가능 리스트 조회
	 *
	 * @param pollIdx
	 * @return
	 */
	public List<PollAnswerAbleMngVO> getAnswerAbleList(int pollIdx) {
		return questionMngServiceMapper.getAnswerAbleList(pollIdx);
	}

	/**
	 * 질문 목록 조회
	 *
	 * @param pollIdx
	 * @return
	 */
	public List<PollQuestionMngVO> getQuestionList(int pollIdx) {
		return questionMngServiceMapper.getQuestionList(new PollMngVO(pollIdx));
	}

	/**
	 * 질문 등록
	 *
	 * @param question
	 * @return
	 */
	public boolean setInsertQuestion(PollQuestionMngVO question) {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		try {
			questionMngServiceMapper.setInsertQuestion(question);
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 질문 응답 문항 목록 조회
	 *
	 * @param question
	 * @return
	 */
	public List<PollAnswerMngVO> getAnswerList(PollQuestionMngVO question) {
		return questionMngServiceMapper.getAnswerList(question);
	}

	/**
	 * 질문 응답 문항 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertQuestionAnswer(PollAnswerMngVO vo) {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		try {
			questionMngServiceMapper.setInsertQuestionAnswer(vo);
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 응답 가능 조건 목록 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollAnswerAbleMngVO> getAbleList(PollQuestionMngVO vo) {
		return questionMngServiceMapper.getAbleList(vo);
	}

	/**
	 * 응답 가능 설정 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertQuestionAble(PollAnswerAbleMngVO vo) {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		try {
			questionMngServiceMapper.setInsertQuestionAble(vo);
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 매트릭스 행 리스트 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollMatrixMngVO> getMatrixRowList(PollQuestionMngVO vo) {
		return questionMngServiceMapper.getMatrixRowList(vo);
	}

	/**
	 * 매트릭스 행 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertMatrixRow(PollMatrixMngVO vo) {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		try {
			questionMngServiceMapper.setInsertMatrixRow(vo);
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 매트릭스 열 리스트 조회
	 *
	 * @param vo
	 * @return
	 */
	public List<PollMatrixColMngVO> getMatrixColList(PollQuestionMngVO vo) {
		return questionMngServiceMapper.getMatrixColList(vo);
	}

	/**
	 * 매트릭스 열 등록
	 *
	 * @param vo
	 * @return
	 */
	public boolean setInsertMatrixCol(PollMatrixColMngVO vo) {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		try {
			questionMngServiceMapper.setInsertMatrixCol(vo);
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			e.printStackTrace();
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 응답 결과 리스트 조회
	 *
	 * @param tuple
	 * @return
	 */
	public List<PollResponseMngVO> getResponseList(Tuple<Integer, String> tuple) {
		return questionMngServiceMapper.getResponseList(tuple);
	}

}