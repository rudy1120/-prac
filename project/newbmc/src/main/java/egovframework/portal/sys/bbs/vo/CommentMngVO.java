package egovframework.portal.sys.bbs.vo;

import egovframework.portal.bbs.vo.CommentVO;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

/**
 * 코멘트 관리 VO
 * 참조 테이블: bbs_comment
 *
 * @author J.Ryeon Lee
 * @since 2016.03.15
 * @see egovframework.portal.bbs.vo.CommentVO
 */
public class CommentMngVO extends CommentVO {

	public void setAdminUserInfo(String hostIp, AdminLoginVO admin) {
		setcWriter(admin.getAdminName());
		setWriterId(admin.getAdminId());
		setHostIp(hostIp);
	}
}
