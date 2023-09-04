package egovframework.portal.sys.log.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.log.vo.LoggingHistoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 통합 로그 삭제 이력 관리 DAO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 6. 8.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 6. 8.
 */
@Mapper("loggingHistoryMapper")
public interface LoggingHistoryMapper {

	public int getTotalCnt(LoggingHistoryVO searchVO);

	public List<LoggingHistoryVO> getList(LoggingHistoryVO searchVO);

	public List<Map<String, String>> getTotalListAsMap(LoggingHistoryVO searchVO);

	public void insert(LoggingHistoryVO insertVO);

}
