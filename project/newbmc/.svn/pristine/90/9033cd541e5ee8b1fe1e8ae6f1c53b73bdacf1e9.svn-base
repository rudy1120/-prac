<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="/common/jsp/common/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
String now = format.format(new Date());
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- <link rel="stylesheet" type="text/css" href="/bmc/font/font.css"> -->
<link rel="stylesheet" type="text/css" href="../font/font.css">
<link rel="stylesheet" type="text/css" href="../css/board2.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>이사날짜 확인</title>
<link rel="stylesheet" type="text/css" href="/bmc/css/apply.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/pluginfree/js/nppfs-1.9.0.js?dummy=<%= now %>"></script> 
<script type="text/javascript" src="/common/js/inputFormValidator.js"></script>

<c:set var="successUrl" value="/bmc/moveRes/dateConfirm.do" />
<c:set var="sAuthType" value="M" />

<script>

// if (document.location.protocol == 'http:') {
//     document.location.href = document.location.href.replace('http:', 'https:');
// }

$(document).ready(function () {
	if($("#gbn2").val() == 'A'){
		document.title = "이사날짜 수정 | 아미4 행복주택";
	}
});
</script>

<script>

function joiner(value) { return value.indexOf("?") > -1 ? "&" : "?"; }

function open_namecheck(postUrl) {
	
	window.open("/NICE/checkplus_input.jsp?successUrl=" + postUrl + "&idx=${idx}&cancelUrl=${cancelUrl}&sAuthType=${sAuthType}", "popupChk", "width=500, height=550, top=100, left=100, fullscreen=no, menubar=no, status=no, toolbar=no, titlebar=yes, location=no, scrollbar=no");
}

function replaceAll(str, searchStr, replaceStr) {
	return str.split(searchStr).join(replaceStr);
}

function go_prev_page() {
	self.close();
}

$(function(){
	//20.04.16 웹접근성 반영 -탭이동시 title 정보 수정 
	titleVal = "본인확인 - " + $("title").html();
	$("title").html(titleVal);
	
	//$("#userAgent").text(navigator.userAgent);
	/*
	npPfsStartup(document.form1, false, true, true, "enc", "on");
	1. form 양식 : 기본값 DOM Document 상의 첫번째 form
	2. 개인방화벽 사용여부 : 기본값 false
	3. 키보드보안 사용여부 : 기본값 false
	4. 단말정보수집 사용여부 : 기본값 false
	5. 키보드보안 E2E 필드 설정 속성명 : 기본값 "enc"
	6. 키보드보안 E2E 필드 설정 속성값: 기본값 "on"
	부가적인 설정은(예, 설치확인 등) /pluginfree/js/nppfs-1.0.0.js를 수정하여 설정하십시오.
	*/
	//npPfsStartup(null, false, true, false, false, "npkencrypt", "on");
});

</script>

</head>

