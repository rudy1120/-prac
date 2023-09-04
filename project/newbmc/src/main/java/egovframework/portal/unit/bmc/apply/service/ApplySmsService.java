package egovframework.portal.unit.bmc.apply.service;

import java.util.List;

import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;

public interface ApplySmsService {

	//지역 데이터
	List<ApplySmsVO> getAreaList() throws Exception;

	//지역에 따른 공급용도
	List<ApplySmsVO> getUsesList(String idx) throws Exception;

	//신청자 정보조회
	List<ApplySmsVO> getApplyInfo(ApplySmsVO applySmsVO) throws Exception;

	//신규 신청자 등록
	void insertApply(ApplySmsVO applySmsVO) throws Exception;

	//신청 정보 수정
	void updateApply(ApplySmsVO applySmsVO) throws Exception;

	//신청 정보 삭제
	void delApply(ApplySmsVO applySmsVO) throws Exception;

	//주택유형 데이터
	List<ApplySmsVO> getHousingTypeList() throws Exception;

	//공급용도 데이터
	List<ApplySmsVO> getPurposerList() throws Exception;
	
	//서비스이용기간 데이터
	List<ApplySmsVO> getServiceTermList() throws Exception;
	
}
