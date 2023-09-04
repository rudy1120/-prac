package egovframework.portal.sys.basic.poll.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.basic.poll.PollDupType;
import egovframework.portal.validation.ByteSize;

/**
 * 설문 정보 VO
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 7. 19.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 7. 19.
 */
public class PollMngVO extends CommonVO {

	private int idx = 0;
	/** 설문 조사명 */
	@NotBlank
	@ByteSize(max = 1000, message = "설문 조사명이 너무 깁니다.")
	private String title = "";
	/** 설문 시작 기간 */
	@NotBlank
	@ByteSize(max = 10)
	private String startDate = "";
	/** 설문 종료 기간 */
	@NotBlank
	@ByteSize(max = 10)
	private String endDate = "";
	/** 사이트 코드 */
	@NotBlank
	@ByteSize(max = 20, message = "사이트 코드 값이 너무 깁니다.")
	private String siteCode = "";
	/** 중복 투표 (1:중복 투표 가능, 2:IP 체크, 3:쿠키 체크, 4:본인 인증 체크) */
	@NotBlank
	@RegExp("^[1|2|3|4]$")
	@ByteSize(max = 1, message = "중복 투표 설정이 올바르지 않습니다.")
	private String dupType = "";
	/** 참여 대상 설명 */
	@ByteSize(max = 1000, message = "참여 대상 설명이 너무 깁니다.")
	private String joinTarget = "";
	/** 참여 대상 성별 (A:전체, M:남, W:여) */
	@NotBlank
	@RegExp("^[A|M|W]$")
	@ByteSize(max = 1, message = "중복 투표 설정이 올바르지 않습니다.")
	private String joinGender = "";
	/** 참여 대상 연령 제한 사용 여부 (Y/N) */
	@NotBlank
	@RegExp("^[Y|N]$")
	@ByteSize(max = 1, message = "참여 대상 연령 제한 사용 여부")
	private String joinAgeYn = "N";
	/** 참여 대상 시작 연령 (1980) */
	@ByteSize(max = 4, message = "참여 대상 시작 연령이 너무 깁니다.")
	private String joinStAge = "";
	/** 참여 대상 종료 연령 (1999) */
	@ByteSize(max = 4, message = "참여 대상 종료 연령이 너무 깁니다.")
	private String joinEdAge = "";
	/** 참여자 추첨 설정 (N:미사용, Y:사용) */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String lotteryYn = "N";
	/** 추첨인원 */
	private int lotteryNum = 0;
	/** 대기인원 */
	private int reservNum = 0;
	/** 설문 결과 공개 여부 (N:비공개, Y:공개) */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String publicYn = "Y";
	/** 머릿말 */
	@ByteSize(max = 4000, message = "머릿말 내용이 너무 깁니다.")
	private String header = "";
	/** 머릿말 첨부파일 */
	private String headerFile = "";
	/** 꼬릿말 */
	@ByteSize(max = 4000, message = "꼬릿말 내용이 너무 깁니다.")
	private String footer = "";
	/** 대표 이미지 */
	private String mainFile = "";
	/** 설문 상태 (N:설문중단, Y:진행) */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String stateYn = "";
	/** 사용자 화면 노출 여부 (N:미노출, Y:노출) */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String viewYn = "N";
	/** 질문 등록 방법 선택 (N:신규 질문 설정, Y:기존 질문 불러오기) */
	@NotBlank
	@RegExp("^[Y|N]$")
	private String questionYn = "N";
	/** 삭제 여부 (default : N) */
	@RegExp("^[Y|N]$")
	private String isDel = "N";
	/** 등록 일자 */
	private Date createDate = null;
	/** 수정 일자 */
	private Date updateDate = null;
	/** 삭제 일자 */
	private Date deleteDate = null;
	/** 추첨 일자 */
	private Date lotDate = null;

	/** 결과 매핑 */
	private int userCnt = 0;
	private String siteName = "";
	private String stateFlag = "";
	/** 검색용 파라미터 */
	private String searchState = "";
	private String searchViewYn = "";
	private String searchSiteCode = "";

	private String headerFileCn = "";
	private String mainFileCn = "";

	private List<PollQuestionMngVO> questionList = new ArrayList<PollQuestionMngVO>();
	private List<PollResponseMngVO> responseList = new ArrayList<PollResponseMngVO>();

	private String tel = "";
	private String beforeIdx = "";
	
	public PollMngVO() {
		super();
	}

	public PollMngVO(int idx) {
		super();
		this.idx = idx;
	}

	public PollMngVO(int idx, String viewYn) {
		super();
		this.idx = idx;
		this.viewYn = viewYn;
	}

