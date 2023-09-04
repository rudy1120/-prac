package egovframework.portal.sys.basic.poll;

/**
 * 중복 투표 구분
 *
 * <pre>
 * 개정이력(Modification Information)
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 7. 20.			권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 7. 20.
 */
public enum PollDupType {

	/** 중복 투표 가능 */
	DUP("1"),
	/** IP 체크 */
	IP("2"),
	/** 쿠키 체크 */
	COOKIE("3"),
	/** 본인 인증 체크 */
	REALNAME("4")
	;

	String code = "";

	private PollDupType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}



}
