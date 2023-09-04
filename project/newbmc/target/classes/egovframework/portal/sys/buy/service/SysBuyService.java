package egovframework.portal.sys.buy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.unit.bmc.buy.vo.SaleVO;

public interface SysBuyService {

	//분양목록 데이터 카운트
	int getTotalCnt(SaleVO searchVO) throws Exception;
	
	//분양목록 데이터
	List<SaleVO> getBuyList(SaleVO searchVO) throws Exception;

	//분양매물 목록
	List<SaleVO> getSaleList(SaleVO saleVO) throws Exception;

	//분양 상세화면
	SaleVO getView(SaleVO saleVO) throws Exception;

	//분양매물 저장
	boolean insertSale(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception;
	
	//분양매물 수정
	boolean updateSale(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception;

	//매물목록 데이터 상세조회
	SaleVO getSale(SaleVO saleVO) throws Exception;

	//분양매물 데이터 삭제
	boolean delSale(HttpServletRequest request,SaleVO saleVO) throws Exception;

	//분양정보 신규저장
	boolean insertSell(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception;

	//분양정보 수정
	boolean updateSell(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception;

	//분양정보 삭제
	boolean delSell(HttpServletRequest request, SaleVO saleVO) throws Exception;

	//공급용도 카운트
	int getTotalUseCnt(SaleVO searchVO) throws Exception;

	//공급용도관리 목록
	List<SaleVO> getUsesList(SaleVO searchVO) throws Exception;

	//공급용도 선택
	SaleVO getEntity(SaleVO saleVO) throws Exception;

	//공급용도 중복체크
	int chkUses(SaleVO saleVO) throws Exception;

	//공급용도 등록
	boolean insertUses(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception;

	//공급용도 수정
	boolean updateUses(MultipartHttpServletRequest request, SaleVO saleVO) throws Exception;

	//공급용도 사용 유무
	int chkUses2(SaleVO saleVO) throws Exception;

	//공급용도 삭제
	boolean delUses(HttpServletRequest request, SaleVO saleVO) throws Exception;

	//공급용도 
	List<ApplySmsVO> getPurposerList() throws Exception;




}
