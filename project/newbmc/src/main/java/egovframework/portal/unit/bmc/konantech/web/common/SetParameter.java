package egovframework.portal.unit.bmc.konantech.web.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;




/**
 * 검색 파라미터 세팅
 * 
 * @author 장진후(Jinhoo.Jang)
 * @team 기술지원팀(Technical Support)
 * @since 2013.06.20
 * @version 1.0
 */
@Component
public class SetParameter {
	
	private static final Logger logger = LoggerFactory.getLogger(SetParameter.class);
		
	public ParameterVO setParameter(@RequestParam Map<String, String> map, HttpServletRequest rq) throws Exception {
		ParameterVO rtnParam = new ParameterVO();
		
		// 사이트
		rtnParam.setSiteNm(getValue(map, "site", "bmc"));
		
		// 키워드 (기본적으로 콤마는 삭제한다. XSS(크로스사이트스크립팅 체크))
		rtnParam.setKwd(getCheckXSS(getValue(map, "kwd", "").replaceAll("\\'", "")));
		
		// 카테고리
		rtnParam.setCategory(getValue(map, "category", rq.getParameter("category")));
			
		// 페이지 번호
		rtnParam.setPageNum(getValue(map, "pageNum", 1));
		
		// 페이지 사이즈
		if("TOTAL".equals(rtnParam.getCategory())) {
			rtnParam.setPageSize(5);
		} else {
			rtnParam.setPageSize(10);	
		}
		
		// 정확히 일치 단어/문장
		rtnParam.setExactlyKeyword(getCheckXSS(getValue(map, "exactlyKeyword", "").replaceAll("\\'", "")));
		// 정확히 일치 단어/문장
		rtnParam.setMustKeyword(getCheckXSS(getValue(map, "mustKeyword", "").replaceAll("\\'", "")));
		// 정확히 일치 단어/문장
		rtnParam.setIgnoreKeyword(getCheckXSS(getValue(map, "ignoreKeyword", "").replaceAll("\\'", "")));
		// boolean 검색시 | or 조건일때  -- 페이지에서 넘어오지는 않지만 빈값으로 세팅한다.
		rtnParam.setOrKeyword(getCheckXSS(getValue(map, "orKeyword", "").replaceAll("\\'", "")));
		// boolean 검색시 ^ 조건일때  -- 페이지에서 넘어오지는 않지만 빈값으로 세팅한다.
		rtnParam.setNearKeyword(getCheckXSS(getValue(map, "nearKeyword", "").replaceAll("\\'", "")));
		// boolean 검색시 ~ 조건일때  -- 페이지에서 넘어오지는 않지만 빈값으로 세팅한다.
		rtnParam.setWithinKeyword(getCheckXSS(getValue(map, "withinKeyword", "").replaceAll("\\'", "")));
		
		// 검색대상
		rtnParam.setSrchFd(getValue(map, "srchFd", "text_idx"));
		
		// 정렬코드
		rtnParam.setSort(getValue(map, "sort", "d"));
		
		// 정렬명
		if("d".equals(rtnParam.getSort())) {
			rtnParam.setSortNm("정확도");
		} else {
			rtnParam.setSortNm("최신순");
		}
		
		// 상세검색 여부
		rtnParam.setDetailSearch(getValue(map, "detailSearch"));
		
		/** 날짜 검색 - 선택*/ 
		rtnParam.setDate(getValue(map, "date", "all"));
		
		//  boolean 검색 조건 판단 
		String oriMinusKwdReg = "([0-9|a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,})!";
		String minusKwdReg = "!([0-9|a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,})";
		String oriAndKwdReg = "([0-9|a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,})&";
		String andKwdReg = "&([0-9|a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,})";
		String originOrkwdReg = "([0-9|a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,})\\|";
		String OrKwdReg = "\\|([0-9|a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,})";
		String oriNearkwdReg = "[0-9|a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,}\\^[0-9]{1,}[a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,}";
		String oriWithinKwdReg = "[0-9|a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,}\\~[0-9]{1,}[a-z|A-Z|ㄱ-ㅎㅏ-ㅣ가-힝]{1,}";
		
//		map = returnDetailKwd(kwd, originaakwdReg, originbbkwdReg, "");
//		System.out.println(map.get("oriKwd"));
//		System.out.println(map.get("typeKwd"));
		
		//조건식 있을경우 기본 키워드는 한 변수에 담는다.
		StringBuffer baseKwd = new StringBuffer();
		
		HashMap<String,String> minusMap = new HashMap<String,String>();
		HashMap<String,String> andMap = new HashMap<String,String>();
		HashMap<String,String> orMap = new HashMap<String,String>();
		HashMap<String,String> etcMap = new HashMap<String,String>();
		
		minusMap = returnDetailKwd(rtnParam.getKwd(), oriMinusKwdReg, minusKwdReg, "!");
		andMap = returnDetailKwd(rtnParam.getKwd(), oriAndKwdReg, andKwdReg, "&");
		orMap = returnDetailKwd(rtnParam.getKwd(), originOrkwdReg, OrKwdReg, "|");
		etcMap = returnDetailKwd(rtnParam.getKwd(), oriNearkwdReg, oriWithinKwdReg, "");
		
		// 검색어에 조건검색식(boolean) 검색이 있다면 정규식으로 각 변수에 키워드들을 담는다.
		if(rtnParam.getKwd().indexOf("|") > -1 || rtnParam.getKwd().indexOf("!") > -1 || rtnParam.getKwd().indexOf("&") > -1 || rtnParam.getKwd().indexOf("^") > -1
				|| rtnParam.getKwd().indexOf("~") > -1) {
			if(!"".equals(minusMap.get("oriKwd")) && !"".equals(minusMap.get("typeKwd"))) {
				baseKwd.append(minusMap.get("oriKwd"));
				rtnParam.setIgnoreKeyword(minusMap.get("typeKwd"));
			}
			if(!"".equals(andMap.get("oriKwd")) && !"".equals(andMap.get("typeKwd"))) {
				baseKwd.append(andMap.get("oriKwd"));
				rtnParam.setMustKeyword(andMap.get("typeKwd"));
			}
			if(!"".equals(orMap.get("oriKwd")) && !"".equals(orMap.get("typeKwd"))) {
				baseKwd.append(orMap.get("oriKwd"));
				rtnParam.setOrKeyword(orMap.get("typeKwd"));
			}
			if(!"".equals(etcMap.get("oriKwd")) || !"".equals(etcMap.get("typeKwd"))) {
				rtnParam.setNearKeyword(etcMap.get("oriKwd").trim());
				rtnParam.setWithinKeyword(etcMap.get("typeKwd").trim());
			}
			
			rtnParam.setBaseKeyword(baseKwd.toString().trim());	
		}else {
			// 조건식이 없으면 일반 검색으를 기본키워드에 담는다.
			rtnParam.setBaseKeyword(rtnParam.getKwd());	
			
		}
		
		logger.debug("baseKwd  : " + rtnParam.getBaseKeyword());
		logger.debug("getIgnoreKeyword : " + rtnParam.getIgnoreKeyword());
		logger.debug("getMustKeyword  : " + rtnParam.getMustKeyword());
		logger.debug("getBaseKeyword  : " + rtnParam.getBaseKeyword());
		logger.debug("getOrKeyword  : " + rtnParam.getOrKeyword());
		logger.debug("getNearKeyword  : " + rtnParam.getNearKeyword());
		logger.debug("getWithinKeyword  : " + rtnParam.getWithinKeyword());
		
		/** 기간 - 슬라이더*/ 
		//srchParam.setSliderValue(CommonUtil.null2Str(request.getParameter("sliderValue"), "5"));
		
		// 시작일
		rtnParam.setStartDate(getValue(map, "startDate", ""));
		
		// 종료일
		rtnParam.setEndDate(getValue(map, "endDate", ""));
		
		if("all".equals(rtnParam.getDate())){
			rtnParam.setStartDate("");
			rtnParam.setEndDate("");
		}else if("1".equals(rtnParam.getDate())){
			rtnParam.setStartDate(CommonUtil.getTargetDate(-1));
			rtnParam.setEndDate(CommonUtil.getTargetDate(0));		
		}else if("7".equals(rtnParam.getDate())){
			rtnParam.setStartDate(CommonUtil.getTargetDate(-7));
			rtnParam.setEndDate(CommonUtil.getTargetDate(0));
		}else if("30".equals(rtnParam.getDate())){
			rtnParam.setStartDate(CommonUtil.getTargetDate(-30));
			rtnParam.setEndDate(CommonUtil.getTargetDate(0));
		}else if("90".equals(rtnParam.getDate())){
			rtnParam.setStartDate(CommonUtil.getTargetDate(-90));
			rtnParam.setEndDate(CommonUtil.getTargetDate(0));
		}else if("365".equals(rtnParam.getDate())){
			rtnParam.setStartDate(CommonUtil.getTargetDate(-365));
			rtnParam.setEndDate(CommonUtil.getTargetDate(0));
		}else if("detail_date".equals(rtnParam.getDate())) {
			rtnParam.setStartDate(map.get("startDate").replace("-", ""));
			rtnParam.setEndDate(map.get("endDate").replace("-", ""));
		}
		
		// 년도
		rtnParam.setYear(getValue(map, "year", ""));
		
		// 서브 체크박스
		rtnParam.setTypeValue(getValue(map, "typeValue", ""));
		
		rtnParam.setAllcheck(getValue(map, "allcheck", ""));
		
		// 검색영역
		rtnParam.setFields(getValue(map, "fields", ""));
	
		// 재검색여부
		rtnParam.setReSrchFlag("on".equals(getValue(map, "reSearch", ""))?true:false );
		if(rq.getParameterValues("preKwd") != null) {
			rtnParam.setPreKwds(rq.getParameterValues("preKwd"));
		}
		
		return rtnParam;
	}
	
