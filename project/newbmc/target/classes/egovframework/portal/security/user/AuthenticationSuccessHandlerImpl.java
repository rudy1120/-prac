package egovframework.portal.security.user;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import egovframework.portal.security.user.vo.User;
import egovframework.portal.security.vo.AjaxResult;
import egovframework.portal.unit.portal.user.mapper.UserMapper;
import egovframework.portal.unit.portal.user.vo.UserLogVO;
import egovframework.portal.util.StringUtil;
import net.arnx.jsonic.JSON;

/**
 * SPRING SECURITY 인증 성공 회원 화면 처리 SERVICE
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.07.15		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.01.02		김장섭				회원 로그인 로그 기록 기능 추가
 * 2017.02.08		J.Ryeon Lee			미인증 회원 검증 로직 추가
 * 2017.02.09		J.Ryeon Lee			휴면 회원 검증 로직 추가
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.07.15
 */
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	@Resource(name = "userMapper")
	protected UserMapper userMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		userMapper.updateLoginInfo(user);

		// 접근 로그
		UserLogVO logVO = new UserLogVO();
		logVO.setUserId(user.getUserId());
		logVO.setIp(request.getRemoteAddr());
		logVO.setUserAction("회원로그인");
		userMapper.insertUserLog(logVO);

		AjaxResult rtn = new AjaxResult();
		rtn.success = Boolean.TRUE;
		rtn.procCode = calProcCode(user);

		response.getWriter().println(JSON.encode(rtn));
	}

	private String calProcCode(User user) {
		if (StringUtil.isBlank(user.getPrivatecode())) {
			return "NOT_CERTIFICATED";
		} else if ("Y".equals(user.getIsDormant())) {
			return "DORMANT_USER";
		} else if (hasBeenSixMonths(user.getLastPwChangeDate())) {
			return "EXPRIRED_PW";
		}

		return null;
	}

	private Boolean hasBeenSixMonths(Date lastPwChangeDate) {
		Calendar changedDate = Calendar.getInstance();
		changedDate.setTime(lastPwChangeDate);
		changedDate.set(Calendar.MONTH, changedDate.get(Calendar.MONTH) + 6);

		return changedDate.before(Calendar.getInstance());
	}

}