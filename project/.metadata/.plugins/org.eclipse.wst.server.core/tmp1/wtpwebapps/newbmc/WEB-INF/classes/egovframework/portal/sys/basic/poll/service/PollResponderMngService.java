package egovframework.portal.sys.basic.poll.service;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponderMngVO;

/**
 * 설문자 정보 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 8. 25.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 8. 25.
 */
public interface PollResponderMngService {

	/** 참여자수 전체 건수 */
	public int getTotalCnt(PollResponderMngVO searchVO);

	/** 참여자 목록 */
	public List<PollResponderMngVO> getList(PollResponderMngVO searchVO);

	/** 참여자 전체 목록(엑셀 다운로드용) */
	public List<Map<String, String>> getTotalListAsMap(PollResponderMngVO searchVO);

	/** 단일 설문자 정보 */
	public PollResponderMngVO getEntity(PollResponderMngVO searchVO);

	/** 당첨 취소 */
	public Boolean lotteryCancel(PollMngVO poll, PollResponderMngVO entity);

	/** 당첨자 추첨 */
	public Boolean lot(PollMngVO poll);

}