	/**
	 * 파라미터 값을 바인딩한다.
	 * @param map 파라미터
	 * @param key 파라미터 키
	 * @param rtnValue null 인 경우 바인딩 
	 * @param chk <script 체크여부
	 * @return
	 * @throws Exception
	 */
	public String getValue(Map<String, String> map, String key, String rtnValue, boolean chk) throws Exception{		
		if(StringUtils.isEmpty(map.get(key)))
			return rtnValue;
		
		if(!checkValue(map.get(key).toString(), chk)){
	
			throw new Exception("허용되지 않는 URL입니다.");
		}
		return map.get(key).toString();
	}
	
	/**
	 * 문자열에 특수문자 및 이상여부를 체크하여 반환한다.
	 * @param value 문자열
	 * @param chk script, frame 체크
	 * @return
	 */
	public boolean checkValue(String value, boolean chk){		

		if(chk){
			if(  (value.toLowerCase()).indexOf("<script") > -1
					|| (value.toLowerCase()).indexOf("<frame") > -1
				    || (value.toLowerCase()).indexOf("<iframe") > -1 ) {
				return false;
			}
		}
		
		if(!value.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
			return false;
		}
		return true;
	}
	
	/**
	 * 문자열 리턴 메소드
	 * 
	 * @param map
	 * @param key
	 * @param rtnValue
	 * 
	 * @return string
	 */
	public String getValue(Map<String, String> map, String key, String rtnValue) {		
		if(StringUtils.isEmpty(map.get(key)))
			return rtnValue;
		
		return map.get(key).toString();
	}
	
