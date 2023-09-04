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
import egovframework.portal.sys.basic.poll.mapper.PollMngServiceMapper;
import egovframework.portal.sys.basic.poll.service.PollMngService;
import egovframework.portal.sys.basic.poll.service.QuestionMngService;
import egovframework.portal.sys.basic.poll.vo.PollAnswerAbleMngVO;
import egovframework.portal.sys.basic.poll.vo.PollAnswerMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixColMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMatrixMngVO;
import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollQuestionMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponderMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponseMngVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.StringUtil;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 설문조사 관리
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
@Service("pollMngService")
public class PollMngServiceImpl extends EgovAbstractServiceImpl implements PollMngService {

	private static final Logger LOGGER = LogManager.getLogger();

	@Autowired
	private PollMngServiceMapper pollMngServiceMapper;

	@Autowired
	private QuestionMngService questionMngService;

	@Resource(name = "txManager")
	private PlatformTransactionManager transactionManager;

	@Resource(name = "EgovFileMngService")
	private EgovFileMngService fileMngService;

	@Resource(name = "EgovFileMngUtil")
	private EgovFileMngUtil fileUtil;

	/**
	 * 설문조사 관리 카운트
	 *
	 * @param param
	 * @return
	 */
	public int getPollMngCnt(PollMngVO param) {
		return pollMngServiceMapper.getPollMngCnt(param);
	}

	/**
	 * 설문조사 관리 목록
	 *
	 * @param param
	 * @return
	 */
	public List<PollMngVO> getPollMngList(PollMngVO param) {
		return pollMngServiceMapper.getPollMngList(param);
	}

	/**
	 * 설문조사 등록
	 *
	 * @param param
	 */
	public boolean setInsertPoll(MultipartHttpServletRequest request, PollMngVO param) throws Exception {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		try {
			param.setHeaderFile((uploadFile(request, "headerFile")));
			param.setMainFile((uploadFile(request, "mainFile")));
			pollMngServiceMapper.setInsertPoll(param);
			if (StringUtil.isNotBlank(param.getBeforeIdx()) && "Y".equals(param.getQuestionYn())) {
				List<PollQuestionMngVO> questionList = questionMngService.getQuestionList(Integer.parseInt(param.getBeforeIdx()));
				for (PollQuestionMngVO question : questionList) {
					List<PollAnswerMngVO> answerList = questionMngService.getAnswerList(question);
					List<PollAnswerAbleMngVO> ableList = questionMngService.getAbleList(question);
					List<PollMatrixMngVO> matrixList = questionMngService.getMatrixRowList(question);
					List<PollMatrixColMngVO> colList = questionMngService.getMatrixColList(question);
					question.setPollIdx(param.getIdx());
					questionMngService.setInsertQuestion(question);
					for (PollAnswerMngVO answer : answerList) {
						answer.setQuestionIdx(question.getIdx());
						questionMngService.setInsertQuestionAnswer(answer);
					}
					for (PollAnswerAbleMngVO able : ableList) {
						able.setQuestionIdx(question.getIdx());
						able.setPollIdx(param.getIdx());
						questionMngService.setInsertQuestionAble(able);
					}
					for (PollMatrixMngVO matrix : matrixList) {
						matrix.setQuestionIdx(question.getIdx());
						questionMngService.setInsertMatrixRow(matrix);
					}
					for (PollMatrixColMngVO col : colList) {
						col.setQuestionIdx(question.getIdx());
						questionMngService.setInsertMatrixCol(col);
					}
				}
			}
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 노출 여부 값 수정
	 *
	 * @param param
	 */
	public boolean setUpdatePollViewYn(PollMngVO param) {

		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());

		try {
			pollMngServiceMapper.setUpdatePollViewYn(param);
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			return Boolean.FALSE;
		}

	}

	/**
	 * 업로드 해야 할 파일을 가져옵니다.
	 *
	 * @param request
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private String uploadFile(MultipartHttpServletRequest request, String type) throws Exception {
		if (request != null) {
			Map<String, MultipartFile> files = new HashMap<String, MultipartFile>();
			String fileCn = "";
			if ("headerFile".equals(type) && request.getFileMap().get("header_file") != null) {
				files.put("headerFile", (request.getFileMap().get("header_file") != null && request.getFileMap().get("header_file").getSize() > 0 ? request.getFileMap().get("header_file") : null));
				fileCn = (request.getParameter("headerFileCn") != null ? request.getParameter("headerFileCn") : "");
			} else if ("mainFile".equals(type) && request.getFileMap().get("main_file") != null) {
				files.put("mainFile", (request.getFileMap().get("main_file") != null && request.getFileMap().get("main_file").getSize() > 0 ? request.getFileMap().get("main_file") : null));
				fileCn = (request.getParameter("mainFileCn") != null ? request.getParameter("mainFileCn") : "");
			} else {
				return "";
			}
			if (!files.isEmpty() && (files.get("headerFile") != null || files.get("mainFile") != null)) {
				List<FileVO> _result = fileUtil.parseFileInf(files, "POLL_", 0, "", "/home/webdata/egov_uploadFile/POLL/", false, request);
				if (_result != null) {
					_result.get(0).setFileCn(fileCn);
					return fileMngService.insertFileInfs(_result); // 파일이 생성되고나면 생성된 첨부파일 ID를 리턴한다.
				}
			}
		}
		return "";
	}

	/**
	 * 설문 조사 상세 정보
	 *
	 * @param param
	 * @return
	 */
	public PollMngVO getPollView(PollMngVO param) {
		return pollMngServiceMapper.getPollView(param);
	}

	/**
	 * 설문조사 수정
	 *
	 * @param param
	 */
	public boolean setPollUpdate(MultipartHttpServletRequest request, PollMngVO param) throws Exception {

		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());

		try {
			/** 첨부파일 처리 **/
			String headerFile = (uploadFile(request, "headerFile"));
			String mainFile = (uploadFile(request, "mainFile"));
			param.setHeaderFile(("".equals(headerFile) ? param.getHeaderFile() : headerFile));
			param.setMainFile(("".equals(mainFile) ? param.getMainFile() : mainFile));

			/** 파일 설명 글 변경에 따른 처리 **/
			if (StringUtil.isNotBlank(param.getHeaderFileCn())) {
				fileMngService.updateFileCn(new FileVO(param.getHeaderFile(), "0", param.getHeaderFileCn()));
			}
			if (StringUtil.isNotBlank(param.getMainFileCn())) {
				fileMngService.updateFileCn(new FileVO(param.getMainFile(), "0", param.getMainFileCn()));
			}

			pollMngServiceMapper.setPollUpdate(param);
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			return Boolean.FALSE;
		}

	}



