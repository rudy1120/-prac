package egovframework.portal.unit.bmc.konantech.web.data;

import java.util.Arrays;

/** 
 * 검색 파라미터 Value Object
 * 
 * @author 장진후(Jinhoo.Jang)
 * @team 기술지원팀(Technical Support)
 * @since 2013.06.20
 * @version 1.0
 * #20160707 김승희 추가 : nowDate, fields, 
 */
public class ParameterVO {

    /** 검색키워드 */
    private String kwd;
    
    /** 이전검색어 배열 */
    public String[] preKwds;
    
    /** 검색 카테고리(탭) */
    private String category;
    
    /** 검색 서브카테고리 */
    private String subCategory;
		
	/** 검색대상 필드 */
	private String srchFd;

	/** 유저 ID */
	private String userId;
	
	/** 사이트명 */
	private String siteNm;

    /** 추천검색어 정보 */
    private String recKwd;

    /** 재검색 여부 (boolean) */
    private boolean reSrchFlag;

    /** 페이지사이즈 */
    private int pageSize;
    
    /** 검색결과페이지번호 */
    private int pageNum;
    
    /** 검색결과페이지번호 */
    private int offSet;
    
    /** 정렬 */
    private String sort;
    
    /** 정렬명 */
    private String sortNm;

	/** 상세검색 여부 플래그 */
    private boolean detailSearch;
    
	/** 제외어 */
    private String exclusiveKwd;
  
	/** 날짜선택사항 */
    private String date;

	/** 시작일 */
	private String startDate;
	
	/** 종료일 */
	private String endDate;
	
	/** 첨부파일 확장자 */
    private String fileExt;
	
    /** 작성자 */
    private String writer;
    
    /** 검색결과 최종 값 */
    private int total;
    
    /** 연도- 배열*/
    private String year;
    
    /** 호출한 위치 */
    private String callLoc;
    
    /** 이미지 뷰어 rowid */
    private int imageRowNo;
    
    /** 시나리오 */
    private String scenario;
    
    /** 하이라이트 키워드 */
    private String hilightKwd;
 
    /** 오늘날짜 20160706 */
    private String nowDate;
    
    /** 검색영역 20160706*/
    private String fields;   
    
    /** 쿼리 추가*/
    private String originalQuery;
	private String previousQuery;
	private String[] previousQueries; 
	
	//정확히 일치하는 단어/문장
	private String exactlyKeyword;
	//반드시 포함하는 단어
	private String mustKeyword;
	//제외하는 단어
	private String ignoreKeyword;
	//제외하는 단어
	private String orKeyword;
	//기본 키워드 세팅변수
	private String baseKeyword;
	// boolean Near Kwd
	private String nearKeyword;
	// boolean Within Kwd
	private String withinKeyword;
	
	private String typeValue;
	
	private String allcheck;
	
	
	
	public String getNearKeyword() {
		return nearKeyword;
	}

	public void setNearKeyword(String nearKeyword) {
		this.nearKeyword = nearKeyword;
	}

	public String getWithinKeyword() {
		return withinKeyword;
	}

	public void setWithinKeyword(String withinKeyword) {
		this.withinKeyword = withinKeyword;
	}

	public String getBaseKeyword() {
		return baseKeyword;
	}

	public void setBaseKeyword(String baseKeyword) {
		this.baseKeyword = baseKeyword;
	}

	public String getOrKeyword() {
		return orKeyword;
	}

	public void setOrKeyword(String orKeyword) {
		this.orKeyword = orKeyword;
	}

	public String getAllcheck() {
		return allcheck;
	}