	/**
	 * 정수 리턴 메소드
	 * 
	 * @param map
	 * @param key
	 * @param rtnValue
	 * 
	 * @return integer
	 */
	public int getValue(Map<String, String> map, String key, int rtnValue) {
		if(StringUtils.isEmpty(map.get(key)) || !NumberUtils.isNumber(map.get(key)))
			return rtnValue;
		
		return Integer.parseInt(map.get(key));
	}
	
	/**
	 * boolean 리턴 메소드
	 * 
	 * @param map
	 * @param key
	 * @param rtnValue
	 * 
	 * @return boolean
	 */
	public boolean getValue(Map<String, String> map, String key) {		
		if(StringUtils.isEmpty(map.get(key)))
			return false;
		
		if("true".equals(map.get(key)) || "on".equals(map.get(key)))
			return true;
		
		return false;
	}
	
	/**
	 * boolean 검색식의 조건 판단
	 * 
	 * @param String
	 * 
	 * @return map
	 */
	public HashMap<String,String> returnDetailKwd(String kwd, String oriRegx, String regx,String delime) {
		
		HashMap<String,String> map = new HashMap<String,String>();
		
		StringBuffer oriKwd = new StringBuffer();
		StringBuffer typeKwd = new StringBuffer();
		
		Pattern oriKwdPat = Pattern.compile(oriRegx);
		Pattern kwdPat = Pattern.compile(regx);
		Matcher oriKwdMat = oriKwdPat.matcher(kwd);
		Matcher kwdMat = kwdPat.matcher(kwd);
		
//		System.out.println(kwd);
//		System.out.println(oriRegx);
//		System.out.println(regx);
//		System.out.println(delime);
		
		
		while(oriKwdMat.find()) {
			oriKwd.append(oriKwdMat.group() + " ");
		}
		while(kwdMat.find()) {
			typeKwd.append(kwdMat.group() + " ");
		}
		
		map.put("oriKwd", oriKwd.toString().replace(delime, ""));
		map.put("typeKwd", typeKwd.toString().replace(delime, ""));
		
		if(!"".equals(map.get("oriKwd"))) {
			logger.debug( "map.get(\"oriKwd\") : " + delime + " ->  " + map.get("oriKwd"));
		}
		if(!"".equals(map.get("typeKwd"))) {
			logger.debug( "map.get(\"typeKwd\") : " + delime + " ->  " + map.get("typeKwd"));
		}
		
		
		return	map;
	}

	/**
	 * 검색 키워드 크로스 사이트 스크립팅(XSS) 방지
	 * 
	 * @param kwd
	 * 
	 * @return kwd_value
	 */
	public static String getCheckXSS(String kwd) {
		String kwd_value = (kwd == null || ("").equals(kwd)) ? "" : kwd;
		kwd_value = kwd_value.replaceAll("</?[a-zA-Z][0-9a-zA-Z가-\uD7A3ㄱ-ㅎ=/\"\'%;:,._()\\-# ]+>","");
		kwd_value = kwd_value.replaceAll(">","");
		kwd_value = kwd_value.replaceAll(">","");
		return kwd_value;
	}
}