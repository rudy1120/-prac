<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#accordion").accordion();
		$("[name=userType]").selectmenu();
	});
</script>

<form action="/makeSessionProc.jsp" accept-charset="utf-8">
	<div id="accordion">
		<h3>세션 생성</h3>
		<div>
			<div style="float: left;">
				<select name="userType">
					<option value="0">성년(여)</option>
					<option value="1">성년(남)</option>
					<option value="2">미성년(남)</option>
					<option value="3">미성년(여)</option>
				</select>
				<input type="hidden" name="nameCheck" value="<%=request.getParameter("nameCheck") %>">
				<input type="hidden" name="authBy" value="<%=request.getParameter("authBy") %>">
				<input type="hidden" name="successUrl" value="<%=request.getParameter("successUrl") %>"/>
				<input type="submit" class="ui-button ui-widget ui-corner-all"/>
			</div>
		</div>
		<h3>세션 타입(랜덤 → 고정 변경시)</h3>
		<div>
			<div style="float: left;">
				<input type="radio" id="randomY" name="randomYn" value="Y" checked="checked"/><label for="randomY">랜덤</label>
				<input type="radio" id="randomN" name="randomYn" value="N"/><label for="randomN">고정</label>
				&nbsp;&nbsp;&nbsp;<input type="submit" class="ui-button ui-widget ui-corner-all"/>
			</div>
		</div>
	</div>
</form>
