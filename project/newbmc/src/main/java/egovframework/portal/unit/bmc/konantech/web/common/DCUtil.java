package egovframework.portal.unit.bmc.konantech.web.common;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Component;

import egovframework.portal.unit.bmc.konantech.web.data.ParameterVO;
import egovframework.portal.unit.bmc.konantech.web.data.SearchVO;


/** 
 * 검색 관련 공통 유틸.
 * 
 * @author KONAN Technology
 * @since 2013.08.20
 * @version 2.0
 */
@Component
public class DCUtil {
	
	/**
	 * 검색어에 대한 escape 처리.
	 * 
	 * @param kwd
	 * 
	 * @return escape kwd
	 */
	public String escapeQuery(String kwd) {
		String str = "";
		char ch;
	        for (int i = 0; i < kwd.length(); i++) {
			ch = kwd.charAt(i);
			switch (ch) {
				case '\"':
					str = str + "\\\"";
					break;
				case '\'':
					str = str + "\\'";
					break;
				case '\\':
					str = str + "\\\\";
					break;
				case '?':
					str = str + "\\?";
					break;         
				case '*':
					str = str + "\\*";
					break;    
				default:
					str = str + ch;
					break;
			}
		}

		return str;
	}

	/** 
	 * 검색엔진 로그정보 로그포맷. 
	 * [클래스+사용자ID$|첫검색|페이지번호|정렬방법^키워드]##이전검색어|현재검색어]
	 *  
	 * @param site 사이트명
	 * @param nmSchCat 카테고리명
	 * @param userId 사용자ID
	 * @param kwd 키워드
	 * @param pageNum 페이지번호
	 * @param reSchFlag 재검색여부(true/false)
	 * @param orderNm 정렬방법
	 * @param recKwd 추천검색어('이전검색어|현재검색어')
	 * 
	 * @return 검색 로그 String
	 */
	public String getLogInfo(String site, String nmSchCat, String userId, String kwd, 
					int pageNum, boolean reSchFlag, String orderNm, String recKwd) {
		StringBuffer logInfo = new StringBuffer("");
		
		logInfo.append(site);
		logInfo.append("@");
		
		logInfo.append(nmSchCat);
		logInfo.append("+");

		// 페이지 이동은 검색으로 간주하지 않음
		if (pageNum > 1) {
			logInfo.append("$||");
			logInfo.append(pageNum);
			logInfo.append("|");
		
		} else {
			logInfo.append(userId);
			logInfo.append("$|");

			if (reSchFlag) {
				logInfo.append("RESEARCH|");
			} else {
				logInfo.append("FIRST|");
			}
			logInfo.append(pageNum);
			logInfo.append("|");
		}

		logInfo.append(orderNm);
		logInfo.append("^");
		logInfo.append(kwd);
		
		// 추천어로그
		if (recKwd != null && recKwd.length() > 0) {
			logInfo.append("]##").append(recKwd);
		}
		return logInfo.toString();
	}
	
	/** 
	 * 검색엔진 로그정보 로그포맷. 
	 * [클래스+사용자ID$|첫검색|페이지번호|정렬방법^키워드|현재검색어]
	 *  
	 * @param ParameterVO
	 * 
	 * @return 검색 로그 String
	 */
	public String getLogInfo(ParameterVO params) {
		StringBuffer logInfo = new StringBuffer("");
		
		logInfo.append(params.getSiteNm());
		logInfo.append("@");
		
		logInfo.append(params.getCategory());
		logInfo.append("+");

		// 페이지 이동은 검색으로 간주하지 않음
		if (params.getPageNum() > 1) {
			logInfo.append("$||");
			logInfo.append(params.getPageNum());
			logInfo.append("|");
		
		} else {
			logInfo.append(params.getUserId());
			logInfo.append("$|");

			logInfo.append("FIRST|");
			logInfo.append(params.getPageNum());
			logInfo.append("|");
		}

		logInfo.append(params.getSortNm());
		logInfo.append("^");
		logInfo.append(params.getKwd());
		
		return logInfo.toString();
	}
    