	/**************************** 사용자단 ****************************/
	/**
	 * 설문조사 관리 카운트
	 *
	 * @param param
	 * @return
	 */
	public int getPollCnt(PollMngVO param) {
		return pollMngServiceMapper.getPollCnt(param);
	}

	/**
	 * 설문조사 관리 목록
	 *
	 * @param param
	 * @return
	 */
	public List<PollMngVO> getPollList(PollMngVO param) {
		return pollMngServiceMapper.getPollList(param);
	}

	/**
	 * IP 주소로 설문 응답여부 확인
	 *
	 * @param paramMap
	 * @return
	 */
	public int getResponderWithIp(Map<String, Object> paramMap) {
		return pollMngServiceMapper.getResponderWithIp(paramMap);
	}

	/**
	 * 해당 유저의 설문 응답여부 확인
	 *
	 * @param paramMap
	 * @return
	 */
	public int getResponderWithPrivateCode(Map<String, Object> paramMap) {
		return pollMngServiceMapper.getResponderWithPrivateCode(paramMap);
	}

	/**
	 * 설문 응답 처리
	 *
	 * @param param
	 * @return
	 */
	public boolean setPollApply(PollMngVO param, UserVO user, String ip) {

		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());

		try {
			PollResponderMngVO responder = null;
			/** 응답자 정보 등록 */
			if (user != null) {
				responder = new PollResponderMngVO(user.getUserName(), user.getBirthday(), user.getGender(), param.getTel(), user.getPrivatecode(), ip, param.getIdx(), "1");
			} else {
				responder = new PollResponderMngVO(ip, param.getIdx(), "1");
			}
			pollMngServiceMapper.setInsertPollApplyResponder(responder);

			/** 응답 내용 등록 */
			for (PollResponseMngVO response : param.getResponseList()) {
				response.setPollIdx(param.getIdx());
				response.setUserIdx(responder.getIdx());
				response.setIsDel("N");

				if (response.getMatList() != null && response.getMatList().size() > 0) {
					/** 매트릭스형 */
					for (PollResponseMngVO mat : response.getMatList()) {
						if (mat != null) {
							response.setMatrixIdx(mat.getMatrixIdx());
							response.setMatrixAnswerIdx(mat.getMatrixAnswerIdx());
							pollMngServiceMapper.setInsertPollApplyResponse(response);
						}
					}
				} else if (response.getOrderList() != null && response.getOrderList().size() > 0) {
					/** 순위 선정형 */
					for (PollResponseMngVO order : response.getOrderList()) {
						if (order != null) {
							response.setAnswerIdx(order.getAnswerIdx());
							response.setValue(order.getValue());
							pollMngServiceMapper.setInsertPollApplyResponse(response);
						}
					}
				} else if (response.getAnswerIdxList() != null && response.getAnswerIdxList().size() > 0) {
					/** 객관식 다답 */
					for (Integer answerIdx : response.getAnswerIdxList()) {
						if (answerIdx != null) {
							response.setAnswerIdx(answerIdx);
							pollMngServiceMapper.setInsertPollApplyResponse(response);
						}
					}
				} else {
					pollMngServiceMapper.setInsertPollApplyResponse(response);
				}
			}
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}

	/**
	 * 설문 응답 처리 (유저가 없는 경우)
	 *
	 * @param param
	 * @return
	 */
	public boolean setPollApply(PollMngVO param, String ip) {
		return setPollApply(param, null, ip);
	}

	/**
	 * 첨부파일 ID를 제거
	 *
	 * @param attachId
	 */
	public boolean setAttachFileNull(String attachId) {
		TransactionStatus txStatus = transactionManager.getTransaction(TransDefinition.getRequiresNew());
		try {
			pollMngServiceMapper.setAttachFileNull(attachId);
			transactionManager.commit(txStatus);
			return Boolean.TRUE;
		} catch (NullPointerException | DataAccessException e) {
			transactionManager.rollback(txStatus);
			LOGGER.error(e);
			return Boolean.FALSE;
		}
	}


}