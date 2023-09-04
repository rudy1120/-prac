package egovframework.portal.unit.bmc.konantech.web.module;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import egovframework.portal.unit.bmc.konantech.web.common.RESTUtil;
import egovframework.portal.unit.bmc.konantech.web.data.ResultListVO;



@Component("restModule")
public class RestModule {
	
	private static final Logger logger = LoggerFactory.getLogger(RestModule.class);
	
	@Value("#{kframework['engine.url']}") protected String engineUrl;
	
	@Value("#{kframework['engine.charsetType']}") protected String charsetType;
	
	@Value("#{kframework['ksf.charsetType']}") protected String ksfCharsetType;
	
	@Value("#{kframework['useWarning']}") protected boolean useWarning;
	
	@Value("#{kframework['ksf.url']}") protected String ksfUrl;
	
	@Value("#{kframework['HTTP_TIMEOUT']}") protected int timeout;
	
	@Autowired
	private RESTUtil restUtil;
	
	/**
	 * REST 방식으로 엔진 검색 호출 (HTTPCLIENT 호출방식)
	 * @version 1.1
	 * @modify 2018.06.07
	 * @modifier 안호빈(hobin.ahn)
	 * @return ResultListVO
	 * @throws Exception 
	 */
	public boolean restSearch_hc(String restUrl, ResultListVO restVO) {
		
		try {
			String resultJson = restUtil.request(restUrl);
			if(resultJson==null) {
				return false;
			}
			JSONParser parser = new JSONParser();
			// JSON 파싱하는 부분 
			
			Object obj = parser.parse(resultJson);
			
			JSONObject jsonObj = (JSONObject) obj;
			JSONObject resultObj = (JSONObject) jsonObj.get("result");
			JSONArray arr = (JSONArray) resultObj.get("rows");
			int arrCnt = arr.size();
			
			restVO.setStatus((String)jsonObj.get("status"));
			restVO.setTotal((Long)resultObj.get("total_count"));
			restVO.setRows(arrCnt);
			
			if(arr != null && arrCnt > 0) {
				HashMap<String, String> map;
				List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>> (); 
						
				JSONObject result;
				JSONObject record;
				for(int i=0; i<arrCnt; i++) {
					map = new HashMap<String, String> ();
					result = (JSONObject) arr.get(i);
					record = (JSONObject) result.get("fields");
					//검색  대상 필드명 추출
					Iterator fields_iter = record.keySet().iterator();
					
					while (fields_iter.hasNext()) {
						String key = (String) fields_iter.next();						
						String value = record.get(key).toString();
						//그룹바이 카운트필드명  버전별 상이 할 수 있어 'count'로 통일
						if("count(*)".equals(key) || "count()".equals(key) ) {
							key = "count";
						}
						//개발라이선스 워닝 메세지 옵션에 따라 삭제
						if(!useWarning) {
							map.put(key, value.replaceAll("\\(WARNING: EVALUATION COPY\\[SEARCH\\]\\)", ""));
						} else {
							map.put(key, value);
						}
					}
					list.add(map);
					map = null;
				}
				
				restVO.setResult(list);
			}
			// 파싱 끝			
		} catch (Exception e) {
			logger.error("ERROR: "+logger.getName() +" restSearch Exception");
			e.printStackTrace();
			return false;
		}finally{
		}		
		return true;
	}
	

	

	 /** 검색어 자동완성 메소드 (getCompleteKwd).
     * @param keyword 키워드
     * @param maxResultCount 최대 결과 수
     * @param flag 결과 형식 플래그 (앞, 뒤 단어 일치 여부)
     * @param language 언어
     * @param charset 문자셋
     * @param domainNo 모듈 도메인 번호
     * @return 결과값(String[])
     * @throws IOException
     * @throws Exception
     * @throws KonanException
     */
    public String getCompleteKwd(String ksfRestUrl, String keyword, int maxResultCount, int flag, int domainNo)
					throws IOException, Exception, KonanException
    {
		// 결과 파싱
		try{
			if(!"".equals(keyword)) {
		        StringBuffer ksfLastUrl = new StringBuffer();
		        ksfLastUrl.append(ksfRestUrl);
		        ksfLastUrl.append("suggest");
		        ksfLastUrl.append("?target=complete");
		        ksfLastUrl.append("&term=" + urlEncode(keyword,"UTF-8") );
		        if(flag == 0)  ksfLastUrl.append("&mode=" + "s");
		        else if(flag == 1) ksfLastUrl.append("&mode=" + "e");
		        ksfLastUrl.append("&domain_no=" + domainNo); 
		        ksfLastUrl.append("&max_count=" + maxResultCount);  
		        
		        logger.info("AKC REST URL :: " + ksfLastUrl.toString());
		        
		        StringBuffer sb = getUrlData(ksfLastUrl.toString(), "UTF-8");
		        
		        if(!"".equals(sb.toString())) {
		        	logger.info("AKC RETURN VALUE :: " + sb.toString());
		        }
		        
		        return sb.toString();
			}else {
				return "";
			}
		} catch (Exception e){
			logger.error("AKC ERROR:" + e.getMessage());
			return "";
		}
    }
	
