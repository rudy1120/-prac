package egovframework.com.dwr;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.dept.service.DeptService;
import egovframework.portal.dept.vo.DeptVO;
import egovframework.portal.staff.service.StaffService;
import egovframework.portal.staff.vo.StaffVO;


@Service("dwrCommonService")
public class DwrCommonService {

	@Resource(name = "staffService")
    private StaffService staffService;
	
	@Resource(name = "deptService")
    private DeptService deptService;	
	
	
	/**
	 * 모든 조직도 정보를 불러온다.
	 * @return
	 * @throws Exception
	 */
	public List<DeptVO> dwrSelectDeptInfo(String keyword) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(keyword != null && !keyword.equals("")){
			param.put("searchKeyword", keyword);
		}
		
		List<DeptVO> resList = deptService.selectDeptInfo(param);
		
		return resList;
	}
	
	/**
	 * 부서명이 상위 부서명을 포함하는 조직도 정보를 불러온다.
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<DeptVO> dwrSelectDeptSearchInfo(String keyword) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(keyword != null && !keyword.equals("")){
			param.put("searchKeyword", keyword);
		}
		List<DeptVO> resList = deptService.selectDeptSearchInfo(param); 
			
		return resList;
	}
	

	/**
	 * DWR
	 * 부서코드로 회원의 정보를 불러온다.
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<StaffVO> selectDeptIdtoStaffInfo(String orgId) throws Exception {
		HashMap<String, Object> param = new HashMap<String, Object>();
		if(orgId != null && !orgId.equals("")){
			param.put("searchKeyword", orgId);
		}
		List<StaffVO> resultList = staffService.selectDeptCodetoStaffInfo(param);
		return resultList;
	}
	
	/**
	 * 회원의 이름으로 회원목록을 반환한다.
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<StaffVO> selectStaffInfoByStaffName(String userName) throws Exception {
		HashMap<String, String> param = new HashMap<String, String>();
		if(userName != null && !userName.equals("")){
			param.put("searchCondition", "1");
			param.put("searchKeyword", userName);
		}
		
		List<StaffVO> resultList = staffService.allStaffList(param);
		return resultList;
	}
	
	
}
