package egovframework.portal.sys.bbs.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import egovframework.portal.bbs.BbsType;
import egovframework.portal.common.CategoryGubunType;
import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.sys.MenuMng.SiteGubun;
import egovframework.portal.sys.bbs.ColType;
import egovframework.portal.util.CollectionUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.validation.NumberOnly;

/**
 * 공통 - 게시판 속성 조회 서비스를 위한 VO 클래스
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2014.09.25		김혜민				최초 생성 및 코딩
 * 2017.06.14		J.Ryeon Lee			코드 정비
 * </pre>
 *
 * @author 개발팀 김혜민
 * @since 2014.09.25
 * @version 1.0
 */
public class BbsConfigVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 4320607301261868059L;

	/** 속성IDX */
	private String ptIdx = "";
	/** 게시판명 */
	private String ptTitle = "";
	/** 사이트 코드 */
	private String ptSiteCode = "";
	/** 머릿말 */
	private String ptTopText = "";
	/** 게시판 타입 */
	private String ptType = "";
	/** 게시물 출력수 */
	private String ptPageSize = "";
	/** 규제 단어 */
	private String ptCheckWord = "";
	/** 접근 불가 IP */
	private String ptCheckIp = "";
	/** 글보기 옵션 */
	private String ptOutFields = "";
	/** 답변시 옵션 */
	private String ptOptionReply = "";
	/** 글쓰기 사용 권한: ptLevelInput1 + ptLevelInput2 값을 세팅. e.g. YY */
	private String ptLevelInput = "";
	/** 답변시 사용 권한: ptLevelReply1 + ptLevelReply2 값을 세팅. e.g. YY */
	private String ptLevelReply = "";
	/** 첨부 파일 갯수 */
	private String ptFileCnt = "";
	/** 첨부 파일 용량 제한 */
	@NumberOnly
	private String ptLimitFileSize = "";
	/** 삭제 여부 */
	private String isDel = "";
	/** 카테고리 사용여부 */
	private String ptCategoryYn = "";
	/** 직접입력시 카테고리 */
	private String ptCategory = "";
	/** 카테고리 구분 B(부서별 카테고리), W(직접 입력) */
	private String ptCategoryGubun = "";
	/** 답글 사용 여부 */
	private String ptReplyYn = "";
	/** 에디터 이용 여부 */
	private String ptEditYn = "";
	/** 비밀글 공개 여부 */
	private String ptPublicYn = "N";
	/** 코멘트 작성 여부 */
	private String ptCommentYn = "";
	/** 담당부서코드 목록 */
	private String ptMngDeptIds = "";
	/** 담당부서명 목록 */
	private String ptMngDeptNms = "";
	/** 담당자ID 목록 */
	private String ptMngIds = "";
	/** 담당자ID 목록 */
	private String ptMngNms = "";
	/** mId 체크 제외 */
	private String ptIsolateYn = "";
	/** 신고형 */
	private String ptReportYn = "";
	/** 글쓰기만 가능 게시판 (미구현) */
	private String ptWriteOnlyYn = "";
	/** RSS 지원 플래그 (Y: 지원, N: 미지원) */
	private String ptRssYn = "";
	/** 게시글 일괄 삭제 사용 여부 (Y: 사용, N: 미사용) */
	private String ptBundleDelYn = "";
	/** 게시글 기간설정 사용 여부 (Y: 사용, N: 미사용) */
	private String ptTermYn = "";
	/** 공지글 기간설정 사용 여부 (Y: 사용, N: 미사용) */
	private String ptNtermYn = "";
	/** 개인정보 수집 목적 */
	private String ptSavePurpose = "";
	/** 개인정보 필수 선택 컬럼 목록 */
	private String ptSaveCols = "";
	/** 개인정보 부가 선택 컬럼 목록 */
	private String ptSaveOptCols = "";
	/** 개인정보 보유 기간 */
	private String ptSavePeriod = "";
	/** 개인정보 수집 동의 문구 */
	private String ptSaveGuide = "";
	/** 게시글 제목 길이 제한 */
	@NumberOnly
	private String ptLimitTitLen = "";
	/** 등록일 */
	private Date createDate = null;
	/** 수정일 */
	private Date updateDate = null;
	/** 삭제일 */
	private Date deleteDate = null;

	/* ##### PROCESS ##### */

	/** {@link #ptSaveCols} 입력용 리스트 */
	private List<String> ptSaveColList = new ArrayList<>();
	/** {@link #ptSaveOptCols} 입력용 리스트 */
	private List<String> ptSaveOptColList = new ArrayList<>();
	/** 메뉴 카테고리 {@link SiteGubun} */
	private String menu_category = "";
	/** 답변시 사용 권한 관리자 flag (非영속화) */
	private String ptLevelReply1 = "";
	/** 답변시 사용 권한 사용자 flag (非영속화) */
	private String ptLevelReply2 = "";
	/** 글쓰기 사용 권한 관리자 flag (非영속화) */
	private String ptLevelInput1 = "";
	/** 글쓰기 사용 권한 사용자 flag (非영속화) */
	private String ptLevelInput2 = "";
	/** 사이트명(개인정보 취급 정보 등록시 필요) */
	private String siteName = "";

	/* ##### 검색 키워드용 ##### */

	/** 검색용 게시판 타입 */
	private String searchPtType = "";

	public BbsConfigVO() {
		// default
	}

	public BbsConfigVO(String ptIdx) {
		setPtIdx(ptIdx);
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public String getPtTitle() {
		return ptTitle;
	}

	public void setPtTitle(String ptTitle) {
		this.ptTitle = ptTitle;
	}

	public String getPtSiteCode() {
		return ptSiteCode;
	}

	public void setPtSiteCode(String ptSiteCode) {
		this.ptSiteCode = ptSiteCode;
	}

	public String getPtTopText() {
		return ptTopText;
	}

	public void setPtTopText(String ptTopText) {
		this.ptTopText = ptTopText;
	}

	public String getPtType() {
		return ptType;
	}

	public void setPtType(String ptType) {
		this.ptType = ptType;
	}

	public String getPtPageSize() {
		return ptPageSize;
	}

	public void setPtPageSize(String ptPageSize) {
		this.ptPageSize = ptPageSize;
	}

	public String getPtCheckWord() {
		return ptCheckWord;
	}

	public void setPtCheckWord(String ptCheckWord) {
		this.ptCheckWord = ptCheckWord;
	}

	public String getPtCheckIp() {
		return ptCheckIp;
	}

	public void setPtCheckIp(String ptCheckIp) {
		this.ptCheckIp = ptCheckIp;
	}

	public String getPtOutFields() {
		return ptOutFields;
	}

	public void setPtOutFields(String ptOutFields) {
		this.ptOutFields = ptOutFields;
	}

	public String getPtOptionReply() {
		return ptOptionReply;
	}

	public void setPtOptionReply(String ptOptionReply) {
		this.ptOptionReply = ptOptionReply;
	}

	public String getPtLevelInput() {
		return ptLevelInput;
	}

	public void setPtLevelInput(String ptLevelInput) {
		this.ptLevelInput = ptLevelInput;
	}

	public String getPtLevelReply() {
		return ptLevelReply;
	}

	public void setPtLevelReply(String ptLevelReply) {
		this.ptLevelReply = ptLevelReply;
	}

	public String getPtFileCnt() {
		return ptFileCnt;
	}

	public void setPtFileCnt(String ptFileCnt) {
		this.ptFileCnt = ptFileCnt;
	}

	public String getPtLimitFileSize() {
		return ptLimitFileSize;
	}

	public void setPtLimitFileSize(String ptLimitFileSize) {
		this.ptLimitFileSize = ptLimitFileSize;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getPtCategoryYn() {
		return ptCategoryYn;
	}

	public void setPtCategoryYn(String ptCategoryYn) {
		this.ptCategoryYn = ptCategoryYn;
	}

	public String getPtCategory() {
		return ptCategory;
	}

	public void setPtCategory(String ptCategory) {
		this.ptCategory = ptCategory;
	}

	public String getPtCategoryGubun() {
		return ptCategoryGubun;
	}

	public void setPtCategoryGubun(String ptCategoryGubun) {
		this.ptCategoryGubun = ptCategoryGubun;
	}

	public String getPtReplyYn() {
		return ptReplyYn;
	}

	public void setPtReplyYn(String ptReplyYn) {
		this.ptReplyYn = ptReplyYn;
	}

	public String getPtEditYn() {
		return ptEditYn;
	}

	public void setPtEditYn(String ptEditYn) {
		this.ptEditYn = ptEditYn;
	}

	public String getPtPublicYn() {
		return ptPublicYn;
	}

	public void setPtPublicYn(String ptPublicYn) {
		this.ptPublicYn = ptPublicYn;
	}

	public String getPtCommentYn() {
		return ptCommentYn;
	}

	public void setPtCommentYn(String ptCommentYn) {
		this.ptCommentYn = ptCommentYn;
	}

	public String getPtMngDeptIds() {
		return ptMngDeptIds;
	}

	public void setPtMngDeptIds(String ptMngDeptIds) {
		this.ptMngDeptIds = ptMngDeptIds;
	}

	public String getPtMngDeptNms() {
		return ptMngDeptNms;
	}

	public void setPtMngDeptNms(String ptMngDeptNms) {
		this.ptMngDeptNms = ptMngDeptNms;
	}

	public String getPtMngIds() {
		return ptMngIds;
	}

	public void setPtMngIds(String ptMngIds) {
		this.ptMngIds = ptMngIds;
	}

	public String getPtMngNms() {
		return ptMngNms;
	}

	public void setPtMngNms(String ptMngNms) {
		this.ptMngNms = ptMngNms;
	}

	public String getPtIsolateYn() {
		return ptIsolateYn;
	}

	public void setPtIsolateYn(String ptIsolateYn) {
		this.ptIsolateYn = ptIsolateYn;
	}

	public String getPtReportYn() {
		return ptReportYn;
	}

	public void setPtReportYn(String ptReportYn) {
		this.ptReportYn = ptReportYn;
	}

	public String getPtWriteOnlyYn() {
		return ptWriteOnlyYn;
	}

	public void setPtWriteOnlyYn(String ptWriteOnlyYn) {
		this.ptWriteOnlyYn = ptWriteOnlyYn;
	}

	public String getPtSavePeriod() {
		return ptSavePeriod;
	}

	public void setPtSavePeriod(String ptSavePeriod) {
		this.ptSavePeriod = ptSavePeriod;
	}

	public String getPtRssYn() {
		return ptRssYn;
	}

	public void setPtRssYn(String ptRssYn) {
		this.ptRssYn = ptRssYn;
	}

	public String getPtBundleDelYn() {
		return ptBundleDelYn;
	}

	public void setPtBundleDelYn(String ptBundleDelYn) {
		this.ptBundleDelYn = ptBundleDelYn;
	}

	public String getPtTermYn() {
		return ptTermYn;
	}

	public void setPtTermYn(String ptTermYn) {
		this.ptTermYn = ptTermYn;
	}

	public String getPtNtermYn() {
		return ptNtermYn;
	}

	public void setPtNtermYn(String ptNtermYn) {
		this.ptNtermYn = ptNtermYn;
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

	public String getMenu_category() {
		return menu_category;
	}

	public void setMenu_category(String menu_category) {
		this.menu_category = menu_category;
	}

	public String getPtLevelReply1() {
		return ptLevelReply1;
	}

	public void setPtLevelReply1(String ptLevelReply1) {
		this.ptLevelReply1 = ptLevelReply1;
	}

	public String getPtLevelReply2() {
		return ptLevelReply2;
	}

	public void setPtLevelReply2(String ptLevelReply2) {
		this.ptLevelReply2 = ptLevelReply2;
	}

	public String getPtLevelInput1() {
		return ptLevelInput1;
	}

	public void setPtLevelInput1(String ptLevelInput1) {
		this.ptLevelInput1 = ptLevelInput1;
	}

	public String getPtLevelInput2() {
		return ptLevelInput2;
	}

	public void setPtLevelInput2(String ptLevelInput2) {
		this.ptLevelInput2 = ptLevelInput2;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSearchPtType() {
		return searchPtType;
	}

	public void setSearchPtType(String searchPtType) {
		this.searchPtType = searchPtType;
	}

	/* =================================== LOGIC =================================== */

	public String getPtSavePurpose() {
		return ptSavePurpose;
	}

	public void setPtSavePurpose(String ptSavePurpose) {
		this.ptSavePurpose = ptSavePurpose;
	}

	public String getPtSaveCols() {
		return ptSaveCols;
	}

	public void setPtSaveCols(String ptSaveCols) {
		this.ptSaveCols = ptSaveCols;
		if (StringUtil.isNotBlank(ptSaveCols)) {
			setPtSaveColList(Arrays.asList(ptSaveCols.split(",")));
		}
	}

	public String getPtSaveOptCols() {
		return ptSaveOptCols;
	}

	public void setPtSaveOptCols(String ptSaveOptCols) {
		this.ptSaveOptCols = ptSaveOptCols;
		if (StringUtil.isNotBlank(ptSaveOptCols)) {
			setPtSaveOptColList(Arrays.asList(ptSaveOptCols.split(",")));
		}
	}

	public String getPtSaveGuide() {
		return ptSaveGuide;
	}

	public void setPtSaveGuide(String ptSaveGuide) {
		this.ptSaveGuide = ptSaveGuide;
	}

	public String getPtLimitTitLen() {
		return ptLimitTitLen;
	}

	public void setPtLimitTitLen(String ptLimitTitLen) {
		this.ptLimitTitLen = ptLimitTitLen;
	}

	public List<String> getPtSaveColList() {
		return ptSaveColList;
	}

	public void setPtSaveColList(List<String> ptSaveColList) {
		this.ptSaveColList = ptSaveColList;
	}

	public List<String> getPtSaveOptColList() {
		return ptSaveOptColList;
	}

	public void setPtSaveOptColList(List<String> ptSaveOptColList) {
		this.ptSaveOptColList = ptSaveOptColList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** @return true: 게시판 타입이 arg와 동일 */
	public Boolean typeIs(BbsType type) {
		return StringUtil.isNotBlank(getPtType()) && type.getCode().equals(getPtType());
	}

	/** 커스텀 카테고리 목록 */
	public List<String> getCustomCategoryList() {
		if ("Y".equals(getPtCategoryYn()) && CategoryGubunType.useCustomCategory(getPtCategoryGubun())) {
			String[] category = StringUtil.isNotBlank(getPtCategory()) //
				? getPtCategory().trim().split("\n") : new String[0];
			return Arrays.asList(category);
		}

		return new ArrayList<>();
	}

	/** @return true: 답글/답변 등록 가능 */
	public Boolean isReplable(Boolean isAdmin) {
		switch (BbsType.toType(getPtType())) {
		case MINWON:
//				if (UserUtil.getInstance() == null) { // 사용자
//					return "Y".equals(getPtLevelReply().substring(1, 2));
//				} else { // 관리자
//					return "Y".equals(getPtLevelReply().substring(0, 1));
//				}
			return isAdmin; // 답변글은 오직 관리자만 등록 가능하도록 수정
		default:
			return "Y".equals(getPtReplyYn());
		}
	}

	public void setDefaultValues() {
		BbsType bbsType = BbsType.toType(getPtType());

		if (StringUtil.isBlank(getPtReplyYn()) || "N".equals(getPtReplyYn())) { // 답글 사용 여부
			setPtReplyYn("N");
		} else {
			setPtReplyYn("Y");
		}

		setPtLevelReply("NN"); // 답변글 작성 권한

		if (StringUtil.isBlank(getPtEditYn()) || "N".equals(getPtEditYn())) { // 에디터 적용 여부
			setPtEditYn("N");
		} else {
			setPtEditYn("Y");
		}

		if (StringUtil.isNotBlank(getPtWriteOnlyYn()) && "Y".equals(getPtWriteOnlyYn())) { // 글쓰기만 지원 여부
			setPtWriteOnlyYn("Y");
		} else {
			setPtWriteOnlyYn("N");
		}

		if (StringUtil.isBlank(getPtReportYn()) || "N".equals(getPtReportYn())) { // 작성자 글 ONLY
			setPtReportYn("N");
		} else {
			setPtReportYn("Y");
		}

		if (StringUtil.isBlank(getPtCategoryYn()) || "N".equals(getPtCategoryYn())) { // 카테고리 사용 여부
			setPtCategoryYn("N");
		} else {
			setPtCategoryYn("Y");
		}
		if ("Y".equals(getPtCategoryYn()) && StringUtil.isEmpty(getPtCategoryGubun())) { // 카테고리 구분값
			setPtCategoryGubun("W");
		}

		if (StringUtil.isBlank(getPtPublicYn()) || "N".equals(getPtPublicYn())) { // 공개글 설정 여부
			setPtPublicYn("N");
		} else {
			setPtPublicYn("Y");
		}

		String defaultFileCnt = "10";
		if (!StringUtil.isNumber(getPtFileCnt())) { // 첨부 파일 허용 개수
			setPtFileCnt(defaultFileCnt);
		}

		if (!StringUtil.isNumber(getPtPageSize()) || "0".equals(getPtPageSize())) { // 페이지당 출력 게시물 건수
			setPtPageSize("10");
		}

		if (!StringUtil.isNumber(getPtSavePeriod())) { // 개인정보 보유 기간
			setPtSavePeriod("10");
		}

//		if (StringUtil.isBlank(getPtSavePurpose())) {
//			setPtSavePurpose("민원 접수 처리 및 사후 관리 서비스 제공");
//		}

		// 리스트로 입력받은 값을 DB에 인서트할 수 있도록 문자열로 변형
		if (getPtSaveColList() != null && getPtSaveColList().size() > 0) {
			setPtSaveCols(CollectionUtil.allElementToString(getPtSaveColList()));
		}
		if (getPtSaveOptColList() != null && getPtSaveOptColList().size() > 0) {
			setPtSaveOptCols(CollectionUtil.allElementToString(getPtSaveOptColList()));
		}

		if ("YN".equals(getPtLevelInput())) { // 개인정보 설정을 세팅할 필요가 없는 경우 해당 값을 초기화
			initPrivacySetting();
		}

		switch (bbsType) {
		case FAQ:
			setPtFileCnt("0");
			setPtPublicYn("N");
			setPtReportYn("N");
			setPtRssYn("N");
			setPtLevelInput("YN");
			initPrivacySetting();
			break;
		case PHOTO:
			if ("0".equals(getPtFileCnt())) {
				setPtFileCnt(defaultFileCnt);
			}
			setPtRssYn("N");
			setPtReportYn("N");
			setPtPublicYn("N");
			break;
		case MOVIE:
			if ("0".equals(getPtFileCnt())) {
				setPtFileCnt(defaultFileCnt);
			}
			setPtRssYn("N");
			setPtReportYn("N");
			setPtPublicYn("N");
			initPrivacySetting();
			break;
		case MINWON:
			setPtRssYn("N");
			setPtLevelReply("YN");
			setPtPublicYn("N");
			break;
		case DOWNLOAD:
			setPtRssYn("N");
			setPtLevelReply("YN");
			setPtPublicYn("N");
			break;
		case BLOG:
			setPtRssYn("N");
			setPtReportYn("N");
			setPtPublicYn("N");
			break;
		default:
			break;
		}

		if (StringUtil.isBlank(getPtLimitFileSize())) {
			setPtLimitFileSize("10");
		}

		if (StringUtil.isBlank(getPtLimitTitLen())) {
			setPtLimitTitLen("200");
		}
	}

	/** 개인정보 취급 기간 설정 정보 초기화 */
	public void initPrivacySetting() {
		setPtSavePurpose(null);
		setPtSaveCols(null);
		setPtSaveOptCols(null);
		setPtSavePeriod(null);
		setPtSaveGuide(null);
		setPtSaveGuide(null);
	}

	/** @return 개인정보 컬럼명을 컴마로 연결 */
	public String getPtSaveColNames() {
		List<String> nameList = new ArrayList<>();
		nameList.add(ColType.NAME.getName());
		nameList.add(ColType.IP.getName());
		nameList.add(ColType.LOG.getName());

		if (StringUtil.isNotBlank(getPtSaveCols())) {
			for (String code : getPtSaveColList()) {
				nameList.add(ColType.toType(code).getName());
			}
		}
		if (StringUtil.isNotBlank(getPtSaveOptCols())) {
			for (String code : getPtSaveOptColList()) {
				nameList.add(ColType.toType(code).getName());
			}
		}

		return CollectionUtil.allElementToString(nameList);
	}

	/** @return 사용자 글쓰기 가능 여부 */
	public Boolean writableForUser() {
		BbsType type = BbsType.toType(getPtType());
		return type != BbsType.FAQ && type != BbsType.MOVIE && "YY".equals(getPtLevelInput());
	}

	/** @return 개인정보 취급 여부 */
	public Boolean includePrivacyData() {
		BbsType type = BbsType.toType(getPtType());
		return type != BbsType.FAQ && "YY".equals(getPtLevelInput());
	}

	@Override
	public String toString() {
		return "BbsConfigVO [ptIdx=" + ptIdx + ", ptTitle=" + ptTitle + ", ptSiteCode=" + ptSiteCode + ", ptTopText=" + ptTopText + ", ptType=" + ptType + ", ptPageSize=" + ptPageSize + ", ptCheckWord=" + ptCheckWord + ", ptCheckIp=" + ptCheckIp + ", ptOutFields=" + ptOutFields + ", ptOptionReply=" + ptOptionReply + ", ptLevelInput=" + ptLevelInput + ", ptLevelReply=" + ptLevelReply + ", ptFileCnt=" + ptFileCnt + ", ptLimitFileSize=" + ptLimitFileSize + ", isDel=" + isDel + ", ptCategoryYn=" + ptCategoryYn + ", ptCategory=" + ptCategory + ", ptCategoryGubun=" + ptCategoryGubun + ", ptReplyYn=" + ptReplyYn + ", ptEditYn=" + ptEditYn + ", ptPublicYn=" + ptPublicYn + ", ptCommentYn=" + ptCommentYn + ", ptMngDeptIds=" + ptMngDeptIds + ", ptMngDeptNms=" + ptMngDeptNms + ", ptMngIds=" + ptMngIds + ", ptMngNms=" + ptMngNms + ", ptIsolateYn=" + ptIsolateYn + ", ptReportYn=" + ptReportYn + ", ptWriteOnlyYn=" + ptWriteOnlyYn + ", ptRssYn=" + ptRssYn + ", ptBundleDelYn=" + ptBundleDelYn + ", ptTermYn=" + ptTermYn + ", ptNtermYn=" + ptNtermYn + ", ptSavePurpose=" + ptSavePurpose + ", ptSaveCols=" + ptSaveCols + ", ptSaveOptCols=" + ptSaveOptCols + ", ptSavePeriod=" + ptSavePeriod + ", ptSaveGuide=" + ptSaveGuide + ", ptLimitTitLen=" + ptLimitTitLen + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + ", ptSaveColList=" + ptSaveColList + ", ptSaveOptColList=" + ptSaveOptColList + ", menu_category=" + menu_category + ", ptLevelReply1=" + ptLevelReply1 + ", ptLevelReply2=" + ptLevelReply2 + ", ptLevelInput1=" + ptLevelInput1 + ", ptLevelInput2=" + ptLevelInput2 + ", siteName=" + siteName + ", searchPtType=" + searchPtType + "]";
	}
}