	/** 
	 * 키워드/코드형식쿼리 생성. 
     *
     * @param nmFd 검색대상 필드명 또는 인덱스명
     * @param kwd 검색어
     * @param schMethod 검색메소드
     * @param query 이전 생성 쿼리  
     * @param logicOp 연결 논리연산자 (ex : and, or, and not) 
     *
     * @return 쿼리 StringBuffer
     */	
     public StringBuffer makeQuery(String nmFd, String kwd, String schMethod, 
    		                              StringBuffer query, String logicOp) {

         StringBuffer tempQuery = new StringBuffer("");

         if (query != null && query.length() > 0) {
        	 tempQuery.append("(" + query + ")");
        	 //tempQuery.append(query);
         }

         if (kwd != null && kwd.length() > 0) {
             if (tempQuery.length() > 0) {
            	 if ("".equalsIgnoreCase(logicOp)) {
            		 tempQuery.append(" and ");
            	 } else {
            		 tempQuery.append(" " + logicOp + " ");
            	 }	 
             }
             tempQuery.append(nmFd);
             tempQuery.append("='");
             tempQuery.append(escapeQuery(kwd));
             tempQuery.append("' ");
             tempQuery.append(schMethod);
         }
         
         return tempQuery;
     }
     
	/** 
	 * 확장형 쿼리 생성.
	 *
	 * @param nmFd 검색대상 필드명 또는 인덱스명
	 * @param op 연산자 (ex : =, >, <) 
	 * @param kwd 키워드
	 * @param query 이전 생성 쿼리 
	 * @param logicOp 논리연산자 (ex : and, or, and not)
	 * @param isText 형태소 검색 여부 (default y)
	 * @param srchMethod 검색 메소드 
	 *
	 * @return 검색쿼리 StringBuffer
	 */
	public StringBuffer makeExpressionQuery(String nmFd, String op, String kwd, boolean isText, 
												String srchMethod, String logicOp, StringBuffer query) {

		StringBuffer tempQuery = new StringBuffer("");

		if (query != null && query.length() > 0) {
			// 이전쿼리가 값이 있을 경우 괄호로 묶어준다
			tempQuery.append("(" + query + ")"); 		
			// 이전쿼리의 논리연산자 append (ex : and, or, and not)
			tempQuery.append(" " + logicOp + " ");		
		}
		
		if (query != null && query.length() > 0) {
			// 
			tempQuery.append("("); 						
		}
		
		// 검색 대상 필드 append
		tempQuery.append(nmFd);
		
		// 연산자 append (ex : =, >, <)
		tempQuery.append(op);
		
		// 형태소 검색 여부 판단 
		if (isText) {
			tempQuery.append("'" + kwd + "' " + srchMethod);
		} else {
			tempQuery.append("'" + kwd + "' ");	
		}
		
		// 생성될 쿼리의 끝 괄호 
		if (query != null && query.length() > 0) {
			tempQuery.append(")");						
		}
		
		return tempQuery;
	}
	
    /** 
     * Like 쿼리 생성.
  	 * 
  	 * @param nmFd 검색대상 필드명 또는 인덱스명
  	 * @param kwd 키워드
  	 * @param query 이전 생성 쿼리
  	 * @param option 좌절단 : left, 우절단 : right, 좌우절단 : all
  	 * 
  	 * @return 검색쿼리 StringBuffer
  	 */
  	public StringBuffer makeLikeQuery(String nmFd, String kwd, String option, 
  			 							StringBuffer query) {
  		StringBuffer tempQuery = new StringBuffer("");
  
  		if (query != null && query.length() > 0) {
  			tempQuery.append("(" + query + ")");
  		}
  		
  		if (kwd != null && kwd.length() > 0) {
  			if (tempQuery.length() > 0) {
  				tempQuery.append(" AND ");
  			}
  			tempQuery.append(nmFd + " like '");
  			
  			if ("all".equals(option) || "left".equals(option)) {
  				tempQuery.append("*");
  			} 
  			
  			tempQuery.append(escapeQuery(kwd));
  			
  			if ("all".equals(option) || "right".equals(option)) {
  	  			tempQuery.append("*");
  			}
  			tempQuery.append("'");
  			
  		}
  		return tempQuery;
  	}

