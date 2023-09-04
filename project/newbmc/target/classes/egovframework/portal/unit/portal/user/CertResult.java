package egovframework.portal.unit.portal.user;

/**
 * 회원 조회 결과
 *
 * @author J.Ryeon Lee
 * @since 2015.11.13
 */
public enum CertResult {

	/** 존재하지 않는 회원ID */
	FAIL(-1),
	/** 비밀번호 불일치 */
	INVALID_PASSWORD(0),
	/** 로그인 */
	SUCCESS(1),
	/** 미인증 회원 */
	NOT_CERTIFICATED(2),
	;

	private int errCode = -1;

	private CertResult(int code) {
		this.errCode = code;
	}

	public int getErrCode() {
		return errCode;
	}
}
