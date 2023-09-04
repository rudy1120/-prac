package egovframework.portal.unit.bmc.konantech.web.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.portal.unit.bmc.konantech.web.module.RestModule;





/**
 * restful API를 호출하기 위한 클래스
 * 
 * @author jinhoo.jang
 * @since 2016.04.08
 * @modify 2018.06.07
 * @modifier 안호빈(hobin.ahn)
 * @desc 소스간소화를 위하여 인터페이스는 삭제(다형성 설계 하지않음.)
 */
@Service("restService")
public class RestService {
	
	
	/** REST 모듈  (HTTPCLIENT 호출방식 포함)*/
	@Autowired
	private RestModule restModule;
	
	/**
	 * KSF 방식으로 인기검색어를 가져온다.(리턴값 map) (HTTPCLIENT 호출방식)
	 *  
	 * @return map
	 */
	public List<HashMap<String, String>> getPopularKwd_hc(int domainNo, int maxResult) {
		return restModule.getPopularKwd_hc(domainNo,maxResult);
	}
	
	/**
	 * KSF 방식으로 오타교정를 가져온다.(리턴값 map) 
	 *  
	 * @return string
	 */
	public String getSpcApi(String kwd) {
		return restModule.getSpcApi(kwd);
	}
	
}