	public PollMngVO(int firstIndex, int lastIndex) {
		super();
		this.setFirstIndex(firstIndex);
		this.setLastIndex(lastIndex);
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getDupType() {
		return dupType;
	}

	public void setDupType(String dupType) {
		this.dupType = dupType;
	}

	public String getJoinTarget() {
		return joinTarget;
	}

	public void setJoinTarget(String joinTarget) {
		this.joinTarget = joinTarget;
	}

	public String getJoinGender() {
		return joinGender;
	}

	public void setJoinGender(String joinGender) {
		this.joinGender = joinGender;
	}

	public String getJoinStAge() {
		return joinStAge;
	}

	public void setJoinStAge(String joinStAge) {
		this.joinStAge = joinStAge;
	}

	public String getJoinEdAge() {
		return joinEdAge;
	}

	public void setJoinEdAge(String joinEdAge) {
		this.joinEdAge = joinEdAge;
	}

	public String getLotteryYn() {
		return lotteryYn;
	}

	public void setLotteryYn(String lotteryYn) {
		this.lotteryYn = lotteryYn;
	}

	public int getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryNum(int lotteryNum) {
		this.lotteryNum = lotteryNum;
	}

	public int getReservNum() {
		return reservNum;
	}

	public void setReservNum(int reservNum) {
		this.reservNum = reservNum;
	}

	public String getPublicYn() {
		return publicYn;
	}

	public void setPublicYn(String publicYn) {
		this.publicYn = publicYn;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeaderFile() {
		return headerFile;
	}

	public void setHeaderFile(String headerFile) {
		this.headerFile = headerFile;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getMainFile() {
		return mainFile;
	}

	public void setMainFile(String mainFile) {
		this.mainFile = mainFile;
	}

	public String getStateYn() {
		return stateYn;
	}

	public void setStateYn(String stateYn) {
		this.stateYn = stateYn;
	}

	public String getViewYn() {
		return viewYn;
	}

	public void setViewYn(String viewYn) {
		this.viewYn = viewYn;
	}

	public String getQuestionYn() {
		return questionYn;
	}

	public void setQuestionYn(String questionYn) {
		this.questionYn = questionYn;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public Date getLotDate() {
		return lotDate;
	}

	public void setLotDate(Date lotDate) {
		this.lotDate = lotDate;
	}

	public int getUserCnt() {
		return userCnt;
	}

	public void setUserCnt(int userCnt) {
		this.userCnt = userCnt;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSearchState() {
		return searchState;
	}

	public void setSearchState(String searchState) {
		this.searchState = searchState;
	}

	public String getSearchViewYn() {
		return searchViewYn;
	}

	public void setSearchViewYn(String searchViewYn) {
		this.searchViewYn = searchViewYn;
	}

	public String getSearchSiteCode() {
		return searchSiteCode;
	}

	public void setSearchSiteCode(String searchSiteCode) {
		this.searchSiteCode = searchSiteCode;
	}

	public String getStateFlag() {
		return stateFlag;
	}

	public void setStateFlag(String stateFlag) {
		this.stateFlag = stateFlag;
	}

	public String getHeaderFileCn() {
		return headerFileCn;
	}

	public void setHeaderFileCn(String headerFileCn) {
		this.headerFileCn = headerFileCn;
	}

	public String getMainFileCn() {
		return mainFileCn;
	}

	public void setMainFileCn(String mainFileCn) {
		this.mainFileCn = mainFileCn;
	}

	public String getJoinAgeYn() {
		return joinAgeYn;
	}

	public void setJoinAgeYn(String joinAgeYn) {
		this.joinAgeYn = joinAgeYn;
	}

	public List<PollQuestionMngVO> getQuestionList() {
		if (questionList == null || questionList.size() == 0) {
			questionList = new ArrayList<PollQuestionMngVO>();
			questionList.add(new PollQuestionMngVO(idx, "2"));
		}
		return questionList;
	}

	public void setQuestionList(List<PollQuestionMngVO> questionList) {
		this.questionList = questionList;
	}

	public List<PollResponseMngVO> getResponseList() {
		return responseList;
	}

	public void setResponseList(List<PollResponseMngVO> responseList) {
		this.responseList = responseList;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBeforeIdx() {
		return beforeIdx;
	}

	public void setBeforeIdx(String beforeIdx) {
		this.beforeIdx = beforeIdx;
	}

	/** @return 참여자 정보 조회 가능 여부 */
	public Boolean hasResponderInfo() {
		return getDupType().equals(PollDupType.REALNAME.getCode());
	}

	/** @return 추첨 가능 여부 */
	public Boolean isLottable() {
		return getLotDate() == null && "Y".equals(getLotteryYn()) && getLotteryNum() > 0;
	}

	@Override
	public String toString() {
		return "PollMngVO [idx=" + idx + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate + ", siteCode=" + siteCode + ", dupType=" + dupType + ", joinTarget=" + joinTarget + ", joinGender=" + joinGender + ", joinAgeYn=" + joinAgeYn + ", joinStAge=" + joinStAge + ", joinEdAge=" + joinEdAge + ", lotteryYn=" + lotteryYn + ", lotteryNum=" + lotteryNum + ", reservNum=" + reservNum + ", publicYn=" + publicYn + ", header=" + header + ", headerFile=" + headerFile + ", footer=" + footer + ", mainFile=" + mainFile + ", stateYn=" + stateYn + ", viewYn=" + viewYn + ", questionYn=" + questionYn + ", isDel=" + isDel + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + ", userCnt=" + userCnt + ", siteName=" + siteName + ", stateFlag=" + stateFlag + ", searchState=" + searchState + ", searchViewYn=" + searchViewYn + ", searchSiteCode=" + searchSiteCode + ", headerFileCn=" + headerFileCn + ", mainFileCn=" + mainFileCn + "]";
	}

}