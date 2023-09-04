package egovframework.portal.unit.bmc.konantech.web.dao;

import java.io.IOException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import egovframework.portal.unit.bmc.konantech.web.common.CommonUtil;
import egovframework.portal.unit.bmc.konantech.web.common.DCUtil;
import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;
import egovframework.portal.unit.bmc.konantech.web.data.ResultListVO;
import egovframework.portal.unit.bmc.konantech.web.data.SearchVO;
import egovframework.portal.unit.bmc.konantech.web.module.RestModule;




@Repository
public class VideoDao {
	private static final Logger logger = LoggerFactory.getLogger(VideoDao.class);
	
	
	@Autowired
	private CommonUtil commonUtil;
	@Autowired
	private DCUtil dcUtil;
	/** REST 모듈  (HTTPCLIENT 호출방식)*/
	@Autowired
	private RestModule restModule;
	
	@Value("#{kframework['engine.charsetType']}") private String charsetType;
	
	@Value("#{kframework['engine.url']}") private String engineUrl;
	
	@Value("#{kframework['boardHilight']}") private String boardHilight;
	
	
	/**
	 * 검색 RESTFUL DAO
	 * @param search_type 
	 * 
	 * @param kwd
	 * @throws IOException 
	 */
	public ResultListVO restSearch(ParameterVO param) throws Exception {
		
		SearchVO searchVO = new SearchVO();
		String fileds = "*"; //검색 대상 필드
		String from = "video.video"; //검색 대상 볼륨,테이블
		StringBuffer hilightKwd = new StringBuffer();
		
		// 쿼리 생성
		StringBuffer query = new StringBuffer();
		StringBuffer sbLog = new StringBuffer();
		String orderNm = "정확도순";	
		String strNmFd = "text_idx".equals(param.getSrchFd())? "text_idx": param.getSrchFd();
		String baseKwd = param.getKwd();
		// 쿼리 부분
		query = dcUtil.makeQuery( strNmFd , baseKwd , "allword", query, "AND");	
		
		// 하이라이트 키워드를 세팅한다.
		hilightKwd.append(baseKwd);

		//결과내재검색
		if( param.getReSrchFlag() ){
			int preCnt = param.getPreKwds().length;
			query.append("  AND  ").append(dcUtil.makePreQuery(strNmFd , baseKwd, param.getPreKwds(), preCnt ,"allword") );
			
			for (int i = 0; i < param.getPreKwds().length; i++) {
				hilightKwd.append(" ");
				hilightKwd.append(param.getPreKwds()[i]);
			}
		}

		logger.debug("[::::: hilightKWD " + hilightKwd.toString() + ":::::]");
		/**
		 * 날짜검색기간 조회
		 * 기간/일/월/년도, 구간검색으로 조회시 자바스크립트에서 시작날짜와 종료날짜 조회하여 전달함.
		 */
		
		
		if (!param.getStartDate().isEmpty() && !param.getEndDate().isEmpty()){
			query = dcUtil.makeRangeQuery("REG_DATE", param.getStartDate()+"000000", param.getEndDate() +"235959", query) ;
		}
		
		//정렬조건	

		query.append(" order by $relevance desc");
		orderNm = "정확도순";

		
		logger.debug("query.toString() : "+ query.toString());
		
		//로그기록 
		//SITE@인물+$|첫검색|1|정확도순^코난	
		sbLog.append(dcUtil.getLogInfo(commonUtil.null2Str(param.getSiteNm(),"nihc"),
				param.getCategory(),
				commonUtil.null2Str( param.getUserId(),""), 
				param.getKwd(),
				param.getPageNum(),
				false,
				orderNm,
				commonUtil.null2Str(param.getRecKwd(),"")));
		
		searchVO.setUrl(engineUrl);
		searchVO.setCharset(charsetType);
		searchVO.setFields_rest(fileds);
		searchVO.setFrom(from);
		searchVO.setHilightFileds(URLEncoder.encode(boardHilight, "UTF-8"));
		searchVO.setHilightTxt(URLEncoder.encode(hilightKwd.toString(), "UTF-8"));
		searchVO.setQuery(URLEncoder.encode(query.toString(), "UTF-8"));
		searchVO.setLogInfo(URLEncoder.encode(sbLog.toString(), "UTF-8"));
		
		// restful URL 생성
		String restUrl = dcUtil.getRestURL(param, searchVO);
		
		System.out.println("검색 url:" + restUrl);
		
		logger.debug(logger.getName()+" , "+"RESTURL : " + restUrl);
		
		ResultListVO restVO = new ResultListVO();
		boolean success = restModule.restSearch_hc(restUrl, restVO);
		
		//초기화
		query.charAt(0);
		sbLog.charAt(0);
		
		if(!success) 
			return null;
				
		return restVO;		
	}
}