<body>

	<h3 style="font-size:3em;margin-bottom:20px;" class="tit"><c:if test="${custList.gbn2 eq 'A'}">아미4 행복주택</c:if> 이사날짜 확인</h3>
    <p style="text-align:center; color:#fff;">- 이사날짜를 예약하고 확인할 수 있습니다 -</p>
	<div class="all_wrap">
		<div class="inr vision">
			<div class="area_box box2 recruit-box1">
			<p class="box-comment"><strong class="strong_st">이사날짜 예약</strong><br>
				<c:if test="${custList.gbn2 eq 'A'}">아미4 행복주택 </c:if>
				이사날짜 확인 페이지 입니다.
			</p>
			</div>
		</div>
		
		<security:authorize access="hasRole('ROLE_USER_TMP') and isAuthenticated()">
			<security:authentication var="user" property="principal"/>
		</security:authorize>
		<c:if test="${empty(user.userName)}"><c:set var="user" value="${null}"/></c:if>
		
		<c:choose>
			<c:when test="${not empty user}">
				<div class="bt_wrap">
					<a href="/bmc/moveRes/movement.do" class="bt-il bt1" title="날짜 예약하기">날짜 예약하기</a>
					<a href="/bmc/moveRes/dateConfirm.do" class="bt-il bt2 on" title="날짜 확인하기">날짜 확인하기</a>
				</div>
				<form:form commandName="moveReserveVO" id="confirmForm" name="confirmForm" action="/bmc/moveRes/chkResUp.do" 
					methodParam="post" htmlEscape="false" target="_self" >
					<input type="hidden" id="gbn2" value="${custList.gbn2}" />
					<input type="hidden" id="cust_id" name="cust_id" value="${custList.cust_id}"/>
					<input type="hidden" id="name" name="name"  value="${user.userName}"/>
					<input type="hidden" id="tel" name="tel" value="${user.tel1}-${user.tel2}-${user.tel3}"/>
					<input type="hidden" id="addr1" name="addr1" value="${custList.addr1}"/>
					<input type="hidden" id="addr2" name="addr2" value="${custList.addr2}"/>
					<div class="gap20"></div>
					<p class="depth2-title" id="subtitle"></p>
					<!-- 본인인증 정보존재 -->
					<div id="chkUpDiv" class="bod_wrap">
						<fieldset>
							<div class="bod_write-il">
								<dl>
									<dt><label for="name">이름</label></dt>
									<dd>
										<span class="spantxt-il">${user.userName}</span> 
									 	<%-- <span id="ckname" name="ckname" class="spantxt-il">${user.userName}</span>  --%>
									</dd>
								</dl>
								<dl>
									<dt><label for="tel">휴대폰번호</label></dt>
									<dd>
										<span class="spantxt-il">${user.tel1}-${user.tel2}-${user.tel3}</span>
										<%-- <span id="cktel" name="cktel" class="spantxt-il">010-5431-5530${user.tel1}-${user.tel2}-${user.tel3}</span> --%>
									</dd>
								</dl>
								<dl>
									<dt><label for="address">동&middot;호수</label></dt>
									<dd style="padding-bottom:10px;">
										<span>${custList.addr1} 동</span>
										<span>${custList.addr2} 호</span>
									</dd>
								</dl>
								<dl>
									<dt><label for="move">날짜&middot;시간</label></dt>
									<dd style="padding-botton:10px">
										<span style="padding-right:20px">${custList.move_date}</span>
										<c:set var="m_time" value="${custList.move_time}"/>
										<c:if test="${m_time eq 'T1'}">
											<c:out value="09:00 ~ 12:00" />
										</c:if>
										<c:if test="${m_time eq 'T2'}">
											<c:out value="12:00 ~ 15:00" />
										</c:if>
										<c:if test="${m_time eq 'T3'}">
											<c:out value="15:00 ~ 18:00" />
										</c:if>
										
										<c:if test="${m_time eq 'T4'}">
											<c:out value="07:00 ~ 09:00" />
										</c:if>
										<c:if test="${m_time eq 'T5'}">
											<c:out value="09:00 ~ 11:00" />
										</c:if>
										<c:if test="${m_time eq 'T6'}">
											<c:out value="11:00 ~ 13:00" />
										</c:if>
										<c:if test="${m_time eq 'T7'}">
											<c:out value="12:00 ~ 14:00" />
										</c:if>
										<c:if test="${m_time eq 'T8'}">
											<c:out value="13:00 ~ 15:00" />
										</c:if>
										<c:if test="${m_time eq 'T9'}">
											<c:out value="14:00 ~ 16:00" />
										</c:if>
										<c:if test="${m_time eq 'T10'}">
											<c:out value="15:00 ~ 17:00" />
										</c:if>
										<c:if test="${m_time eq 'T11'}">
											<c:out value="16:00 ~ 18:00" />
										</c:if>
										<c:if test="${m_time eq 'T12'}">
											<c:out value="17:00 ~ 19:00" />
										</c:if>
										<c:if test="${m_time eq 'T13'}">
											<c:out value="18:00 ~ 20:00" />
										</c:if>
										<c:if test="${m_time eq '14'}">
											<c:out value="19:00 ~ 21:00" />
										</c:if>
		
									</dd>
								</dl>
							</div>
		
							<div id="YDiv" class="btnall_wrap">
								<div style="float: right;">
									<div class="btn_in">
										<span>※ 수정은 1회에 한해서 가능합니다.</span>
										<input type="submit" class="btn_blue btn_wrap-il" value="수정"/>
									</div>
								</div>
							</div>
		
						</fieldset>
					</div>
				</form:form>
			</c:when>
			<c:otherwise>
				<div class="bt_wrap">
					<a href="/bmc/moveRes/movement.do" class="bt-il bt1" title="날짜 예약하기">날짜 예약하기</a>
					<a href="/bmc/moveRes/dateConfirm.do" class="bt-il bt2 on" title="날짜 확인하기">날짜 확인하기</a>
				</div>		
				<div>
					<form name="reqSIRENForm" id="frmRealName" title="본인인증" action="#" onsubmit="return false;" method="post">
						<div>
							<div class="gap"></div>
							<span class="intro-txt-il ReName-txt-il">본인확인이 필요한 서비스입니다.<br/>
							아래 "<strong class="ReNameS-il">본인확인</strong>" 버튼을 클릭하여 인증 절차를 진행해주세요.</span>
							<div class="gap20-il"></div>
							<img src="/bmc/images/content/ReName_icon.jpg" alt=""  class="ReNameImg-il"/>
							<div class="btnall_wrap">
								<div class="btn_wrap" style="float:none; text-align:center;">
									<div class="btn_in">
										<input type="submit" onclick="open_namecheck('${successUrl}');" class="btn_wrap-il" value="본인확인" title="새창 열림" />
										<input type="button" onclick="go_prev_page(); return false;" class="btn_wrap-il" value="취소" title="창 닫기"/>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

</body></html>