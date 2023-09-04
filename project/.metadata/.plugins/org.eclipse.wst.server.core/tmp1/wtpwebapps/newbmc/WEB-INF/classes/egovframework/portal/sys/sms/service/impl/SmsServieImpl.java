package egovframework.portal.sys.sms.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import java.util.List;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import egovframework.portal.sys.bbs.vo.BbsMngVO;
import egovframework.portal.sys.sms.mapper.SmsMapper;
import egovframework.portal.sys.sms.service.SmsService;
import egovframework.portal.sys.smsService.vo.SmsServiceVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
/**
 * SMS을 관리하는 Service
 * @author SM
 *
 */
@Service
public class SmsServieImpl extends EgovAbstractServiceImpl implements SmsService {
	
	@Resource
	private SmsMapper smsMapper;
	
	//문자 SC_TRAN insert
	@Override
	public void insertSmsQueue(BbsMngVO searchVO) throws Exception {

		int insertCnt = searchVO.getSmsOptions().split(",").length;
		String[] arr = searchVO.getSmsOptions().split(",");
		
		//SmsServiceVO vo = new SmsServiceVO();
		
		
		List<SmsServiceVO> list = new ArrayList<>();
		List<String> phoneList = new ArrayList<>();
		
		//분양공고일 경우
		if(searchVO.getSmsGbn().equals("A")) {

			System.out.println("분양공고일경우");
				
			//해당 선택옵션 신청자들 들고오기
			for(int i=0; i<insertCnt; i++) {
				
				//vo.setUsescode(arr[i]);
				list = smsMapper.getList3(arr[i]);
				for(int j=0; j<list.size(); j++) {
					
					phoneList.add(list.get(j).getPhone());
				}
			}
			
		}
		//폰번호 중복 제거
		phoneList = new ArrayList<>(new HashSet<>(phoneList));
		
		System.out.println("게시기간:" + searchVO.getbTermSdate());
		
		int checkInterval = 500;		
		for(int a=0; a<phoneList.size(); a++) {
			searchVO.setTrPhone(phoneList.get(a));
			searchVO.setCheckIndex(a / checkInterval);
			
			//SC_TRAN 테이블 삽입
			System.out.println(phoneList.get(a));
			smsMapper.insertSmsQueue(searchVO);
		}
		
	}
	
	//SC_TRAN 존재하는지 확인
/*	@Override
	public String getSmsQueue(String bIdx) throws Exception {
		String cnt = smsMapper.getSmsQueue(bIdx);
		return cnt;
	}*/

	//문자 서비스 사전 작업
	@Override
	public void updateCk(BbsMngVO searchVO) throws Exception {
		
		
		
		//1.문자서비스 실행 유무
		if(searchVO.getSmsYN().equals("Y")||searchVO.getSmsYN()=="Y") {

			//1.1 게시기간 없음
			if(searchVO.getbTermSdate().equals("")) {
				//1.2 게시기간 없음 (문자서비스 체크했던말던 SC_TRAN에는 없음!)
				//1.2.1 SC_TRAN에 새로 추가(문자발송시간은 이제 수정 현재시각으로 설정)
				
				System.out.println("게시기간 없음 :" + 2);
				
				searchVO.setbTermSdate("");
				insertSmsQueue(searchVO);
			}
			else {
				System.out.println("게시기간 있음 :" + 1);
				
				//게시기간과 현재날짜시각 비교
				int compare = compareDate(searchVO);

				if( compare > 0) {
				//1.1.1 게시기간 전에 수정할 경우 - 원글에서 문자서비스 설정했으면 아직 문자서비스 실행전으로 SC_TRAN에 해당 데이터 존재, 설정안했으면 SC_TRAN에 없음
					//게시기간이 현재시각보다 뒤
					System.out.println("게시기간 전에 수정 :" + 3);
					String cnt = ckCnt(searchVO);
					
					if(cnt.equals('0')) {
						//1.1.1.1 SC_TRAN에 테이블에 없을 경우(원글에서 문자서비스 설정 안 한 경우) -SC_TRAN 테이블 추가 
						smsMapper.insertSmsQueue(searchVO);
					}else {
						//1.1.1.2 SC_TRAN에 테이블에 있는경우(원글에서 문자서비스 설정한 경우) - SC_TRAN 테이블 수정
						
						//해당사항 삭제 후 새로 삽입
						smsMapper.deleteSmsQueue(searchVO.getbIdx());
						//smsMapper.updateSmsQueue(searchVO);
						insertSmsQueue(searchVO);
					}

					
					
				}else {
				//1.1.2 게시기간 이후 수정할 경우 - 이미 게재된 상황 SC_TRAN에 새로 추가만 하면 됌(문자발송시간은 이제 수정 현재시각으로 설정)
					//게시기간이 현재시각과 같거나 앞.. 이미 문자발송된 상태
					//1.1.2.1 SC_TRAN에 새로 추가(문자발송시간은 이제 수정 현재시각으로 설정)
					
					System.out.println("게시기간 이후 수정 :" + 4);
					searchVO.setbTermSdate("");
					insertSmsQueue(searchVO);
				}
			}
			
		}else if(searchVO.getSmsYN().equals("N")||searchVO.getSmsYN()=="N") {
			//2.문자서비스 실행안할거다.. -> 기존에 문자서비스 있는지 없는지 확인 
				//2.1 SC_TRAN에 있는지 확인...
					//2.1.1 있으면 삭제..
			
			System.out.println("문자서비스 실행x :" + 5);
			
			String cnt = ckCnt(searchVO);
			
			if(cnt.equals('0')) {
				
			}else {
				//해당사항 삭제
				smsMapper.deleteSmsQueue(searchVO.getbIdx());
			}
			
		}
	}
	


