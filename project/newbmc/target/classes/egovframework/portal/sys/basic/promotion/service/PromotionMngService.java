package egovframework.portal.sys.basic.promotion.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.sys.basic.promotion.vo.PromotionVO;

/**
 * 홍보자료 관리 SERVICE (배너, 홍보이미지, 팝업존)
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017. 7. 18.		J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2017. 7. 18.
 */
public interface PromotionMngService {

	/** 전체 홍보자료 건수(타입에 일치하는 정보 only) */
	int getTotalCnt(PromotionVO searchVO);

	/** 홍보자료 목록(타입에 일치하는 정보 only) */
	List<PromotionVO> getList(PromotionVO searchVO);

	/** 홍보자료 상세 */
	PromotionVO getEntity(PromotionVO searchVO);

	/** 홍보자료 등록 */
	String insert(PromotionVO searchVO, MultipartHttpServletRequest request);

	/** 홍보자료 수정 */
	String update(PromotionVO searchVO, MultipartHttpServletRequest request);

	/** 홍보자료 삭제 */
	String delete(PromotionVO searchVO);

	/** 홍보자료 정렬순서 변경 */
	String changeOrder(PromotionVO searchVO);

	/** 배너 엑셀 업로드 */
	int uploadBannerExcel(MultipartFile file, Boolean copy) throws Exception;

	/** 정렬순서 MAX값을 반환 */
	List<PromotionVO> getPrmtOrderList(PromotionVO searchVO);


}