	public void setAllcheck(String allcheck) {
		this.allcheck = allcheck;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getExactlyKeyword() {
		return exactlyKeyword;
	}

	public void setExactlyKeyword(String exactlyKeyword) {
		this.exactlyKeyword = exactlyKeyword;
	}

	public String getMustKeyword() {
		return mustKeyword;
	}

	public void setMustKeyword(String mustKeyword) {
		this.mustKeyword = mustKeyword;
	}

	public String getIgnoreKeyword() {
		return ignoreKeyword;
	}

	public void setIgnoreKeyword(String ignoreKeyword) {
		this.ignoreKeyword = ignoreKeyword;
	}

	public String getKwd() {
		return kwd;
	}

	public void setKwd(String kwd) {
		this.kwd = kwd;
	}
	
	public String[] getPreKwds() {
		return preKwds;
	}

	public void setPreKwds(String[] preKwds) {
		this.preKwds = preKwds;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getRecKwd() {
		return recKwd;
	}

	public void setRecKwd(String recKwd) {
		this.recKwd = recKwd;
	}

	public boolean getReSrchFlag() {
		return reSrchFlag;
	}

	public void setReSrchFlag(boolean reSrchFlag) {
		this.reSrchFlag = reSrchFlag;
	}

	public String getSrchFd() {
		return srchFd;
	}

	public void setSrchFd(String srchFd) {
		this.srchFd = srchFd;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSiteNm() {
		return siteNm;
	}

	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
		
    public String getSortNm() {
		return sortNm;
	}

	public void setSortNm(String sortNm) {
		this.sortNm = sortNm;
	}

	public boolean getDetailSearch() {
		return detailSearch;
	}

	public void setDetailSearch(boolean detailSearch) {
		this.detailSearch = detailSearch;
	}
	
	public String getExclusiveKwd() {
		return exclusiveKwd;
	}

	public void setExclusiveKwd(String exclusiveKwd) {
		this.exclusiveKwd = exclusiveKwd;
	}	
	  
    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @param count
	 */
	public void addTotal(int count) {
		this.total += count;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
	
	 public String getCallLoc() {
			return callLoc;
	}

	public void setCallLoc(String callLoc) {
		this.callLoc = callLoc;
	}

	public int getImageRowNo() {
		return imageRowNo;
	}

	public void setImageRowNo(int imageRowNo) {
		this.imageRowNo = imageRowNo;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public String getHilightKwd() {
		return hilightKwd;
	}

	public void setHilightKwd(String hilightKwd) {
		this.hilightKwd = hilightKwd;
	}
	
	public String getNowDate() {
		return nowDate;
	}

	public void setNowDate(String nowDate) {
		this.nowDate = nowDate;
	}
	
	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}
	
	public String getPreviousQuery() {
		return previousQuery;
	}

	public void setPreviousQuery(String previousQuery) {
		this.previousQuery = previousQuery;
	}
	
	public String getOriginalQuery() {
		return originalQuery;
	}

	public void setOriginalQuery(String originalQuery) {
		this.originalQuery = originalQuery;
	}
	
	public String[] getPreviousQueries() {
		return previousQueries;
	}

	public void setPreviousQueries(String[] previousQueries) {
		this.previousQueries = previousQueries;
	}

	@Override
	public String toString() {
		return "ParameterVO [kwd=" + kwd + ", preKwds=" + Arrays.toString(preKwds) + ", category=" + category
				+ ", subCategory=" + subCategory + ", srchFd=" + srchFd + ", userId=" + userId + ", siteNm=" + siteNm
				+ ", recKwd=" + recKwd + ", reSrchFlag=" + reSrchFlag + ", pageSize=" + pageSize + ", pageNum="
				+ pageNum + ", offSet=" + offSet + ", sort=" + sort + ", sortNm=" + sortNm + ", detailSearch="
				+ detailSearch + ", exclusiveKwd=" + exclusiveKwd + ", date=" + date + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", fileExt=" + fileExt + ", writer=" + writer + ", total=" + total
				+ ", year=" + year + ", callLoc=" + callLoc + ", imageRowNo=" + imageRowNo + ", scenario=" + scenario
				+ ", hilightKwd=" + hilightKwd + ", nowDate=" + nowDate + ", fields=" + fields + ", originalQuery="
				+ originalQuery + ", previousQuery=" + previousQuery + ", previousQueries="
				+ Arrays.toString(previousQueries) + ", exactlyKeyword=" + exactlyKeyword + ", mustKeyword="
				+ mustKeyword + ", ignoreKeyword=" + ignoreKeyword + ", orKeyword=" + orKeyword + ", baseKeyword="
				+ baseKeyword + ", nearKeyword=" + nearKeyword + ", withinKeyword=" + withinKeyword + ", typeValue="
				+ typeValue + ", allcheck=" + allcheck + "]";
	}
	
	
}