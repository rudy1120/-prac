package egovframework.portal.security.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import egovframework.portal.security.RoleType;
import egovframework.portal.security.system.vo.User;
import egovframework.portal.security.vo.Role;
import egovframework.portal.sys.sysAuth.service.SysMemberService;
import egovframework.portal.sys.sysAuth.vo.AdminLoginVO;

@Service("systemUserService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	protected SysMemberService sysMemberService;

	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		AdminLoginVO data = sysMemberService.selectSysMemberDataDetail(new AdminLoginVO(userId));
		User user = new User();
		if (data != null) {
			BeanUtils.copyProperties(data, user, "adminGroupCode");
			user.setUsername(data.getAdminId());
			user.setPassword(data.getPwd());
			user.setAdminGroupCode(data.getAdminGroupCode());

			List<Role> roles = new ArrayList<Role>();
			roles.add(new Role(RoleType.ADMIN.getCode()));
			user.setAuthorities(roles);
		} else {
			throw new UsernameNotFoundException("invalid user id");
		}

		return user;
	}

}
