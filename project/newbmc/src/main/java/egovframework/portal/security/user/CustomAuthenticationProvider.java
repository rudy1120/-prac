package egovframework.portal.security.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import egovframework.portal.security.user.vo.User;
import egovframework.portal.util.StringUtil;
import egovframework.portal.util.YSecukeyPadUtil;

/**
 * Spring Security AuthenticationProvider Custom Class
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정내용
 * --------------	----------------	---------------------------
 * 2017. 3. 13.		권태성				최초 생성 및 코딩
 * </pre>
 *
 * @author 권태성
 * @since 2017. 3. 13.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Resource(name = "portalUserService")
	private UserDetailsService userDetailsService = new UserDetailsServiceImpl();
	@Resource(name = "portalPasswordEncoder")
	private PasswordEncoder passwordEncoder = new PasswordEncoderImpl();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = (String) authentication.getPrincipal();
		String userPw = (String) authentication.getCredentials();
		User user = (User) userDetailsService.loadUserByUsername(userId);

		if (!checkPassword(userPw, user.getPassword())) {
			throw new BadCredentialsException("ID/비밀번호를 확인해주세요.");
		}

		/*
		 * 2017. 03. 13 권태성
		 * UsernamePasswordAuthenticationToken의 user 파라미터에 대한 설명
		 * 보통은 UsernamePasswordAuthenticationToken의 첫번째 인자는 userId를 담는데
		 * onAuthenticationSuccess 클래스에서 authentication.getPrincipal()로 User 객체를 가져오기 위해
		 * userId가 아닌 user 객체를 담아준다. (보통은 여기에 id를 담음..)
		 */
		return new UsernamePasswordAuthenticationToken(user, userPw, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	/**
	 * 가상키패드 사용유무에 따라 패스워드 체크
	 *
	 * @param userPw
	 * @param dbPw
	 * @return
	 */
	public boolean checkPassword(String userPw, String dbPw) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String loginPasswdInputUseYn = request.getParameter("loginPasswdInputUseYn");
		if (StringUtil.isNotBlank(loginPasswdInputUseYn) && "Y".equals(loginPasswdInputUseYn)) {
			return passwordEncoder.matches(YSecukeyPadUtil.decode(request, "password"), dbPw);
		} else {
			return passwordEncoder.matches(userPw, dbPw);
		}
	}

}