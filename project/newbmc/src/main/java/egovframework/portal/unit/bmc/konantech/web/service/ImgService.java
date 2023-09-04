package egovframework.portal.unit.bmc.konantech.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.konantech.web.dao.ImgDao;
import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;
import egovframework.portal.unit.bmc.konantech.web.data.ResultListVO;


@Service("imgService")
public class ImgService {
	
	@Autowired
	private ImgDao imgDao;
	
	public ResultListVO search(ParameterVO paramVO) throws Exception {
		
		ResultListVO result;
		try {
			result = imgDao.restSearch(paramVO);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
