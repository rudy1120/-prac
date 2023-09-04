package egovframework.portal.sys.basic.poll.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.basic.poll.vo.PollMngVO;
import egovframework.portal.sys.basic.poll.vo.PollResponderMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 설문 참여자 정보 관리 MAPPER
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
@Mapper
public interface PollResponderMngMapper {

	/** 전체 참여자 건수 */
	int getTotalCnt(PollResponderMngVO searchVO);

	/** 참여자 목록 */
	List<PollResponderMngVO> getList(PollResponderMngVO searchVO);

	/** 전체 참여자 목록 */
	List<Map<String, String>> getTotalListAsMap(PollResponderMngVO searchVO);

	/** 단일 참여자 정보 */
	PollResponderMngVO getEntity(PollResponderMngVO searchVO);

	/** 추첨 취소 */
	void lotteryCancel(PollResponderMngVO entity);

	/** 추첨 */
	void lot(PollMngVO poll);

}
