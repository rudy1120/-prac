package egovframework.portal.unit.bmc.user.vo;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

import egovframework.portal.common.vo.CommonVO;
import egovframework.portal.unit.common.UserType;
import egovframework.portal.unit.portal.user.vo.ConfirmVO;
import egovframework.portal.util.UserUtil;
import egovframework.portal.validation.ByteSize;

import egovframework.portal.unit.bmc.util.BmcUserUtil;

public class BmcUserVO extends CommonVO {

	/* 회원 정보 */

	/** DI값 */
	private String privatecode = "";
	/** 부모 DI */
	private String parentPrivatecode = "";
	/** 부모 성명 */
	private String parentName = "";
	/** 회원ID */
	@NotEmpty
	@ByteSize(max = 48)
	private String userId = "";
	/** 이름 */
	private String userName = "";
	/** 비밀번호(암호화) */
	@NotEmpty
	@ByteSize(max = 15)
	private String password = "";
	/** 전화번호 지역번호 */
	@NotBlank
	@RegExp("[0-9]{0,4}")
	private String tel1 = "";
	/** 전화번호 중간번호 */
	@NotBlank
	@RegExp("[0-9]{0,4}")
	private String tel2 = "";
	@NotBlank
	@RegExp("[0-9]{0,4}")
	/** 전화번호 개인번호 */
	private String tel3 = "";
	/** 생년월일 */
	private String birthday = "";
	/** 성별 */
	private String gender = "";
	/** 로그인 횟수 */
	private int loginCount = 0;
	/** 마지막 로그인 일자 */
	private Date lastestLoginDt = null;
	/** 마지막 로그인 일자 */
	private Date lastPwChangeDate = null;
	/** 등록일 */
	private Date createDate = null;
	/** 수정일 */
	private Date updateDate = null;
	/** 상태 */
	private String isDormant = "";
	/**  */
	private ConfirmVO confirm = new ConfirmVO();

	/* FOR UPDATE */

	/** 새 비밀번호 */
	private String newPassword = "";
	/** 비밀번호 확인용 필드 */
	private String retypePw = "";

	/* 비영구적 필드 */

	/** sns api 연동 메뉴별 코멘트 달기용. @see {@link UserType} 늘 {@link UserType#MEMBER#getCode()} 값을 가짐. */
	private String userType = "";
	/** 처리 관리자 ID */
	private String adminId = "";
	/** 나이 (매해 바뀌는 정보이므로 영속화하지 않고 세션 생성시 생년월일로부터 계산해 세팅) */
	private int age = -1;
	/** 암호화된 비밀번호 */
	private String encryptedPw = "";
	/** 암호화된 비밀번호2 */
	private String encryptedRetypePw = "";
	/** 암호화된 비밀번호3 */
	private String encryptedNewPw = "";

	public BmcUserVO() {
		// default
	}

	public BmcUserVO(String userId, String privatecode) {
		setUserId(userId);
		setPrivatecode(privatecode);
	}

	public String getPrivatecode() {
		return privatecode;
	}

	public void setPrivatecode(String privatecode) {
		this.privatecode = privatecode;
	}

	public String getParentPrivatecode() {
		return parentPrivatecode;
	}

	public void setParentPrivatecode(String parentPrivatecode) {
		this.parentPrivatecode = parentPrivatecode;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		setEncryptedPw(UserUtil.getEncodedPw(password));
	}

	public String getTel1() {
		return tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getTel3() {
		return tel3;
	}

	public void setTel3(String tel3) {
		this.tel3 = tel3;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public Date getLastestLoginDt() {
		return lastestLoginDt;
	}

	public void setLastestLoginDt(Date lastestLoginDt) {
		this.lastestLoginDt = lastestLoginDt;
	}

	public Date getLastPwChangeDate() {
		return lastPwChangeDate;
	}

	public void setLastPwChangeDate(Date lastPwChangeDate) {
		this.lastPwChangeDate = lastPwChangeDate;
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

	public String getIsDormant() {
		return isDormant;
	}

	public void setIsDormant(String isDormant) {
		this.isDormant = isDormant;
	}

	public ConfirmVO getConfirm() {
		return confirm;
	}

	public void setConfirm(ConfirmVO confirm) {
		this.confirm = confirm;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
		setEncryptedNewPw(UserUtil.getEncodedPw(newPassword));
	}

	public String getRetypePw() {
		return retypePw;
	}

	public void setRetypePw(String retypePw) {
		this.retypePw = retypePw;
		setEncryptedRetypePw(UserUtil.getEncodedPw(retypePw));
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEncryptedPw() {
		return encryptedPw;
	}

	public void setEncryptedPw(String encryptedPw) {
		this.encryptedPw = encryptedPw;
	}

	public String getEncryptedRetypePw() {
		return encryptedRetypePw;
	}

	public void setEncryptedRetypePw(String encryptedRetypePw) {
		this.encryptedRetypePw = encryptedRetypePw;
	}

	public String getEncryptedNewPw() {
		return encryptedNewPw;
	}

	public void setEncryptedNewPw(String encryptedNewPw) {
		this.encryptedNewPw = encryptedNewPw;
	}

	public boolean isChild() {
		return false;
	}

	public boolean isNotChild() {
		return !isChild();
	}

	public void setSessionInfo() {
		BmcUserVO user = BmcUserUtil.getInstance();
		setUserId(user.getUserId());
		setUserName(user.getUserName());
		setPrivatecode(user.getPrivatecode());
		setBirthday(user.getBirthday());
	}

}
