package egovframework.portal.sys.bbs.vo;

import java.io.Serializable;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import com.ibm.icu.text.SimpleDateFormat;

import egovframework.portal.bbs.BbsType;
import egovframework.portal.bbs.service.impl.AdapterCDATA;
import egovframework.portal.common.NuriType;
import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.dept.vo.DeptVO;
import egovframework.portal.sys.bbs.BoardState;
import egovframework.portal.sys.sysAuth.AuthType;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.SecurityUtil;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.UserUtil;
import egovframework.portal.validation.ByteSize;
import net.arnx.jsonic.JSONHint;

/**
 * 관리자용 공통 게시판 VO
 * 참고 TABLE: PT_BBS_BOARD
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2017.06.08		권태성				사용자단 VO 병합, 캐시 처리
 * 2017.06.14		J.Ryeon Lee			컬럼 정비 및 미사용 컬럼 삭제 및 수정
 * 2017.06.30		권태성				MyBatis 캐시 처리를 위해 attachId 변수 추가
 * </pre>
 *
 * @author ?
 * @since ?
 */
public class BbsMngVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 4691964437017835157L;

	/** 게시판 마스터 */
	private BbsConfigVO config = new BbsConfigVO();

	/** 게시판 IDX */
	private String bIdx = "";
	/** 속성 IDX */
	private String ptIdx = "";
	/** 부모 IDX */
	private String bSame = "";
	/** 정렬 순서 */
	private String bSort = "";
	/** 단계 */
	private String bLevel = "";
	/** 작성자(암호화) */
	private String bWrite = "";
	/** 작성자 ID */
	private String writeId = "";
	/** 개인 코드 */
	private String bPrivatecode = "";
	/** 비밀번호 */
	@NotEmpty
	@ByteSize(max = 100)
	private String bPassword = "";
	/** 제목 */
	@NotBlank
	@ByteSize(max = 500)
	private String bTitle = "";
	/** 내용 */
	@NotBlank
	private String bContent = "";
	/** 조회수 */
	private int bCnt = 0;
	/** 전화번호(암호화) */
	@RegExp(value = "[0-9]{2,4}-[0-9]{3,4}-[0-9]{4}")
	private String bPhone = "";
	/** 커스텀 카테고리 */
	private String bCategory = "";
	/** 부서 카테고리(부서명) */
	private String bDeptNm = "";
	/** 부서 카테고리(코드값) */
	private String bDeptGroup = "";
	/** 주소 */
	@ByteSize(max = 200)
	private String bAddr1 = "";
	/** 상세 주소 */
	@ByteSize(max = 300)
	private String bAddr2 = "";
	/** 관리자 답변 여부 */
	private String bReplyAdmin = "";
	/** 공개 여부 */
	@RegExp(value = "[Y|N]")
	private String bPublic = "";
	/** 공지 여부 */
	@RegExp(value = "[Y|N]")
	private String bNotice = "";
	/** 메인 노출 여부 */
	private String bMainYn = "";
	/** 공공누리 마크 부착 여부 */
	private String bNuriType = "";
	/** 개인정보 수집(필수) */
	@RegExp(value = "[Y]")
	private String privacyYn = "";
	/** 개인정보 수집(선택) */
	@RegExp(value = "[Y]")
	private String optPrivacyYn = "";
	/** 작성자 부서코드 */
	private String bWriteDeptId = "";
	/** 작성자 부서명 */
	private String bWriteDeptNm = "";
	/** 작성자 연락처 */
	private String bWriteDeptTel = "";
	/** 작성자 IP(암호화) */
	private String bHostIp = "";
	/** 게시글 상태 {@link BoardState} */
	private int bState = -1;
	/** 게시기간 시작일 */
	private String bTermSdate = "";
	private String bTermStime = "";
	/** 게시기간 종료일 */
	private String bTermEdate = "";
	private String bTermEtime = "";
	/** 공지기간 시작일 */
	private String bNtermSdate = "";
	/** 공지기간 종료일 */
	private String bNtermEdate = "";

	/** 분류(달력형게시판) */
	private String bClass = "";
	/** 장소(달력형게시판) */
	private String bPlace = "";
	/** 주관(달력형게시판) */
	private String bOrganize = "";
	/** 시작일자(달력형게시판) */
	private String bSdate = "";
	/** 시작시간(달력형게시판) */
	private String bStime = "";
	/** 종료일자(달력형게시판) */
	private String bEdate = "";
	/** 종료시간(달력형게시판) */
	private String bEtime = "";
	/** 노출유무 */
	private String showYn = "";

	/** 삭제 여부 */
	private String isDel = "";
	/** 등록일 */
	private Date createDate = null;
	/** 수정일 */
	private Date updateDate = null;
	/** 삭제일 */
	private Date deleteDate = null;

	/* ### PROCESS ### */

	/**  */
	private int fileCnt = 0;
	/** 비밀번호 암호화용 */
	private String encPasswd = "";
	/** sitecode (MAIN SERVICE USE) */
	private String siteCode = "";
	/**  */
	private int replyCnt = 0;
	/**  */
	private String bodFileExt = "";
	/** 확장자 목록 */
	private HashSet<String> fileExts = new HashSet<>();
	/**  */
	private List<String> ext_list = null;
	/** 이동할 게시판 */
	private String aftAdIdx = "";
	/** 이동전 게시판 idx */
	private String preBidx = "";
	/** 코멘트 개수 */
	private String cmtCnt = "";
	/** 암호 확인 후 처리 프로세스 분기 플래그(del: 삭제, edit: 수정) */
	private String bbsMode = "";
	/**  */
	private String bCategoryNm = "";
	/**  */
	private String searchMode = "";
	/**  */
	private String loadUrl = "";
	/**  */
	private String newMode = "";
	/** 일괄삭제용 @author 상천규 */
	private List<String> delChecked = new ArrayList<>();

	/* ### 검색 키워드용 ### */

	/** 삭제 여부 */
	private String searchDelete = "";
	/** 카테고리 */
	private String searchCategory = "";
	/** 부서 */
	private String searchDept = "";
	/** DI */
	private String searchPrivatecode = "";
	/** 메인 노출 여부 */
	private String searchMainYn = "";
	/** 공개글 여부 */
	private String searchPublicYn = "";
	/** 레벨 */
	private String searchLevel = "";
	/** 부서 목록 */
	private List<DeptVO> searchDeptList = new ArrayList<>();
	/** 첨부파일 Id */
	private String attachId = "";
	/** 게시글 검색시 공지글 포함 여부 */
	private String containNotiYn = "";
	
	/* ##### 문자서비스용 ##### */
	/** 문자서비스 발송 여부*/
	@RegExp(value = "[Y|N]")
	private String smsYN = "";
	/** 문자서비스 분양공고 임대공고 구분값*/
	private String smsGbn = "";
	/** 문자서비스 메시지*/
	private String smsMsg = "";
	/** 문자서비스 발신 전화번호*/
	private String trCallback = "";
	/** 문자서비스 수신 전화번호*/
	private String trPhone = "";
	/** 문자 체크*/
	/*private String usesCk = "";*/
	private String smsOptions = "";
	/*문자제목*/
	private String smsSubject = "";
	/*현재 게시글 이전 게시물 idx*/
	private String preIdx = "";
	/*현재 게시글 이후 게시물 idx*/
	private String nextIdx = "";
	/*작성 관련부서*/
	private String rltdDeptNm = "";
	/*게시글 문의처*/
	private String bEnquiry = "";
	/*유튜브 영상 ID*/
	private String bYoutube = "";
	/* 순차발송 관련 파라미터 */
	private int checkIndex = 0;
	
	/*메인 게시글 등록*/
	@RegExp(value = "[Y|N]")
	private String MainNotice = "";
	
	private int MainOrder = 0;
	private int exOrder = 0;
	
	public int getExOrder() {
		return exOrder;
	}

	public void setExOrder(int exOrder) {
		this.exOrder = exOrder;
	}

	public String getMainNotice() {
		return MainNotice;
	}

	public void setMainNotice(String mainNotice) {
		MainNotice = mainNotice;
	}

	public int getMainOrder() {
		return MainOrder;
	}

	public void setMainOrder(int mainOrder) {
		MainOrder = mainOrder;
	}

	public BbsMngVO() {
		// default
	}

	public BbsMngVO(String bIdx) {
		setbIdx(bIdx);
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public BbsConfigVO getConfig() {
		return config;
	}

	public void setConfig(BbsConfigVO config) {
		this.config = config;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getbIdx() {
		return bIdx;
	}

	public void setbIdx(String bIdx) {
		this.bIdx = bIdx;
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbSame() {
		return bSame;
	}

	public void setbSame(String bSame) {
		this.bSame = bSame;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbSort() {
		return bSort;
	}

	public void setbSort(String bSort) {
		this.bSort = bSort;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbLevel() {
		return bLevel;
	}

	public void setbLevel(String bLevel) {
		this.bLevel = bLevel;
	}

	public String getbWrite() {
		return bWrite;
	}

	public void setbWrite(String bWrite) {
		this.bWrite = bWrite;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbPrivatecode() {
		return bPrivatecode;
	}

	public void setbPrivatecode(String bPrivatecode) {
		this.bPrivatecode = bPrivatecode;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbPassword() {
		return bPassword;
	}

	public void setbPassword(String bPassword) {
		this.bPassword = bPassword;
		setEncPasswd(StringUtil.isNotEmpty(bPassword) ? SecurityUtil.encrypt(bPassword) : "");
	}

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	@XmlJavaTypeAdapter(AdapterCDATA.class)
	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbHostIp() {
		return bHostIp;
	}

	public void setbHostIp(String bHostIp) {
		this.bHostIp = bHostIp;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbPublic() {
		return bPublic;
	}

	public void setbPublic(String bPublic) {
		this.bPublic = bPublic;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbNotice() {
		return bNotice;
	}

	public void setbNotice(String bNotice) {
		this.bNotice = bNotice;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getEncPasswd() {
		return encPasswd;
	}

	public void setEncPasswd(String encPasswd) {
		this.encPasswd = encPasswd;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbDeptNm() {
		return bDeptNm;
	}

	public void setbDeptNm(String bDeptNm) {
		this.bDeptNm = StringUtil.isNotEmpty(bDeptNm) ? bDeptNm.trim() : "";
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getWriteId() {
		return writeId;
	}

	public void setWriteId(String writeId) {
		this.writeId = writeId;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbNuriType() {
		return bNuriType;
	}

	public void setbNuriType(String bNuriType) {
		this.bNuriType = bNuriType;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbWriteDeptId() {
		return bWriteDeptId;
	}

	public void setbWriteDeptId(String bWriteDeptId) {
		this.bWriteDeptId = bWriteDeptId;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbWriteDeptNm() {
		return bWriteDeptNm;
	}

	public void setbWriteDeptNm(String bWriteDeptNm) {
		this.bWriteDeptNm = bWriteDeptNm;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbWriteDeptTel() {
		return bWriteDeptTel;
	}

	public void setbWriteDeptTel(String bWriteDeptTel) {
		this.bWriteDeptTel = bWriteDeptTel;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSearchDelete() {
		return searchDelete;
	}

	public void setSearchDelete(String searchDelete) {
		this.searchDelete = searchDelete;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String cDate) throws ParseException {
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createDate = transFormat.parse(cDate);
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public int getbCnt() {
		return bCnt;
	}

	public void setbCnt(int bCnt) {
		this.bCnt = bCnt;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getBodFileExt() {
		return bodFileExt;
	}

	public void setBodFileExt(String bodFileExt) {
		this.bodFileExt = bodFileExt;
		if (StringUtil.isNotBlank(bodFileExt)) {
			setFileExts(new HashSet<>(Arrays.asList(bodFileExt.split(","))));
		}
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public HashSet<String> getFileExts() {
		return fileExts;
	}

	public void setFileExts(HashSet<String> hashSet) {
		this.fileExts = hashSet;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public List<String> getExt_list() {
		return ext_list;
	}

	public void setExt_list(List<String> ext_list) {
		this.ext_list = ext_list;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public int getReplyCnt() {
		return replyCnt;
	}

	public void setReplyCnt(int replyCnt) {
		this.replyCnt = replyCnt;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getAftAdIdx() {
		return aftAdIdx;
	}

	public void setAftAdIdx(String aftAdIdx) {
		this.aftAdIdx = aftAdIdx;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getPreBidx() {
		return preBidx;
	}

	public void setPreBidx(String preBidx) {
		this.preBidx = preBidx;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbCategory() {
		return bCategory;
	}

	public void setbCategory(String bCategory) {
		this.bCategory = bCategory;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSearchDept() {
		return searchDept;
	}

	public void setSearchDept(String searchDept) {
		this.searchDept = searchDept;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbPhone() {
		return bPhone;
	}

	public void setbPhone(String bPhone) {
		this.bPhone = bPhone;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbMainYn() {
		return bMainYn;
	}

	public void setbMainYn(String bMainYn) {
		this.bMainYn = bMainYn;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbDeptGroup() {
		return bDeptGroup;
	}

	public void setbDeptGroup(String bDeptGroup) {
		this.bDeptGroup = bDeptGroup;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbAddr1() {
		return bAddr1;
	}

	public void setbAddr1(String bAddr1) {
		this.bAddr1 = bAddr1;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbAddr2() {
		return bAddr2;
	}

	public void setbAddr2(String bAddr2) {
		this.bAddr2 = bAddr2;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getCmtCnt() {
		return cmtCnt;
	}

	public void setCmtCnt(String cmtCnt) {
		this.cmtCnt = cmtCnt;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getPrivacyYn() {
		return privacyYn;
	}

	public void setPrivacyYn(String privacyYn) {
		this.privacyYn = privacyYn;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getOptPrivacyYn() {
		return optPrivacyYn;
	}

	public void setOptPrivacyYn(String optPrivacyYn) {
		this.optPrivacyYn = optPrivacyYn;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public int getbState() {
		return bState;
	}

	public void setbState(int bState) {
		this.bState = bState;
	}


	@XmlTransient
	@JSONHint(ignore = true)
	public String getbTermSdate() {
		return bTermSdate;
	}

	public void setbTermSdate(String bTermSdate) {
		this.bTermSdate = bTermSdate;
	}
	

	public String getbTermStime() {
		return bTermStime;
	}

	public void setbTermStime(String bTermStime) {
		this.bTermStime = bTermStime;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbTermEdate() {
		return bTermEdate;
	}

	public void setbTermEdate(String bTermEdate) {
		this.bTermEdate = bTermEdate;
	}


	public String getbTermEtime() {
		return bTermEtime;
	}

	public void setbTermEtime(String bTermEtime) {
		this.bTermEtime = bTermEtime;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbNtermSdate() {
		return bNtermSdate;
	}

	public void setbNtermSdate(String bNtermSdate) {
		this.bNtermSdate = bNtermSdate;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbNtermEdate() {
		return bNtermEdate;
	}

	public void setbNtermEdate(String bNtermEdate) {
		this.bNtermEdate = bNtermEdate;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbClass() {
		return bClass;
	}

	public void setbClass(String bClass) {
		this.bClass = bClass;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbPlace() {
		return bPlace;
	}

	public void setbPlace(String bPlace) {
		this.bPlace = bPlace;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbOrganize() {
		return bOrganize;
	}

	public void setbOrganize(String bOrganize) {
		this.bOrganize = bOrganize;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbSdate() {
		return bSdate;
	}

	public void setbSdate(String bSdate) {
		this.bSdate = bSdate;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbStime() {
		return bStime;
	}

	public void setbStime(String bStime) {
		this.bStime = bStime;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbEdate() {
		return bEdate;
	}

	public void setbEdate(String bEdate) {
		this.bEdate = bEdate;
	}

	public String getbEtime() {
		return bEtime;
	}

	public void setbEtime(String bEtime) {
		this.bEtime = bEtime;
	}

	public String getShowYn() {
		return showYn;
	}

	public void setShowYn(String showYn) {
		this.showYn = showYn;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbReplyAdmin() {
		return bReplyAdmin;
	}

	public void setbReplyAdmin(String bReplyAdmin) {
		this.bReplyAdmin = bReplyAdmin;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getBbsMode() {
		return bbsMode;
	}

	public void setBbsMode(String bbsMode) {
		this.bbsMode = bbsMode;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getbCategoryNm() {
		return bCategoryNm;
	}

	public void setbCategoryNm(String bCategoryNm) {
		this.bCategoryNm = bCategoryNm;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getLoadUrl() {
		return loadUrl;
	}

	public void setLoadUrl(String loadUrl) {
		this.loadUrl = loadUrl;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getNewMode() {
		return newMode;
	}

	public void setNewMode(String newMode) {
		this.newMode = newMode;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSearchPrivatecode() {
		return searchPrivatecode;
	}

	public void setSearchPrivatecode(String searchPrivatecode) {
		this.searchPrivatecode = searchPrivatecode;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSearchMainYn() {
		return searchMainYn;
	}

	public void setSearchMainYn(String searchMainYn) {
		this.searchMainYn = searchMainYn;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSearchPublicYn() {
		return searchPublicYn;
	}

	public void setSearchPublicYn(String searchPublicYn) {
		this.searchPublicYn = searchPublicYn;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getSearchLevel() {
		return searchLevel;
	}

	public void setSearchLevel(String searchLevel) {
		this.searchLevel = searchLevel;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public List<DeptVO> getSearchDeptList() {
		return searchDeptList;
	}

	public void setSearchDeptList(List<DeptVO> searchDeptList) {
		this.searchDeptList = searchDeptList;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public void setOnlybContent(String bContent) {
		this.bContent = bContent;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public List<String> getDelChecked() {
		return delChecked;
	}

	public void setDelChecked(List<String> delChecked) {
		this.delChecked = delChecked;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	@XmlTransient
	@JSONHint(ignore = true)
	public String getContainNotiYn() {
		return containNotiYn;
	}

	public void setContainNotiYn(String containNotiYn) {
		this.containNotiYn = containNotiYn;
	}

	/* =================================== LOGIC =================================== */

	public Boolean isNotModitable(AdminLoginVO admin, BbsConfigVO bbsConfigVO) {
		return !isModitable(admin, bbsConfigVO);
	}

	public Boolean isModitable(AdminLoginVO admin, BbsConfigVO bbsConfigVO) {
		if (admin.getAdminAccessLevelCode() == AuthType.SUPER.getCode()) { // 최고 관리자는 모든 프로세스 권한을 가짐
			return Boolean.TRUE;
		} else if (StringUtil.isNotBlank(bbsConfigVO.getPtMngIds()) ) { // 게시판 관리자 확인
			String[] adminList = bbsConfigVO.getPtMngIds().split(",");
			for (String adminId : adminList) {
				if(admin.getAdminId().equals(adminId.trim())){
					return Boolean.TRUE;
				}
			}
		} else if (StringUtil.isNotBlank(getbPrivatecode())) { // 일반 사용자가 작성한 글
			return Boolean.TRUE;
		} else if (StringUtil.isBlank(getWriteId())) { // 마이그레이션
			return Boolean.FALSE;
		} else if (getWriteId().equals(admin.getAdminId())) { // 본인이 작성한 글
			return Boolean.TRUE;
		}

		return false;
	}

	public void setDefaultValue(BbsConfigVO config) {
		BbsType type = BbsType.toType(config.getPtType());
		switch (type) {
			case FAQ:
				setbPublic("Y");
				break;
			case BASIC:
				if ("N".equals(config.getPtPublicYn())) { // 공개 설정 미사용
					setbPublic("Y");
				}
				break;
			case PHOTO:
				setbPublic("Y");
				break;
			case MOVIE:
				setbPublic("Y");
				break;
			case BLOG:
				setbPublic("Y");
				break;
			case MINWON:
				setbPublic("N");
				break;
			case DOWNLOAD:
				setbPublic("Y");
				break;
			default:
				break;
		}

		if (StringUtil.isBlank(getbPublic()) || getbPublic().equals("Y")) {
			setbPublic("Y");
		} else {
			setbPublic("N");
		}

		if (StringUtil.isBlank(getbNotice()) || getbNotice().equals("N")) {
			setbNotice("N");
		} else {
			setbNotice("Y");
		}
		
		if (StringUtil.isBlank(getMainNotice()) || getMainNotice().equals("N")) {
			setMainNotice("N");
		} else {
			setMainNotice("Y");
		}

		if (StringUtil.isBlank(getbNuriType())) {
			setbNuriType(NuriType.NO_USE.getCode());
		}

		if (StringUtil.isNotBlank(getbMainYn())) {
//			switch (getbMainYn()) {
//				case "1": // 부서만
//					setbMainYn("N");
//					break;
//				case "2": // 대표만
//					setbMainYn("Y");
//					setbDeptGroup(null);
//					break;
//				default: // 대표+부서
//					setbMainYn("Y");
//					break;
//			}
		} else {
			setbMainYn("Y");
		}
	}

	public void setSessionInfo() {
		UserVO user = UserUtil.getInstance();
		setbWrite(user.getUserName());
		setbPrivatecode(user.getPrivatecode());
	}

	public void setAdminInfo(AdminLoginVO admin) {
		setbWrite(admin.getName());
		setWriteId(admin.getAdminId());
		setbWriteDeptId(admin.getDeptId());
		setbWriteDeptNm(admin.getDeptName());
		setbWriteDeptTel(admin.getTel());
		
	}

	/** @return true: 현재 인증된 사용자가 작성한 글, false: 관리자 또는 다른 사용자가 작성한 글 */
	public Boolean isWritedBy(UserVO user) {
		String di = user != null ? user.getPrivatecode() : null;
		return StringUtil.noBlankValue(di, getbPrivatecode()) && user.getPrivatecode().equals(getbPrivatecode());
	}

	/** @return true: 관리자 본인이 작성한 글, false: 다른 관리자 또는 사용자가 작성한 글 */
	public Boolean isMyArticle(AdminLoginVO admin) {
		if (StringUtil.isNotEmpty(getbPrivatecode())) { // 사용자 작성 글
			return Boolean.FALSE;
		} else { // 관리자 작성글
			return getWriteId().equals(admin.getAdminId());
		}
	}

	@Override
	public String toString() {
		return "BbsMngVO [config=" + config + ", bIdx=" + bIdx + ", ptIdx=" + ptIdx + ", bSame=" + bSame + ", bSort=" + bSort + ", bLevel=" + bLevel + ", bWrite=" + bWrite + ", writeId=" + writeId + ", bPrivatecode=" + bPrivatecode + ", bPassword=" + bPassword + ", bTitle=" + bTitle + ", bContent=" + bContent + ", bCnt=" + bCnt + ", bPhone=" + bPhone + ", bCategory=" + bCategory + ", bDeptNm=" + bDeptNm + ", bDeptGroup=" + bDeptGroup + ", bAddr1=" + bAddr1 + ", bAddr2=" + bAddr2 + ", bReplyAdmin=" + bReplyAdmin + ", bPublic=" + bPublic + ", bNotice=" + bNotice + ", bMainYn=" + bMainYn + ", bNuriType=" + bNuriType + ", privacyYn=" + privacyYn + ", optPrivacyYn=" + optPrivacyYn + ", bWriteDeptId=" + bWriteDeptId + ", bWriteDeptNm=" + bWriteDeptNm + ", bWriteDeptTel=" + bWriteDeptTel + ", bHostIp=" + bHostIp + ", bState=" + bState + ", isDel=" + isDel + ", createDate=" + createDate + ", updateDate=" + updateDate + ", deleteDate=" + deleteDate + ", fileCnt=" + fileCnt + ", encPasswd=" + encPasswd + ", siteCode=" + siteCode + ", replyCnt=" + replyCnt + ", bodFileExt=" + bodFileExt + ", ext_list=" + ext_list + ", aftAdIdx=" + aftAdIdx + ", preBidx=" + preBidx + ", cmtCnt=" + cmtCnt + ", bbsMode=" + bbsMode + ", bCategoryNm=" + bCategoryNm + ", searchMode=" + searchMode + ", loadUrl=" + loadUrl + ", newMode=" + newMode + ", delChecked=" + delChecked + ", searchDelete=" + searchDelete + ", searchCategory=" + searchCategory + ", searchDept=" + searchDept + ", searchPrivatecode=" + searchPrivatecode + ", searchMainYn=" + searchMainYn + ", searchPublicYn=" + searchPublicYn + ", searchLevel=" + searchLevel + ", searchDeptList=" + searchDeptList + ", attachId=" + attachId + "]";
	}

	public BbsType getType()
	{
		BbsType type = BbsType.toType(config.getPtType());
		return type;
	}

	public String getSmsYN() {
		return smsYN;
	}

	public void setSmsYN(String smsYN) {
		this.smsYN = smsYN;
	}


	public String getSmsGbn() {
		return smsGbn;
	}

	public void setSmsGbn(String smsGbn) {
		this.smsGbn = smsGbn;
	}

	public String getSmsMsg() {
		return smsMsg;
	}

	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}


	public String getSmsOptions() {
		return smsOptions;
	}

	public void setSmsOptions(String smsOptions) {
		this.smsOptions = smsOptions;
	}

	public String getTrCallback() {
		return trCallback;
	}

	public void setTrCallback(String trCallback) {
		this.trCallback = trCallback;
	}

	public String getTrPhone() {
		return trPhone;
	}

	public void setTrPhone(String trPhone) {
		this.trPhone = trPhone;
	}

	public String getSmsSubject() {
		return smsSubject;
	}

	public void setSmsSubject(String smsSubject) {
		this.smsSubject = smsSubject;
	}

	public String getPreIdx() {
		return preIdx;
	}

	public void setPreIdx(String preIdx) {
		this.preIdx = preIdx;
	}

	public String getNextIdx() {
		return nextIdx;
	}

	public void setNextIdx(String nextIdx) {
		this.nextIdx = nextIdx;
	}

	public String getRltdDeptNm() {
		return rltdDeptNm;
	}

	public void setRltdDeptNm(String rltdDeptNm) {
		this.rltdDeptNm = rltdDeptNm;
	}

	public String getbEnquiry() {
		return bEnquiry;
	}

	public void setbEnquiry(String bEnquiry) {
		this.bEnquiry = bEnquiry;
	}

	public String getbYoutube() {
		return bYoutube;
	}

	public void setbYoutube(String bYoutube) {
		this.bYoutube = bYoutube;
	}

	public int getCheckIndex() {
		return checkIndex;
	}

	public void setCheckIndex(int checkIndex) {
		this.checkIndex = checkIndex;
	}
	
	
}