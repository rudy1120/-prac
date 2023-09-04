<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/jsp/common/taglib.jsp"%>
<%@page import="egovframework.portal.util.StringUtil"%>
<%@page import="egovframework.portal.util.UserUtil"%>
<%@page import="egovframework.portal.unit.portal.user.vo.UserVO"%>
<%@page import="egovframework.portal.common.Constant"%>
<%@page import="org.apache.commons.lang.RandomStringUtils"%>
<%@page import="egovframework.portal.util.SecurityUtil"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="org.springframework.security.authentication.UsernamePasswordAuthenticationToken"%>
<%@page import="org.springframework.security.core.Authentication"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.UnsupportedEncodingException"%>
<%@page import="egovframework.portal.security.RoleType"%>
<%@page import="egovframework.portal.security.vo.Role"%>
<%@page import="egovframework.portal.security.user.vo.User"%>
<%@page import="egovframework.portal.unit.common.UserType"%>
<%
	String randomYn = request.getParameter("randomYn");
	Boolean isRandom = StringUtil.isBlank(randomYn) || "Y".equals(randomYn);
	String nameCheck = request.getParameter("nameCheck");
	String authBy = request.getParameter("authBy");
	String first = "김이박권상최채장신문";
	String last = "가인연섭유아유하상민정용성자연수지영미훈우진선장재인순랑세경시소이희";

	User user = new User();
// 	user.setUserId(isRandom ? RandomStringUtils.random(5, Constant.PW_RANGE_CHARACTERS) : "yhdev");
	user.setPassword(SecurityUtil.encrypt("1"));
	user.setPrivatecode(
// 		isRandom ? "DEV_DI_BY_" + request.getLocalAddr() + user.getUserId() : "DEV_ID_BY_YHDEV_STATIC"
		isRandom ? "DEV_DI_BY_" + request.getLocalAddr() + RandomStringUtils.random(5, Constant.PW_RANGE_CHARACTERS) : "DEV_ID_BY_YHDEV_STATIC"
	);
// 	user.setTel1(isRandom ? RandomStringUtils.random(3, Constant.PW_RANGE_NUMBERS) + "" : "010");
// 	user.setTel2(isRandom ? RandomStringUtils.random(4, Constant.PW_RANGE_NUMBERS) + "" : "0101");
// 	user.setTel3(isRandom ? RandomStringUtils.random(4, Constant.PW_RANGE_NUMBERS) + "" : "0202");

	int userType = Integer.parseInt(request.getParameter("userType"));
	if (isRandom) {
		switch (userType) {
			case 1 : // 성년/남자
				user.setBirthday("19450815");
				user.setGender("M");
				break;
			case 2 : // 미성년/남자
				user.setBirthday("20100815");
				user.setGender("M");
				break;
			case 3 : // 미성년/여자
				user.setBirthday("20100815");
				user.setGender("F");
				break;
			default : // 성년/여자
				user.setBirthday("19450815");
				user.setGender("F");
				break;
		}
	} else {
		user.setBirthday("19870530");
		user.setGender("F");
	}

	user.setUserName(isRandom ? RandomStringUtils.random(1, first) + RandomStringUtils.random(2, last) : "이고정");
	user.setUserType(UserType.MEMBER.getCode());
	user.setAge(UserUtil.calculateManAge(user.getBirthday()));

	List<Role> roles = new ArrayList<Role>();
	roles.add(new Role(StringUtil.isNotBlank(authBy) && authBy.equals("ipin")
			? RoleType.USER_TMP_IPIN.getCode()
			: RoleType.USER_TMP.getCode()));
	user.setAuthorities(roles);

	Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
	SecurityContextHolder.getContext().setAuthentication(authentication);
%>
<c:set var="nameCheck" value="<%=nameCheck %>"/>
<c:if test="${nameCheck != 'Y'}">
<script type="text/javascript">
	alert("[이름]: <%=user.getUserName() %>(<%=(user.getGender().equals("M") ? "남" : "여") %>)\n[아이디]: <%=user.getUserId() %>");
	location.href = "<%=request.getParameter("successUrl").replace("&amp;", "&") %>";
</script>
</c:if>
<c:if test="${nameCheck == 'Y'}">
	<script type="text/javascript">
		alert("인증되었습니다.");
		opener.location.href = "<%=request.getParameter("successUrl").replace("&amp;amp;", "&").replace("&amp;", "&") %>";
		self.close();
	</script>
</c:if>
