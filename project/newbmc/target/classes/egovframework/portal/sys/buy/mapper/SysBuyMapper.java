package egovframework.portal.sys.buy.mapper;

import java.util.List;
import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.unit.bmc.buy.vo.SaleVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface SysBuyMapper {

	//분양목록 데이터
	List<SaleVO> getBuyList(SaleVO searchVO) throws Exception;
	
	//분양목록 데이터 카운트
	int getTotalCnt(SaleVO searchVO) throws Exception;

	//분양매물 목록 조회
	List<SaleVO> getSaleList(SaleVO saleVO) throws Exception;

	//분양상세화면
	SaleVO getView(SaleVO saleVO) throws Exception;

	//분양매물 신규등록
	void insertSale(SaleVO vo) throws Exception;

	//분양매물 업데이트
	void updateSale(SaleVO vo) throws Exception;
	
	//분양매물 상세조회
	SaleVO getSale(SaleVO saleVO) throws Exception;

	//분양매물 데이터 삭제
	void delSale(SaleVO saleVO) throws Exception;

	//분양정보 신규 등록
	void insertSell(SaleVO vo) throws Exception;

	//지역별 공급용도 저장
	void insertAreaUses(SaleVO vo) throws Exception;

	//사업별 공급용도 삭제
	void deleteAreaUses(SaleVO vo) throws Exception;

	//분양정보 수정
	void updateSell(SaleVO vo) throws Exception;

	//분양정보 삭제
	boolean delSell(SaleVO saleVO) throws Exception;

	void delSales(SaleVO saleVO) throws Exception;

	//지역별 공급용도 수정 저장
	void updateAreaUses(SaleVO vo) throws Exception;

	//공급용도 카운트
	int getTotalUseCnt(SaleVO searchVO) throws Exception;

	//공급용도 목록
	List<SaleVO> getUsesList(SaleVO searchVO) throws Exception;

	SaleVO getEntity(SaleVO saleVO) throws Exception;

	//공급용도 중복체크
	int chkUses(SaleVO saleVO) throws Exception;

	//공급용도 등록
	boolean insertUses(SaleVO saleVO) throws Exception;

	//공급용도 수정
	boolean updateUses(SaleVO saleVO) throws Exception;

	//공급용도 사용유무
	int chkUses2(SaleVO saleVO) throws Exception;

	//공급용도 삭제
	boolean delUses(SaleVO saleVO) throws Exception;

	//공급용도 목록
	List<ApplySmsVO> getPurposerList() throws Exception;



}
