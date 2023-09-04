package egovframework.portal.unit.bmc.buy.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.portal.unit.bmc.buy.vo.SaleVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface BuyMapper {

	//분양매물 목록 총 개수 조회
	int getTotalCnt(SaleVO buyVO) throws Exception;

	//분양매물목록 데이터 조회
	List<SaleVO> getList(SaleVO buyVO) throws Exception;

	//분양중 지역 데이터 리스트 조회
	List<ApplySmsVO> getAreaList() throws Exception;

	//전체 공급용도 데이터
	List<ApplySmsVO> getAllPurposerList() throws Exception;

	String getMapInfo(SaleVO saleVO);

} 
