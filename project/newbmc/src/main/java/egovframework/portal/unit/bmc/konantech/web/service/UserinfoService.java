package egovframework.portal.unit.bmc.konantech.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.portal.unit.bmc.konantech.web.dao.UserinfoDao;
import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;
import egovframework.portal.unit.bmc.konantech.web.data.ResultListVO;





@Service("userinfoService")
public class UserinfoService {
	
	@Autowired
	private UserinfoDao userinfoDao;
	
	public ResultListVO search(ParameterVO paramVO) throws Exception {
		
		ResultListVO result;
		try {
			result = userinfoDao.restSearch(paramVO);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