	//문자 서비스 삭제 작업
	@Override
	public void delCk(BbsMngVO searchVO) throws Exception {
		//1.1 게시기간 없음
		if(searchVO.getbTermSdate().equals("")) {
			System.out.println("게시기간확인:"  + searchVO.getbTermSdate());
			System.out.println("삭제글..");;
		}
		else {
			System.out.println("삭제 글 게시기간 있음 :" + 1);
			
			
			//게시기간 현재날짜시각 비교
			int compare = compareDate(searchVO);

			if( compare > 0) {
			//1.1.1 게시기간 전에 수정할 경우 - 원글에서 문자서비스 설정했으면 아직 문자서비스 실행전으로 SC_TRAN에 해당 데이터 존재, 설정안했으면 SC_TRAN에 없음
				//게시기간이 현재시각보다 뒤
				System.out.println("게시기간 전에 수정 :" + 3);
				String cnt = ckCnt(searchVO);
				
				if(cnt.equals('0')) {
					//1.1.1.1 SC_TRAN에 테이블에 없을 경우(원글에서 문자서비스 설정 안 한 경우) -SC_TRAN 테이블 추가 
					smsMapper.insertSmsQueue(searchVO);
				}else {
					//1.1.1.2 SC_TRAN에 테이블에 있는경우(원글에서 문자서비스 설정한 경우) - SC_TRAN 테이블 수정
					
					//해당사항 삭제 후 새로 삽입
					smsMapper.deleteSmsQueue(searchVO.getbIdx());
					//smsMapper.updateSmsQueue(searchVO);
					insertSmsQueue(searchVO);
				}

				
				
			}else {
			//1.1.2 게시기간 이후 수정할 경우 - 이미 게재된 상황 SC_TRAN에 새로 추가만 하면 됌(문자발송시간은 이제 수정 현재시각으로 설정)
				//게시기간이 현재시각과 같거나 앞.. 이미 문자발송된 상태
				//1.1.2.1 SC_TRAN에 새로 추가(문자발송시간은 이제 수정 현재시각으로 설정)
				
				System.out.println("게시기간 이후 수정 :" + 4);
				searchVO.setbTermSdate("");
				insertSmsQueue(searchVO);
			}
		}
		
	}
	
	//SC_TRAN에 테이블 데이터 확인
	public String ckCnt(BbsMngVO searchVO) throws Exception{
		
		String cnt = smsMapper.getSmsQueue(searchVO.getbIdx());
		System.out.println("SC_TRAN에서 값확인 : " + cnt);

		return cnt;
	}
	
	//게시기간과 현재날짜 비교
	public int compareDate(BbsMngVO searchVO) throws Exception{
		
		
		//게시기간과 현재날짜시각 비교
		Date nowDate = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String to = transFormat.format(nowDate);
		
		//Date nowDate = null;
		try {
			nowDate = transFormat.parse(to);
		}catch(java.text.ParseException e){
			e.printStackTrace();
		}
		System.out.println("현재시간 확인 :" + to);
		System.out.println("게시기간 확인 : " + searchVO.getbTermSdate()); //2019-09-26 09:00

		//게시기간 Date 형으로 변환
		String bTermSdate = searchVO.getbTermSdate();
		SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date sdate = transFormat2.parse(bTermSdate);
		
		//int compare = bTermSdate.compareTo(to);
		int compare =  sdate.compareTo(nowDate);
		System.out.println("날짜비교!:" +compare);
		
		return compare;
	}

}
