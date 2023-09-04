package egovframework.portal.unit.bmc.konantech.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.konantech.web.dao.ContentsDao;
import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;
import egovframework.portal.unit.bmc.konantech.web.data.ResultListVO;


@Service("contentsService")
public class ContentsService {
	
	@Autowired
	private ContentsDao contentsDao;
	
	public ResultListVO search(ParameterVO paramVO) throws Exception {
		
		ResultListVO result;
		try {
			result = contentsDao.restSearch(paramVO);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
