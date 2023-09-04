package egovframework.portal.unit.bmc.konantech.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.portal.unit.bmc.konantech.web.dao.VideoDao;
import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;
import egovframework.portal.unit.bmc.konantech.web.data.ResultListVO;


@Service("videoService")
public class VideoService {
	
	@Autowired
	private VideoDao videoDao;
	
	public ResultListVO search(ParameterVO paramVO) throws Exception {
		
		ResultListVO result;
		try {
			result = videoDao.restSearch(paramVO);
			return result;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
}
