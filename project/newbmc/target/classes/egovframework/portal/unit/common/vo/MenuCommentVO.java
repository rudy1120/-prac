package egovframework.portal.unit.common.vo;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

import egovframework.portal.common.SessionKey;
import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.security.user.vo.User;
import egovframework.portal.unit.common.UserType;
import egovframework.portal.util.SessionUtil;

/**
 * 메뉴 코멘트 VO
 * 관리 TABLE: menu_comment
 *
 * @author J.Ryeon Lee
 * @since 2016.06.22
 */
public class MenuCommentVO extends CommonVO {

	/** PK */
	private String idx = "";
	/** 부모 PK */
	private String upIdx = "";
	/** 사이트 코드 */
	private String sitecode = "";
	/** 메뉴 IDX */
	private String mId = "";
	/** 로그인 유저 종류 @see egovframework.portal.unit.common.UserType */
	private String userType = "";
	/** 유저 ID */
	private String userId = "";
	/** 유저 성명 */
	private String userName = "";
	/** DI */
	private String privatecode = "";
	/** remote host ip */
	private String hostIp = "";
	/** 삭제 여부 (Y: 삭제, N: 사용) */
	private String isDel = "";
	/** 미구현 */
	private String isPublic = "";
	/** 내용 */
	private String cContent = "";
	/** 등록일 */
	private Date createDate = null;
	/** 수정일 */
	private Date updateDate = null;
	/** 삭제일 */
	private Date deleteDate = null;

	/* SNS 연동 관련 비영속화 필드 */

	/** facebook user id */
	private String fbUserId = "";
	/** facebook user name */
	private String fbUserName = "";
	/** twitter user id */
	private String twUserId = "";
	/** twitter user name */
	private String twUserName = "";

	public MenuCommentVO() {
		// default
	}

	public MenuCommentVO(String idx) {
		setIdx(idx);
	}

	public MenuCommentVO(String sitecode, String mId) {
		setSitecode(sitecode);
		setmId(mId);
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getUpIdx() {
		return upIdx;
	}

	public void setUpIdx(String upIdx) {
		this.upIdx = upIdx;
	}

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getcContent() {
		return cContent;
	}

	public void setcContent(String cContent) {
		this.cContent = cContent;
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

	/** 로그인 된 정보를 VO에 저장 */
	public void setUserInfo(HttpServletRequest request, Authentication authentication) {
		UserType _userType = UserType.toType(SessionUtil.getSessionAttrAsString(request, SessionKey.USER_TYPE.getKey()));
		switch (_userType) {
			case FACEBOOK:
				setUserId(SessionUtil.getSessionAttrAsString(request, SessionKey.FB_USER_ID.getKey()));
				setUserName(SessionUtil.getSessionAttrAsString(request, SessionKey.FB_USER_NAME.getKey()));
				setUserType(_userType.getCode());
				break;
			case TWITTER:
				setUserId(SessionUtil.getSessionAttrAsString(request, SessionKey.TW_USER_ID.getKey()));
				setUserName(SessionUtil.getSessionAttrAsString(request, SessionKey.TW_USER_NAME.getKey()));
				setUserType(_userType.getCode());
				break;
			default:
				User user = (User) authentication.getPrincipal();
				setUserId(user.getUserId());
				setUserName(user.getUserName());
				setPrivatecode(user.getPrivatecode());
				setUserType(user.getUserType());
				break;
		}

		setHostIp(SessionUtil.getRemoteAddr(request));
	}

}
