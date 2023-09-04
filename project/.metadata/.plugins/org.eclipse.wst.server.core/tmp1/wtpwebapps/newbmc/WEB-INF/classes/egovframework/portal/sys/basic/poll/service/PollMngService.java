package egovframework.portal.sys.basic.poll.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.unit.portal.user.vo.UserVO;

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
public interface PollMngService {

	/**
	 * 설문조사 관리 카운트
	 *
	 * @param param
	 * @return
	 */
	public int getPollMngCnt(PollMngVO param);

	/**
	 * 설문조사 관리 목록
	 *
	 * @param param
	 * @return
	 */
	public List<PollMngVO> getPollMngList(PollMngVO param);

	/**
	 * 설문조사 등록
	 *
	 * @param param
	 */
	public boolean setInsertPoll(MultipartHttpServletRequest request, PollMngVO param) throws Exception;

	/**
	 * 노출 여부 값 수정
	 *
	 * @param param
	 */
	public boolean setUpdatePollViewYn(PollMngVO param);

	/**
	 * 설문 조사 상세 정보
	 *
	 * @param param
	 * @return
	 */
	public PollMngVO getPollView(PollMngVO param);

	/**
	 * 설문조사 수정
	 *
	 * @param param
	 */
	public boolean setPollUpdate(MultipartHttpServletRequest request, PollMngVO param) throws Exception;

	/**************************** 사용자단 ****************************/

	/**
	 * 설문조사 관리 카운트
	 *
	 * @param param
	 * @return
	 */
	public int getPollCnt(PollMngVO param);

	/**
	 * 설문조사 관리 목록
	 *
	 * @param param
	 * @return
	 */
	public List<PollMngVO> getPollList(PollMngVO param);

	/**
	 * IP 주소로 설문 응답여부 확인
	 *
	 * @param paramMap
	 * @return
	 */
	public int getResponderWithIp(Map<String, Object> paramMap);

	/**
	 * 해당 유저의 설문 응답여부 확인
	 *
	 * @param paramMap
	 * @return
	 */
	public int getResponderWithPrivateCode(Map<String, Object> paramMap);

	/**
	 * 설문 응답 처리
	 *
	 * @param param
	 * @return
	 */
	public boolean setPollApply(PollMngVO param, UserVO user, String ip);

	/**
	 * 설문 응답 처리 (유저가 없는 경우)
	 *
	 * @param param
	 * @return
	 */
	public boolean setPollApply(PollMngVO param, String ip);

	/**
	 * 첨부파일 ID를 제거
	 *
	 * @param attachId
	 */
	public boolean setAttachFileNull(String attachId);

}