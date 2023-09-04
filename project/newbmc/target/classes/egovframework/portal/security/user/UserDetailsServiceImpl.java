package egovframework.portal.security.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import egovframework.portal.security.RoleType;
import egovframework.portal.security.user.vo.User;
import egovframework.portal.security.vo.Role;
import egovframework.portal.unit.common.UserType;
import egovframework.portal.unit.portal.user.service.UserService;
import egovframework.portal.unit.portal.user.vo.UserVO;
import egovframework.portal.util.UserUtil;

/**
 * SPRING SECURITY 회원 정보 취득 SERVICE
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자				수정 내용
 * -------------	----------------	---------------------------
 * 2016.07.15		J.Ryeon Lee			최초 생성 및 코딩
 * 2017.02.08		J.Ryeon Lee			미인증 회원 롤 추가
 * 2017.02.09		J.Ryeon Lee			휴면 회원 롤 추가
 * </pre>
 *
 * @author J.Ryeon Lee
 * @since 2016.07.15
 */
@Service("portalUserService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Resource
	protected UserService userService;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		UserVO data = userService.getUserByUserId(userId);
		User user = new User();
		if (data != null) {
			BeanUtils.copyProperties(data, user);
			user.setUsername(data.getUserId());
			user.setPassword(data.getPassword());
			user.setUserType(UserType.MEMBER.getCode());
			user.setAge(UserUtil.calculateManAge(user.getBirthday()));

			List<Role> roles = new ArrayList<Role>();
			roles.add(new Role(RoleType.getType(user).getCode()));
			user.setAuthorities(roles);
		} else {
			throw new UsernameNotFoundException("invalid user id");
		}

		return user;
	}

}