    /** 
     * IN쿼리 생성.
     *
     * @param nmFd 검색대상 필드명 또는 인덱스명
     * @param code 조회 대상 코드 값
     * @param isNumber code 값이 숫자값인지 여부 (true : 숫자, false : 문자)
     * @param query 이전생성 쿼리
     * @return 검색쿼리 StringBuffer
     */
    public StringBuffer makeINQuery(String nmFd, String code[], boolean isNumber, 
    										StringBuffer query) { 

        StringBuffer tempQuery = new StringBuffer("");
        StringBuffer inQuery = new StringBuffer("");

        if (query != null && query.length() > 0) {
            tempQuery.append(" (" + query + ") ");
        }

        if ((code != null) && (code.length > 0)) {

            boolean check = true;
            for (int i = 0; i < code.length; i++) {
                if (!"".equals(code[i])) {
                    check = false;
                    break;
                }
            }

            if (check) {
                return tempQuery;
            }

            if (tempQuery.length() > 0) {
                tempQuery.append(" AND ");
            }

            tempQuery.append(nmFd);
            tempQuery.append(" IN {");
            for (int i = 0; i < code.length; i++) {
                if (code[i] != null && code[i].length() > 0) {
                    if (inQuery.length() > 0) {
                        inQuery.append(", ");
                    }
                    if (isNumber) { 
                    	inQuery.append(code[i]);
                    } else {
                    	inQuery.append("'" + code[i] + "'"); 
                    }
                }
            }

            tempQuery.append(inQuery.toString());
            tempQuery.append("}");
        }
        return tempQuery;
    }

	
	/** 
	 * 구간검색 쿼리 생성.
	 * 
	 * @param nmFd 검색대상 필드명 또는 인덱스명
	 * @param startVal 시작값
	 * @param endVal 종료값 
	 * @param query 이전 생성 쿼리
	 * @return 검색 쿼리 StringBuffer
	 */
	public StringBuffer makeRangeQuery( String nmFd, String startVal, String endVal, 
												StringBuffer query) {
		
		StringBuffer tempQuery = new StringBuffer("");
		
		if ("".equals(startVal) && "".equals(endVal) ){
			return query;
		}
		
		if ( query != null && query.length() > 0 ){
			tempQuery.append( "(" + query +")");
			tempQuery.append(" AND ");			
		}
		
		tempQuery.append("(");
		
		if(!startVal.equals("")){		
			tempQuery.append(nmFd);
			tempQuery.append(" >= '");
			tempQuery.append(startVal);
			tempQuery.append("'");
		}       
		
		if(!endVal.equals("")){	
			if(!startVal.equals("")){
				tempQuery.append(" AND ");
			}
			tempQuery.append(nmFd);
			tempQuery.append(" <= '");
			tempQuery.append(endVal);
			tempQuery.append("'");
		}
		
		tempQuery.append(")");
		
		return tempQuery;
	}
	
