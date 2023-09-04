<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%
	/**
	 * 비밀번호 변경 알림 JSP
	 *
	 * 수정일			수정자			수정 내용
	 * -------------	------------	-----------------------------
	 * 2017.04.10		J.Ryeon Lee		최초 생성 및 코드 수정
	 * 2020.01.16       박상혁          비밀번호 변경 알림 -> 세션유지조건 페이지 변경
	 *
	 *
	 * @author J.Ryeon Lee
	 * @since 2017.04.10
	 */
%>
<div style="text-align:right;margin-right:30px" id="timer"></div>

<c:set var="admin_modify_mId" value="0112010000" />

<% 
	String t = String.valueOf(request.getAttribute("sessionTime"));
	if (!t.equals("null") ) {
		session.setMaxInactiveInterval(Integer.valueOf(t) * 60); 
	}
%>

<script>

var initMinute;
var remainSecond;

$(document).ready(function() {
	var t = <%=session.getMaxInactiveInterval()%>;
	clearTime(parseInt(t));
	setTimer();
});

function clearTime(min) {
	initMinute = parseInt(min / 60);
	remainSecond = min;
}

function setTimer() {
	remainMinute_ = parseInt(remainSecond / 60);
	remainSecond_ = remainSecond % 60;
	
	if (remainSecond > 0) {
		
		$('#timer').empty();
		$('#timer').append(Lpad(remainMinute_, 2) + " : " + Lpad(remainSecond_, 2));
		remainSecond--;
		setTimeout('setTimer()', 1000);
	} else {
		alert('세션이 만료되어 자동 로그아웃 됩니다.');
		location.href = '/sys/login.do';
	}
}

function Lpad(str, n) {
	str = str + '';
	while (str.length < n) {
		str = '0' + str;
	}
	return str;
} 

</script>

