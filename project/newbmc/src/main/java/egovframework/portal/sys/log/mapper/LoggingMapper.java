package egovframework.portal.sys.log.mapper;

import java.util.List;
import java.util.Map;

import egovframework.portal.sys.log.vo.LoggingVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 웹로그 패이지 태깅용 DAO
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 3. 27.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 3. 27.
 */
@Mapper("loggingMapper")
public interface LoggingMapper {

	public void adminLogInsert(LoggingVO sqlParams);

	public int getTotalCnt(LoggingVO searchVO);

	public List<LoggingVO> getList(LoggingVO searchVO);

	public List<Map<String, String>> getTotalListAsMap(LoggingVO searchVO);

	public void deleteExpiredLogs(Map<String, Object> params);

}
