package egovframework.portal.unit.portal.budget.service;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 6. 22.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 6. 22.
 */
public interface BudgetYearService {

	/**
	 * 예산서 조회
	 *
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> getBudgetYear(Map<String, Object> paramMap);

	/**
	 * 연도별 예산 데이터 조회
	 *
	 * @param paramMap
	 * @return
	 */
	public List<Map<String, Object>> getBudgetTableData(Map<String, Object> paramMap);

}