	/** 검색어 오타교정 (getSpcApi).
     * @param keyword 키워드
     * @return 결과값(String)
     * @throws IOException
     * @throws Exception
     * @throws KonanException
     */
	public String getSpcApi(String kwd) {
			
		// 결과 파싱
		try{
			if(!"".equals(kwd)) {
		        StringBuffer ksfLastUrl = new StringBuffer();
		        ksfLastUrl.append(ksfUrl);
		        ksfLastUrl.append("suggest?target=spell&term=");
		        ksfLastUrl.append(urlEncode(kwd,"UTF-8") );
		        
		        logger.info("SPC REST URL :: " + ksfLastUrl.toString());
		        
		        StringBuffer sb = getUrlData(ksfLastUrl.toString(), "UTF-8");
		        String returnData = "";
		        if(!"".equals(sb.toString())) {
	        		returnData = sb.toString().replaceAll("\"|\\[|\\]", ""); 
	        		logger.info("SPC RETURN VALUE :: " + returnData);
		        }

		        return returnData;
			}else {
				return "";
			}
		} catch (Exception e){
			logger.error("SPC ERROR:" + e.getMessage());
			return "";
		}
			
	}
	
	/**
	 * KSF 방식으로 인기검색어를 가져온다.(리턴값 2차배열)(HTTPCLIENT 호출방식)
	 *  
	 * @return String[][]
	 */
	public List<HashMap<String, String>> getPopularKwd_hc(int domainNo, int maxResult) {
		
		
		try {
			
			StringBuffer ksfRestUrl = new StringBuffer();
			ksfRestUrl.append(ksfUrl);
			ksfRestUrl.append("rankings");
			ksfRestUrl.append("?domain_no=" + domainNo);
			ksfRestUrl.append("&max_count=" + maxResult);				 
			
			logger.info("PPK REST URL :: " + ksfRestUrl.toString());
			System.out.println("PPK REST URL :" + ksfRestUrl.toString());
			List<HashMap<String, String>> ppkResult = null;
			
			String resultJson = restUtil.request(ksfRestUrl.toString());
			if(resultJson==null) {
				return null;
			}
			
			JSONParser parser = new JSONParser();
			// JSON 파싱하는 부분 
			
			Object obj = parser.parse(resultJson);
			
			JSONArray jsonArr = (JSONArray) obj;
			JSONArray ppk = null;
			if(jsonArr != null && jsonArr.size() > 0) {
				ppkResult = new ArrayList<HashMap<String, String>> ();
				HashMap<String, String> ppkMap = null;
				
				for(int i=0; i<jsonArr.size(); i++) {
					ppk = (JSONArray) jsonArr.get(i);
					ppkMap = new HashMap<String, String>();
					
					ppkMap.put("ppk", (String) ppk.get(0));
					ppkMap.put("meta", (String) ppk.get(1));
					
					ppkResult.add(ppkMap);
					ppkMap = null;
				}
			}
			if(ppkResult != null) {
				for (int i = 0; i < ppkResult.size(); i++) {
					logger.info("PPK RETURN VALUE :: " + ppkResult.get(i).get("ppk"));
					logger.info("PPK RETURN VALUE :: " + ppkResult.get(i).get("meta"));
				}
			}
			
			return ppkResult;
			// 파싱 끝			
		} catch (Exception e) {
			logger.error("PPK ERROR:" + e.getMessage());
			return null;
		}
	}
	
	
	
	public StringBuffer getUrlData(String urlinfo, String charset) {

    	URL url =null;
		HttpURLConnection conn = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		// 조회
		try{
			url = new URL( urlinfo);
			conn = (HttpURLConnection) url.openConnection();
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName(charset)));

			for(String line = br.readLine(); line != null ; line = br.readLine()){
				sb.append(line);
			}
			
			return sb;
		} catch (IOException ie) {
			return  new StringBuffer();
		} finally {
			try{if( br != null) {br.close();}}catch(IOException ie){System.out.println(ie.getMessage());}
			if( conn != null){conn.disconnect();}
			if( url != null){url = null;}
		}
		
	}
    
    /**
	 * Method Name : urlEncode
	 * Description : 통합검색 엔진에 호출하기 위한 URL 정보를 인코딩하여 반환한다.
	 *
	 * @param strUrl 검색 URL
	 * @param enc 인코딩
	 * @return String URL 인코딩처리하여 반환
	 */
	public static String urlEncode(String strUrl, String enc) {
		String tmpUrl;
		try{
			if(null == enc ){
				tmpUrl = URLEncoder.encode(strUrl,"UTF-8");
			}else {
				tmpUrl = URLEncoder.encode(strUrl, enc);
			}
			return tmpUrl;
		}catch(UnsupportedEncodingException us) {
			return "";
		}
	}
}
