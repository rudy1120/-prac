package egovframework.portal.sys.basic.poll.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponderMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponseMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

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
@Mapper("pollMngServiceMapper")
public interface PollMngServiceMapper {

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
	public void setInsertPoll(PollMngVO param);

	/**
	 * 노출 여부 값 수정
	 *
	 * @param param
	 */
	public void setUpdatePollViewYn(PollMngVO param);

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
	public void setPollUpdate(PollMngVO param);

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
	 * 설문 참여자 처리
	 *
	 * @param param
	 * @return
	 */
	public boolean setInsertPollApplyResponder(PollResponderMngVO param);

	/**
	 * 설문 응답 등록 처리
	 *
	 * @param param
	 * @return
	 */
	public boolean setInsertPollApplyResponse(PollResponseMngVO param);

	/** 추첨일자 갱신(별도의 추첨 플래그 없이 갱신일자의 유무로 추첨여부를 판단하므로 이 처리로 해당 설문은 추첨 완료됨) */
	public void updateLotDate(PollMngVO poll);

	/**
	 * 첨부파일 ID를 제거
	 *
	 * @param attachId
	 */
	public void setAttachFileNull(String attachId);

}