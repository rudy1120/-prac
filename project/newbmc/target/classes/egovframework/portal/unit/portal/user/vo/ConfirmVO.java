package egovframework.portal.unit.portal.user.vo;

import org.hibernate.validator.constraints.NotBlank;
import org.springmodules.validation.bean.conf.loader.annotation.handler.RegExp;

/**
 * 회원 서비스 이용 관련 사용자 동의 정보 관리 VO
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016. 12. 14.	J.Ryeon Lee			최초 생성 및 코딩
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016. 12. 14.
 */
public class ConfirmVO {

	/* 동의 정보 */

	/** 사이트 이용약관 동의 */
	@NotBlank(message = "사이트 이용약관에 동의하지 않으셨습니다.")
	@RegExp(value = "Y", message = "사이트 이용약관에 동의하지 않으셨습니다.")
	private String confirm1Yn = "";
	/** 필수 개인 정보 수집/이용 동의 */
	@NotBlank(message = "필수 개인정보 수집/이용에 동의하지 않으셨습니다.")
	@RegExp(value = "Y", message = "필수 개인정보 수집/이용에 동의하지 않으셨습니다.")
	private String confirm2Yn = "";
	/** 선택 개인 정보 수집/이용 동의 */
	@RegExp("^[Y|N]$")
	private String confirm3Yn = "";

	/* spring form tag commandName에 맞춰 쓰기 위해 {@link UserVO}의 입력 필드 중 일부를 이식. 실제 post된 값을 다루지는 않음 */

	/** 회원ID */
	private String userId = "";
	/** 비밀번호(암호화) */
	private String password = "";
	/** 전화번호 지역번호 */
	private String tel1 = "";
	/** 전화번호 중간번호 */
	private String tel2 = "";
	/** 전화번호 개인번호 */
	private String tel3 = "";
	/** 생년월일 */
	private String birthday = "";
	/** 성별 */
	private String sex = "";

	/* 비영속화 필드 */

	/** 회원 권한 타입 */
	@NotBlank(message = "유형을 선택하지 않으셨습니다.")
	private String ageType = "";

	public String getConfirm1Yn() {
		return confirm1Yn;
	}

	public void setConfirm1Yn(String confirm1Yn) {
		this.confirm1Yn = confirm1Yn;
	}

	public String getConfirm2Yn() {
		return confirm2Yn;
	}

	public void setConfirm2Yn(String confirm2Yn) {
		this.confirm2Yn = confirm2Yn;
	}

	public String getConfirm3Yn() {
		return confirm3Yn;
	}

	public void setConfirm3Yn(String confirm3Yn) {
		this.confirm3Yn = confirm3Yn;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAgeType() {
		return ageType;
	}

	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}

}
