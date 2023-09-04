package egovframework.portal.sys.bbs.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import egovframework.portal.sys.bbs.vo.BbsConfigVO;

/**
 * 게시판 관리 SERVICE
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.09.25		김혜민				최초 생성 및 코딩
 * 2017.06.14		J.Ryeon Lee			코드 리팩토링
 * </pre>
 *
 * @author 김혜민
 * @since 2014.09.25
 */
public interface BbsConfigService {

	/** 게시판 목록 */
	public List<BbsConfigVO> getBbsConfigList(BbsConfigVO searchVO);

	/** 게시판 권한 목록 */
	public List<BbsConfigVO> getBbsConfigAuthList(BbsConfigVO searchVO);

	/** 전체 목록(엑셀 다운로드용) */
	public List<Map<String, String>> getTotalBbsConfigAuthListAsMap(BbsConfigVO searchVO);

	/** 게시판 목록 개수 */
	public int getBbsConfigCnt(BbsConfigVO searchVO);

	/** 게시판 등록 */
	public String insertBbsConfig(HttpServletRequest request, BbsConfigVO searchVO);

	/** 게시판 ENTITY FETCH */
	public BbsConfigVO getBbsConfigView(BbsConfigVO searchVO);

	/** 게시판 ENTITY FETCH BY PK */
	public BbsConfigVO getEntityByPk(String ptIdx);

	/** 게시판 수정 */
	public String updateBbsConfig(HttpServletRequest request, BbsConfigVO searchVO);

	/** 게시판 삭제 */
	public String deleteBbsConfig(HttpServletRequest request, BbsConfigVO searchVO);

	/** 타입이 동일한 게시판 목록 */
	public List<BbsConfigVO> configBoardList(BbsConfigVO searchVO);

	/** 메뉴에서 사용중인 게시판인지 검증 */
	public boolean isUsedBbs(String ptIdx);

}
