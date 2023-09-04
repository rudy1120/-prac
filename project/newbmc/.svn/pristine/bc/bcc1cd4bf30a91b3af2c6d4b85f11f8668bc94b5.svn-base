package egovframework.portal.unit.bmc.apply.mapper;

import java.util.List;

import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface ApplySmsMapper {

	//지역데이터
	public List<ApplySmsVO> getAreaList();

	//지역에 따른 공급용도
	public List<ApplySmsVO> getUsesList(String idx);

	//신청자 정보조회
	public List<ApplySmsVO> getApplyInfo(ApplySmsVO applySmsVO);

	//신규 신청자 등록
	public void insertApply(ApplySmsVO applySmsVO);

	//기존 정보 삭제
	public void deleteApply(ApplySmsVO applySmsVO);

	//주택유형 데이터
	public List<ApplySmsVO> getHousingTypeList();

	//임대주택 신청자 정보조회
	public List<ApplySmsVO> getApplyInfo2(ApplySmsVO applySmsVO);

	//임대주택 신청자 등록
	public void insertApply2(ApplySmsVO applySmsVO);

	//임대주택 신청자 삭제
	public void deleteApply2(ApplySmsVO applySmsVO);

	//공급용도 데이터
	public List<ApplySmsVO> getPurposerList();

	//서비스이용기간 데이터
	public List<ApplySmsVO> getServiceTermList();

}
