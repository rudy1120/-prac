package egovframework.portal.sys.pub.vo;

import java.io.Serializable;

import egovframework.portal.common.vo.CommonVO;

@SuppressWarnings("serial")
public class SysPublicVO extends CommonVO implements Serializable {
	
	//카테고리 PK
	private String catIdx;
	
	//카테고리명
	private String catName;
	
	//정렬순서
	private String orderNum;
	
	//사용여부
	private String useYn;
	
	//등록일자
	private String regDate;
	
	//수정일자
	private String uptDate;
	
	//등록자ID
	private String regId;
	
	//수정자ID
	private String uptId;
	
	//사전정보공표 PK
	private String pubIdx;
	
	//공표제목
	private String name;
	
	//공표항목
	private String article;
	
	//시기
	private String time;
	
	//주기
	private String cycle;
	
	//공개부서
	private String depart;
	
	//게시판 링크
	private String link;
	
	//통계 PK
	private int idx;
	
	//접근일시
	private String accessDate;
	
	//검색조건 : 카테고리
	private String searchCatIdx;
	
	//검색조건 : 공표명
	private String searchName;
	
	//모니터링 
	private String usernm;
	
	private String email;
	
	private String tel;
	
	private String create_date;
	
	private String title;
	
	private String content;
	
	private int ptidx;
	
	
	
	public int getPtidx() {
		return ptidx;
	}

	public void setPtidx(int ptidx) {
		this.ptidx = ptidx;
	}

	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCatIdx() {
		return catIdx;
	}

	public void setCatIdx(String catIdx) {
		this.catIdx = catIdx;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUptDate() {
		return uptDate;
	}

	public void setUptDate(String uptDate) {
		this.uptDate = uptDate;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getUptId() {
		return uptId;
	}

	public void setUptId(String uptId) {
		this.uptId = uptId;
	}

	public String getPubIdx() {
		return pubIdx;
	}

	public void setPubIdx(String pubIdx) {
		this.pubIdx = pubIdx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCycle() {
		return cycle;
	}

	public void setCycle(String cycle) {
		this.cycle = cycle;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getAccessDate() {
		return accessDate;
	}

	public void setAccessDate(String accessDate) {
		this.accessDate = accessDate;
	}

	public String getSearchCatIdx() {
		return searchCatIdx;
	}

	public void setSearchCatIdx(String searchCatIdx) {
		this.searchCatIdx = searchCatIdx;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
}