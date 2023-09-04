package egovframework.portal.unit.portal.user.vo;

import java.util.Date;

import egovframework.portal.common.vo.CommonVO;

/**
 * 회원 접근정보 로그 VO
 * 관리 table: user_log
 *
 * @author 김장섭
 * @since 2017.01.02
 */
public class UserLogVO extends CommonVO {

	/* 회원 접근로그 정보 */

	/** 사용자 아이디 */
	private String userId = "";
	/** 접근 아이피 */
	private String ip = "";
	/** 접근 행동 정보 */
	private String userAction = "";
	/** 접근 일시 */
	private Date logDate = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public Date getLogDate() {
		return logDate;
	}

	public void setLogDate(Date logDate) {
		this.logDate = logDate;
	}

}
