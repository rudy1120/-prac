package egovframework.portal.bbs.vo;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import egovframework.portal.common.SessionKey;
import egovframework.portal.unit.common.UserType;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.SessionUtil;
import egovframework.portal.util.UserUtil;

/**
 * 게시글 코멘트 VO
 * 참조 TABLE: PT_BBS_COMMENT
 *
 * @author J.Ryeon Lee
 * @since 2016.03.08
 */
public class CommentVO {

	/** PK */
	private String idx = "";
	/** 게시판 PK */
	private String ptIdx = "";
	/** 게시글 PK */
	private String bIdx = "";
	/** 코멘트 내용 */
	private String cContent = "";
	/** 작성자명 */
	private String cWriter = "";
	/** 작성자 USER ID */
	private String writerId = "";
	/** DI */
	private String privatecode = "";
	/** 등록 HOST IP */
	private String hostIp = "";
	/** 삭제 플래그 (Y: 삭제, N: 사용) */
	private String isDel = "";
	/** 공개 플래그 (Y: 공개, N: 미공개) (20160308 기준 미구현) */
	private String isPublic = "";
	/** 등록일 */
	private Date createDate = null;
	/** 수정일 */
	private Date updateDate = null;
	/** 삭제일 */
	private Date deleteDate = null;
	/** 로그인 유저 종류 @see egovframework.portal.unit.common.UserType */
	private String userType = "";

	/* SNS 연동 관련 비영속화 필드 */

	/** facebook user id */
	private String fbUserId = "";
	/** facebook user name */
	private String fbUserName = "";
	/** twitter user id */
	private String twUserId = "";
	/** twitter user name */
	private String twUserName = "";

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getbIdx() {
		return bIdx;
	}

	public String getPtIdx() {
		return ptIdx;
	}

	public void setPtIdx(String ptIdx) {
		this.ptIdx = ptIdx;
	}

	public void setbIdx(String bIdx) {
		this.bIdx = bIdx;
	}

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
	}

	public String getcWriter() {
		return cWriter;
	}

	public void setcWriter(String cWriter) {
		this.cWriter = cWriter;
	}

	public String getWriterId() {
		return writerId;
	}

	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}

	public String getPrivatecode() {
		return privatecode;
	}

	public void setPrivatecode(String privatecode) {
		this.privatecode = privatecode;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
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

//	public void setUserInfo(Authentication authentication, String hostIp) {
//		User user = (User) authentication.getPrincipal();
//		setcWriter(user.getUserName());
//		setWriterId(user.getUserId());
//		setPrivatecode(user.getPrivatecode());
//		setHostIp(hostIp);
//	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getFbUserId() {
		return fbUserId;
	}

	public void setFbUserId(String fbUserId) {
		this.fbUserId = fbUserId;
	}

	public String getFbUserName() {
		return fbUserName;
	}

	public void setFbUserName(String fbUserName) {
		this.fbUserName = fbUserName;
	}

	public String getTwUserId() {
		return twUserId;
	}

	public void setTwUserId(String twUserId) {
		this.twUserId = twUserId;
	}

	public String getTwUserName() {
		return twUserName;
	}

	public void setTwUserName(String twUserName) {
		this.twUserName = twUserName;
	}

	public void setUserInfo(HttpServletRequest request) {
		UserType _userType = UserType.toType(SessionUtil.getSessionAttrAsString(request, SessionKey.USER_TYPE.getKey()));
		switch (_userType) {
			case FACEBOOK:
				setWriterId(SessionUtil.getSessionAttrAsString(request, SessionKey.FB_USER_ID.getKey()));
				setcWriter(SessionUtil.getSessionAttrAsString(request, SessionKey.FB_USER_NAME.getKey()));
				setUserType(_userType.getCode());
				break;
			case TWITTER:
				setWriterId(SessionUtil.getSessionAttrAsString(request, SessionKey.TW_USER_ID.getKey()));
				setcWriter(SessionUtil.getSessionAttrAsString(request, SessionKey.TW_USER_NAME.getKey()));
				setUserType(_userType.getCode());
				break;
			default:
				UserVO user = UserUtil.getInstance();
				setWriterId(user.getUserId());
				setcWriter(user.getUserName());
				setPrivatecode(user.getPrivatecode());
				setUserType(UserType.MEMBER.getCode());
				break;
		}

		setHostIp(SessionUtil.getRemoteAddr(request));
	}

}
