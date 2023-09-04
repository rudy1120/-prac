package egovframework.portal.unit.bmc.apply.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.apply.mapper.ApplySmsMapper;
import egovframework.portal.unit.bmc.apply.service.ApplySmsService;
import egovframework.portal.unit.bmc.apply.vo.ApplySmsVO;

@Service
public class ApplySmsServiceImpl implements ApplySmsService {

	@Resource
	private ApplySmsMapper applySmsMapper;
	
	//지역 데이터
	@Override
	public List<ApplySmsVO> getAreaList() throws Exception {
		return applySmsMapper.getAreaList();
	}

	//지역에 따른 공급용도
	@Override
	public List<ApplySmsVO> getUsesList(String idx) throws Exception {
		return applySmsMapper.getUsesList(idx);
	}
	
	//신청자 정보조회
	@Override
	public List<ApplySmsVO> getApplyInfo(ApplySmsVO applySmsVO) throws Exception {
		
		List<ApplySmsVO> result;
		
		if(applySmsVO.getSavetype()==null || applySmsVO.getSavetype().length()==0) {
			//분양알림
			System.out.println("분양알림");
			result = applySmsMapper.getApplyInfo(applySmsVO);
		}else {
			//임대주택
			System.out.println("임대주택");
			result = applySmsMapper.getApplyInfo2(applySmsVO);
		}
		return result;
	}

	//신규신청자 등록
	@Override
	public void insertApply(ApplySmsVO applySmsVO) throws Exception {
		//applySmsMapper.insertApply(applySmsVO);
		
		if(applySmsVO.getHousingtype()==null || applySmsVO.getHousingtype().length()==0) {
			//분양알림
			//int insertCnt = applySmsVO.getAreauses().split(",").length; //1-11, 1-12, 2-16
			int insertCnt = applySmsVO.getUsesCk().split(",").length;
			String[] arr = applySmsVO.getUsesCk().split(",");
			
			int insertCntTerm = applySmsVO.getTermCk().split(",").length;
			String[] arr2 = applySmsVO.getTermCk().split(",");
			
			//공급용도
			for(int i=0; i<insertCnt; i++) { 

				//arr[0]:1-11 arr[1]:1-12. arr[2]:2-16
				//String[] arr2 = arr[i].split("-");
				
				//applySmsVO.setIdx(i+1);
				/*
				applySmsVO.setAreacode(arr2[0]);
				applySmsVO.setUsescode(arr2[1]);*/
				
				applySmsVO.setUsescode(arr[i]);
				
				//서비스 이용기간
				for(int j=0; j<insertCntTerm; j++) { 
					applySmsVO.setTermcode(arr2[j]);
				}
				
				applySmsMapper.insertApply(applySmsVO);
				//applySmsVO.setAreacode(insertCnt[i]);
				
			}
			
		}else {
			//임대주택
			
			int insertCnt = applySmsVO.getHousingtype().split(",").length;
			String[] arr = applySmsVO.getHousingtype().split(",");
			
			int insertCntTerm = applySmsVO.getTermCk().split(",").length;
			String[] arr2 = applySmsVO.getTermCk().split(",");
			
			for(int i=0; i<insertCnt; i++) {
				applySmsVO.setHousingtype(arr[i]);
				
				for(int j=0; j<insertCntTerm; j++) { 
					applySmsVO.setTermcode(arr2[j]);
				}
				applySmsMapper.insertApply2(applySmsVO);
			}
			
		}
	}

	//신청정보 수정
	@Override
	public void updateApply(ApplySmsVO applySmsVO) throws Exception {
		
		applySmsVO.setName(applySmsVO.getName2());
		applySmsVO.setPhone(applySmsVO.getPhone2());

		if(applySmsVO.getHousingtype()==null || applySmsVO.getHousingtype().length()==0) {
			//분양공고
			
			//1.기존 정보 삭제
			applySmsMapper.deleteApply(applySmsVO);
			
			//2.새정보 등록
			//int insertCnt = applySmsVO.getAreauses().split(",").length; //1-11, 1-12, 2-16
			int insertCnt = applySmsVO.getUsesCk().split(",").length;
			String[] arr = applySmsVO.getUsesCk().split(",");
			
			int insertCntTerm = applySmsVO.getTermCk().split(",").length;
			String[] arr2 = applySmsVO.getTermCk().split(",");
			
			//공급용도
			for(int i=0; i<insertCnt; i++) {
				//String[] arr2 = arr[i].split("-");
				
			/*	applySmsVO.setAreacode(arr2[0]);
				applySmsVO.setUsescode(arr2[1]);*/

				applySmsVO.setUsescode(arr[i]);
				
				//서비스 이용기간
				for(int j=0; j<insertCntTerm; j++) { 
					applySmsVO.setTermcode(arr2[j]);
					
				}
				
				applySmsMapper.insertApply(applySmsVO);
			}
			
		}else {
			//임대주택
			
			//1.기존 정보 삭제
			applySmsMapper.deleteApply2(applySmsVO);
			
			//2.새정보 등록
			int insertCnt = applySmsVO.getHousingtype().split(",").length;
			String[] arr = applySmsVO.getHousingtype().split(",");
			
			int insertCntTerm = applySmsVO.getTermCk().split(",").length;
			String[] arr2 = applySmsVO.getTermCk().split(",");
			
			//임대주택
			for(int i=0; i<insertCnt; i++) {
				applySmsVO.setHousingtype(arr[i]);
				
				//서비스 이용기간
				for(int j=0; j<insertCntTerm; j++) { 
					applySmsVO.setTermcode(arr2[j]);
				}
				applySmsMapper.insertApply2(applySmsVO);
			}
			
		}
	}
	

	//신청 정보 삭제
	@Override
	public void delApply(ApplySmsVO applySmsVO) throws Exception {
		
		//신청 정보 삭제
		if(applySmsVO.getSavetype()==null || applySmsVO.getSavetype().length()==0) {
			//분양정보
			applySmsMapper.deleteApply(applySmsVO);
		}else {
			//임대주택
			applySmsMapper.deleteApply2(applySmsVO);
		}
	}

	//주택유형 데이터
	@Override
	public List<ApplySmsVO> getHousingTypeList() throws Exception {
		return applySmsMapper.getHousingTypeList();
	}

	//공급용도 데이터
	@Override
	public List<ApplySmsVO> getPurposerList() throws Exception {
		return applySmsMapper.getPurposerList();
	}
	
	//서비스이용기간 데이터
	@Override
	public List<ApplySmsVO> getServiceTermList() throws Exception{
		return applySmsMapper.getServiceTermList();
	}

}
