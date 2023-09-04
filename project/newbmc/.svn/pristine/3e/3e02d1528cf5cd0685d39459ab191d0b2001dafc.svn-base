package egovframework.portal.unit.bmc.konantech.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.portal.unit.bmc.konantech.web.dao.MenuDao;
import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;
import egovframework.portal.unit.bmc.konantech.web.data.ResultListVO;





@Service("menuService")
public class MenuService {
	
	@Autowired
	private MenuDao menuDao;
	
	public ResultListVO search(ParameterVO paramVO) throws Exception {
		
		ResultListVO result;
		try {
			result = menuDao.restSearch(paramVO);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
