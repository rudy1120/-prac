package egovframework.portal.sys.prism.mapper;

import java.util.List;

import egovframework.portal.sys.prism.vo.PrismMngVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * 관리자 - 정책연구보고서 게시판 MAPPER
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 *	수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2019.10.22		박선민				최초 생성
 * </pre>
 *
 * @author 박선민
 * @since 2019.10.22
 */
@Mapper("prismMngMapper")
public interface PrismMngMapper {

	int getTotalCnt(PrismMngVO searchVO) throws Exception;

	/*정책연구보고서 목록 조회*/
	List<PrismMngVO> getList(PrismMngVO searchVO) throws Exception;
	
	/*정책연구보고서 상세 조회*/
	PrismMngVO getEntity(PrismMngVO searchVO) throws Exception;

	/*관리자 정책연구보고서 게시물 등록*/
	void insert(PrismMngVO vo) throws Exception;
	
	/*관리자 정책연구보고서 수정 처리*/
	void modify(PrismMngVO vo) throws Exception;

	/*관리자 정책연구보고서 삭제*/
	void delete(PrismMngVO prismVO) throws Exception;

	/*조회수 업데이트*/
	void updateCnt(PrismMngVO searchVO);

}
