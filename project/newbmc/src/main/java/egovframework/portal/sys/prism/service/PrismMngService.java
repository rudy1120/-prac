package egovframework.portal.sys.prism.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.prism.vo.PrismMngVO;

/**
 * 관리자 - 정책연구보고서 게시판 SERVICE
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
public interface PrismMngService {

	int getTotalCnt(PrismMngVO searchVO) throws Exception;

	/*정책연구보고서 목록 조회*/
	List<PrismMngVO> getList(PrismMngVO searchVO) throws Exception;

	/*정책연구보고서 상세 조회*/
	PrismMngVO getEntity(PrismMngVO searchVO) throws Exception;

	/*관리자 정책연구보고서 등록*/
	boolean insert(MultipartHttpServletRequest request, PrismMngVO prismVO) throws Exception;

	/*관리자 정책연구보고서 수정 처리*/
	boolean modify(MultipartHttpServletRequest request, PrismMngVO prismVO) throws Exception;
	
	/*관리자 정책연구보고서 삭제*/
	boolean delete(HttpServletRequest request, PrismMngVO prismVO) throws Exception;

	/*조회수 업데이트*/
	void updateCnt(PrismMngVO prismVO) throws Exception;


}