	/** 
	 * 재검색 쿼리 생성. 
	 * 
	 * @param nmFd 검색대상 필드명 또는 인덱스명
	 * @param kwd 키워드
	 * @param prevKwd 이전 키워드 배열
	 * @param prevKwdLength 이전 키워드 배열 길이
	 * @param schMethod 검색 메소드
	 * @return 검색 쿼리 StringBuffer
	 */
	public StringBuffer makePreQuery(String nmFd, String kwd, String[] prevKwd, 
											int prevKwdLength, String schMethod) {
		StringBuffer query = new StringBuffer("");

		if (prevKwd != null && prevKwdLength > 0) {
			for (int i = 0; i < prevKwdLength; i++) {
				if (!escapeQuery(prevKwd[i]).equalsIgnoreCase(kwd)) {
					if (query.length() > 0) {
						query.append(" AND ");
					}
					query.append(nmFd);
					query.append("='");
					query.append(escapeQuery(prevKwd[i]));	
					//query.append(escapeQuery(CommonUtil.changeEncode(prevKwd[i], "ISO-8859-1", "EUC-KR")));
					//이전 키워드가 깨질 경우 위의 코드로 대체하도록 한다.
					
					query.append("' ");
					query.append(schMethod);
				}
			}
			if (query.length() > 0) { 
				query = new StringBuffer("(").append(query).append(")");
			}
		}

		return query;
	}
	

	/**
	 * 배열로 값을 받아와서 한번에 쿼리를 생성
	 * 
	 * @param queryValue
	 * @param param
	 * 
	 * @return
	 */
	public StringBuffer makeMasterQuery(String[][] queryValue, ParameterVO param){
		StringBuffer query = new StringBuffer();
		// 재검색 여부 체크
		if(param.getReSrchFlag() && param.getPreKwds() != null){
			for(int i=0; i<param.getPreKwds().length; i++){
				for(int j=0; j<queryValue.length; j++){					
					if(j == 0)
						query.append("( ");
					query.append(queryValue[j][0]);
					query.append(chkSrchMethod(param.getPreKwds()[i], queryValue[j][1]));
					
					if(j < queryValue.length-1){
						query.append("OR ");
					}else{
						query.append(" ) AND ");
					}				
				}
			}
		}
		
		for(int i=0; i<queryValue.length; i++){			
			if(i == 0)
				query.append("( ");
			query.append(queryValue[i][0]);
			query.append(chkSrchMethod(param.getKwd(), queryValue[i][1]));
			
			if(i < queryValue.length-1){
				query.append("OR ");
			}else{
				query.append(" )");
			}
		}
		
		return query;
	}
	
	/**
	 * 검색 타입을 체크하여 파람값을 리턴해준다.
	 * 
	 * @param kwd
	 * @param srchType
	 * 
	 * @return query
	 */
	public String chkSrchMethod( String kwd, String srchType ){
		StringBuffer query = new StringBuffer();
		
		if(srchType.equals("like")){
			query.append(" like '*" + kwd + "*' ");
		}else if(srchType.equals("left")){
			query.append(" like '*" + kwd + "' ");
		}else if(srchType.equals("right")){
			query.append(" like '" + kwd + "*' ");
		}else{	
			query.append(" = '" + kwd + "' " + srchType);
		}
		
		return query.toString();
	}
	
	/**
	 * 파라미터값을 기준으로 REST URL을 생성
	 * 
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String getRestURL(ParameterVO paramVO, SearchVO searchVO) throws UnsupportedEncodingException {
		StringBuffer url = new StringBuffer();
		url.append(searchVO.getUrl());
		url.append("?select=" + searchVO.getFields_rest());
		url.append("&from=" + searchVO.getFrom());
		url.append("&where=" + searchVO.getQuery());
		url.append("&offset=" + (paramVO.getPageNum()-1)*paramVO.getPageSize());
		url.append("&limit=" + paramVO.getPageSize());
		url.append("&charset=" + searchVO.getCharset());
		url.append("&hilite-fields=" +searchVO.getHilightFileds());
		url.append("&hilite-keywords=" +searchVO.getHilightTxt());
		url.append("&custom=" + searchVO.getLogInfo());
		if(searchVO.getSynDomainNo() != null && !"".equals(searchVO.getSynDomainNo())) url.append("&syn-domain-no=" +searchVO.getSynDomainNo());
		if(searchVO.getDefaultHilight() != null && !"".equals(searchVO.getDefaultHilight())) url.append("&default-hilight=" +searchVO.getDefaultHilight());
		return url.toString();
	}
}
